package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;

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
	
	public Object getSecondExpressionEvaluate(Unit unit) {
		return expression2.evaluate(unit);
	}
	
	public Object getFirstExpressionEvaluate(Unit unit) {
		return expression1.evaluate(unit);
	}
	
	private final Expression expression1;
	private final Expression expression2;
	
	
	@Override
	public abstract Boolean evaluate(Unit unit);

}
