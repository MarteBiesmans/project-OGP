package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * @Value
 * @author Marthe
 *
 */
@Value
public class Cube {

	public static final int X_MIN = 0;
	public static final int Y_MIN = 0;
	public static final int Z_MIN = 0;
	public static final int X_MAX = 50;
	public static final int Y_MAX = 50;
	public static final int Z_MAX = 50;
	
	public static final double SIDE_LENGTH = 1;

	@Raw
	public Cube(int x, int y, int z) throws IllegalArgumentException {
		if (! isValidCube(x, y, z))
			throw new IllegalArgumentException();
		else {
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
	
	public boolean isValidCube(int x, int y,int z) {
		return (isValidX(x) && isValidY(y) && isValidZ(z));
	}
	
	public Position getCenter() {
		return new Position(SIDE_LENGTH/2, SIDE_LENGTH/2, SIDE_LENGTH/2, this);
	}
	
	public boolean isValidX(int x) {
		if (x >= X_MIN &&  x < X_MAX)
			return true;
		else
			return false;
	}
	
	@Basic @Raw
	public int getX() {
		return this.x;
	}
	
	private int x;
	
	public boolean isValidY(int y) {
		if (y >= Y_MIN && y < Y_MAX) {
			return true;
		} else {
			return false;
		}
	}
	
	@Basic @Raw
	public int getY() {
		return this.y;
	}
	
	private int y;
	
	public boolean isValidZ(int z) {
		if (z >= Z_MIN && z < Z_MAX) {
			return true;
		} else {
			return false;
		}
	}
	
	@Basic @Raw
	public int getZ() {
		return this.z;
	}
	
	private int z;
	
}
