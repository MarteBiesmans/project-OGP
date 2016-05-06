package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.model.programs.type.CubeType;

public class HereExpression extends CubeExpression {

	public HereExpression() {
	}
	
	public CubeType evaluate(Unit unit) {
		return evaluate(unit, null);
	}
	
	@Override
	public CubeType evaluate(Unit unit, Cube cube) {
		return new CubeType(unit.getCube());
	}
	
	@Override
	public String toString(){
		return "here";
	}

}
