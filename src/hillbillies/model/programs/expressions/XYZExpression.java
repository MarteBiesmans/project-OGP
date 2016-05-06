package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.model.programs.type.CubeType;

public class XYZExpression extends CubeExpression {

	public XYZExpression(int x, int y, int z) {
		expression = new VariableExpression(new CubeType(new Cube(x, y, z)));
	}
	
	private VariableExpression expression;
	
	@Override
	public CubeType evaluate(Unit unit, Cube cube) {
		return new CubeType((Cube) expression.evaluate(unit, cube).getValue());
	}
	
	@Override
	public String toString(){
		return expression.toString();
	}

}
