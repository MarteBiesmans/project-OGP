package hillbillies.model.programs.statements;

import hillbillies.model.Counter;
import hillbillies.model.Task;
import hillbillies.model.programs.expressions.IExpression;
import hillbillies.model.programs.type.Type;

public class AssignmentStatement extends Statement {
	
	private AssignmentStatement(String variableName, IExpression<?> value, boolean hasBeenFullyExecuted) {
		super(hasBeenFullyExecuted);
		this.variableName = variableName;
		this.expression = value;	
	}

	public AssignmentStatement(String variableName, IExpression<?> value) {
		this(variableName, value, false);
	}

	@Override
	public void execute(Task task, Counter counter) {
		counter.increment();
		task.setGlobalVariable(getVariableName(), new Type(getExpression().evaluate(task)));
		SetHasFullyExecutedToTrue();
	}

	@Override
	public boolean canExecute(Task task, Counter counter) {
		if (hasBeenFullyExecuted() || counter.getCount() + 1 > counter.getMaxValue())
			return false;
		return true;
	}

	public String getVariableName() {
		return variableName;
	}

	private final String variableName;

	public IExpression<?> getExpression() {
		return expression;
	}

	private final IExpression<?> expression;

	@Override
	public boolean isWellFormed() {
		return true;
	}

	@Override
	public boolean containsActionStatement() {
		return false;
	}
	
	@Override
	public AssignmentStatement clone() {
		return new AssignmentStatement(variableName, expression, hasBeenFullyExecuted());
	}

//	public AssignmentStatement(String variableName, Expression<?> valueExpression) {
//		setVariableName(variableName);
//		setVariableType(valueExpression.evaluate(getTask()).getClass());
//		setValueExpression(valueExpression);
//	}
//	
//	@Override
//	public void reset() {
//		getTask().removeGlobalVariable(getVariableName());
//		super.reset();
//	}
//
//	@Override
//	public void execute() {
//		getTask().setGlobalVariable(getVariableName(), getValueExpression().evaluate(getTask()));
//		setCompleted(true);
//	}
//
//	private String getVariableName() {
//		return variableName;
//	}
//
//	private void setVariableName(String variableName) {
//		this.variableName = variableName;
//	}
//
//	private String variableName;
//
//	private Object getVariableType() {
//		return variableType;
//	}
//
//	private void setVariableType(Object variableType) {
//		this.variableType = variableType;
//	}
//
//	private Object variableType;
//
//	private Expression<?> getValueExpression() {
//		return valueExpression;
//	}
//
//	public void setValueExpression(Expression<?> valueExpression) {
//		this.valueExpression = valueExpression;
//	}
//
//	private Expression<?> valueExpression;
//
//	@Override
//	public boolean isMutable() {
//		return true;
//	}

}
