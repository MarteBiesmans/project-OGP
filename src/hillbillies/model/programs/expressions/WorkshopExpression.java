package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;

public class WorkshopExpression extends CubeExpression {

	public WorkshopExpression() {
	}

	@Override
	public Cube evaluate(Unit unit) {
		Cube nearestWorkshopSoFar = null;
		for (Cube workshop : unit.getWorld().getAllWorkshops()) {
				if (nearestWorkshopSoFar == null)
					nearestWorkshopSoFar = workshop;
				else if (unit.getPosition().getDistanceSquare(workshop.getCenter()) < unit.getPosition()
						.getDistanceSquare(nearestWorkshopSoFar.getCenter()))
					nearestWorkshopSoFar = workshop;
		}
		return nearestWorkshopSoFar;
	}

	@Override
	public String toString() {
		return "";
	}

}
