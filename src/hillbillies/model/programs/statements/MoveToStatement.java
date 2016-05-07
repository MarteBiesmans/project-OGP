package hillbillies.model.programs.statements;

import hillbillies.model.Counter;
import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.model.programs.expressions.CubeExpression;

public class MoveToStatement extends ActionStatement {
	
	private MoveToStatement(CubeExpression c, boolean hasBeenFullyExecuted) {
		super(hasBeenFullyExecuted);
		this.cube = c;
	}
	
	public MoveToStatement(CubeExpression c) {
		this(c, false);
	}
	
	private CubeExpression cube;

	@Override
	public void execute(Unit unit, Cube cube, Counter counter) {
		counter.increment();
		getActionHandler().move(worm);
		SetHasFullyExecutedToTrue();
	}

	@Override
	public boolean canExecute(Unit unit, Cube cube, Counter counter) {
		counter.increment();
		if (counter.getCount() > 1000 || hasBeenFullyExecuted()) {
			return false;
		}
		return worm.canMove();
	}

	@Override
	public MoveStatement clone() {
		return new MoveStatement(getActionHandler(), hasBeenFullyExecuted());
	}

	

}
