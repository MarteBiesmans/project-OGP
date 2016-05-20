package hillbillies.model.programs.statements;

import hillbillies.model.Counter;
import hillbillies.model.Cube;
import hillbillies.model.Task;
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
	public WorkStatement clone() {
		return new WorkStatement(getCube(), hasBeenFullyExecuted());
	}

}
