package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;
import hillbillies.model.programs.type.UnitType;

public class ThisExpression extends UnitExpression {

	public ThisExpression() {
	}
	
	@Override
	public UnitType evaluate(Unit unit) {
		return new UnitType(unit);
	}
	
	@Override
	public String toString(){
		return "this";
	}

}
