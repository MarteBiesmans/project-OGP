package hillbillies.model.programs.expressions;

import hillbillies.model.Task;
import hillbillies.model.programs.type.BooleanType;

public class TrueExpression extends BooleanExpression {

	public TrueExpression() {
	}
	
	@Override
	public BooleanType evaluate(Task task) {
		return new BooleanType(true);
	}
	
	@Override
	public String toString() {
		return "true";
	}

}
