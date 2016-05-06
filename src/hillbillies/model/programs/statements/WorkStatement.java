package hillbillies.model.programs.statements;

import hillbillies.model.Counter;
import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.model.programs.expressions.CubeExpression;

public class WorkStatement extends ActionStatement {
	
	public WorkStatement(CubeExpression expression) {
		this.cube = expression;
	}
	
	private CubeExpression cube;

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
	public Statement clone() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
