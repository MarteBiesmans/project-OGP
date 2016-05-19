package hillbillies.model.programs.expressions;

import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.model.programs.type.UnitType;

public class FriendExpression extends UnitExpression {

	public FriendExpression() {
	}

	@Override
	public UnitType evaluate(Task task) {
		Unit nearestFriendSoFar = null;
		for (Unit other : task.getUnit().getWorld().getAllUnits()) {
			if (other != task.getUnit() && task.getUnit().getFaction() == other.getFaction()) {
				if (nearestFriendSoFar == null)
					nearestFriendSoFar = other;
				else if (task.getUnit().getPosition().getDistanceSquare(other.getPosition()) < task.getUnit().getPosition()
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
