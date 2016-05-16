package hillbillies.model.programs.expressions;

import hillbillies.model.Task;

public class VariableExpression extends Expression<Object> {

	public VariableExpression(String name) {
		variableName = name;
	}
	
	private String variableName;	

	@Override
	public Object evaluate(Task task) {
		return task.getUnit().getFaction().getScheduler().getGlobalVariable(variableName);
	}
	
	@Override
	public String toString(){
		return variableName;
	}

}
