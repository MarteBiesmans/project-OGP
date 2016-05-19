package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Task;
import hillbillies.model.programs.type.CubeType;

public class NextToExpression extends CubeExpression {

	public NextToExpression(ICubeExpression e) {
		expression = e;
	}
	
	public ICubeExpression getExpression() {
		return expression;
	}
	
	public CubeType getExpressionEvaluate(Task task) {
		return getExpression().evaluate(task);
	}
	
	private final ICubeExpression expression;
	
	@Override
	public CubeType evaluate(Task task) {
		for (Cube c: ((Cube) getExpressionEvaluate(task).getValue()).getAllNeighbouringCubes(task.getUnit().getWorld())) {
			if (c.isPassableIn(task.getUnit().getWorld()))
				return new CubeType(c);
		}
		return new CubeType(null);
	}
	
	@Override
	public String toString(){
		return "nextTo" + getExpression().toString();
	}

}
