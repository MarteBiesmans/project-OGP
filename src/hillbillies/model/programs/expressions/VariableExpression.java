package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;

public class VariableExpression extends Expression {

	public VariableExpression(Object o) {
		object = o;
	}
	
	@Override
	public Object evaluate(Unit unit) {
		return object;
	}
	
	private final Object object;

}
