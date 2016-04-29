package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;

public abstract class UnitExpression extends Expression {

	@Override
	public abstract Unit evaluate(Unit unit);

}
