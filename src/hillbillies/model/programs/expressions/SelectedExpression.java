package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Task;
import hillbillies.model.programs.type.CubeType;

public class SelectedExpression extends CubeExpression {

	public SelectedExpression() {
	}
	
	public XYZExpression getExpression() {
		return expression;
	}
	
	public CubeType getExpressionEvaluate(Task task) {
		return new CubeType((Cube) getExpression().evaluate(task).getValue());
	}
	
	private XYZExpression expression;
	
	@Override
	public CubeType evaluate(Task task) {
		return new CubeType(task.getCube());
	}
	
	@Override
	public String toString(){
		return "selected";
	}

}
