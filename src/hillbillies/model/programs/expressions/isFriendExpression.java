package hillbillies.model.programs.expressions;

import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.model.programs.type.BooleanType;
import hillbillies.model.programs.type.Type;

public class IsFriendExpression extends BooleanExpression {

	public IsFriendExpression(IUnitExpression e) {
		expression = e;
	}
	
	public IUnitExpression getExpression() {
		return expression;
	}
	
	public Type getExpressionEvaluate(Task task) {
		return getExpression().evaluate(task);
	}
	
	private final IUnitExpression expression;

	@Override
	public BooleanType evaluate(Task task) {
		return new BooleanType(((Unit) getExpressionEvaluate(task).getValue()).getFaction() == task.getUnit().getFaction());
	}

	@Override
	public String toString(){
		return getExpression().toString() + "isFriend";
	}
}
