package hillbillies.model;

/**
 * @Value
 * @author Marte & Ellen
 *
 */
public class Position {


	public Position(double x, double y, double z) {
		Cube cube = new Cube((int) (x / Cube.SIDE_LENGTH),
								(int) (y / Cube.SIDE_LENGTH),
								(int) (z / Cube.SIDE_LENGTH));
		
		this.setCube(cube);
		this.setXValue(x % Cube.SIDE_LENGTH);
		this.setYValue(y % Cube.SIDE_LENGTH);
		this.setZValue(z % Cube.SIDE_LENGTH);
	}
	
	public Position(double x, double y, double z, Cube cube) {
		this.setXValue(x);
		this.setYValue(y);
		this.setZValue(z);
		this.setCube(cube);
	}
	
	public boolean isValidPosition() {
		return (this.cube.)
		
		return ((this.getRealX()<=Cube.X_MAX) && (this.getRealX()>=Cube.X_MIN) &&
				(this.getRealY()<=Cube.Y_MAX) && (this.getRealY()>=Cube.Y_MIN) &&
				(this.getRealZ()<=Cube.Z_MAX) && (this.getRealZ()>=Cube.Z_MIN));
	
	public boolean isValidCube() {
		
	}
	
	public void setCube() {
		
	}
	
	private Cube cube;
	

	}
	
	public boolean isValidX(Double x) {
		if (this.getCube() != null) {
			if (x >= 0 || x < Cube.SIDE_LENGTH) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	public double getXValue() {
		return this.getX().doubleValue();
	}

	public double getRealX() {
		if (this.getCube() != null) {
			return this.getCube().getXValue() * Cube.SIDE_LENGTH + this.getXValue();
		} else {
			return this.getXValue();
		}
	}

	public void setXValue(double x) {
		this.setX(new Double(x));
	}

	public boolean isValidY(Double y) {
		if (this.getCube() != null) {
			if (y >= 0 || y < Cube.SIDE_LENGTH) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	public double getYValue() {
		return this.getY().doubleValue();
	}

	public double getRealY() {
		if (this.getCube() != null) {
			return this.getCube().getYValue() * Cube.SIDE_LENGTH + this.getYValue();
		} else {
			return this.getYValue();
		}
	}

	public void setYValue(double y) {
		this.setY(new Double(y));
	}

	public boolean isValidZ(Double z) {
		if (this.getCube() != null) {
			if (z >= 0 || z < Cube.SIDE_LENGTH) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	public double getZValue() {
		return this.getZ().doubleValue();
	}

	public double getRealZ() {
		if (this.getCube() != null) {
			return this.getCube().getZValue() * Cube.SIDE_LENGTH + this.getZValue();
		} else {
			return this.getZValue();
		}
	}

	public void setZValue(double z) {
		this.setZ(new Double(z));
	}
	
	public Position min(Position other) {
		return new Position(this.getRealX() - other.getRealX(),
				this.getRealY() - other.getRealY(),
				this.getRealZ() - other.getRealZ());
	}
}
