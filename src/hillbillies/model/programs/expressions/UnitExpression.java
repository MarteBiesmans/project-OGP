package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;
import hillbillies.model.programs.type.UnitType;

public abstract class UnitExpression extends Expression<UnitType> {
	
	@Override
	public abstract UnitType evaluate(Unit unit);


}
