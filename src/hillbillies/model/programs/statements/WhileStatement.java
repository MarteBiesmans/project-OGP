package hillbillies.model.programs.statements;

import java.util.HashSet;

import hillbillies.model.Counter;
import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.model.programs.expressions.BooleanExpression;

public class WhileStatement extends Statement {

	public WhileStatement(BooleanExpression condition, Statement body) {
		setCondition(condition);
		setBody(body);
	}
	
	public BooleanExpression getCondition() {
		return condition;
	}
	
	public void setCondition(BooleanExpression condition) {
		this.condition = condition;
	}
	
	private BooleanExpression condition;
	
	private boolean isConditionChecked() {
		return conditionChecked;
	}
	
	private void setConditionChecked(boolean conditionChecked) {
		this.conditionChecked = conditionChecked;
	}
	
	private boolean conditionChecked;
	
	public Statement getBody() {
		return body;
	}
	
	public void setBody(Statement body) {
		this.body = body;
		body.setParentStatement(this);
	}

	private Statement body;
	
	@Override
	public void execute() {
		if(!isConditionChecked()){
			if(getCondition().evaluate().getValue()){
				setConditionChecked(true);
			}else{
				setCompleted(true);
			}
		}else{
			getBody().execute();
			if(getBody().isCompleted()){
				reset();
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
		children.add(getBody());
		return children;
	}

	@Override
	public boolean isMutable() {
		return true;
	}

}
