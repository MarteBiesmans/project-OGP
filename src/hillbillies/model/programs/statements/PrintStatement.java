package hillbillies.model.programs.statements;

import hillbillies.model.Counter;
import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.model.programs.expressions.Expression;

public class PrintStatement extends Statement {
	
	private PrintStatement(Expression<?> expression, boolean hasBeenFullyExecuted) {
		super(hasBeenFullyExecuted);
		this.expression = expression;
	}

	public PrintStatement(Expression<?> expression) {
		this(expression, false);
	}
	
	public Expression<?> getExpression() {
		return expression;
	}
	
	private final Expression<?> expression;

	@Override
	public void execute(Unit unit, Cube cube, Counter counter) {
		counter.increment();
		System.out.println(expression.evaluate(unit, cube).toString());
		super.SetHasFullyExecutedToTrue();
	}

	@Override
	public boolean canExecute(Unit unit, Cube cube, Counter counter) {
		counter.increment();
		if (counter.getCount() > 1000 || hasBeenFullyExecuted()) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isWellFormed() {
		return true;
	}

	@Override
	public boolean containsActionStatement() {
		return false;
	}

	@Override
	public PrintStatement clone() {
		return new PrintStatement(getExpression(), hasBeenFullyExecuted());
	}

}
