package hillbillies.model.programs.statements;

import java.util.HashSet;

import hillbillies.model.Counter;
import hillbillies.model.Task;

public abstract class Statement implements Cloneable {

	protected Statement(boolean hasFullyExecuted) {
		this.hasFullyExecuted = hasFullyExecuted;
	}

	protected Statement() {
		this(false);
	}

	/**
	 * Naming with 'fully' to avoid confusion since execute() doesn't per se
	 * make this attribute true
	 */
	private boolean hasFullyExecuted;

	public boolean hasBeenFullyExecuted() {
		return hasFullyExecuted;
	}

	public void SetHasFullyExecutedToTrue() {
		hasFullyExecuted = true;
	}

	public Statement getExecutingStatement() {
		return this;
	}

	public abstract void execute(Task task, Counter counter);

	public abstract boolean canExecute(Task task, Counter counter); // TODO:
																	// rekening
																	// houden
																	// met
																	// coutner
																	// max value
																	// ofzo

	public abstract boolean isWellFormed();

	public abstract boolean containsActionStatement();

	public abstract Statement clone();

	public HashSet<Statement> getDirectChildStatements() {
		return new HashSet<Statement>();
	}

	protected Statement getParentStatement() {
		return parentStatement;
	}

	public void setParentStatement(Statement parentStatement) {
		this.parentStatement = parentStatement;
	}

	protected boolean hasParentStatement() {
		return (getParentStatement() != null);
	}

	private Statement parentStatement = null;

	public void reset(Task task) {
		this.hasFullyExecuted = false;
		for (Statement child : getDirectChildStatements())
			child.reset(task);
	}

}
