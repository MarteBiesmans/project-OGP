package hillbillies.model.programs.expressions;

import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.model.programs.type.BooleanType;
import hillbillies.model.programs.type.Type;

public class IsFriendExpression extends BooleanExpression {

	public IsFriendExpression(UnitExpression e) {
		expression = e;
	}
	
	public UnitExpression getExpression() {
		return expression;
	}
	
	public Type getExpressionEvaluate(Task task) {
		return getExpression().evaluate(task);
	}
	
	private final UnitExpression expression;

	@Override
	public BooleanType evaluate(Task task) {
		return new BooleanType(((Unit) getExpressionEvaluate(task).getValue()).getFaction() == task.getUnit().getFaction());
	}

	@Override
	public String toString(){
		return getExpression().toString() + "isFriend";
	}
}
