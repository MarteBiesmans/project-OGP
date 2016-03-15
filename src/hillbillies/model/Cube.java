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
		if ((! isValidX(x)) && (! isValidY(y)) && (! isValidZ(z)))
			throw new IllegalArgumentException();
		else {
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
	
	private int x;
	private int y;
	private int z;
	
	public boolean isValidCube() {
		return (isValidX(this.getX()) && isValidY(this.getY()) && isValidZ(this.z));
	}
	
	public boolean isValidX(int x) {
		if (x >= X_MIN || x < X_MAX)
			return true;
		else
			return false;
	}
	
	@Basic @Raw
	public int getX() {
		return this.x;
	}
	
	public boolean isValidY(int y) {
		if (y >= Y_MIN || y < Y_MAX) {
			return true;
		} else {
			return false;
		}
	}
	
	@Basic @Raw
	public int getY() {
		return this.y;
	}
	
	public boolean isValidZ(int z) {
		if (z >= Z_MIN || z < Z_MAX) {
			return true;
		} else {
			return false;
		}
	}
	
	@Basic @Raw
	public int getZ() {
		return this.z;
	}

}
