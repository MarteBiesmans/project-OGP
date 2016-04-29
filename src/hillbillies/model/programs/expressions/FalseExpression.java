package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;

public class FalseExpression extends NullaryBooleanExpression {

	public FalseExpression() {
		super();
	}

	@Override
	public Boolean evaluate(Unit unit) {
		return false;
	}
	
	@Override
	public String toString() {
		return "false";
	}

}
