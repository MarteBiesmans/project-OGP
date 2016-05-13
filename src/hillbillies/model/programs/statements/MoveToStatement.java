package hillbillies.model.programs.statements;

import hillbillies.model.Counter;
import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.model.programs.expressions.CubeExpression;
import hillbillies.model.programs.expressions.UnitExpression;

public class MoveToStatement extends ActionStatement {
	
	public MoveToStatement(CubeExpression c) {
		this.cube = c;
	}
	
	public CubeExpression getCube() {
		return cube;
	}
	
	private CubeExpression cube;

	public void execute() {
		if(getTask().getUnit() == null){
			throw new NullPointerException("this task has no unit");
		}
		getTask().getUnit().moveTo(getCube().evaluate().getValue());
		this.setCompleted(true);
	}

}
