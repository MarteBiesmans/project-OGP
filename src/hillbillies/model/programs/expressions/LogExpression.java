package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Log;
import hillbillies.model.Unit;

public class LogExpression extends CubePositionExpression {

	public LogExpression(Unit unit) {
		super(unit);
	}

	@Override
	public Cube evaluate() {
		Unit unit = (Unit) getObject();
		Log nearestLogSoFar = null;
		for (Log log: unit.getWorld().getAllLogs()) {
			if (nearestLogSoFar == null)
				nearestLogSoFar = log;
			else if (unit.getPosition().getDistanceSquare(log.getPosition()) < unit.getPosition().getDistanceSquare(nearestLogSoFar.getPosition()))
					nearestLogSoFar = log;
		}
		if (nearestLogSoFar != null)
			return nearestLogSoFar.getPosition().getCube();
		else
			return unit.getCube();
	}

}
