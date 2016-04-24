package hillbillies.model.programs.expressions;

import hillbillies.model.Boulder;
import hillbillies.model.Cube;
import hillbillies.model.Unit;

public class WorkshopExpression extends CubePositionExpression {

	public WorkshopExpression(Unit unit) {
		super(unit);
	}

	@Override
	public Cube evaluate() {
		Unit unit = (Unit) getObject();
		Cube nearestWorkshopSoFar = null;
		for (Cube workshop: unit.getWorld().getAllWorkshops()) {
			if (nearestWorkshopSoFar == null)
				nearestWorkshopSoFar = workshop;
			else if (unit.getPosition().getDistanceSquare(workshop.getCenter()) < unit.getPosition().getDistanceSquare(nearestWorkshopSoFar.getCenter())))
					nearestWorkshopSoFar = new Cube(workshop.getX(), workshop.getY(), workshop.getZ());
		}
		if (nearestWorkshopSoFar != null)
			return nearestWorkshopSoFar;
		else
			return unit.getCube();
	}

}
