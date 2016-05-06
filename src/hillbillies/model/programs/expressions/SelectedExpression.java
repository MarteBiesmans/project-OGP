package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.model.programs.type.CubeType;

public class SelectedExpression extends CubeExpression {

	public SelectedExpression() {
	}
	
	public XYZExpression getExpression() {
		return expression;
	}
	
	public CubeType getExpressionEvaluate(Unit unit, Cube cube) {
		return new CubeType((Cube) getExpression().evaluate(unit, cube).getValue());
	}
	
	private XYZExpression expression;
	
	@Override
	public CubeType evaluate(Unit unit, Cube cube) {
		return new CubeType(cube);
	}
	
	@Override
	public String toString(){
		return "selected";
	}

}
