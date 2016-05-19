package hillbillies.model.programs.statements;

import hillbillies.model.Counter;
import hillbillies.model.Task;

public class BreakStatement extends Statement {
	
	public BreakStatement(boolean hasBeenFullyExecuted) {
		super(hasBeenFullyExecuted);
	}
	
	public BreakStatement() {
		this(false);
	}
	
	@Override
	public void execute(Task task, Counter counter) {
		counter.increment();
		Statement whileStatement = getWhileStatement();
		if(whileStatement == null){
			throw new IllegalStateException("This break is not in a while loop.");
		}
		whileStatement.SetHasFullyExecutedToTrue();
		this.SetHasFullyExecutedToTrue();
	}

	@Override
	public boolean canExecute(Task task, Counter counter) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isWellFormed(){
		return getWhileStatement() != null;
	}

	@Override
	public BreakStatement clone() {
		return new BreakStatement();
	}

	@Override
	public boolean containsActionStatement() {
		return false;
	}
	
	private Statement getWhileStatement(){
		Statement upperStatement = getParentStatement();
		while(!(upperStatement instanceof WhileStatement)){
			if(upperStatement.hasParentStatement()){
				upperStatement = upperStatement.getParentStatement();
			} else {
				return null;
			}
		}
		return upperStatement;
	}
	
	
	
//	public BreakStatement() {
//	}
//	
//	
//	/**
//	 * Return the most inner while or for loop that is a parent of this
//	 * break statement.
//	 * @return
//	 */
//
//	
//	@Override
//	public boolean isMutable() {
//		return false;
//	}
//	

}
