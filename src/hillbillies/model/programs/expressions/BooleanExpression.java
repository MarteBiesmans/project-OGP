package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;
import hillbillies.model.programs.type.BooleanType;

public abstract class BooleanExpression extends Expression<BooleanType> {

	@Override
	public abstract BooleanType evaluate(Unit unit);

}



