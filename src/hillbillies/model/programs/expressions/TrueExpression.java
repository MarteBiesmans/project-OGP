package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;

public class TrueExpression extends NullaryBooleanExpression {

	public TrueExpression() {
		super();
	}

	@Override
	public Boolean evaluate(Unit unit) {
		return true;
	}
	
	@Override
	public String toString() {
		return "true";
	}

}
