package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
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
	
	public UnitType getExpressionEvaluate(Unit unit, Cube cube) {
		return getExpression().evaluate(unit, cube);
	}
	
	private final UnitExpression expression;
	
	@Override
	public BooleanType evaluate(Unit unit, Cube cube) {
		return new BooleanType(!((Unit) getExpressionEvaluate(unit, cube).getValue()).isDead());
	}
	
	@Override
	public String toString(){
		return getExpression().toString()+"isAlive";
	}

}
