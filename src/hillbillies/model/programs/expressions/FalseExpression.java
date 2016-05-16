package hillbillies.model.programs.expressions;

import hillbillies.model.Task;
import hillbillies.model.programs.type.BooleanType;

public class FalseExpression extends BooleanExpression {

	public FalseExpression() {
	}
	
	@Override
	public BooleanType evaluate(Task task) {
		return new BooleanType(false);
	}
	
	@Override
	public String toString() {
		return "false";
	}

}
