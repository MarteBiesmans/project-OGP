package hillbillies.model.programs.statements;

import hillbillies.model.Counter;
import hillbillies.model.Task;
import hillbillies.model.programs.expressions.IBooleanExpression;

public class WhileStatement extends Statement {
	
	private WhileStatement(Statement body, Statement bodyCopy, IBooleanExpression condition, boolean hasBeenFullyExecuted) {
		super(hasBeenFullyExecuted);
		this.condition = condition;
		this.bodyCopy = bodyCopy.clone();
		this.setBody(body.clone());
	}	
	
	public WhileStatement(IBooleanExpression condition, Statement statement) {
		this(statement, statement, condition, false);
		this.body.SetHasFullyExecutedToTrue();
	}
	
	private Statement getBody() {
		return this.body;
	}
	
	private void setBody(Statement body) {
		this.body = body;
		body.setParentStatement(this);
	}
	
	private Statement body;
	
	private Statement getBodyCopy() {
		return this.bodyCopy;
	}
	
	private final Statement bodyCopy;
	
	private IBooleanExpression getCondition() {
		return this.condition;
	}
	
	private final IBooleanExpression condition;
	
	@Override
	public Statement getExecutingStatement() {
		if (!getBody().hasBeenFullyExecuted())
			return getBody().getExecutingStatement();
		else
			return this;
	}
	
	@Override
	public void execute(Task task, Counter counter) {
		counter.increment();
		if (!getBody().hasBeenFullyExecuted()) {
			getBody().execute(task, counter);
			return;
		}
		if ((boolean) getCondition().evaluate(task).getValue()) {
			this.setBody(this.bodyCopy.clone());
			getBody().execute(task, counter);
			return;
		}
		SetHasFullyExecutedToTrue();
		
	}

	@Override
	public boolean canExecute(Task task, Counter counter) {
		counter.increment();
		if (counter.getCount() > 1000 || hasBeenFullyExecuted()) {
			return false;
		}
		if (!getBody().hasBeenFullyExecuted())
			return getBody().canExecute(task, counter);
		if ((boolean) getCondition().evaluate(task).getValue()) {
			return getBodyCopy().canExecute(task, counter);
		}
		// body has finished and condition is false. Next time this statement is executed it
		// will be set to fully executed.
		return true;
	}

	@Override
	public boolean isWellFormed() {
		return getBody().isWellFormed();
	}

	@Override
	public boolean containsActionStatement() {
		return getBody().containsActionStatement();
	}

	@Override
	public WhileStatement clone() {
		return new WhileStatement(getBody().clone(), getBodyCopy().clone(), condition, hasBeenFullyExecuted());
	}
	
	@Override
	public void reset(Task task) {
		this.setBody(this.getBodyCopy());
		this.getBody().SetHasFullyExecutedToTrue();
		super.reset(task);
	}

}
