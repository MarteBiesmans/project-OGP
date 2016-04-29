package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;

public abstract class BooleanExpression extends Expression {

	@Override
	public abstract Boolean evaluate(Unit unit);

}



