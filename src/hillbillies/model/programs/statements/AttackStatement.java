package hillbillies.model.programs.statements;

import hillbillies.model.Counter;
import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.model.programs.expressions.UnitExpression;

public class AttackStatement extends ActionStatement {
	
	private AttackStatement(boolean hasBeenFullyExecuted) {
		super(false, hasBeenFullyExecuted);
	}
	
	public AttackStatement() {
		this(false);
	}

	@Override
	public void execute(Task task, Counter counter) {
		counter.increment();
//		TODO: getActionHandler().move(worm);
		SetHasFullyExecutedToTrue();
	}

	@Override
	public boolean canExecute(Task task, Counter counter) {
		counter.increment();
		if (counter.getCount() > 1000 || hasBeenFullyExecuted()) {
			return false;
		}
		return true;
//		TODO: weghalen return worm.canMove();
	}

	@Override
	public AttackStatement clone() {
		return new AttackStatement(hasBeenFullyExecuted());
	}

//	public AttackStatement(UnitExpression expression) {
//		this.unit = expression;
//	}
//	
//	public UnitExpression getUnit() {
//		return unit;
//	}
//	
//	private UnitExpression unit;
//	
//	@Override
//	public void execute() {
//		if(getTask().getUnit() == null){
//			throw new NullPointerException("this task has no unit");
//		}
//		getTask().getUnit().attack((Unit) getUnit().evaluate(getTask()).getValue());
//	}
	
}
