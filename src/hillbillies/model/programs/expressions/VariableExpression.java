package hillbillies.model.programs.expressions;

import hillbillies.model.Task;
import hillbillies.model.programs.type.Type;

public class VariableExpression<T extends Type> extends Expression<T> {

	public VariableExpression(String name) {
		variableName = name;
	}
	
	private String variableName;	

	@Override
	public T evaluate(Task task) {
		return (T) task.getGlobalVariable(variableName);
	}
	
	@Override
	public String toString(){
		return variableName;
	}

}
