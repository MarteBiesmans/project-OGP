package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;
import hillbillies.model.programs.type.Type;

public class ParenthesisExpression extends Expression<Type> {

	public ParenthesisExpression(Expression<?> e) {
		expression = e;
	}
	
	public Expression<?> getExpression() {
		return expression;
	}
	
	public Type getExpressionEvaluate(Unit unit) {
		return getExpression().evaluate(unit);
	}
	
	private final Expression<?> expression;
	
	@Override
	public Type evaluate(Unit unit) {
		return getExpressionEvaluate(unit);
	}
	
	@Override
	public String toString(){
		return "("+getExpression().toString()+")";
	}

}
