package hillbillies.model.programs.statements;

import hillbillies.model.Counter;
import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.model.programs.expressions.UnitExpression;

public class AttackStatement extends ActionStatement {

	public AttackStatement(UnitExpression expression) {
		this.unit = expression;
	}
	
	private UnitExpression unit;
	
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
