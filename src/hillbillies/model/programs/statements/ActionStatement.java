package hillbillies.model.programs.statements;

import hillbillies.part3.programs.ITaskFactory;

public abstract class ActionStatement extends Statement {

	protected ActionStatement(ITaskFactory factory, boolean hasBeenFullyExecuted) {
		super(hasBeenFullyExecuted);
		TaskFactory = factory;
	}

	public ActionStatement(ITaskFactory factory) {
		this(factory, false);
	}

	public ITaskFactory getTaskFactory() {
		return TaskFactory;
	}

	private final ITaskFactory TaskFactory;

	@Override
	public boolean isWellFormed() {
		return true;
	}

	@Override
	public boolean containsActionStatement() {
		return true;
	}

}
