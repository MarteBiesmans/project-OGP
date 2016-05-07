package hillbillies.model.programs.statements;

import hillbillies.model.Counter;
import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.model.programs.expressions.BooleanExpression;

public class WhileStatement extends Statement {

	private WhileStatement(BooleanExpression condition, Statement body, Statement bodyCopy, boolean hasBeenFullyExecuted) {
		super(hasBeenFullyExecuted);
		this.condition = condition;
		this.body = body.clone();
		this.bodyCopy = bodyCopy.clone();
	}	
	
	public WhileStatement(BooleanExpression condition, Statement body) {
		this(condition, body, body, false);
		this.body.SetHasFullyExecutedToTrue();
	}
	
	private Statement body;
	private final Statement bodyCopy;
	private final BooleanExpression condition;
	
	@Override
	public void execute(Unit unit, Cube cube, Counter counter) {
		counter.increment();
		if (!body.hasBeenFullyExecuted()) {
			body.execute(unit, cube, counter);
			return;
		}
		if ((boolean) condition.evaluate(unit, cube).getValue()) {
			this.body = this.bodyCopy.clone();
			body.execute(unit, cube, counter);
			return;
		}
		SetHasFullyExecutedToTrue();
		
	}

	@Override
	public boolean canExecute(Unit unit, Cube cube, Counter counter) {
		counter.increment();
		if (counter.getCount() > 1000 || hasBeenFullyExecuted()) {
			return false;
		}
		if (!body.hasBeenFullyExecuted())
			return body.canExecute(unit, cube, counter);
		if ((boolean) condition.evaluate(unit, cube).getValue()) {
			return bodyCopy.canExecute(unit, cube, counter);
		}
		// body has finished and condition is false. Next time this statement is executed it
		// will be set to fully executed.
		return true;
	}

	@Override
	public boolean isWellFormed() {
		return body.isWellFormed();
	}

	@Override
	public boolean containsActionStatement() {
		return body.containsActionStatement();
	}

	@Override
	public WhileStatement clone() {
		return new WhileStatement(condition, body.clone(), bodyCopy.clone(), hasBeenFullyExecuted());
	}

}
