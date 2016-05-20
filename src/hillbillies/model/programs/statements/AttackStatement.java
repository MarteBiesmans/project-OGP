package hillbillies.model.programs.statements;

import hillbillies.model.Counter;
import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.model.programs.expressions.IUnitExpression;

public class AttackStatement extends ActionStatement {
	
	private AttackStatement(IUnitExpression unit, boolean hasBeenFullyExecuted) {
		super(false, hasBeenFullyExecuted);
		this.unit = unit;
	}
	
	public AttackStatement(IUnitExpression unit) {
		this(unit, false);
	}
	
	public IUnitExpression getUnit() {
		return unit;
	}
	
	private final IUnitExpression unit;

	@Override
	public void execute(Task task, Counter counter) {
		counter.increment();
		task.getUnit().attack((Unit) getUnit().evaluate(task).getValue());
	}

	@Override
	public AttackStatement clone() {
		return new AttackStatement(getUnit(), hasBeenFullyExecuted());
	}


//	
//	@Override
//	public void execute() {
//		if(getTask().getUnit() == null){
//			throw new NullPointerException("this task has no unit");
//		}
//		getTask().getUnit().attack((Unit) getUnit().evaluate(getTask()).getValue());
//	}
	
}
