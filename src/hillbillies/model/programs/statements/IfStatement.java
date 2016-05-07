package hillbillies.model.programs.statements;

import hillbillies.model.Counter;
import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.model.programs.expressions.BooleanExpression;

public class IfStatement extends Statement {

	private IfStatement(BooleanExpression condition, Statement then, Statement otherwise,
			boolean hasNotBeenExecutedOnce, boolean hasFullyExecuted, boolean solvedCondition) {
		super(hasFullyExecuted);
		this.condition = condition;
		this.then = then;
		this.otherwise = otherwise;
		this.hasNotBeenExecutedOnce = hasNotBeenExecutedOnce;
		this.solvedCondition = solvedCondition;
	}

	public IfStatement(BooleanExpression condition, Statement then, Statement otherwise) {
		this(condition, then, otherwise, true, false, false);
	}

	private final BooleanExpression condition;
	private final Statement then;
	private final Statement otherwise;
	private boolean solvedCondition;
	private boolean hasNotBeenExecutedOnce;
	
	@Override
	public void execute(Unit unit, Cube cube, Counter counter) {
		if (hasNotBeenExecutedOnce) {
			counter.increment();
			solvedCondition = (boolean) condition.evaluate(unit, cube).getValue();
			hasNotBeenExecutedOnce = false;
		}
		if (!this.hasBeenFullyExecuted()) {
			if (solvedCondition) {
				then.execute(unit, cube, counter);
				return;
			} else {
				otherwise.execute(unit, cube, counter);
				return;
			}
		}
	}

	@Override
	public boolean canExecute(Unit unit, Cube cube, Counter counter) {
		counter.increment();
		if (hasBeenFullyExecuted() || counter.getCount() > 1000)
			return false;

		if (hasNotBeenExecutedOnce) {
			if ((boolean) condition.evaluate(unit, cube).getValue())
				return then.canExecute(unit, cube, counter);
			else
				return otherwise.canExecute(unit, cube, counter);
		}
		if (!(then.hasBeenFullyExecuted() || otherwise.hasBeenFullyExecuted())) {
			if (solvedCondition) {
				return then.canExecute(unit, cube, counter);
			}
			if (!solvedCondition)
				return otherwise.canExecute(unit, cube, counter);
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
		return new IfStatement(condition, then.clone(), otherwise.clone(), hasNotBeenExecutedOnce,
				hasBeenFullyExecuted(), solvedCondition);
	}

	@Override
	public boolean hasBeenFullyExecuted() {
		return (then.hasBeenFullyExecuted() || otherwise.hasBeenFullyExecuted());
	}
}
