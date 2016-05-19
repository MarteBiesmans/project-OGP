package hillbillies.model.programs.expressions;

import hillbillies.model.Task;
import hillbillies.model.programs.type.Type;

public class ParenthesisExpression<T extends Type> extends Expression<T> {

	public ParenthesisExpression(Expression<?> e) {
		expression = e;
	}
	
	public Expression<?> getExpression() {
		return expression;
	}
	
	@SuppressWarnings("unchecked")
	public T getExpressionEvaluate(Task task) {
		return (T) getExpression().evaluate(task);
	}
	
	private final Expression<?> expression;
	
	@Override
	public T evaluate(Task task) {
		return getExpressionEvaluate(task);
	}
	
	@Override
	public String toString(){
		return "("+getExpression().toString()+")";
	}

}
