package hillbillies.model;

import java.util.Iterator;

import be.kuleuven.cs.som.annotate.*;

/**
 * A value class describing a position. The position consists of a cube and a
 * position in this cube.
 * 
 * @Value
 * @author Marte & Ellen
 *
 */
@Value
public class Position {

	public Position(double x, double y, double z) throws IllegalArgumentException {
		Cube cube = new Cube((int) (x / Cube.SIDE_LENGTH), (int) (y / Cube.SIDE_LENGTH), (int) (z / Cube.SIDE_LENGTH));
		x %= Cube.SIDE_LENGTH;
		y %= Cube.SIDE_LENGTH;
		z %= Cube.SIDE_LENGTH;

		this.cube = cube;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Position(double x, double y, double z, Cube cube) throws IllegalArgumentException, NullPointerException {
		if (cube == null)
			throw new NullPointerException();

		this.cube = new Cube((int) (cube.getX() + (x / Cube.SIDE_LENGTH)), (int) (cube.getY() + (y / Cube.SIDE_LENGTH)),
				(int) (cube.getZ() + (z / Cube.SIDE_LENGTH)));

		x %= Cube.SIDE_LENGTH;
		y %= Cube.SIDE_LENGTH;
		z %= Cube.SIDE_LENGTH;

		if (x < 0)
			x += Cube.SIDE_LENGTH;
		if (y < 0)
			y += Cube.SIDE_LENGTH;
		if (z < 0)
			z += Cube.SIDE_LENGTH;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * return the cube of this position
	 */
	public Cube getCube() {
		return this.cube;
	}

	private Cube cube;

	private double x;
	private double y;
	private double z;

	/**
	 * returns a position substracted by the other position
	 * 
	 * @param other
	 *            the position to substract from this position
	 * @return the result position
	 */
	public Position min(Position other) {
		return new Position(this.getRealX() - other.getRealX(), this.getRealY() - other.getRealY(),
				this.getRealZ() - other.getRealZ());
	}

	/**
	 * return the x-value of this position
	 */
	public double getX() {
		return this.x;
	}

	/**
	 * return the real x-value of this position.
	 */
	public double getRealX() {
		return this.cube.getX() * Cube.SIDE_LENGTH + this.getX();
	}

	/**
	 * return the y-value of this position
	 */
	public double getY() {
		return this.y;
	}

	/**
	 * return the real y-value of this position.
	 */
	public double getRealY() {
		return this.cube.getY() * Cube.SIDE_LENGTH + this.getY();
	}

	/**
	 * return the z-value of this position
	 */
	public double getZ() {
		return this.z;
	}

	/**
	 * return the real z-value of this position.
	 */
	public double getRealZ() {
		return this.cube.getZ() * Cube.SIDE_LENGTH + this.getZ();
	}

	/**
	 * check wether this position is valid for a given world
	 * 
	 * @param world
	 *            the world to check
	 * @return false if the given world equals null
	 * @return false if the position is not within the boundaries of the given
	 *         world
	 */
	public boolean isValidIn(World world) {

		if (world == null)
			return false;

		return !(this.getRealX() < 0 || this.getRealX() > world.getNbCubesX() || this.getRealY() < 0
				|| this.getRealY() > world.getNbCubesY() || this.getRealZ() < 0
				|| this.getRealZ() > world.getNbCubesZ());
	}

	/**
	 * check wether this position is valid for any object in a given world
	 * 
	 * @param world
	 *            the world to check
	 * @return false if the position is not valid in the given world
	 * @return false if the cube where the position is located is not passable
	 */
	public boolean isValidForObjectIn(World world) {
		if (!this.isValidIn(world))
			return false;
		if (!this.getCube().isPassableIn(world)) {
			return false;
		}
		return true;
	}

	/**
	 * check wether this position is stable for any unit in a given world
	 * 
	 * @param world
	 *            the world to check
	 * @return false if the position is not valid for any unit in the given
	 *         world
	 * @return true if the position is valid and at least one neigbouring cube
	 *         is solid/impassable
	 */
	public boolean isStableForUnitIn(World world) {
		if (!this.isValidForObjectIn(world))
			return false;

		if (this.getCube().getZ() == 0)
			return true;

		Iterator<Cube> it = this.getCube().getAllAdjacentCubes(world).iterator();
		while (it.hasNext()) {
			Cube NeighbouringCube = it.next();
			if (!NeighbouringCube.isPassableIn(world))
				return true;
		}

		return false;
	}

	/**
	 * check wether this position is stable for any material in a given world
	 * 
	 * @param world
	 *            the world to check
	 * @return false if the position is not valid for any object in the given
	 *         world
	 * @return false if the cube of the position is not directly above a solid
	 *         cube and the z-coordinate of the cube is not 0
	 */
	public boolean isStableForMaterialIn(World world) {
		if (!this.isValidForObjectIn(world))
			return false;
		Cube cubeBelow = new Cube(this.getCube().getX(), this.getCube().getY(), this.getCube().getZ() - 1);
		if ((this.getCube().getZ() != 0) && (cubeBelow.isPassableIn(world)))
			return false;
		return true;
	}

	/**
	 * create the string representation
	 */
	@Override
	public String toString() {
		return "(" + this.getRealX() + "," + this.getRealY() + "," + this.getRealZ() + ")";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cube == null) ? 0 : cube.hashCode());
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (cube == null) {
			if (other.cube != null)
				return false;
		} else if (!cube.equals(other.cube))
			return false;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
			return false;
		return true;
	}

	public double getDistanceSquare(Position other) {
		return ((this.getRealX() - other.getRealX()) * (this.getRealX() - other.getRealX()))
				+ ((this.getRealY() - other.getRealY()) * (this.getRealY() - other.getRealY()))
				+ ((this.getRealZ() - other.getRealZ()) * (this.getRealZ() - other.getRealZ()));
	}
}
