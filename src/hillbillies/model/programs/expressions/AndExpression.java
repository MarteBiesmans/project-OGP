package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;
import hillbillies.model.programs.type.BooleanType;
import hillbillies.model.programs.type.Type;

public class AndExpression extends BooleanExpression {

	public AndExpression(BooleanExpression e1, BooleanExpression e2) {
		expression1 = e1;
		expression2 = e2;
	}
	
	public Expression<Type> getFirstExpression() {
		return expression1;
	}
	
	public Expression<Type> getSecondExpression() {
		return expression2;
	}
	
	public Object getSecondExpressionEvaluate(Unit unit) {
		return expression2.evaluate(unit);
	}
	
	public Object getFirstExpressionEvaluate(Unit unit) {
		return expression1.evaluate(unit);
	}
	
	private final Expression<Type> expression1;
	private final Expression<Type> expression2;

	@Override
	public BooleanType evaluate(Unit unit) {
		return new BooleanType((boolean) getFirstExpressionEvaluate(unit) && 
				(boolean) getSecondExpressionEvaluate(unit));
	}

	@Override
	public String toString(){
		return getFirstExpression().toString() + "AND" + getSecondExpression().toString();
	}

}
