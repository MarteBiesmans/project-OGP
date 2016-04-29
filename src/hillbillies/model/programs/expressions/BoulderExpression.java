package hillbillies.model.programs.expressions;

import hillbillies.model.Boulder;
import hillbillies.model.Cube;
import hillbillies.model.Unit;

public class BoulderExpression extends NullaryCubeExpression {

	public BoulderExpression() {
		super();
	}

	@Override
	public Cube evaluate(Unit unit) {
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
		return nearestBoulderSoFar.getPosition().getCube();
	}

	@Override
	public String toString() {
		return "boulder";
	}

}
