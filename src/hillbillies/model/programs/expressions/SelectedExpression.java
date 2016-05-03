package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;
import hillbillies.model.programs.type.CubeType;

public class SelectedExpression extends CubeExpression {

	public SelectedExpression() {
	}
	
	@Override
	public CubeType evaluate(Unit unit) {
		return new CubeType(null);
	}
	
	@Override
	public String toString(){
		return "";
	}

}
