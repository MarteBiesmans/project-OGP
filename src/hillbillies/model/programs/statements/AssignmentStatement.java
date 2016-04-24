package hillbillies.model.programs.statements;

import hillbillies.model.Counter;
import hillbillies.model.Unit;
import hillbillies.model.programs.expressions.Expression;
import worms.model.Worm;
import worms.model.programs.statements.AssignmentStatement;
import worms.model.programs.type.Type;

public class AssignmentStatement extends Statement {

	private AssignmentStatement(String variableName, Expression rhs, boolean hasFullyExecuted) {
		super(hasFullyExecuted);
		this.variableName = variableName;
		this.expression = rhs;	
	}

	public AssignmentStatement(String variableName, Expression rhs) {
		this(variableName, rhs, false);
	}

	@Override
	public void execute(Unit unit, Counter counter) {
		counter.increment();
		unit.getProgram().setGlobal(getVariableName(), new Type(getExpression().evaluate(unit)));
		SetHasFullyExecutedToTrue();
	}

	@Override
	public boolean canExecute(Unit unit, Counter counter) {
		counter.increment();		
		if (hasBeenFullyExecuted() || counter.getCount()>1000)
			return false;
		return true;
	}

	public String getVariableName() {
		return variableName;
	}

	private final String variableName;

	public Expression getExpression() {
		return expression;
	}

	private final Expression expression;

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
