package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Log;
import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.model.programs.type.CubeType;

public class LogExpression extends CubeExpression {

	public LogExpression() {
	}
	
	@Override
	public CubeType evaluate(Task task) {
		Log nearestLogSoFar = null;
		for (Log log : task.getUnit().getWorld().getAllLogs()) {
				if (nearestLogSoFar == null)
					nearestLogSoFar = log;
				else if (task.getUnit().getPosition().getDistanceSquare(log.getPosition()) < task.getUnit().getPosition()
						.getDistanceSquare(nearestLogSoFar.getPosition()))
					nearestLogSoFar = log;
		}
		if (nearestLogSoFar == null)
			return null;
		return new CubeType(nearestLogSoFar.getPosition().getCube());
	}
	
	public CubeType testEvaluate(Task task) {
		return new CubeType((Cube) task.getUnit().getWorld().getAllMaterials().stream().
				filter(material -> material instanceof Log).
				map(log -> new LogDistPair((Log) log, task.getUnit())).
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
