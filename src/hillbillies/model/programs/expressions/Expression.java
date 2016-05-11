package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;

public abstract class Expression<T> {

	public abstract T evaluate(Unit unit, Cube cube);

}
