package hillbillies.model.programs.statements;

import hillbillies.model.Cube;
import hillbillies.model.programs.expressions.CubeExpression;

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
		getTask().getUnit().moveTo((Cube) getCube().evaluate(getTask()).getValue());
		this.setCompleted(true);
	}

}
