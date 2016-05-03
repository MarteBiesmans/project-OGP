package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;
import hillbillies.model.programs.type.CubeType;

public class HereExpression extends CubeExpression {

	public HereExpression() {
	}
	
	@Override
	public CubeType evaluate(Unit unit) {
		return new CubeType(unit.getCube());
	}
	
	@Override
	public String toString(){
		return "here";
	}

}
