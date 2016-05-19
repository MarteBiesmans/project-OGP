package hillbillies.model.programs.statements;

import hillbillies.model.Counter;
import hillbillies.model.Cube;
import hillbillies.model.Task;
import hillbillies.model.programs.expressions.CubeExpression;
import hillbillies.model.programs.expressions.ICubeExpression;

public class WorkStatement extends ActionStatement {
	
	private WorkStatement(ICubeExpression cube, boolean hasBeenFullyExecuted) {
		super(false, hasBeenFullyExecuted);
		this.cube = cube;
	}
	
	public WorkStatement(ICubeExpression cube) {
		this(cube, false);
	}
	
	public ICubeExpression getCube() {
		return cube;
	}
	
	private final ICubeExpression cube;

	@Override
	public void execute(Task task, Counter counter) {
		counter.increment();
		task.getUnit().workAt((Cube) getCube().evaluate(task).getValue());
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
		return new WorkStatement(getCube(), hasBeenFullyExecuted());
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
