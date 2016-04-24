package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;

public abstract class CubePositionExpression extends Expression {

	protected CubePositionExpression(Object o) {
		object = o;
	}
	
	public Object getObject() {
		return this.object;
	}
	
	public abstract Cube evaluate();
	
	private final Object object;

}
