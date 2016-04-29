package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;

public abstract class CubeExpression extends Expression {

	@Override
	public abstract Cube evaluate(Unit unit);

}
