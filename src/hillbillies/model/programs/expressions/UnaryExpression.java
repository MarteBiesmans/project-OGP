package hillbillies.model.programs.expressions;

public abstract class UnaryExpression extends BooleanExpression {

	protected UnaryExpression(Expression e) {
		expression = e;
	}
	
	public Expression getExpression() {
		return expression;
	}
	
	public Boolean getExpressionEvaluate() {
		return (Boolean) getExpression().evaluate();
	}
	
	private final Expression expression;

}
