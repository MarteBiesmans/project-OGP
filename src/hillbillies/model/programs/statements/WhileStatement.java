package hillbillies.model.programs.statements;

import java.util.HashSet;

import hillbillies.model.Counter;
import hillbillies.model.Task;
import hillbillies.model.programs.expressions.BooleanExpression;

public class WhileStatement extends Statement {
	
	private WhileStatement(Statement body, Statement bodyCopy, BooleanExpression condition, boolean hasBeenFullyExecuted) {
		super(hasBeenFullyExecuted);
		this.condition = condition;
		this.bodyCopy = bodyCopy.clone();
		this.setBody(body.clone());
	}	
	
	public WhileStatement(Statement statement, BooleanExpression condition) {
		this(statement, statement, condition, false);
		this.body.SetHasFullyExecutedToTrue();
	}
	
	private Statement getBody() {
		return this.body;
	}
	
	private void setBody(Statement body) {
		this.body = body;
		body.setParentStatement(this);
	}
	
	private Statement body;
	
	private Statement getBodyCopy() {
		return this.bodyCopy;
	}
	
	private final Statement bodyCopy;
	
	private BooleanExpression getCondition() {
		return this.condition;
	}
	
	private final BooleanExpression condition;
	
	@Override
	public void execute(Task task, Counter counter) {
		counter.increment();
		if (!getBody().hasBeenFullyExecuted()) {
			getBody().execute(task, counter);
			return;
		}
		if ((boolean) getCondition().evaluate(task).getValue()) {
			this.setBody(this.bodyCopy.clone());
			getBody().execute(task, counter);
			return;
		}
		SetHasFullyExecutedToTrue();
		
	}

	@Override
	public boolean canExecute(Task task, Counter counter) {
		counter.increment();
		if (counter.getCount() > 1000 || hasBeenFullyExecuted()) {
			return false;
		}
		if (!getBody().hasBeenFullyExecuted())
			return getBody().canExecute(task, counter);
		if ((boolean) getCondition().evaluate(task).getValue()) {
			return getBodyCopy().canExecute(task, counter);
		}
		// body has finished and condition is false. Next time this statement is executed it
		// will be set to fully executed.
		return true;
	}

	@Override
	public boolean isWellFormed() {
		return getBody().isWellFormed();
	}

	@Override
	public boolean containsActionStatement() {
		return getBody().containsActionStatement();
	}

	@Override
	public WhileStatement clone() {
		return new WhileStatement(getBody().clone(), getBodyCopy().clone(), condition, hasBeenFullyExecuted());
	}

//	public WhileStatement(BooleanExpression condition, Statement body) {
//		setCondition(condition);
//		setBody(body);
//	}
//	
//	@Override
//	public Statement getExecutingStatement() {
//		return getBody().getExecutingStatement();
//	}
//	
//	public BooleanExpression getCondition() {
//		return condition;
//	}
//	
//	public void setCondition(BooleanExpression condition) {
//		this.condition = condition;
//	}
//	
//	private BooleanExpression condition;
//	
//	private boolean isConditionChecked() {
//		return conditionChecked;
//	}
//	
//	private void setConditionChecked(boolean conditionChecked) {
//		this.conditionChecked = conditionChecked;
//	}
//	
//	private boolean conditionChecked;
//	
//	public Statement getBody() {
//		return body;
//	}
//	
//	public void setBody(Statement body) {
//		this.body = body;
//		body.setParentStatement(this);
//	}
//
//	private Statement body;
//	
//	@Override
//	public void execute() {
//		if(!isConditionChecked()){
//			if((boolean) getCondition().evaluate(getTask()).getValue()){
//				setConditionChecked(true);
//			}else{
//				setCompleted(true);
//			}
//		}else{
//			getBody().execute();
//			if(getBody().isCompleted()){
//				reset();
//			}
//		}
//	}
//	
//	@Override
//	public void reset() {
//		setConditionChecked(false);
//		super.reset();
//	}
//	
//	public HashSet<Statement> getDirectChildStatements() {
//		HashSet<Statement> children = new HashSet<Statement>();
//		children.add(getBody());
//		return children;
//	}
//
//	@Override
//	public boolean isMutable() {
//		return true;
//	}
//	
//	public boolean isWellFormed(){
//		return getBody().isWellFormed();
//	}

}
