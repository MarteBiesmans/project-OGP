package hillbillies.model.programs.statements;

import hillbillies.model.Counter;
import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.model.programs.expressions.CubeExpression;
import hillbillies.model.programs.expressions.UnitExpression;

public class FollowStatement extends ActionStatement {

	public FollowStatement(UnitExpression expression) {
		this.unit = expression;
	}
	
	public UnitExpression getUnit() {
		return unit;
	}
	
	private UnitExpression unit;
	
	@Override
	public void execute() {
		if(getTask().getUnit() == null){
			throw new NullPointerException("this task has no unit");
		}
		getTask().getUnit().follow(getUnit().evaluate().getValue());
		this.setCompleted(true);
	}
	
}
