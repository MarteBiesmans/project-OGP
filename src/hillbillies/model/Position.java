package hillbillies.model;

public class Position extends Vector<Double> {

	private Cube cube;

	public Position(double x, double y, double z) {
		super(new Double(x), new Double(y), new Double(z));
		this.cube = null;
	}
	
	public Position(double x, double y, double z, Cube cube) {
		super(new Double(x), new Double(y), new Double(z));
		this.cube = cube;
	}

	public Position(Double x, Double y, Double z) {
		super(x, y, z);
		this.cube = null;
	}
	
	public Position(Double x, Double y, Double z, Cube cube) {
		super(x, y, z);
		this.cube = cube;
	}

	public Cube getCube() {
		return this.cube;
	}

	@Override
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

	@Override
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

	@Override
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
	
	public void toCube() {
		if (this.cube == null) {
			Cube cube = new Cube((int) (this.getXValue() / Cube.SIDE_LENGTH),
					(int) (this.getYValue() / Cube.SIDE_LENGTH),
					(int) (this.getZValue() / Cube.SIDE_LENGTH));
			
			this.cube = cube;
			this.setXValue(this.getXValue() % Cube.SIDE_LENGTH);
			this.setYValue(this.getYValue() % Cube.SIDE_LENGTH);
			this.setZValue(this.getZValue() % Cube.SIDE_LENGTH);
		}
	}
}
