package hillbillies.model.programs.expressions;

public abstract class BinaryExpression extends BooleanExpression {

	public BinaryExpression(Expression e1, Expression e2) {
		expression1 = e1;
		expression2 = e2;
	}
	
	public Expression getFirstExpression() {
		return expression1;
	}
	
	public Expression getSecondExpression() {
		return expression2;
	}
	
	public Boolean getSecondExpressionEvaluate() {
		return (Boolean) expression2.evaluate();
	}
	
	public Boolean getFirstExpressionEvaluate() {
		return (Boolean) expression1.evaluate();
	}
	
	private final Expression expression1;
	private final Expression expression2;

}
