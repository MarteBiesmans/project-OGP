package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.model.programs.type.CubeType;

public class XYZExpression extends CubeExpression {

	public XYZExpression(int x, int y, int z) {
		cube = new Cube(x, y, z);
	}
	
	private Cube cube;
	
	private Cube getCube() {
		return cube;
	}
	
	public CubeType evaluate() {
		return evaluate(null, null);
	}
	
	@Override
	public CubeType evaluate(Unit unit, Cube cube) {
		return new CubeType(getCube());
	}
	
	@Override
	public String toString(){
		return getCube().toString();
	}

}
