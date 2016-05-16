package hillbillies.model.programs.statements;

import hillbillies.model.programs.expressions.Expression;

public class AssignmentStatement extends Statement {

	public AssignmentStatement(String variableName, Object variableType, Expression<?> valueExpression) {
		setVariableName(variableName);
		setVariableType(variableType); //TODO: is type bijhouden wel nodig?
		setValueExpression(valueExpression);
		try {
			if (getVariableType().getClass() != getValueExpression().evaluate(getTask()).getClass()) {
				throw new IllegalArgumentException("Value is not of the given type.");
			}
		} catch (Exception e) {
		}
	}
	
	@Override
	public void reset() {
		getTask().removeGlobalVariable(getVariableName());
		super.reset();
	}

	@Override
	public void execute() {
		getTask().setGlobalVariable(getVariableName(), getValueExpression().evaluate(getTask()));
		setCompleted(true);
	}

	private String getVariableName() {
		return variableName;
	}

	private void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	private String variableName;

	private Object getVariableType() {
		return variableType;
	}

	private void setVariableType(Object variableType) {
		this.variableType = variableType;
	}

	private Object variableType;

	private Expression<?> getValueExpression() {
		return valueExpression;
	}

	public void setValueExpression(Expression<?> valueExpression) {
		this.valueExpression = valueExpression;
	}

	private Expression<?> valueExpression;

	@Override
	public boolean isMutable() {
		return true;
	}

}
