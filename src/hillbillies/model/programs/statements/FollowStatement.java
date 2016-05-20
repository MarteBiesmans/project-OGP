package hillbillies.model.programs.statements;

import hillbillies.model.Counter;
import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.model.programs.expressions.IUnitExpression;

public class FollowStatement extends ActionStatement {
	
	private FollowStatement(IUnitExpression unit, boolean hasBeenFullyExecuted) {
		super(false, hasBeenFullyExecuted);
		this.unit = unit;
	}
	
	public FollowStatement(IUnitExpression unit) {
		this(unit, false);
	}
	
	public IUnitExpression getUnit() {
		return unit;
	}
	
	private final IUnitExpression unit;


	@Override
	public void execute(Task task, Counter counter) {
		counter.increment();
		task.getUnit().follow((Unit) getUnit().evaluate(task).getValue());
	}

	@Override
	public FollowStatement clone() {
		return new FollowStatement(getUnit(), hasBeenFullyExecuted());
	}
	
}
