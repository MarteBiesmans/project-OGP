package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;

public class ThisExpression extends UnitExpression {

	public ThisExpression() {
		super();
	}
	
	@Override
	public Unit evaluate(Unit unit) {
		return unit;
	}
	
	@Override
	public String toString(){
		return "this";
	}

}
