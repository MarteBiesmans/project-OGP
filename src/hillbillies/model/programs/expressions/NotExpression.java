package hillbillies.model.programs.expressions;

public class NotExpression extends UnaryExpression {

	public NotExpression(BooleanExpression e1) {
		super(e1);
	}
	
	@Override
	public Boolean evaluate() {
		return ! getExpressionEvaluate();
	}
	
	@Override
	public String toString(){
		return "NOT"+getExpression().toString();
	}
}
