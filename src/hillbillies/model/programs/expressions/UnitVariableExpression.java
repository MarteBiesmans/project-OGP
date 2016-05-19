package hillbillies.model.programs.expressions;

import hillbillies.model.programs.type.UnitType;

public class UnitVariableExpression extends VariableExpression<UnitType> implements IUnitExpression {

	public UnitVariableExpression(String name) {
		super(name);
	}

}
