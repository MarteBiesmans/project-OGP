package hillbillies.model.programs.statements;

import hillbillies.model.Counter;
import hillbillies.model.Cube;
import hillbillies.model.Task;
import hillbillies.model.programs.expressions.CubeExpression;

public class WorkStatement extends ActionStatement {

	private WorkStatement(boolean hasBeenFullyExecuted) {
		super(false, hasBeenFullyExecuted);
	}
	
	public WorkStatement() {
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
	public WorkStatement clone() {
		return new WorkStatement(hasBeenFullyExecuted());
	}
	
//	public WorkStatement(CubeExpression expression) {
//		this.cube = expression;
//	}
//	
//	public CubeExpression getCube() {
//		return cube;
//	}
//
//	private CubeExpression cube;
//
//	public void execute() {
//		if(getTask().getUnit() == null){
//			throw new NullPointerException("this task has no unit");
//		}
//		getTask().getUnit().workAt((Cube) getCube().evaluate(getTask()).getValue());
//	}

}
