package hillbillies.model.programs.statements;

import java.util.ArrayList;
import java.util.List;

import hillbillies.model.Counter;
import hillbillies.model.Unit;

public class SequenceOfStatements extends Statement {

	private SequenceOfStatements(List<Statement> statements, boolean hasBeenFullyExecuted) {
		super(hasBeenFullyExecuted);
		this.listofstatements = statements;
	}
	
	public SequenceOfStatements(List<Statement> statements) {
		this(statements, false);
	}

	private final List<Statement> listofstatements;
	
	@Override
	public void execute(Unit unit, Counter counter) {
		counter.increment();
		for (Statement statement:  listofstatements) {
			if (!statement.hasBeenFullyExecuted()) {
				statement.execute(unit, counter);
				return;
			}
		}
		SetHasFullyExecutedToTrue();
	}


	@Override
	public boolean canExecute(Unit unit, Counter counter) {
		counter.increment();
		if (hasBeenFullyExecuted() || counter.getCount() > 1000)
			return false;
		for (Statement statement: listofstatements) {
			if (!statement.hasBeenFullyExecuted() ) {
				return statement.canExecute(unit, counter);
			}
		}
		return true;
	}


	@Override
	public boolean isWellFormed() {
		for (Statement statement: listofstatements) {
			if(!statement.isWellFormed())
				return false;
		}
		return true;
	}


	@Override
	public boolean containsActionStatement() {
		for (Statement statement: listofstatements) {
			if(statement.containsActionStatement())
				return true;
		}
		return false;
	}

	@Override
	public SequenceOfStatements clone() {
		List<Statement> clonedlist = new ArrayList<Statement>();
		for(Statement statement: listofstatements) {
			clonedlist.add(statement.clone());
		}
		return new SequenceOfStatements(clonedlist , hasBeenFullyExecuted());
	}

}
