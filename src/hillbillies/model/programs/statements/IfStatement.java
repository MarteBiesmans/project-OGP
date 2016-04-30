package hillbillies.model.programs.statements;

import hillbillies.model.Counter;
import hillbillies.model.Unit;
import hillbillies.model.programs.expressions.Expression;

public class IfStatement extends Statement {
	
	private final Expression condition;
	private final Statement then;
	private final Statement otherwise;
	private boolean solvedCondition;
	private boolean hasNotBeenExecutedOnce;
	
	private IfStatement(Expression condition, Statement then, Statement otherwise, boolean hasNotBeenExecutedOnce, boolean hasFullyExecuted, boolean solvedCondition) {
		super(hasFullyExecuted);
		this.condition = condition;
		this.then = then;
		this.otherwise = otherwise;
		this.hasNotBeenExecutedOnce = hasNotBeenExecutedOnce;
		this.solvedCondition = solvedCondition;
	}

	public IfStatement(Expression condition, Statement then, Statement otherwise){
		this(condition, then, otherwise, true, false, false);
	}

	@Override
	public void execute(Unit unit, Counter counter) {
		counter.increment();
		if(hasNotBeenExecutedOnce){
			solvedCondition= (boolean) condition.evaluate(unit);
			hasNotBeenExecutedOnce=false;
		}
		if(!(then.hasBeenFullyExecuted() || otherwise.hasBeenFullyExecuted())){
			if(solvedCondition){
				then.execute(unit, counter);
				return;
			}
			else{
				otherwise.execute(unit, counter);
				return;
			}
		}
		else
			this.SetHasFullyExecutedToTrue();
	}

	@Override
	public boolean canExecute(Unit unit, Counter counter) {
		counter.increment();		
		if (hasBeenFullyExecuted() || counter.getCount()>1000)
			return false;

		if (hasNotBeenExecutedOnce){
			if((boolean) condition.evaluate(unit))
				return then.canExecute(unit, counter);
			else
				return otherwise.canExecute(unit, counter);
		}
		if(!(then.hasBeenFullyExecuted() || otherwise.hasBeenFullyExecuted())){
			if(solvedCondition){
				return then.canExecute(unit, counter);
			}
			if(!solvedCondition)
				return otherwise.canExecute(unit, counter);
		}
		return true;
	}

	@Override
	public boolean isWellFormed() {
		return then.isWellFormed() && otherwise.isWellFormed();
	}

	@Override
	public boolean containsActionStatement() {
		return then.containsActionStatement() || otherwise.containsActionStatement();
	}

	@Override
	public IfStatement clone() {
		return new IfStatement(condition, then.clone(), otherwise.clone(), hasNotBeenExecutedOnce, hasBeenFullyExecuted(), solvedCondition);
	}
}
