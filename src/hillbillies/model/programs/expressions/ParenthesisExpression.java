package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.model.programs.type.Type;

public class ParenthesisExpression extends Expression<Type> {

	public ParenthesisExpression(Expression<?> e) {
		expression = e;
	}
	
	public Expression<?> getExpression() {
		return expression;
	}
	
	public Type getExpressionEvaluate(Unit unit, Cube cube) {
		return getExpression().evaluate(unit, cube);
	}
	
	private final Expression<?> expression;
	
	@Override
	public Type evaluate(Unit unit, Cube cube) {
		return getExpressionEvaluate(unit, cube);
	}
	
	@Override
	public String toString(){
		return "("+getExpression().toString()+")";
	}

}
