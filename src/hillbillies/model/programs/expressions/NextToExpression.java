package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;

public class NextToExpression extends UnaryCubeExpression {

	public NextToExpression(CubePositionExpression e) {
		super(e);
	}
	
	@Override
	public Cube evaluate(Unit unit) {
		for (Cube cube: ((Cube) getExpressionEvaluate(unit)).getAllNeighbouringCubes(unit.getWorld())) {
			if (cube.isPassableIn(unit.getWorld()))
				return cube;
		}
		return unit.getCube();
	}
	
	@Override
	public String toString(){
		return "nextTo" + getExpression().toString();
	}

}
