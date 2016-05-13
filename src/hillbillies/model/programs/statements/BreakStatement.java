package hillbillies.model.programs.statements;

import hillbillies.model.Counter;
import hillbillies.model.Cube;
import hillbillies.model.Unit;

public class BreakStatement extends Statement {
	
	public BreakStatement() {
	}
	
	@Override
	public void execute() {
		Statement whileStatement = getWhileStatement();
		if(whileStatement == null){
			throw new IllegalStateException("This break is not in a while loop.");
		}
		whileStatement.setCompleted(true);
	}
	
	/**
	 * Return the most inner while or for loop that is a parent of this
	 * break statement.
	 * @return
	 */
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
	
	@Override
	public boolean isMutable() {
		return false;
	}

}
