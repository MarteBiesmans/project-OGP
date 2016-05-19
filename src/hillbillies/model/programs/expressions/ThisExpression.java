package hillbillies.model.programs.expressions;

import hillbillies.model.Task;
import hillbillies.model.programs.type.UnitType;

public class ThisExpression extends UnitExpression {

	public ThisExpression() {
	}
	
	@Override
	public UnitType evaluate(Task task) {
		return new UnitType(task.getUnit());
	}
	
	@Override
	public String toString(){
		return "this";
	}

}
