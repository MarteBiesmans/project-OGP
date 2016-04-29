package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;

public class NextToExpression extends UnaryCubeExpression {

	public NextToExpression(BooleanExpression e) {
		super(e);
	}
	
	@Override
	public Cube evaluate(Unit unit) {
		return null;
	}
	
	@Override
	public String toString(){
		return "";
	}

}
