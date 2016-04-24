package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;

public class PositionOfExpression extends CubePositionExpression {

	public PositionOfExpression(Unit unit) {
		super(unit);
	}

	@Override
	public Cube evaluate() {
		return ((Unit) getObject()).getCube();
	}

}
