package hillbillies.model.programs.expressions;

import hillbillies.model.Task;
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

	public UnitType getExpressionEvaluate(Task task) {
		return getExpression().evaluate(task);
	}

	private final UnitExpression expression;
	

	@Override
	public BooleanType evaluate(Task task) {
		return new BooleanType(((Unit) getExpressionEvaluate(task).getValue()).getNbMaterials() != 0);
	}

	@Override
	public String toString() {
		return getExpression().toString() + "carriesItem";
	}

}
