package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.Task;
import hillbillies.model.programs.type.CubeType;

public class WorkshopExpression extends CubeExpression {

	public WorkshopExpression() {
	}

	@Override
	public CubeType evaluate(Task task) {
		Cube nearestWorkshopSoFar = null;
		for (Cube workshop : task.getUnit().getWorld().getAllWorkshops()) {
				if (nearestWorkshopSoFar == null)
					nearestWorkshopSoFar = workshop;
				else if (task.getUnit().getPosition().getDistanceSquare(workshop.getCenter()) < task.getUnit().getPosition()
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
