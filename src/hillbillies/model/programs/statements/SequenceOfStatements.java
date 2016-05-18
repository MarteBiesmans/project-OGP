package hillbillies.model.programs.statements;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SequenceOfStatements extends Statement {

	public SequenceOfStatements(List<Statement> statements) {
		this.statements = statements;
		for (Statement statement : statements) {
			if(statement == null){
				System.out.println("Warning: statement cannot be null.");
			} else {
				statement.setParentStatement(this);
			}
		}
		statements.removeAll(Collections.singleton(null));
		resetIterator();
	}
	
	@Override
	public Statement getExecutingStatement() {
		return getCurrentStatement().getExecutingStatement();
	}
	
	public void execute(){
		getCurrentStatement().execute();
		if(getCurrentStatement().isCompleted()){
			setNextStatement();
		}
	}
	
	private void resetIterator(){
		setIterator(getStatements().iterator());
		setNextStatement();
	}
	
	private void setNextStatement(){
		if(getIterator().hasNext())
			setCurrentStatement(getIterator().next());
		else
			this.setCompleted(true);
	}
	
	@Override
	public void reset(){
		super.reset();
		resetIterator();
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
	
	private List<Statement> statements;
	
	private List<Statement> getStatements(){
		return statements;
	}


	@Override
	public boolean isMutable() {
		return true;
	}
	
	public boolean isWellFormed(){
		for (Statement statement : statements) {
			if(!statement.isWellFormed()){
				return false;
			}
		}
		return true;
	}
	
}
