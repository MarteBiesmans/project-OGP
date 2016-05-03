package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.model.programs.type.CubeType;

public class NextToExpression extends CubeExpression {

	public NextToExpression(CubeExpression e) {
		expression = e;
	}
	
	public CubeExpression getExpression() {
		return expression;
	}
	
	public CubeType getExpressionEvaluate(Unit unit) {
		return getExpression().evaluate(unit);
	}
	
	private final CubeExpression expression;
	
	@Override
	public CubeType evaluate(Unit unit) {
		for (Cube cube: ((Cube) getExpressionEvaluate(unit).getValue()).getAllNeighbouringCubes(unit.getWorld())) {
			if (cube.isPassableIn(unit.getWorld()))
				return new CubeType(cube);
		}
		return new CubeType(null);
	}
	
	@Override
	public String toString(){
		return "nextTo" + getExpression().toString();
	}

}
