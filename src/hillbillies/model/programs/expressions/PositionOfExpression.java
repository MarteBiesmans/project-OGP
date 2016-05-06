package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.model.programs.type.CubeType;
import hillbillies.model.programs.type.UnitType;

public class PositionOfExpression extends CubeExpression {

	public PositionOfExpression(UnitExpression e) {
		expression = e;
	}
	
	public UnitExpression getExpression() {
		return expression;
	}
	
	public UnitType getExpressionEvaluate(Unit unit, Cube cube) {
		return getExpression().evaluate(unit, cube);
	}
	
	private final UnitExpression expression;
	
	@Override
	public CubeType evaluate(Unit unit, Cube cube) {
		return new CubeType(((Unit) getExpressionEvaluate(unit, cube).getValue()).getCube());
	}
	
	@Override
	public String toString(){
		return "positionOf" + getExpression().toString();
	}

}
