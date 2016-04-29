package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;

public class XYZExpression extends CubeExpression {

	public XYZExpression(int x, int y, int z) {
		cube = new Cube(x, y, z);
	}
	
	private Cube cube;
	
	@Override
	public Cube evaluate(Unit unit) {
		return cube;
	}
	
	@Override
	public String toString(){
		return cube.toString();
	}

}
