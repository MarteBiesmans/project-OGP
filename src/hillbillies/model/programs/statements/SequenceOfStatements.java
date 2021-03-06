package hillbillies.model.programs.statements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import hillbillies.model.Task;
import hillbillies.model.Counter;

public class SequenceOfStatements extends Statement {

	private SequenceOfStatements(List<Statement> statements, boolean hasBeenFullyExecuted) {
		super(hasBeenFullyExecuted);
		this.listOfStatements = statements;
		for (Statement statement : statements) {
			if (statement == null) {
				System.out.println("Warning: statement cannot be null.");
			} else {
				statement.setParentStatement(this);
			}
		}
		statements.removeAll(Collections.singleton(null));
		resetIterator();
	}

	public SequenceOfStatements(List<Statement> statements) {
		this(statements, false);
	}

	private List<Statement> getListOfStatements() {
		return this.listOfStatements;
	}

	private final List<Statement> listOfStatements;
	
	@Override
	public Statement getExecutingStatement() {
		return getCurrentStatement().getExecutingStatement();
	}

	@Override
	public void execute(Task task, Counter counter) {
		counter.increment();
		getCurrentStatement().execute(task, counter);
		if (getCurrentStatement().hasBeenFullyExecuted()) {
			setNextStatement();
		}
	}

	@Override
	public boolean canExecute(Task task, Counter counter) {
		if (hasBeenFullyExecuted() || counter.getCount() + 1 > counter.getMaxValue())
			return false;
		return getCurrentStatement().canExecute(task, counter);
	}

	@Override
	public boolean isWellFormed() {
		for (Statement statement : getListOfStatements()) {
			if (!statement.isWellFormed())
				return false;
		}
		return true;
	}

	@Override
	public boolean containsActionStatement() {
		for (Statement statement : getListOfStatements()) {
			if (statement.containsActionStatement())
				return true;
		}
		return false;
	}

	@Override
	public SequenceOfStatements clone() {
		List<Statement> clonedlist = new ArrayList<Statement>();
		for (Statement statement : getListOfStatements()) {
			clonedlist.add(statement.clone());
		}
		return new SequenceOfStatements(clonedlist, hasBeenFullyExecuted());
	}

	public HashSet<Statement> getDirectChildStatements() {
		HashSet<Statement> children = new HashSet<Statement>();
		children.addAll(getListOfStatements());
		return children;
	}

	private void resetIterator() {
		setIterator(getListOfStatements().iterator());
		setNextStatement();
	}

	private void setNextStatement() {
		if (getIterator().hasNext())
			setCurrentStatement(getIterator().next());
		else
			this.SetHasFullyExecutedToTrue();
	}
	
	private Statement getCurrentStatement() {
		return currentStatement;
	}

	private void setCurrentStatement(Statement currentStatement) {
		this.currentStatement = currentStatement;
	}

	private Statement currentStatement;

	private Iterator<Statement> getIterator() {
		return iterator;
	}

	private void setIterator(Iterator<Statement> iterator) {
		this.iterator = iterator;
	}

	private Iterator<Statement> iterator = null;

}
