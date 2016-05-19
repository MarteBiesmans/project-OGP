package hillbillies.model.programs.expressions;

import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.model.programs.type.CubeType;
import hillbillies.model.programs.type.UnitType;

public class PositionOfExpression extends CubeExpression {

	public PositionOfExpression(IUnitExpression e) {
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
	public CubeType evaluate(Task task) {
		return new CubeType(((Unit) getExpressionEvaluate(task).getValue()).getCube());
	}
	
	@Override
	public String toString(){
		return "positionOf" + getExpression().toString();
	}

}
