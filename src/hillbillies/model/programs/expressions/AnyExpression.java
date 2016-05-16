package hillbillies.model.programs.expressions;

import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.model.programs.type.UnitType;

public class AnyExpression extends UnitExpression {

	public AnyExpression() {
	}

	@Override
	public UnitType evaluate(Task task) {
		Unit nearestUnitSoFar = null;
		for (Unit other : task.getUnit().getWorld().getAllUnits()) {
			if (other != task.getUnit()) {
				if (nearestUnitSoFar == null)
					nearestUnitSoFar = other;
				else if (task.getUnit().getPosition().getDistanceSquare(other.getPosition()) < task.getUnit().getPosition()
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
