package hillbillies.model.programs.expressions;

import hillbillies.model.Task;
import hillbillies.model.programs.type.Type;

public abstract class Expression<T extends Type> implements IExpression<T> {

	public abstract T evaluate(Task task);

}
