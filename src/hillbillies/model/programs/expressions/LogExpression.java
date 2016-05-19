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
		try {
			return new CubeType((Cube) task.getUnit().getWorld().getAllMaterials().stream()
					.filter(material -> material instanceof Log)
					.map(log -> new LogDistPair((Log) log, task.getUnit()))
					.reduce((x, y) -> x.getMinimum(y)).get().getLog().getPosition().getCube());
		} catch (NullPointerException exc) {
			return null;
		}
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
