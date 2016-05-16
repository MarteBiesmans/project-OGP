package hillbillies.model.programs.statements;

import java.util.HashSet;

import hillbillies.model.programs.expressions.BooleanExpression;

public class IfStatement extends Statement {

	public IfStatement(BooleanExpression condition, Statement ifBody, Statement elseBody) {
		setCondition(condition);
		setIfBody(ifBody);
		setElseBody(elseBody);
		setConditionChecked(false);
	}

	public BooleanExpression getCondition() {
		return condition;
	}

	public void setCondition(BooleanExpression condition) {
		this.condition = condition;
	}

	private BooleanExpression condition;

	private boolean isConditionOutcome() {
		return conditionOutcome;
	}

	private void setConditionOutcome(boolean conditionOutcome) {
		this.conditionOutcome = conditionOutcome;
	}

	private boolean conditionOutcome;

	private boolean isConditionChecked() {
		return conditionChecked;
	}

	private void setConditionChecked(boolean conditionChecked) {
		this.conditionChecked = conditionChecked;
	}

	private boolean conditionChecked;

	public Statement getIfBody() {
		return ifBody;
	}

	public void setIfBody(Statement ifBody) {
		this.ifBody = ifBody;
		this.getIfBody().setParentStatement(this);
	}

	private Statement ifBody;

	public Statement getElseBody() {
		return elseBody;
	}

	public void setElseBody(Statement elseBody) {
		this.elseBody = elseBody;
		if (elseBody != null) {
			elseBody.setParentStatement(this);
		}
	}

	private Statement elseBody;

	public void execute() {
		if (!isConditionChecked()) {
			setConditionOutcome((boolean) getCondition().evaluate(getTask()).getValue());
			setConditionChecked(true);
			if (!isConditionOutcome() && getElseBody() == null)
				this.setCompleted(true);
		} else {
			if (isConditionOutcome()) {
				getIfBody().execute();
				if (getIfBody().isCompleted()) {
					this.setCompleted(true);
				}
			} else {
				if (getElseBody() != null) {
					getElseBody().execute();
					if (getElseBody().isCompleted())
						this.setCompleted(true);
				} else
					this.setCompleted(true);
			}
		}

	}
	
	@Override
	public void reset() {
		setConditionChecked(false);
		super.reset();
	}
	
	public HashSet<Statement> getDirectChildStatements() {
		HashSet<Statement> children = new HashSet<Statement>();
		children.add(getIfBody());
		if (getElseBody() != null)
			children.add(getElseBody());
		return children;
	}

	@Override
	public boolean isMutable() {
		return true;
	}
	
	public boolean isWellFormed(){
		return (getIfBody().isWellFormed() && (getElseBody() == null || getElseBody().isWellFormed()));
	}
	
}