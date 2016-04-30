package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;
import hillbillies.model.programs.type.Type;

public abstract class Expression<T extends Type> {

	public abstract T evaluate(Unit unit);

}
