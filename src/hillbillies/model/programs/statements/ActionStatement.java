package hillbillies.model.programs.statements;

import hillbillies.model.Counter;
import hillbillies.model.Task;

public abstract class ActionStatement extends Statement {
	
	protected ActionStatement(boolean hasBeenExecutedOnce, boolean hasBeenFullyExecuted) {
		super(hasBeenFullyExecuted);
		this.setHasBeenExecutedOnce(hasBeenExecutedOnce);
	}

	public ActionStatement() {
		this(false, false);
	}
	
	public boolean getHasBeenExecutedOnce() {
		return this.hasBeenExecutedOnce;
	}
	
	public void setHasBeenExecutedOnce(boolean executedOnce) {
		this.hasBeenExecutedOnce = executedOnce;
	}
	
	private boolean hasBeenExecutedOnce;

	@Override
	public boolean isWellFormed() {
		return true;
	}

	@Override
	public boolean containsActionStatement() {
		return true;
	}
	
	@Override
	public boolean canExecute(Task task, Counter counter) {
		if (counter.getCount() +1 > counter.getMaxValue() || hasBeenFullyExecuted()) {
			return false;
		}else if (getHasBeenExecutedOnce() && !hasBeenFullyExecuted())
			return false;;
		return true;
	}
	
//	@Override
//	public boolean isMutable() {
//		return false;
//	}
	
}
