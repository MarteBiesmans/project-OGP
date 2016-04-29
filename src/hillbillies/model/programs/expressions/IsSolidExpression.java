package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;
import hillbillies.model.Cube;

public class IsSolidExpression extends UnaryBooleanExpression {

	public IsSolidExpression(CubeExpression e) {
		super(e);
	}
	
	@Override
	public Boolean evaluate(Unit unit) {
		return (!((Cube) getExpressionEvaluate(unit)).isPassableIn(unit.getWorld()));
	}
	
	@Override
	public String toString(){
		return getExpression().toString()+"isSolid";
	}

}
