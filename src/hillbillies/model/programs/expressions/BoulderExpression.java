package hillbillies.model.programs.expressions;

import hillbillies.model.Boulder;
import hillbillies.model.Cube;
import hillbillies.model.DistPair;
import hillbillies.model.Task;
import hillbillies.model.programs.type.CubeType;

public class BoulderExpression extends CubeExpression {

	public BoulderExpression() {
	}

	@Override
	public CubeType evaluate(Task task) {
		try {
			return new CubeType((Cube) task.getUnit().getWorld().getAllMaterials()
					.stream()
					.filter(material -> material instanceof Boulder)
					.map(boulder -> new DistPair<Boulder>((Boulder) boulder, task.getUnit().getPosition().getDistanceSquare(boulder.getPosition())))
					.reduce(DistPair<Boulder>::getMinimum).get().getThing().getPosition().getCube());
		} catch (NullPointerException exc) {
			return null;
		}
	}

}
