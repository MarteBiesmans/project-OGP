package hillbillies.model.programs.expressions;

import hillbillies.model.Task;

public class ParenthesisExpression extends Expression<Object> {

	public ParenthesisExpression(Expression<?> e) {
		expression = e;
	}
	
	public Expression<?> getExpression() {
		return expression;
	}
	
	public Object getExpressionEvaluate(Task task) {
		return getExpression().evaluate(task);
	}
	
	private final Expression<?> expression;
	
	@Override
	public Object evaluate(Task task) {
		return getExpressionEvaluate(task);
	}
	
	@Override
	public String toString(){
		return "("+getExpression().toString()+")";
	}

}
