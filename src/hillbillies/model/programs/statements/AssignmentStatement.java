package hillbillies.model.programs.statements;

import hillbillies.model.Counter;
import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.model.programs.expressions.Expression;
import hillbillies.model.programs.type.BooleanType;
import hillbillies.model.programs.type.Type;

public class AssignmentStatement extends Statement {

	public AssignmentStatement(String variableName, Object variableType, Expression<?> valueExpression) {
		setVariableName(variableName);
		setVariableType(variableType);
		setValueExpression(valueExpression);
		try {
			if (getVariableType().getClass() != getValueExpression().evaluate().getClass()) {
				throw new IllegalArgumentException("Value is not of the given type.");
			}
		} catch (Exception e) {
		}
	}

	@Override
	public void execute() {
		getTask().setGlobalVariable(getVariableName(), getValueExpression().evaluate());
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
