package hillbillies.model.programs.expressions;

import hillbillies.model.Boulder;
import hillbillies.model.Task;
import hillbillies.model.programs.type.CubeType;

public class BoulderExpression extends CubeExpression {

	public BoulderExpression() {
	}

	@Override
	public CubeType evaluate(Task task) {
		Boulder nearestBoulderSoFar = null;
		for (Boulder boulder : task.getUnit().getWorld().getAllBoulders()) {
				if (nearestBoulderSoFar == null)
					nearestBoulderSoFar = boulder;
				else if (task.getUnit().getPosition().getDistanceSquare(boulder.getPosition()) < task.getUnit().getPosition()
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
