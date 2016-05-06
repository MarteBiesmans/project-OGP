package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.model.programs.type.BooleanType;
import hillbillies.model.programs.type.UnitType;

public class CarriesItemExpression extends BooleanExpression {

	public CarriesItemExpression(UnitExpression e) {
		expression = e;
	}

	public UnitExpression getExpression() {
		return expression;
	}

	public UnitType getExpressionEvaluate(Unit unit, Cube cube) {
		return getExpression().evaluate(unit, cube);
	}

	private final UnitExpression expression;
	
	public BooleanType evaluate() {
		return evaluate(null, null);
	}

	@Override
	public BooleanType evaluate(Unit unit, Cube cube) {
		return new BooleanType(((Unit) getExpressionEvaluate(unit, cube).getValue()).getNbMaterials() != 0);
	}

	@Override
	public String toString() {
		return getExpression().toString() + "carriesItem";
	}

}
