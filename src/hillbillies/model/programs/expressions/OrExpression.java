package hillbillies.model.programs.expressions;

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
	
	public BooleanType getSecondExpressionEvaluate(Unit unit) {
		return getSecondExpression().evaluate(unit);
	}
	
	public BooleanType getFirstExpressionEvaluate(Unit unit) {
		return getFirstExpression().evaluate(unit);
	}
	
	private final BooleanExpression expression1;
	private final BooleanExpression expression2;

	@Override
	public BooleanType evaluate(Unit unit) {
		return new BooleanType((boolean) getFirstExpressionEvaluate(unit).getValue() || 
				(boolean) getSecondExpressionEvaluate(unit).getValue());
	}

	@Override
	public String toString(){
		return getFirstExpression().toString() + "AND" + getSecondExpression().toString();
	}

}
