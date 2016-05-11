package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;

public class VariableExpression extends Expression<Object> {

	public VariableExpression(Object o) {
		object = o;
	}

	public Object evaluate() {
		return evaluate(null, null);
	}

	@Override
	public Object evaluate(Unit unit, Cube cube) {
		return object;
	}

	private final Object object;

}
