package hillbillies.model.programs.statements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import hillbillies.model.Task;
import hillbillies.model.Counter;

public class SequenceOfStatements extends Statement {
	
	private SequenceOfStatements(List<Statement> statements, boolean hasBeenFullyExecuted) {
		super(hasBeenFullyExecuted);
		this.listOfStatements = statements;
		for (Statement statement : statements) {
			if(statement == null){
				System.out.println("Warning: statement cannot be null.");
			} else {
				statement.setParentStatement(this);
			}
		}
		statements.removeAll(Collections.singleton(null));
	}
	
	public SequenceOfStatements(List<Statement> statements) {
		this(statements, false);
	}
	
	private List<Statement> getListOfStatements() {
		return this.listOfStatements;
	}

	private final List<Statement> listOfStatements;
	
	@Override
	public void execute(Task task, Counter counter) {
		counter.increment();
		for (Statement statement:  getListOfStatements()) {
			if (!statement.hasBeenFullyExecuted()) {
				statement.execute(task, counter);
				return;
			}
		}
		SetHasFullyExecutedToTrue();
	}

	@Override
	public boolean canExecute(Task task, Counter counter) {
		counter.increment();
		if (hasBeenFullyExecuted() || counter.getCount() > 1000)
			return false;
		for (Statement statement: getListOfStatements()) {
			if (!statement.hasBeenFullyExecuted() ) {
				return statement.canExecute(task, counter);
			}
		}
		return true;
	}

	@Override
	public boolean isWellFormed() {
		for (Statement statement: getListOfStatements()) {
			if(!statement.isWellFormed())
				return false;
		}
		return true;
	}

	@Override
	public boolean containsActionStatement() {
		for (Statement statement: getListOfStatements()) {
			if(statement.containsActionStatement())
				return true;
		}
		return false;
	}

	@Override
	public SequenceOfStatements clone() {
		List<Statement> clonedlist = new ArrayList<Statement>();
		for(Statement statement: getListOfStatements()) {
			clonedlist.add(statement.clone());
		}
		return new SequenceOfStatements(clonedlist , hasBeenFullyExecuted());
	}
	
	public HashSet<Statement> getDirectChildStatements(){
		HashSet<Statement> children = new HashSet<Statement>();
		children.addAll(getListOfStatements());
		return children;
	}

//	public SequenceOfStatements(List<Statement> statements) {
//		this.statements = statements;
//		for (Statement statement : statements) {
//			if(statement == null){
//				System.out.println("Warning: statement cannot be null.");
//			} else {
//				statement.setParentStatement(this);
//			}
//		}
//		statements.removeAll(Collections.singleton(null));
//		resetIterator();
//	}
//	
//	@Override
//	public Statement getExecutingStatement() {
//		return getCurrentStatement().getExecutingStatement();
//	}
//	
//	public void execute(){
//		getCurrentStatement().execute();
//		if(getCurrentStatement().isCompleted()){
//			setNextStatement();
//		}
//	}
//	
//	private void resetIterator(){
//		setIterator(getStatements().iterator());
//		setNextStatement();
//	}
//	
//	private void setNextStatement(){
//		if(getIterator().hasNext())
//			setCurrentStatement(getIterator().next());
//		else
//			this.setCompleted(true);
//	}
//	
//	@Override
//	public void reset(){
//		super.reset();
//		resetIterator();
//	}
//	
//	private Statement getCurrentStatement() {
//		return currentStatement;
//	}
//	
//	private void setCurrentStatement(Statement currentStatement) {
//		this.currentStatement = currentStatement;
//	}
//	
//	private Statement currentStatement;
//	
//	private Iterator<Statement> getIterator() {
//		return iterator;
//	}
//	
//	private void setIterator(Iterator<Statement> iterator) {
//		this.iterator = iterator;
//	}
//	
//	private Iterator<Statement> iterator = null;
//	
//	private List<Statement> statements;
//	
//	private List<Statement> getStatements(){
//		return statements;
//	}
//
//
//	@Override
//	public boolean isMutable() {
//		return true;
//	}
//	
//	public boolean isWellFormed(){
//		for (Statement statement : statements) {
//			if(!statement.isWellFormed()){
//				return false;
//			}
//		}
//		return true;
//	}
	
}
