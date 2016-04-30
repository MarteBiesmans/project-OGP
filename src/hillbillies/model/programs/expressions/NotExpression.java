package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;
import hillbillies.model.programs.type.BooleanType;

public class NotExpression extends BooleanExpression {

	public NotExpression(BooleanExpression e) {
		expression = e;
	}
	
	public BooleanExpression getExpression() {
		return expression;
	}
	
	public BooleanType getExpressionEvaluate(Unit unit) {
		return getExpression().evaluate(unit);
	}
	
	private final BooleanExpression expression;
	
	@Override
	public BooleanType evaluate(Unit unit) {
		return new BooleanType(! ((boolean) getExpressionEvaluate(unit).getValue()));
	}
	
	@Override
	public String toString(){
		return "NOT" + getExpression().toString();
	}
}
