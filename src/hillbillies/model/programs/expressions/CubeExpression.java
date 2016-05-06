package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.model.programs.type.CubeType;

public abstract class CubeExpression extends Expression<CubeType> {

	@Override
	public abstract CubeType evaluate(Unit unit, Cube cube);

}
