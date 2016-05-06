package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Log;
import hillbillies.model.Unit;
import hillbillies.model.programs.type.CubeType;

public class LogExpression extends CubeExpression {

	public LogExpression() {
	}
	
	public CubeType evaluate(Unit unit) {
		return evaluate(unit, null);
	}
	
	@Override
	public CubeType evaluate(Unit unit, Cube cube) {
		Log nearestLogSoFar = null;
		for (Log log : unit.getWorld().getAllLogs()) {
				if (nearestLogSoFar == null)
					nearestLogSoFar = log;
				else if (unit.getPosition().getDistanceSquare(log.getPosition()) < unit.getPosition()
						.getDistanceSquare(nearestLogSoFar.getPosition()))
					nearestLogSoFar = log;
		}
		if (nearestLogSoFar == null)
			return null;
		return new CubeType(nearestLogSoFar.getPosition().getCube());
	}

	@Override
	public String toString() {
		return "log";
	}

}
