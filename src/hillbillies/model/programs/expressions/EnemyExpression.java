package hillbillies.model.programs.expressions;

import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.model.programs.type.UnitType;

public class EnemyExpression extends UnitExpression {

	public EnemyExpression() {
	}

	@Override
	public UnitType evaluate(Task task) {
		Unit nearestEnemySoFar = null;
		for (Unit other : task.getUnit().getWorld().getAllUnits()) {
			if (other != task.getUnit() && task.getUnit().getFaction() != other.getFaction()) {
				if (nearestEnemySoFar == null)
					nearestEnemySoFar = other;
				else if (task.getUnit().getPosition().getDistanceSquare(other.getPosition()) < task.getUnit().getPosition()
						.getDistanceSquare(nearestEnemySoFar.getPosition()))
					nearestEnemySoFar = other;
			}
		}
		return new UnitType(nearestEnemySoFar);
	}

	@Override
	public String toString() {
		return "enemy";
	}

}
