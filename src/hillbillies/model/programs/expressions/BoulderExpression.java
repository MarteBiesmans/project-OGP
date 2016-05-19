package hillbillies.model.programs.expressions;

import hillbillies.model.Boulder;
import hillbillies.model.Cube;
import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.model.programs.type.CubeType;

public class BoulderExpression extends CubeExpression {

	public BoulderExpression() {
	}

	@Override
	public CubeType evaluate(Task task) {
		try {
			return new CubeType((Cube) task.getUnit().getWorld().getAllMaterials().stream()
					.filter(material -> material instanceof Boulder)
					.map(boulder -> new BoulderDistPair((Boulder) boulder, task.getUnit()))
					.reduce((x, y) -> x.getMinimum(y)).get().getBoulder().getPosition().getCube());
		} catch (NullPointerException exc) {
			return null;
		}
	}

}
