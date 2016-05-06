package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.model.programs.type.UnitType;

public class EnemyExpression extends UnitExpression {

	public EnemyExpression() {
	}
	
	public UnitType evaluate(Unit unit) {
		return evaluate(unit, null);
	}

	@Override
	public UnitType evaluate(Unit unit, Cube cube) {
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
		return new UnitType(nearestEnemySoFar);
	}

	@Override
	public String toString() {
		return "enemy";
	}

}
