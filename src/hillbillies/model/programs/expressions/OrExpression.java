package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.model.programs.type.BooleanType;

public class OrExpression extends BooleanExpression {

	public OrExpression(BooleanExpression e1, BooleanExpression e2) {
		expression1 = e1;
		expression2 = e2;
	}
	
	public BooleanExpression getFirstExpression() {
		return expression1;
	}
	
	public BooleanExpression getSecondExpression() {
		return expression2;
	}
	
	public BooleanType getSecondExpressionEvaluate(Unit unit, Cube cube) {
		return getSecondExpression().evaluate(unit, cube);
	}
	
	public BooleanType getFirstExpressionEvaluate(Unit unit, Cube cube) {
		return getFirstExpression().evaluate(unit, cube);
	}
	
	private final BooleanExpression expression1;
	private final BooleanExpression expression2;

	@Override
	public BooleanType evaluate(Unit unit, Cube cube) {
		return new BooleanType((boolean) getFirstExpressionEvaluate(unit, cube).getValue() || 
				(boolean) getSecondExpressionEvaluate(unit, cube).getValue());
	}

	@Override
	public String toString(){
		return getFirstExpression().toString() + "AND" + getSecondExpression().toString();
	}

}
