package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;

public class ParenthesisExpression extends Expression<Object> {

	public ParenthesisExpression(Expression<?> e) {
		expression = e;
	}
	
	public Expression<?> getExpression() {
		return expression;
	}
	
	public Object getExpressionEvaluate(Unit unit, Cube cube) {
		return getExpression().evaluate(unit, cube);
	}
	
	private final Expression<?> expression;
	
	@Override
	public Object evaluate(Unit unit, Cube cube) {
		return getExpressionEvaluate(unit, cube);
	}
	
	@Override
	public String toString(){
		return "("+getExpression().toString()+")";
	}

}
