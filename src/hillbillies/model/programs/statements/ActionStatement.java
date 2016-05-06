package hillbillies.model.programs.statements;

public abstract class ActionStatement extends Statement {
	
	protected ActionStatement(boolean hasBeenFullyExecuted) {
		super(hasBeenFullyExecuted);
	}

	public ActionStatement() {
		this(false);
	}

	@Override
	public boolean isWellFormed() {
		return true;
	}

	@Override
	public boolean containsActionStatement() {
		return true;
	}
	
}
