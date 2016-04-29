package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;

public class OrExpression extends BinaryExpression {

	public OrExpression(BooleanExpression e1, BooleanExpression e2) {
		super(e1,e2);
	}

	@Override
	public Boolean evaluate(Unit unit) {
		return (Boolean) getFirstExpressionEvaluate(unit) || 
				(Boolean) getSecondExpressionEvaluate(unit);
	}
	@Override
	public String toString(){
		return getFirstExpression().toString()+"OR"+getSecondExpression().toString();
	}

}
