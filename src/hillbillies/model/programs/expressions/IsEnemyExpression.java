package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;

public class IsEnemyExpression extends UnaryBooleanExpression {

	public IsEnemyExpression(UnitExpression e) {
		super(e);
	}

	@Override
	public Boolean evaluate(Unit unit) {
		return ((Unit) getExpressionEvaluate(unit)).getFaction() != unit.getFaction();
	}

	@Override
	public String toString(){
		return getExpression().toString() + "isEnemy";
	}

}
