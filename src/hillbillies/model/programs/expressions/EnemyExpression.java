package hillbillies.model.programs.expressions;

import hillbillies.model.DistPair;
import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.model.programs.type.UnitType;

public class EnemyExpression extends UnitExpression {

	public EnemyExpression() {
	}

	@Override
	public UnitType evaluate(Task task) {
		try {
			return new UnitType((Unit) task.getUnit().getWorld().getAllUnits()
					.stream()
					.filter(unit -> unit.getFaction() != task.getUnit().getFaction())
					.map(unit -> new DistPair<Unit>((Unit) unit, task.getUnit().getPosition().getDistanceSquare(unit.getPosition())))
					.reduce(DistPair<Unit>::getMinimum).get().getThing());
		} catch (NullPointerException exc) {
			return null;
		}
	}

	@Override
	public String toString() {
		return "enemy";
	}

}
