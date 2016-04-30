package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;

public class HereExpression extends CubeExpression {

	public HereExpression() {
	}
	
	@Override
	public Cube evaluate(Unit unit) {
		return unit.getCube();
	}
	
	@Override
	public String toString(){
		return "here";
	}

}
