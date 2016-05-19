package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Task;
import hillbillies.model.programs.type.BooleanType;
import hillbillies.model.programs.type.CubeType;

public class IsPassableExpression extends BooleanExpression {

	public IsPassableExpression(ICubeExpression e) {
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
	public BooleanType evaluate(Task task) {
		return new BooleanType(((Cube) getExpressionEvaluate(task).getValue()).isPassableIn(task.getUnit().getWorld()));
	}
	
	@Override
	public String toString(){
		return getExpression().toString()+"isPassable";
	}

}
