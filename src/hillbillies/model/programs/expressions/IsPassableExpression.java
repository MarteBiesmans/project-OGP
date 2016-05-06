package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.model.programs.type.BooleanType;
import hillbillies.model.programs.type.CubeType;

public class IsPassableExpression extends BooleanExpression {

	public IsPassableExpression(CubeExpression e) {
		expression = e;
	}
	
	public CubeExpression getExpression() {
		return expression;
	}
	
	public CubeType getExpressionEvaluate(Unit unit, Cube cube) {
		return getExpression().evaluate(unit, cube);
	}
	
	private final CubeExpression expression;
	
	@Override
	public BooleanType evaluate(Unit unit, Cube cube) {
		return new BooleanType(((Cube) getExpressionEvaluate(unit, cube).getValue()).isPassableIn(unit.getWorld()));
	}
	
	@Override
	public String toString(){
		return getExpression().toString()+"isPassable";
	}

}
