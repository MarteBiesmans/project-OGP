package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;

public class PositionOfExpression extends UnaryCubeExpression {

	public PositionOfExpression(UnitExpression e) {
		expression = e;
	}
	
	public Expression getExpression() {
		return expression;
	}
	
	public Object getExpressionEvaluate(Unit unit) {
		return getExpression().evaluate(unit);
	}
	
	private final Expression expression;
	
	@Override
	public Cube evaluate(Unit unit) {
		return ((Unit) getExpressionEvaluate(unit)).getCube();
	}
	
	@Override
	public String toString(){
		return "positionOf" + getExpression().toString();
	}

}
