package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
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
	
	public Type getExpressionEvaluate(Unit unit, Cube cube) {
		return getExpression().evaluate(unit, cube);
	}
	
	private final UnitExpression expression;

	@Override
	public BooleanType evaluate(Unit unit, Cube cube) {
		return new BooleanType(((Unit) getExpressionEvaluate(unit, cube).getValue()).getFaction() == unit.getFaction());
	}

	@Override
	public String toString(){
		return getExpression().toString() + "isFriend";
	}
}
