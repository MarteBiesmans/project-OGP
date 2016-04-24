package hillbillies.model.programs.statements;

import hillbillies.model.Unit;

public class BreakStatement extends Statement {

	public BreakStatement() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Unit unit, Counter counter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canExecute(Unit unit, Counter counter) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isWellFormed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsActionStatement() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Statement clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
