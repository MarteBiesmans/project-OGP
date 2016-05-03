package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;
import hillbillies.model.programs.type.CubeType;

public class PositionOfExpression extends CubeExpression {

	public PositionOfExpression(UnitExpression e) {
		expression = e;
	}
	
	public UnitExpression getExpression() {
		return expression;
	}
	
	public Object getExpressionEvaluate(Unit unit) {
		return getExpression().evaluate(unit);
	}
	
	private final UnitExpression expression;
	
	@Override
	public CubeType evaluate(Unit unit) {
		return new CubeType(((Unit) getExpressionEvaluate(unit)).getCube());
	}
	
	@Override
	public String toString(){
		return "positionOf" + getExpression().toString();
	}

}
