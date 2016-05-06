package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.model.programs.type.Type;

public class VariableExpression extends Expression<Type> {

	public VariableExpression(Type o) {
		object = o;
	}

	public Type evaluate() {
		return evaluate(null, null);
	}

	@Override
	public Type evaluate(Unit unit, Cube cube) {
		return object;
	}

	private final Type object;

}
