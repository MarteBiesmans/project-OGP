package hillbillies.model.programs.statements;

import hillbillies.model.Counter;
import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.model.programs.expressions.Expression;
import hillbillies.model.programs.type.BooleanType;
import hillbillies.model.programs.type.Type;

public class AssignmentStatement extends Statement {
	
	private AssignmentStatement(String variableName, Expression<?> value, boolean hasFullyExecuted) {
		super(hasFullyExecuted);
		this.variableName = variableName;
		this.expression = value;	
	}

	public AssignmentStatement(String variableName, Expression<?> value) {
		this(variableName, value, false);
	}

	@Override
	public void execute(Unit unit, Cube cube, Counter counter) {
		counter.increment();
		unit.getProgram().setGlobal(getVariableName(), new Type(getExpression().evaluate(unit, cube)));
		SetHasFullyExecutedToTrue();
	}

	@Override
	public boolean canExecute(Unit unit, Cube cube, Counter counter) {
		counter.increment();		
		if (hasBeenFullyExecuted() || counter.getCount()>1000)
			return false;
		return true;
	}

	public String getVariableName() {
		return variableName;
	}

	private final String variableName;

	public Expression<?> getExpression() {
		return expression;
	}

	private final Expression<?> expression;

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

}
