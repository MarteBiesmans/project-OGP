package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.model.programs.type.UnitType;

public class FriendExpression extends UnitExpression {

	public FriendExpression() {
	}
	
	public UnitType evaluate(Unit unit) {
		return evaluate(unit, null);
	}

	@Override
	public UnitType evaluate(Unit unit, Cube cube) {
		Unit nearestFriendSoFar = null;
		for (Unit other : unit.getWorld().getAllUnits()) {
			if (other != unit && unit.getFaction() == other.getFaction()) {
				if (nearestFriendSoFar == null)
					nearestFriendSoFar = other;
				else if (unit.getPosition().getDistanceSquare(other.getPosition()) < unit.getPosition()
						.getDistanceSquare(nearestFriendSoFar.getPosition()))
					nearestFriendSoFar = other;
			}
		}
		return new UnitType(nearestFriendSoFar);
	}

	@Override
	public String toString() {
		return "friend";
	}

}
