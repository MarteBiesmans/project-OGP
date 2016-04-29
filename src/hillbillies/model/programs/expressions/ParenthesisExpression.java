package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;

public class ParenthesisExpression extends UnaryBooleanExpression {

	public ParenthesisExpression(BooleanExpression e) {
		super(e);
	}
	
	@Override
	public Boolean evaluate(Unit unit) {
		return (Boolean) (getExpressionEvaluate(unit));
	}
	
	@Override
	public String toString(){
		return "("+getExpression().toString()+")";
	}

}
