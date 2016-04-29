package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;

public class IsAliveExpression extends UnaryBooleanExpression {

	public IsAliveExpression(UnitExpression e) {
		super(e);
	}
	
	@Override
	public Boolean evaluate(Unit unit) {
		return (!((Unit) getExpressionEvaluate(unit)).isDead());
	}
	
	@Override
	public String toString(){
		return getExpression().toString()+"isAlive";
	}

}
