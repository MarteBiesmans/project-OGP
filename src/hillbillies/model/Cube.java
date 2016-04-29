package hillbillies.model;

import java.util.HashSet;
import java.util.Set;

import be.kuleuven.cs.som.annotate.*;

/**
 * the class cube is similar to the class position, where position consists of
 * doubles, cube consists of integers.
 * 
 * @author Ellen & Marte
 * @Value
 * @invar x, y and z are always positive integers
 */
@Value
public class Cube {

	/**
	 * constant registering the side length of one cube
	 */
	public static final double SIDE_LENGTH = 1;

	/**
	 * create a new cube
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
	 *             given x, y or z are not legal for any world
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
	 * create a new cube
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
	 *             given x, y or z are not legal in the given world
	 */
	@Raw
	public Cube(int x, int y, int z, World world) throws IllegalArgumentException {
		if (!isValidCubeCoordinate(x, y, z, world))
			throw new IllegalArgumentException();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Check whether the given coördinates are valid in any world.
	 * 
	 * @param x
	 *            The position to check on the x-axis
	 * @param y
	 *            The position to check on the y-axis
	 * @param z
	 *            The position to check on the z-axis
	 * @return true if all coördinates are greater or equal than zero.
	 */
	private boolean isValidCubeCoordinate(int x, int y, int z) {
		if ((x < 0) || (y < 0) || (z < 0))
			return false;
		return true;
	}

	/**
	 * Checks if the given coördinates are valid in the given world.
	 * 
	 * @param x
	 *            The position to check on the x-axis
	 * @param y
	 *            The position to check on the y-axis
	 * @param z
	 *            The position to check on the z-axis
	 * @param world
	 * @return true if all coördinates fit in the boundaries of the given world.
	 */
	protected boolean isValidCubeCoordinate(int x, int y, int z, World world) {
		if ((x < 0) || (x >= world.getNbCubesX()) || (y < 0) || (y >= world.getNbCubesY()) || (z < 0)
				|| (z >= world.getNbCubesZ()))
			return false;
		return true;
	}

	/**
	 * Checks if this cube is valid in the given world.
	 * 
	 * @param world
	 *            The world the cube has to fit in
	 * @return true if the cube coördinates fit in the given world.
	 */
	public boolean isValidIn(World world) {
		return isValidCubeCoordinate(this.getX(), this.getY(), this.getZ(), world);
	}

	/**
	 * returns the coördinate on the x-axis.
	 */
	@Basic
	@Raw
	public int getX() {
		return this.x;
	}

	/**
	 * returns the coördinate on the y-axis.
	 */
	@Basic
	@Raw
	public int getY() {
		return this.y;
	}

	/**
	 * returns the coördinate on the z-axis.
	 */
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
	 * returns the center position of this cube.
	 */
	public Position getCenter() {
		return new Position(SIDE_LENGTH / 2, SIDE_LENGTH / 2, SIDE_LENGTH / 2, this);
	}

	/**
	 * returns the difference of this cube with another cube.
	 * 
	 * @param other
	 *            The cube to substract from this cube.
	 */
	public Cube min(Cube other) {
		return new Cube(this.getX() - other.getX(), this.getY() - other.getY(), this.getZ() - other.getZ());
	}

	/**
	 * returns if the cube is passable in the given world.
	 */
	public boolean isPassableIn(World world) {
		return world.getTerrainType(this).isPassable();
	}

	/**
	 * returns whether the given cube is the same or an adjacent cube to this.
	 * @note	note the subtle difference with directly adjacent cubes and neighbouring cubes
	 * @param other
	 *            The cube to compare to this cube
	 * @return true if the other cube is the same cube
	 * @return true if the other cube is an adjacent cube,
	 * 			i.e. one of the 26 cubes touching this cube with at least one corner point
	 */
	public boolean isSameOrAdjacentCube(Cube other) {
		int diffX = this.getX() - other.getX();
		int diffY = this.getY() - other.getY();
		int diffZ = this.getZ() - other.getZ();

		return (((diffX == -1) || (diffX == 0) || (diffX == 1)) && ((diffY == -1) || (diffY == 0) || (diffY == 1))
				&& ((diffZ == -1) || (diffZ == 0) || (diffZ == 1)));
	}

	/**
	 * returns whether the given cube is the same or a directly adjacent cube to this.
	 * @note	note the subtle difference with adjacent cubes and neighbouring cubes
	 * @param other
	 *            The cube to compare to this cube
	 * @return true if the other cube is the same cube
	 * @return true if the other cube is a directly adjacent cube, 
	 * 			i.e. one of the 6 cubes touching this cube with at least one side plane
	 */
	public boolean isSameOrDirectlyAdjacentCube(Cube other) {
		if (this.equals(other))
			return true;
		int diffX = this.getX() - other.getX();
		int diffY = this.getY() - other.getY();
		int diffZ = this.getZ() - other.getZ();

		if (((diffX != -1) && (diffX != 0) && (diffX != 1)) || ((diffY != -1) && (diffY != 0) && (diffY != 1))
				|| ((diffZ != -1) && (diffZ != 0) && (diffZ != 1))) {
			return false;
		}

		int nbDiff = 0;
		if (diffX != 0)
			nbDiff += 1;
		if (diffY != 0)
			nbDiff += 1;
		if (diffZ != 0)
			nbDiff += 1;

		return (nbDiff == 1);
	}

	/**
	 * checks if the given cube is the same or a neighbouring cube from this cube.
	 * @note	note the subtle difference with adjacent and directly adjacent cubes.
	 * 
	 * @param other
	 *            The cube to compare to this cube
	 * @return true if the other cube is the same cube
	 * @return true if the other cube is a neighbouring cube,
	 * 			i.e. one of the 8 cubes in the same z-level touching this cube with at least one side rib
	 */
	public boolean isSameOrNeighbouringCube(Cube other) {
		return (this.isSameOrAdjacentCube(other) && (this.getZ() == other.getZ()));
	}

	/**
	 * returns a set of all adjacent cubes that are valid in the given world.
	 * @note	note the subtle difference with directly adjacent cubes and neighbouring cubes
	 * @param world
	 *            The world the cubes must fit in
	 * @return a set of all adjacent cubes valid in the given world,
	 * 			i.e. at most 26 cubes touching this cube with at least one corner point
	 */
	public Set<Cube> getAllAdjacentCubes(World world) {

		Set<Cube> result = new HashSet<Cube>();
		for (int x = -1; x < 2; x++)
			for (int y = -1; y < 2; y++)
				for (int z = -1; z < 2; z++) {
					try {
						Cube possibleCube = new Cube(this.getX() + x, this.getY() + y, this.getZ() + z);
						if (possibleCube.isValidIn(world))
							result.add(possibleCube);
					} catch (IllegalArgumentException e) {
					}
				}
		result.remove(this);
		return result;
	}

	/**
	 * returns a set of all directly adjacent cubes that are valid in the given
	 * world.
	 * @note	note the subtle difference with adjacent cubes and neighbouring cubes
	 * 
	 * @param world
	 *            The world the cubes must fit in
	 * @return a set of all directly adjacent cubes valid in the given world,
	 * 			i.e. at most 6 cubes touching this cube with at least one side plane
	 */
	public Set<Cube> getAllDirectlyAdjacentCubes(World world) {
		Set<Cube> result = new HashSet<Cube>();
		for (Cube cube : this.getAllAdjacentCubes(world)) {
			if (cube.isSameOrDirectlyAdjacentCube(this))
				result.add(cube);
		}
		return result;
	}

	/**
	 * returns a set of all neighbouring cubes that are valid in the given world
	 * @note	note the subtle difference with adjacent and directly adjacent cubes.
	 * 
	 * @param world
	 *            The world the cubes must fit in
	 * @return a set of all neighbouring cubes,
	 * 			i.e. at most 8 cubes in the same z-level touching this cube with at least one side rib
	 */
	public Set<Cube> getAllNeighbouringCubes(World world) {
		Set<Cube> result = new HashSet<Cube>();
		for (Cube cube : this.getAllAdjacentCubes(world)) {
			if (this.isSameOrNeighbouringCube(cube))
				result.add(cube);
		}
		return result;
	}

	/**
	 * check whether this cube is solid and connected to the border in the given
	 * world.
	 * 
	 * @param world
	 *            The world the cube is located in
	 * @return true if this cube is solid and connected to the border of the
	 *         given world
	 */
	public boolean isSolidConnectedToBorderIn(World world) {
		return world.connectedUtil.isSolidConnectedToBorder(this.getX(), this.getY(), this.getZ());
	}

	/**
	 * create a string representation
	 */
	@Override
	public String toString() {
		return "(" + this.getX() + "," + this.getY() + "," + this.getZ() + ")";
	}

	/**
	 * calculate the hashcode of this cube
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		result = prime * result + z;
		return result;
	}

	/**
	 * check whether this cube has equal values to the given object
	 * @param	obj
	 * 			the object to compare this cube to
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Cube))
			return false;
		Cube other = (Cube) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		if (z != other.z)
			return false;
		return true;
	}

	/**
	 * check whether this cube is workable in the given world by the given unit
	 * 
	 * @param world
	 *            The world the cube is in
	 * @param unit
	 *            The unit that wants to work on this cube
	 * @return true if there is a task that can be completed on this cube in the
	 *         given world by this unit
	 */
	public boolean isWorkableCubeInBy(World world, Unit unit) {
		if (unit.getNbMaterials() > 0 && this.getCenter().isValidForObjectIn(world)) {
			return true;
		} else if (world.getTerrainType(this) == TerrainType.WORKSHOP && world.getBouldersIn(this).size() > 0
				&& world.getLogsIn(this).size() > 0) {
			return true;
		} else if (world.getBouldersIn(this).size() > 0) {
			return true;
		} else if (world.getLogsIn(this).size() > 0) {
			return true;
		} else if (world.getTerrainType(this) == TerrainType.WOOD || world.getTerrainType(this) == TerrainType.ROCK) {
			return true;
		}
		return false;
	}

	/**
	 * return the 'weight' of the movement from this cube to the next cube
	 * @note	necessary for the pathfinding algorithm
	 * @param next
	 *            The next cube
	 * @return the weight of the movement from this cube to the next cube based
	 *         on distance and z-level
	 */
	public double getDistanceWeightTo(Cube next) {
		double weight = 1;
		double distance = Math.sqrt((this.getX() - next.getX()) * (this.getX() - next.getX())
				+ (this.getY() - next.getY()) * (this.getY() - next.getY())
				+ (this.getZ() - next.getZ()) * (this.getZ() - next.getZ()));
		weight *= distance;
		if (this.getZ() - next.getZ() == -1)
			weight *= 0.5;
		else if (this.getZ() - next.getZ() == 1)
			weight *= 1.2;
		return weight;
	}
}
