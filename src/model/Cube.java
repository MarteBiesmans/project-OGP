package model;

public class Cube extends Vector<Integer> {

	public static final int X_MIN = 0;
	public static final int Y_MIN = 0;
	public static final int Z_MIN = 0;
	public static final int X_MAX = 50;
	public static final int Y_MAX = 50;
	public static final int Z_MAX = 50;
	
	public static final double SIDE_LENGTH = 1;

	
	public Cube(int x, int y, int z) {
		super(new Integer(x), new Integer(y), new Integer(z));
	}
	
	public Cube(Integer x, Integer y, Integer z) {
		super(x, y, z);
	}

	@Override
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

	@Override
	public boolean isValidY(Integer y) {
		if (y >= Y_MIN || y < Y_MAX) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getYValue() {
		return this.getX().intValue();
	}
	
	public void setYValue(int y) {
		this.setX(new Integer(y));
	}

	@Override
	public boolean isValidZ(Integer z) {
		if (z >= Z_MIN || z < Z_MAX) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getZValue() {
		return this.getX().intValue();
	}
	
	public void setZValue(int z) {
		this.setX(new Integer(z));
	}

}
