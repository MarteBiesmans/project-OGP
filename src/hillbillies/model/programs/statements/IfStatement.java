package hillbillies.model.programs.statements;

import java.util.HashSet;

import hillbillies.model.Task;
import hillbillies.model.programs.expressions.BooleanExpression;
import hillbillies.model.Counter;

public class IfStatement extends Statement {
	
	
	private IfStatement(BooleanExpression condition, Statement then, Statement otherwise, boolean hasBeenExecutedOnce, boolean hasBeenFullyExecuted, boolean solvedCondition) {
		super(hasBeenFullyExecuted);
		this.condition = condition;
		this.then = then;
		then.setParentStatement(this);
		this.otherwise = otherwise;
		if (otherwise != null)
			otherwise.setParentStatement(this);
		this.setHasBeenExecutedOnce(hasBeenExecutedOnce);
		this.setSolvedCondition(solvedCondition);
	}

	public IfStatement(BooleanExpression condition, Statement then, Statement otherwise){
		this(condition, then, otherwise, false, false, false);
	}
	
	private BooleanExpression getCondition() {
		return this.condition;
	}
	
	private final BooleanExpression condition;
	
	private Statement getThen() {
		return this.then;
	}
	
	private final Statement then;
	
	private Statement getOtherwise() {
		return this.otherwise;
	}
	
	private final Statement otherwise;
	
	private boolean getSolvedCondition() {
		return this.solvedCondition;
	}
	
	private void setSolvedCondition(boolean condition) {
		this.solvedCondition = condition;
	}
	
	private boolean solvedCondition;
	
	private boolean getHasBeenExecutedOnce() {
		return this.hasBeenExecutedOnce;
	}
	
	private void setHasBeenExecutedOnce(boolean executedOnce) {
		this.hasBeenExecutedOnce = executedOnce;
	}
	
	private boolean hasBeenExecutedOnce;

	@Override
	public void execute(Task task, Counter counter) {
		counter.increment();
		if(!getHasBeenExecutedOnce()){
			setSolvedCondition((boolean) getCondition().evaluate(task).getValue());
			setHasBeenExecutedOnce(true);
		}
		if(!(getThen().hasBeenFullyExecuted() || getOtherwise().hasBeenFullyExecuted())){
			if(getSolvedCondition()){
				getThen().execute(task, counter);
				return;
			}
			else{
				getOtherwise().execute(task, counter);
				return;
			}
		}
		else
			this.SetHasFullyExecutedToTrue();
	}

	@Override
	public boolean canExecute(Task task, Counter counter) {
		counter.increment();		
		if (hasBeenFullyExecuted() || counter.getCount()>1000)
			return false;

		if (!getHasBeenExecutedOnce()){
			if((boolean) getCondition().evaluate(task).getValue())
				return getThen().canExecute(task, counter);
			else
				return getOtherwise().canExecute(task, counter);
		}
		if(!(getThen().hasBeenFullyExecuted() || getOtherwise().hasBeenFullyExecuted())){
			if(getSolvedCondition()){
				return getThen().canExecute(task, counter);
			}
			if(!getSolvedCondition())
				return getOtherwise().canExecute(task, counter);
		}
		return true;
	}

	@Override
	public boolean isWellFormed() {
		return getThen().isWellFormed() && getOtherwise().isWellFormed();
	}

	@Override
	public boolean containsActionStatement() {
		if (this.getOtherwise() == null) 
			return getThen().containsActionStatement();
		else 
		return getThen().containsActionStatement() && getOtherwise().containsActionStatement();
	}

	@Override
	public IfStatement clone() {
		return new IfStatement(getCondition(), getThen().clone(), getOtherwise().clone(), getHasBeenExecutedOnce(), hasBeenFullyExecuted(), getSolvedCondition());
	}
	
	public HashSet<Statement> getDirectChildStatements() {
	HashSet<Statement> children = new HashSet<Statement>();
	children.add(getThen());
	if (getOtherwise() != null)
		children.add(getOtherwise());
	return children;
}

//	public IfStatement(BooleanExpression condition, Statement ifBody, Statement elseBody) {
//		setCondition(condition);
//		setIfBody(ifBody);
//		setElseBody(elseBody);
//		setConditionChecked(false);
//	}
//	
//	@Override
//	public Statement getExecutingStatement() {
//		if (getConditionOutcome())
//			return getIfBody().getExecutingStatement();
//		else if (getElseBody() != null)
//			return getElseBody().getExecutingStatement();
//		return this; //TODO: this should never happen, right?..
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
//	private boolean getConditionOutcome() {
//		return conditionOutcome;
//	}
//
//	private void setConditionOutcome(boolean conditionOutcome) {
//		this.conditionOutcome = conditionOutcome;
//	}
//
//	private boolean conditionOutcome;
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
//	public Statement getIfBody() {
//		return ifBody;
//	}
//
//	public void setIfBody(Statement ifBody) {
//		this.ifBody = ifBody;
//		this.getIfBody().setParentStatement(this);
//	}
//
//	private Statement ifBody;
//
//	public Statement getElseBody() {
//		return elseBody;
//	}
//
//	public void setElseBody(Statement elseBody) {
//		this.elseBody = elseBody;
//		if (elseBody != null) {
//			elseBody.setParentStatement(this);
//		}
//	}
//
//	private Statement elseBody;
//
//	public void execute() {
//		if (!isConditionChecked()) {
//			setConditionOutcome((boolean) getCondition().evaluate(getTask()).getValue());
//			setConditionChecked(true);
//			if (!isConditionOutcome() && getElseBody() == null)
//				this.setCompleted(true);
//		} else {
//			if (isConditionOutcome()) {
//				getIfBody().execute();
//				if (getIfBody().isCompleted()) {
//					this.setCompleted(true);
//				}
//			} else {
//				if (getElseBody() != null) {
//					getElseBody().execute();
//					if (getElseBody().isCompleted())
//						this.setCompleted(true);
//				} else
//					this.setCompleted(true);
//			}
//		}
//
//	}
//	
//	@Override
//	public void reset() {
//		setConditionChecked(false);
//		super.reset();
//	}
//	

//
//	@Override
//	public boolean isMutable() {
//		return true;
//	}
//	
//	public boolean isWellFormed(){
//		return (getIfBody().isWellFormed() && (getElseBody() == null || getElseBody().isWellFormed()));
//	}
	
}