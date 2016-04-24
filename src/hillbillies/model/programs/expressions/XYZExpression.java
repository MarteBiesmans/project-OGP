package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;

public class XYZExpression extends CubePositionExpression {

	public XYZExpression(int x, int y, int z) {
		super(new Cube(x, y, z));
	}

	@Override
	public Cube evaluate() {
		return (Cube) getObject();
	}

}
