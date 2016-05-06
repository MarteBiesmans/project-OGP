package hillbillies.model.programs.statements;

import hillbillies.model.Counter;
import hillbillies.model.Cube;
import hillbillies.model.Unit;

public abstract class Statement {

	protected Statement(boolean hasfullyexecuted) {
		this.hasfullyexecuted = hasfullyexecuted;
	}
	
	protected Statement() {
		this(false);
	}
	
	/**
	 * Naming with 'fully' to avoid confusion since execute() doesn't per se
	 * make this attribute true
	 */
	private boolean hasfullyexecuted;

	public boolean hasBeenFullyExecuted() {
		return hasfullyexecuted;
	}
	
	public void SetHasFullyExecutedToTrue() {
		hasfullyexecuted = true;
	}

	public abstract void execute(Unit unit, Cube cube, Counter counter);
	
	public abstract boolean canExecute(Unit unit, Cube cube, Counter counter);
	
	public abstract boolean isWellFormed();
	
	public abstract boolean containsActionStatement();
	
	public abstract Statement clone();

}
