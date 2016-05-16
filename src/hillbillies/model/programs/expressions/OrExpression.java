package hillbillies.model.programs.expressions;

import hillbillies.model.Task;
import hillbillies.model.programs.type.BooleanType;

public class OrExpression extends BooleanExpression {

	public OrExpression(BooleanExpression e1, BooleanExpression e2) {
		expression1 = e1;
		expression2 = e2;
	}
	
	public BooleanExpression getFirstExpression() {
		return expression1;
	}
	
	public BooleanExpression getSecondExpression() {
		return expression2;
	}
	
	public BooleanType getSecondExpressionEvaluate(Task task) {
		return getSecondExpression().evaluate(task);
	}
	
	public BooleanType getFirstExpressionEvaluate(Task task) {
		return getFirstExpression().evaluate(task);
	}
	
	private final BooleanExpression expression1;
	private final BooleanExpression expression2;

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
