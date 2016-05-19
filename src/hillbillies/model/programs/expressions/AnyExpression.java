package hillbillies.model.programs.expressions;

import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.model.programs.type.UnitType;
import hillbillies.model.DistPair;

public class AnyExpression extends UnitExpression {

	public AnyExpression() {
	}

	@Override
	public UnitType evaluate(Task task) {		
		try {
			return new UnitType((Unit) task.getUnit().getWorld().getAllUnits()
					.stream()
					.map(unit -> new DistPair<Unit>((Unit) unit, task.getUnit().getPosition().getDistanceSquare(unit.getPosition())))
					.reduce(DistPair<Unit>::getMinimum).get().getThing());
		} catch (NullPointerException exc) {
			return null;
		}
	}
	
	@Override
	public String toString() {
		return "any";
	}

}
