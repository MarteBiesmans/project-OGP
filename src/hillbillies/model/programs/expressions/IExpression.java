package hillbillies.model.programs.expressions;

import hillbillies.model.Task;
import hillbillies.model.programs.type.Type;

public interface IExpression<T extends Type> {
	
	public abstract T evaluate(Task task);

}
