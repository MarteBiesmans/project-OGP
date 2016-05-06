package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.model.programs.type.BooleanType;

public abstract class BooleanExpression extends Expression<BooleanType> {

	@Override
	public abstract BooleanType evaluate(Unit unit, Cube cube);

}



