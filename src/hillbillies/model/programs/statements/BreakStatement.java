package hillbillies.model.programs.statements;

import hillbillies.model.Counter;
import hillbillies.model.Cube;
import hillbillies.model.Unit;

public class BreakStatement extends Statement {

	@Override
	public void execute(Unit unit, Cube cube, Counter counter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canExecute(Unit unit, Cube cube, Counter counter) {
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
