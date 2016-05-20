package hillbillies.model.programs.statements;

import java.util.HashSet;

import hillbillies.model.Task;
import hillbillies.model.programs.expressions.BooleanExpression;
import hillbillies.model.Counter;

public class IfStatement extends Statement {

	private IfStatement(BooleanExpression condition, Statement then, Statement otherwise, boolean hasBeenExecutedOnce,
			boolean hasBeenFullyExecuted, boolean solvedCondition) {
		super(hasBeenFullyExecuted);
		this.condition = condition;
		this.then = then;
		then.setParentStatement(this);
		this.otherwise = otherwise;
		if (otherwise != null)
			otherwise.setParentStatement(this);
		this.setHasBeenExecutedOnce(hasBeenExecutedOnce);
		this.setSolvedCondition(solvedCondition);
	}

	public IfStatement(BooleanExpression condition, Statement then, Statement otherwise) {
		this(condition, then, otherwise, false, false, false);
	}

	private BooleanExpression getCondition() {
		return this.condition;
	}

	private final BooleanExpression condition;

	private Statement getThen() {
		return this.then;
	}

	private final Statement then;

	private Statement getOtherwise() {
		return this.otherwise;
	}

	private final Statement otherwise;

	private boolean getSolvedCondition() {
		return this.solvedCondition;
	}

	private void setSolvedCondition(boolean condition) {
		this.solvedCondition = condition;
	}

	private boolean solvedCondition;

	private boolean getHasBeenExecutedOnce() {
		return this.hasBeenExecutedOnce;
	}

	private void setHasBeenExecutedOnce(boolean executedOnce) {
		this.hasBeenExecutedOnce = executedOnce;
	}

	private boolean hasBeenExecutedOnce;

	@Override
	public Statement getExecutingStatement() {
		if (getHasBeenExecutedOnce()) {
			if (getSolvedCondition())
				return getThen().getExecutingStatement();
			else if (getOtherwise() != null)
				return getOtherwise().getExecutingStatement();
			return this;
		} else
			return this;
	}

	@Override
	public void execute(Task task, Counter counter) {
		counter.increment();
		if (!getHasBeenExecutedOnce()) {
			setSolvedCondition((boolean) getCondition().evaluate(task).getValue());
			setHasBeenExecutedOnce(true);
		}
		if (!(getThen().hasBeenFullyExecuted() || getOtherwise().hasBeenFullyExecuted())) {
			if (getSolvedCondition()) {
				getThen().execute(task, counter);
				return;
			} else {
				getOtherwise().execute(task, counter);
				return;
			}
		} else
			this.SetHasFullyExecutedToTrue();
	}

	@Override
	public boolean canExecute(Task task, Counter counter) {
		if (hasBeenFullyExecuted() || counter.getCount() + 1 > counter.getMaxValue())
			return false;

		if (!getHasBeenExecutedOnce()) {
			if ((boolean) getCondition().evaluate(task).getValue())
				return getThen().canExecute(task, counter);
			else
				return getOtherwise().canExecute(task, counter);
		}
		if (!(getThen().hasBeenFullyExecuted() || getOtherwise().hasBeenFullyExecuted())) {
			if (getSolvedCondition()) {
				return getThen().canExecute(task, counter);
			}
			if (!getSolvedCondition())
				return getOtherwise().canExecute(task, counter);
		}
		return true;
	}

	@Override
	public boolean isWellFormed() {
		return getThen().isWellFormed() && getOtherwise().isWellFormed();
	}

	@Override
	public boolean containsActionStatement() {
		if (this.getOtherwise() == null)
			return getThen().containsActionStatement();
		else
			return getThen().containsActionStatement() && getOtherwise().containsActionStatement();
	}

	@Override
	public IfStatement clone() {
		return new IfStatement(getCondition(), getThen().clone(), getOtherwise().clone(), getHasBeenExecutedOnce(),
				hasBeenFullyExecuted(), getSolvedCondition());
	}

	public HashSet<Statement> getDirectChildStatements() {
		HashSet<Statement> children = new HashSet<Statement>();
		children.add(getThen());
		if (getOtherwise() != null)
			children.add(getOtherwise());
		return children;
	}
	
	 @Override
	 public void reset(Task task) {
	 this.setHasBeenExecutedOnce(false);
	 super.reset(task);
	 }

}