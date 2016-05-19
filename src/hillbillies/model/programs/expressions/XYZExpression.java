package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Task;
import hillbillies.model.programs.type.CubeType;

public class XYZExpression extends CubeExpression {

	public XYZExpression(int x, int y, int z) {
		cube = new Cube(x, y, z);
	}
	
	private Cube cube;
	
	private Cube getCube() {
		return cube;
	}
	
	@Override
	public CubeType evaluate(Task task) {
		return new CubeType(getCube());
	}
	
	@Override
	public String toString(){
		return getCube().toString();
	}

}
