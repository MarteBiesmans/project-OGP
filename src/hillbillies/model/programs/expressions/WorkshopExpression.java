package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.model.programs.type.CubeType;

public class WorkshopExpression extends CubeExpression {

	public WorkshopExpression() {
	}
	
	public CubeType evaluate(Unit unit) {
		return evaluate(unit, null);
	}

	@Override
	public CubeType evaluate(Unit unit, Cube cube) {
		Cube nearestWorkshopSoFar = null;
		for (Cube workshop : unit.getWorld().getAllWorkshops()) {
				if (nearestWorkshopSoFar == null)
					nearestWorkshopSoFar = workshop;
				else if (unit.getPosition().getDistanceSquare(workshop.getCenter()) < unit.getPosition()
						.getDistanceSquare(nearestWorkshopSoFar.getCenter()))
					nearestWorkshopSoFar = workshop;
		}
		return new CubeType(nearestWorkshopSoFar);
	}

	@Override
	public String toString() {
		return "workshop";
	}

}
