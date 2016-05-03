package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;
import hillbillies.model.programs.type.UnitType;

public class AnyExpression extends UnitExpression {

	public AnyExpression() {
	}

	@Override
	public UnitType evaluate(Unit unit) {
		Unit nearestUnitSoFar = null;
		for (Unit other : unit.getWorld().getAllUnits()) {
			if (other != unit) {
				if (nearestUnitSoFar == null)
					nearestUnitSoFar = other;
				else if (unit.getPosition().getDistanceSquare(other.getPosition()) < unit.getPosition()
						.getDistanceSquare(nearestUnitSoFar.getPosition()))
					nearestUnitSoFar = other;
			}
		}
		return new UnitType(nearestUnitSoFar);
	}

	@Override
	public String toString() {
		return "any";
	}

}
