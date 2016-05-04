package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.model.programs.type.CubeType;

public class SelectedExpression extends CubeExpression {

	public SelectedExpression(XYZExpression e) {
		expression = e;
	}
	
	public XYZExpression getExpression() {
		return expression;
	}
	
	public CubeType getExpressionEvaluate(Unit unit) {
		return new CubeType((Cube) getExpression().evaluate(unit).getValue());
	}
	
	private XYZExpression expression;
	
	@Override
	public CubeType evaluate(Unit unit) {
		return new CubeType((Cube) getExpressionEvaluate(unit).getValue());
	}
	
	@Override
	public String toString(){
		return "selected";
	}

}
