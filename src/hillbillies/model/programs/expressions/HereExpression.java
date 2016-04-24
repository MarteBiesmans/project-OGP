package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;

public class HereExpression extends CubePositionExpression {

	public HereExpression(Unit unit) {
		super(unit);
	}

	@Override
	public Cube evaluate() {
		return ((Unit) getObject()).getCube();
	}

}
