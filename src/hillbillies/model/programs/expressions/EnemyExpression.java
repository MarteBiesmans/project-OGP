package hillbillies.model.programs.expressions;

import hillbillies.model.Unit;

public class EnemyExpression extends UnitExpression {

	public EnemyExpression() {
		super();
	}

	@Override
	public Unit evaluate(Unit unit) {
		Unit nearestEnemySoFar = null;
		for (Unit other : unit.getWorld().getAllUnits()) {
			if (other != unit && unit.getFaction() != other.getFaction()) {
				if (nearestEnemySoFar == null)
					nearestEnemySoFar = other;
				else if (unit.getPosition().getDistanceSquare(other.getPosition()) < unit.getPosition()
						.getDistanceSquare(nearestEnemySoFar.getPosition()))
					nearestEnemySoFar = other;
			}
		}
		return nearestEnemySoFar;
	}

	@Override
	public String toString() {
		return "enemy";
	}

}
