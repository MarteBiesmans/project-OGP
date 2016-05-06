package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.model.programs.type.UnitType;

public class ThisExpression extends UnitExpression {

	public ThisExpression() {
	}
	
	public UnitType evaluate(Unit unit) {
		return evaluate(unit, null);
	}
	
	@Override
	public UnitType evaluate(Unit unit, Cube cube) {
		return new UnitType(unit);
	}
	
	@Override
	public String toString(){
		return "this";
	}

}
