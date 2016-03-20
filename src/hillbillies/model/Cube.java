package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * @Value
 * @author Marthe
 *
 */
@Value
public class Cube {
	
	public static final double SIDE_LENGTH = 1;

	@Raw
	public Cube(int x, int y, int z) throws IllegalArgumentException {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Position getCenter() {
		return new Position(SIDE_LENGTH/2, SIDE_LENGTH/2, SIDE_LENGTH/2, this);
	}
	
	@Basic @Raw
	public int getX() {
		return this.x;
	}
	
	@Basic @Raw
	public int getY() {
		return this.y;
	}

	@Basic @Raw
	public int getZ() {
		return this.z;
	}
	
	private int x;
	private int y;
	private int z;
	
}
