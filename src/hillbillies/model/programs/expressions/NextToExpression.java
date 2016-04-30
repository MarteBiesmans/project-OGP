package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;

public class NextToExpression extends UnaryCubeExpression {

	public NextToExpression(CubeExpression e) {
		expression = e;
	}
	
	public Expression getExpression() {
		return expression;
	}
	
	public Object getExpressionEvaluate(Unit unit) {
		return getExpression().evaluate(unit);
	}
	
	private final Expression expression;
	
	@Override
	public Cube evaluate(Unit unit) {
		for (Cube cube: ((Cube) getExpressionEvaluate(unit)).getAllNeighbouringCubes(unit.getWorld())) {
			if (cube.isPassableIn(unit.getWorld()))
				return cube;
		}
		return null;
	}
	
	@Override
	public String toString(){
		return "nextTo" + getExpression().toString();
	}

}
