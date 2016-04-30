package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;
import hillbillies.model.programs.type.BooleanType;
import hillbillies.model.programs.type.Type;

public class IsEnemyExpression extends BooleanExpression {

	public IsEnemyExpression(UnitExpression e) {
		expression = e;
	}
	
	public UnitExpression getExpression() {
		return expression;
	}
	
	public Type getExpressionEvaluate(Unit unit) {
		return getExpression().evaluate(unit);
	}
	
	private final UnitExpression expression;

	@Override
	public BooleanType evaluate(Unit unit) {
		return new BooleanType(((Unit) getExpressionEvaluate(unit).getValue()).getFaction() != unit.getFaction());
	}

	@Override
	public String toString(){
		return getExpression().toString() + "isEnemy";
	}
}
