package hillbillies.model.programs.statements;

import hillbillies.model.Cube;
import hillbillies.model.programs.expressions.CubeExpression;

public class WorkStatement extends ActionStatement {

	public WorkStatement(CubeExpression expression) {
		this.cube = expression;
	}
	
	public CubeExpression getCube() {
		return cube;
	}

	private CubeExpression cube;

	public void execute() {
		if(getTask().getUnit() == null){
			throw new NullPointerException("this task has no unit");
		}
		getTask().getUnit().workAt((Cube) getCube().evaluate(getTask()).getValue());
	}

}
