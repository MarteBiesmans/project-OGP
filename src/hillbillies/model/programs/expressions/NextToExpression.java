package hillbillies.model.programs.expressions;

import hillbillies.model.Cube;
import hillbillies.model.World;

public class NextToExpression extends CubePositionExpression {

	public NextToExpression(Cube c, World w) {
		super(c);
		this.world = w;
	}

	@Override
	public Cube evaluate() {
		for (Cube cube: ((Cube) getObject()).getAllNeighbouringCubes(world)) {
			if (cube.isPassableIn(world))
				return cube;
		}
		return (Cube) getObject();
	}
	
	private World world;

}
