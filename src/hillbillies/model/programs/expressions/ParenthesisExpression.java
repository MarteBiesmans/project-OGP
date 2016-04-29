package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;

public class ParenthesisExpression extends UnaryExpression {

	public ParenthesisExpression(Expression e) {
		super(e);
	}
	
	@Override
	public Boolean evaluate(Unit unit) {
		return (getExpressionEvaluate(unit));
	}
	
	@Override
	public String toString(){
		return "("+getExpression().toString()+")";
	}

}
