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
	
	@Override
	public void reset(Task task) {
		task.removeGlobalVariable(getVariableName());
		super.reset(task);
	}

}
