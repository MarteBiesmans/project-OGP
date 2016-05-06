package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.model.programs.type.BooleanType;

public class NotExpression extends BooleanExpression {

	public NotExpression(BooleanExpression e) {
		expression = e;
	}
	
	public BooleanExpression getExpression() {
		return expression;
	}
	
	public BooleanType getExpressionEvaluate(Unit unit, Cube cube) {
		return getExpression().evaluate(unit, cube);
	}
	
	private final BooleanExpression expression;
	
	@Override
	public BooleanType evaluate(Unit unit, Cube cube) {
		return new BooleanType(! ((boolean) getExpressionEvaluate(unit, cube).getValue()));
	}
	
	@Override
	public String toString(){
		return "NOT" + getExpression().toString();
	}
}
