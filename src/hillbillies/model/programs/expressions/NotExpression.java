package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;

public class NotExpression extends UnaryExpression {

	public NotExpression(BooleanExpression e) {
		super(e);
	}
	
	@Override
	public Boolean evaluate(Unit unit) {
		return (! ((Boolean) getExpressionEvaluate(unit)));
	}
	
	@Override
	public String toString(){
		return "NOT"+getExpression().toString();
	}
}
