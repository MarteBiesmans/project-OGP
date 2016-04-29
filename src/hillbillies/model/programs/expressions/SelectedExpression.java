package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;

public class SelectedExpression extends NullaryCubeExpression {

	public SelectedExpression() {
		super();
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
