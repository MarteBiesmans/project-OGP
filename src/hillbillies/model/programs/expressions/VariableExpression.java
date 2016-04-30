package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;
import hillbillies.model.programs.type.Type;

public class VariableExpression extends Expression<Type> {

	public VariableExpression(Type o) {
		object = o;
	}
	
	@Override
	public Type evaluate(Unit unit) {
		return object;
	}
	
	private final Type object;

}
