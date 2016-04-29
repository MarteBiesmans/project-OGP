package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Log;
import hillbillies.model.Unit;

public class LogExpression extends NullaryCubeExpression {

	public LogExpression() {
		super();
	}

	@Override
	public Cube evaluate(Unit unit) {
		if (unit.getWorld().getAllLogs().size() == 0)
			return unit.getCube();
		Log nearestLogSoFar = null;
		for (Log log : unit.getWorld().getAllLogs()) {
			if (nearestLogSoFar == null)
				nearestLogSoFar = log;
			else if (unit.getPosition().getDistanceSquare(log.getPosition()) < unit.getPosition()
					.getDistanceSquare(nearestLogSoFar.getPosition()))
				nearestLogSoFar = log;
		}
		return nearestLogSoFar.getPosition().getCube();
	}

	@Override
	public String toString() {
		return "log";
	}

}
