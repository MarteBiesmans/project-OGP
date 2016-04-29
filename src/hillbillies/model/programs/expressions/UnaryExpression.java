package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;

public abstract class UnaryExpression extends BooleanExpression {

	protected UnaryExpression(Expression e) {
		expression = e;
	}
	
	public Expression getExpression() {
		return expression;
	}
	
	public Object getExpressionEvaluate(Unit unit) {
		return (Boolean) getExpression().evaluate(unit);
	}
	
	private final Expression expression;

}
