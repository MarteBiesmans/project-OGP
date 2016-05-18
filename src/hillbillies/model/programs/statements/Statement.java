package hillbillies.model.programs.statements;

import java.util.HashSet;

import hillbillies.model.Counter;
import hillbillies.model.Task;

public abstract class Statement implements Cloneable {

	public abstract void execute();
	
	public abstract boolean canExecute(Counter couneter);
	
	public abstract boolean isMutable();
	
	public boolean isWellFormed() {
		return true;
	}
	
	public Statement getExecutingStatement() {
		return this;
	}
	
	@Override
	public Statement clone() {
		try {
			if (isMutable())
				return (Statement) super.clone();
			else
				return this;
		} catch (CloneNotSupportedException exc) {
			assert false;
			return null;
		}
	}
	
	public HashSet<Statement> getDirectChildStatements(){
		return new HashSet<Statement>();
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	private boolean completed = false;

	public void reset() {
		setCompleted(false);
		for (Statement child : getDirectChildStatements())
			child.reset();
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

	protected Task getTask() {
		if (this.hasParentStatement()) {
			return this.getParentStatement().getTask();
		} else {
			return this.task;
		}
	}

	public void setTask(Task task) {
		this.task = task;
	}

	private Task task;

}
