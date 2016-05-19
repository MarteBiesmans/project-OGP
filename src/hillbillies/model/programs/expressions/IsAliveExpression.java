package hillbillies.model.programs.expressions;

import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.model.programs.type.BooleanType;
import hillbillies.model.programs.type.UnitType;

public class IsAliveExpression extends BooleanExpression {
	
	public IsAliveExpression(IUnitExpression e) {
		expression = e;
	}
	
	public IUnitExpression getExpression() {
		return expression;
	}
	
	public UnitType getExpressionEvaluate(Task task) {
		return getExpression().evaluate(task);
	}
	
	private final IUnitExpression expression;
	
	@Override
	public BooleanType evaluate(Task task) {
		return new BooleanType(!((Unit) getExpressionEvaluate(task).getValue()).isDead());
	}
	
	@Override
	public String toString(){
		return getExpression().toString()+"isAlive";
	}

}
