package hillbillies.model.programs.expressions;

import hillbillies.model.Task;

public abstract class Expression<T> {

	public abstract T evaluate(Task task);

}
