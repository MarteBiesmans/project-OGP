package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.model.programs.type.BooleanType;

public class FalseExpression extends BooleanExpression {

	public FalseExpression() {
	}
	
	public BooleanType evaluate() {
		return evaluate(null, null);
	}
	
	@Override
	public BooleanType evaluate(Unit unit, Cube cube) {
		return new BooleanType(false);
	}
	
	@Override
	public String toString() {
		return "false";
	}

}
