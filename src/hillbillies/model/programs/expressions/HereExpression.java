package hillbillies.model.programs.expressions;

import hillbillies.model.Task;
import hillbillies.model.programs.type.CubeType;

public class HereExpression extends CubeExpression {

	public HereExpression() {
	}
	
	@Override
	public CubeType evaluate(Task task) {
		return new CubeType(task.getUnit().getCube());
	}
	
	@Override
	public String toString(){
		return "here";
	}

}
