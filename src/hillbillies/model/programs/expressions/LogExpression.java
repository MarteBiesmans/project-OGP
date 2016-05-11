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
	
	public CubeType testEvaluate(Unit unit, Cube cube) {
		return new CubeType((Cube) unit.getWorld().getAllMaterials().stream().
				filter(material -> material instanceof Log).
				map(log -> new LogDistPair((Log) log, unit)).
				reduce((x, y) -> x.getMinimum(y)).
				get().getLog().getPosition().getCube());
	}

	private class LogDistPair {

		private final Double distance;
		private Log log;

		private LogDistPair(Log log, Unit unit) {
			this.log = log;
			this.distance = unit.getPosition().getDistanceSquare(log.getPosition());
		}

		private Double getDistance() {
			return distance;
		}

		private Log getLog() {
			return log;
		}
		
		private LogDistPair getMinimum(LogDistPair other) {
			if (this.getDistance() <= other.getDistance())
				return this;
			else
				return other;
		}

	}

	@Override
	public String toString() {
		return "log";
	}

}
