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
	
	public CubeType getExpressionEvaluate(Unit unit, Cube cube) {
		return getExpression().evaluate(unit, cube);
	}
	
	private final CubeExpression expression;
	
	@Override
	public CubeType evaluate(Unit unit, Cube cube) {
		for (Cube c: ((Cube) getExpressionEvaluate(unit, cube).getValue()).getAllNeighbouringCubes(unit.getWorld())) {
			if (c.isPassableIn(unit.getWorld()))
				return new CubeType(c);
		}
		return new CubeType(null);
	}
	
	@Override
	public String toString(){
		return "nextTo" + getExpression().toString();
	}

}
