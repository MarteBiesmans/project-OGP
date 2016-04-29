package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;

public class HereExpression extends NullaryCubeExpression {

	public HereExpression() {
		super();
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
