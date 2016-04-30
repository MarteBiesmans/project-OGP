package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;
import hillbillies.model.programs.type.BooleanType;
import hillbillies.model.programs.type.UnitType;

public class IsAliveExpression extends BooleanExpression {
	
	public IsAliveExpression(UnitExpression e) {
		expression = e;
	}
	
	public UnitExpression getExpression() {
		return expression;
	}
	
	public UnitType getExpressionEvaluate(Unit unit) {
		return getExpression().evaluate(unit);
	}
	
	private final UnitExpression expression;
	
	@Override
	public BooleanType evaluate(Unit unit) {
		return new BooleanType(!((Unit) getExpressionEvaluate(unit).getValue()).isDead());
	}
	
	@Override
	public String toString(){
		return getExpression().toString()+"isAlive";
	}

}
