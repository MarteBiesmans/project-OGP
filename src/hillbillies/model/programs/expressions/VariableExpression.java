package hillbillies.model.programs.expressions;

public class VariableExpression extends Expression {

	protected VariableExpression(Object o) {
		object = o;
	}
	
	@Override
	public Boolean evaluate() {
		return false;
	}
	
	private final Object object;

}
