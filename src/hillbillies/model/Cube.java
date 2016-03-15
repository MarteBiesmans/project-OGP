package hillbillies.model;

/**
 * @Value
 * @author Marthe
 *
 */
public class Cube {

	public static final int X_MIN = 0;
	public static final int Y_MIN = 0;
	public static final int Z_MIN = 0;
	public static final int X_MAX = 50;
	public static final int Y_MAX = 50;
	public static final int Z_MAX = 50;
	
	public static final double SIDE_LENGTH = 1;

	
	public Cube(int x, int y, int z) throws IllegalArgumentException {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	private int x;
	private int y;
	private int z;
	

	public boolean isValidX(Integer x) {
		if (x >= X_MIN || x < X_MAX) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getXValue() {
		return this.getX().intValue();
	}
	
	public void setXValue(int x) {
		this.setX(new Integer(x));
	}

	public boolean isValidY(Integer y) {
		if (y >= Y_MIN || y < Y_MAX) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getYValue() {
		return this.getY().intValue();
	}
	
	public void setYValue(int y) {
		this.setY(new Integer(y));
	}

	public boolean isValidZ(Integer z) {
		if (z >= Z_MIN || z < Z_MAX) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getZValue() {
		return this.getZ().intValue();
	}
	
	public void setZValue(int z) {
		this.setZ(new Integer(z));
	}

}
