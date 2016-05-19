package hillbillies.model.programs.statements;

import hillbillies.model.Counter;
import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.model.programs.expressions.UnitExpression;

public class FollowStatement extends ActionStatement {
	
	private FollowStatement(boolean hasBeenFullyExecuted) {
		super(false, hasBeenFullyExecuted);
	}
	
	public FollowStatement(IUnitExpression unit) {
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
	public FollowStatement clone() {
		return new FollowStatement(hasBeenFullyExecuted());
	}

//	public FollowStatement(UnitExpression expression) {
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
//		getTask().getUnit().follow(((Unit) getUnit().evaluate(getTask()).getValue()));
//	}
	
}
