package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;
import hillbillies.model.programs.type.BooleanType;

public class FalseExpression extends BooleanExpression {

	public FalseExpression() {
		super();
	}

	@Override
	public BooleanType evaluate(Unit unit) {
		return new BooleanType(false);
	}
	
	@Override
	public String toString() {
		return "false";
	}

}
