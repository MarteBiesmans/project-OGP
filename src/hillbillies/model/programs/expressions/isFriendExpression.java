package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;

public class IsFriendExpression extends BinaryBooleanExpression {

	public IsFriendExpression(UnitExpression e1, UnitExpression e2) {
		super(e1,e2);
	}

	@Override
	public Boolean evaluate(Unit unit) {
		return ((Unit) getFirstExpressionEvaluate(unit)).getFaction() == 
				((Unit) getSecondExpressionEvaluate(unit)).getFaction();
	}

	@Override
	public String toString(){
		return getFirstExpression().toString() + "isFriendOf" + getSecondExpression().toString();
	}

}
