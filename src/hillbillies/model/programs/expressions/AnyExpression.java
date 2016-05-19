package hillbillies.model.programs.expressions;

import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.model.programs.type.UnitType;

public class AnyExpression extends UnitExpression {

	public AnyExpression() {
	}

	@Override
	public UnitType evaluate(Task task) {		
		try {
			return new UnitType((Unit) task.getUnit().getWorld().getAllUnits().stream()
					.map(unit -> new UnitDistPair((Unit) unit, task.getUnit()))
					.reduce((x,y) -> x.getMinimum(y)).get().getUnit());
		} catch (NullPointerException exc) {
			return null;
		}
	}
	
	private class UnitDistPair {

		private final Double distance;
		private Unit unit;

		private UnitDistPair(Unit other, Unit self) {
			this.unit = other;
			this.distance = self.getPosition().getDistanceSquare(other.getPosition());
		}

		private Double getDistance() {
			return distance;
		}

		private Unit getUnit() {
			return unit;
		}

		private UnitDistPair getMinimum(UnitDistPair other) {
			if (this.getDistance() <= other.getDistance())
				return this;
			else
				return other;
		}

	}

	@Override
	public String toString() {
		return "any";
	}

}
