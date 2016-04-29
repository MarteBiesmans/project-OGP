package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;

public class FriendExpression extends UnitExpression {

	public FriendExpression() {
		super();
	}

	@Override
	public Unit evaluate(Unit unit) {
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
		return nearestFriendSoFar;
	}

	@Override
	public String toString() {
		return "friend";
	}

}
