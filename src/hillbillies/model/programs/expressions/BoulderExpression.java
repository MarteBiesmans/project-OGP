package hillbillies.model.programs.expressions;

import hillbillies.model.Boulder;
import hillbillies.model.Cube;
import hillbillies.model.Unit;

public class BoulderExpression extends CubePositionExpression {

	public BoulderExpression(Unit unit) {
		super(unit);
	}

	@Override
	public Cube evaluate() {
		Unit unit = (Unit) getObject();
		Boulder nearestBoulderSoFar = null;
		for (Boulder log: unit.getWorld().getAllBoulders()) {
			if (nearestBoulderSoFar == null)
				nearestBoulderSoFar = log;
			else if (unit.getPosition().getDistanceSquare(log.getPosition()) < unit.getPosition().getDistanceSquare(nearestBoulderSoFar.getPosition()))
					nearestBoulderSoFar = log;
		}
		if (nearestBoulderSoFar != null)
			return nearestBoulderSoFar.getPosition().getCube();
		else
			return unit.getCube();
	}

}
