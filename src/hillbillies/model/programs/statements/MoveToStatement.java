package hillbillies.model.programs.statements;

import hillbillies.model.Counter;
import hillbillies.model.Cube;
import hillbillies.model.Task;
import hillbillies.model.programs.expressions.ICubeExpression;

public class MoveToStatement extends ActionStatement {
	
	private MoveToStatement(ICubeExpression cube, boolean hasBeenFullyExecuted) {
		super(false, hasBeenFullyExecuted);
		this.cube = cube;
	}
	
	public MoveToStatement(ICubeExpression cube) {
		this(cube, false);
	}
	
	public ICubeExpression getCube() {
		return cube;
	}
	
	private final ICubeExpression cube;

	@Override
	public void execute(Task task, Counter counter) {
		counter.increment();
		task.getUnit().moveTo((Cube) getCube().evaluate(task).getValue());
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
	public MoveToStatement clone() {
		return new MoveToStatement(getCube(), hasBeenFullyExecuted());
	}

//	public MoveToStatement(CubeExpression c) {
//		this.cube = c;
//	}
//
//	public CubeExpression getCube() {
//		return cube;
//	}
//
//	private CubeExpression cube;
//
//	public void execute() {
//		if (getTask().getUnit() == null) {
//			throw new NullPointerException("this task has no unit");
//		}
//		getTask().getUnit().moveTo((Cube) getCube().evaluate(getTask()).getValue());
//	}

}
