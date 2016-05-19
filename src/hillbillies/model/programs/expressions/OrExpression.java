package hillbillies.model.programs.expressions;

import hillbillies.model.Task;
import hillbillies.model.programs.type.BooleanType;

public class OrExpression extends BooleanExpression {

	public OrExpression(IBooleanExpression e1, IBooleanExpression e2) {
		expression1 = e1;
		expression2 = e2;
	}
	
	public IBooleanExpression getFirstExpression() {
		return expression1;
	}
	
	public IBooleanExpression getSecondExpression() {
		return expression2;
	}
	
	private final IBooleanExpression expression1;
	
	public BooleanType getFirstExpressionEvaluate(Task task) {
		return getFirstExpression().evaluate(task);
	}
	
	public BooleanType getSecondExpressionEvaluate(Task task) {
		return getSecondExpression().evaluate(task);
	}
	
	private final IBooleanExpression expression2;

	@Override
	public BooleanType evaluate(Task task) {
		return new BooleanType((boolean) getFirstExpressionEvaluate(task).getValue() || 
				(boolean) getSecondExpressionEvaluate(task).getValue());
	}

	@Override
	public String toString(){
		return getFirstExpression().toString() + "AND" + getSecondExpression().toString();
	}

}
