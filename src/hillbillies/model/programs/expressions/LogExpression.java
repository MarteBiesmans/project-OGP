package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.DistPair;
import hillbillies.model.Log;
import hillbillies.model.Task;
import hillbillies.model.programs.type.CubeType;

public class LogExpression extends CubeExpression {

	public LogExpression() {
	}

	@Override
	public CubeType evaluate(Task task) {
		try {
			return new CubeType((Cube) task.getUnit().getWorld().getAllMaterials()
					.stream()
					.filter(material -> material instanceof Log)
					.map(log -> new DistPair<Log>((Log) log, task.getUnit().getPosition().getDistanceSquare(log.getPosition())))
					.reduce(DistPair<Log>::getMinimum).get().getThing().getPosition().getCube());
		} catch (NullPointerException exc) {
			return null;
		}
	}

	@Override
	public String toString() {
		return "log";
	}

}
