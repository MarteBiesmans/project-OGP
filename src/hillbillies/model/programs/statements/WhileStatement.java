package hillbillies.model.programs.statements;

import hillbillies.model.Counter;
import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.model.programs.expressions.Expression;
import hillbillies.model.programs.type.BooleanType;

public class WhileStatement extends Statement {

	private WhileStatement(Statement body, Statement bodyCopy, Expression<BooleanType> condition, boolean hasBeenFullyExecuted) {
		super(hasBeenFullyExecuted);
		this.condition = condition;
		this.bodyCopy = bodyCopy.clone();
		this.body = body.clone();
	}	
	
	public WhileStatement(Expression<?> condition, Statement body) {
		this(body, body, ((Expression<BooleanType>) condition), false);
		this.body.SetHasFullyExecutedToTrue();
	}
	
	private Statement body;
	private final Statement bodyCopy;
	private final Expression<BooleanType> condition;
	
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
		return new WhileStatement(body.clone(), bodyCopy.clone(), condition, hasBeenFullyExecuted());
	}

}
