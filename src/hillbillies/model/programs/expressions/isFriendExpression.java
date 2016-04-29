package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;

public class IsFriendExpression extends UnaryBooleanExpression {

	public IsFriendExpression(UnitExpression e) {
		super(e);
	}

	@Override
	public Boolean evaluate(Unit unit) {
		return ((Unit) getExpressionEvaluate(unit)).getFaction() == unit.getFaction();
	}

	@Override
	public String toString(){
		return getExpression().toString() + "isFriend";
	}

}
