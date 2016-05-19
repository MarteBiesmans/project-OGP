package hillbillies.model.programs.statements;

public abstract class ActionStatement extends Statement {
	
	protected ActionStatement(boolean hasBeenExecutedOnce, boolean hasBeenFullyExecuted) {
		super(hasBeenFullyExecuted);
		this.setHasBeenExecutedOnce(hasBeenExecutedOnce);
	}

	public ActionStatement() {
		this(false, false);
	}
	
	boolean getHasBeenExecutedOnce() {
		return this.hasBeenExecutedOnce;
	}
	
	void setHasBeenExecutedOnce(boolean executedOnce) {
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
	
//	@Override
//	public boolean isMutable() {
//		return false;
//	}
	
}
