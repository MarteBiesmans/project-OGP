package hillbillies.model.programs.expressions;

public class OrExpression extends BinaryExpression {

	public OrExpression(BooleanExpression e1, BooleanExpression e2) {
		super(e1,e2);
	}

	@Override
	public Boolean evaluate() {
		return getFirstExpressionEvaluate() || getSecondExpressionEvaluate();
	}
	@Override
	public String toString(){
		return getFirstExpression().toString()+"OR"+getSecondExpression().toString();
	}

}
