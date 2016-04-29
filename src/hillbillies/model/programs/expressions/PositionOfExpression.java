package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;

public class PositionOfExpression extends UnaryCubeExpression {

	public PositionOfExpression(UnitExpression e) {
		super(e);
	}
	
	@Override
	public Cube evaluate(Unit unit) {
		return ((Unit) getExpressionEvaluate(unit)).getCube();
	}
	
	@Override
	public String toString(){
		return "positionOf" + getExpression().toString();
	}

}
