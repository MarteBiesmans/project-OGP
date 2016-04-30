package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;
import hillbillies.model.programs.type.BooleanType;
import hillbillies.model.programs.type.Type;

public class IsEnemyExpression extends BooleanExpression {

	public IsEnemyExpression(UnitExpression e) {
		expression = e;
	}
	
	public Expression<BooleanType> getExpression() {
		return expression;
	}
	
	public Type getExpressionEvaluate(Unit unit) {
		return getExpression().evaluate(unit);
	}
	
	private final Expression<BooleanType> expression;

	@Override
	public Boolean evaluate(Unit unit) {
		return ((Unit) getExpressionEvaluate(unit)).getFaction() != unit.getFaction();
	}

	@Override
	public String toString(){
		return getExpression().toString() + "isEnemy";
	}

}
