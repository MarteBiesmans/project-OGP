package hillbillies.model.programs.expressions;

import hillbillies.model.Task;
import hillbillies.model.programs.type.BooleanType;

public class NotExpression extends BooleanExpression {

	public NotExpression(IBooleanExpression e) {
		expression = e;
	}
	
	public IBooleanExpression getExpression() {
		return expression;
	}
	
	public BooleanType getExpressionEvaluate(Task task) {
		return getExpression().evaluate(task);
	}
	
	private final IBooleanExpression expression;
	
	@Override
	public BooleanType evaluate(Task task) {
		return new BooleanType(! ((boolean) getExpressionEvaluate(task).getValue()));
	}
	
	@Override
	public String toString(){
		return "NOT" + getExpression().toString();
	}
}
