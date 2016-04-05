package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * @Value
 * 
 * @author Marte & Ellen
 *
 */
@Value
public class Cube {

	/**
	 * 
	 * @param x
	 *            the position of this new cube on the x-axis
	 * @param y
	 *            the position of this new cube on the y-axis
	 * @param z
	 *            the position of this new cube on the z-axis
	 * 
	 * @post The position on the x-, y- and z-axis of this new cube is equal to
	 *       the given x, y and z
	 * 
	 * @throws IllegalArgumentException
	 *             given x, y or z are not a legal
	 */
	@Raw
	public Cube(int x, int y, int z) throws IllegalArgumentException {
		if (!isValidCubeCoordinate(x, y, z))
			throw new IllegalArgumentException();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * 
	 * @param x
	 *            the position of this new cube on the x-axis
	 * @param y
	 *            the position of this new cube on the y-axis
	 * @param z
	 *            the position of this new cube on the z-axis
	 * @param world
	 *            the world this cube is located in
	 * 
	 * @post The position on the x-, y- and z-axis of this new cube is equal to
	 *       the given x, y and z
	 * 
	 * @throws IllegalArgumentException
	 *             given x, y or z are not legal
	 */
	@Raw
	public Cube(int x, int y, int z, World world) throws IllegalArgumentException {
		if (!isValidCubeCoordinate(x, y, z, world))
			throw new IllegalArgumentException();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public boolean isValidCubeCoordinate(int x, int y, int z) {
		if ((x < 0) || (y < 0) || (z < 0))
			return false;
		return true;
	}

	public boolean isValidCubeCoordinate(int x, int y, int z, World world) {
		if ((x < 0) || (x > world.getNbCubes()) || (y < 0) || (y > world.getNbCubes()) || (z < 0)
				|| (z > world.getNbCubes()))
			return false;
		return true;
	}

	@Basic
	@Raw
	public int getX() {
		return this.x;
	}

	@Basic
	@Raw
	public int getY() {
		return this.y;
	}

	@Basic
	@Raw
	public int getZ() {
		return this.z;
	}

	/**
	 * Variables registering the position of this cube on the x-, y- and z-axis.
	 */
	private int x;
	private int y;
	private int z;

	/**
	 * constant registering the side length of one cube
	 */
	public static final double SIDE_LENGTH = 1;

	public Position getCenter() {
		return new Position(SIDE_LENGTH / 2, SIDE_LENGTH / 2, SIDE_LENGTH / 2, this);
	}

	public Cube min(Cube other) {
		return new Cube(this.getX() - other.getX(), this.getY() - other.getY(), this.getZ() - other.getZ());
	}

	public boolean equals(Cube other) {
		return (this.getX() == other.getX() && this.getY() == other.getY() && this.getZ() == other.getZ());
	}

	public boolean isAdjacentCube(Cube other) {
		Cube cubeDiff = this.min(other);
		return ((cubeDiff.getX() == -1 || cubeDiff.getX() == 0 || cubeDiff.getX() == 1)
				&& (cubeDiff.getY() == -1 || cubeDiff.getY() == 0 || cubeDiff.getY() == 1)
				&& (cubeDiff.getZ() == -1 || cubeDiff.getZ() == 0 || cubeDiff.getZ() == 1));
	}

}
