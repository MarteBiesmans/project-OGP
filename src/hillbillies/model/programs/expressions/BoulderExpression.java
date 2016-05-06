package hillbillies.model.programs.expressions;

import hillbillies.model.Boulder;
import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.model.programs.type.CubeType;

public class BoulderExpression extends CubeExpression {

	public BoulderExpression() {
	}
	
	public CubeType evaluate(Unit unit) {
		return evaluate(unit, null);
	}

	@Override
	public CubeType evaluate(Unit unit, Cube cube) {
		Boulder nearestBoulderSoFar = null;
		for (Boulder boulder : unit.getWorld().getAllBoulders()) {
				if (nearestBoulderSoFar == null)
					nearestBoulderSoFar = boulder;
				else if (unit.getPosition().getDistanceSquare(boulder.getPosition()) < unit.getPosition()
						.getDistanceSquare(nearestBoulderSoFar.getPosition()))
					nearestBoulderSoFar = boulder;
		}
		if (nearestBoulderSoFar == null)
			return null;
		return new CubeType(nearestBoulderSoFar.getPosition().getCube());
	}

	@Override
	public String toString() {
		return "boulder";
	}

}
