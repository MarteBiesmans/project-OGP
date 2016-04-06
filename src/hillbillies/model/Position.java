package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * @Value
 * @author Marte & Ellen
 *
 */
@Value
public class Position {


	public Position(double x, double y, double z) throws IllegalArgumentException {
		Cube cube = new Cube((int) (x / Cube.SIDE_LENGTH),
								(int) (y / Cube.SIDE_LENGTH),
								(int) (z / Cube.SIDE_LENGTH));
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
		
		this.cube = new Cube((int) (cube.getX() + (x / Cube.SIDE_LENGTH)),
				(int) (cube.getY() + (y / Cube.SIDE_LENGTH)),
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
	
	public Cube getCube() {
		return this.cube;
	}
	
	private Cube cube;
	
	private double x;
	private double y;
	private double z;
	
	public Position min(Position other) {
		return new Position(this.getRealX() - other.getRealX(),
				this.getRealY() - other.getRealY(),
				this.getRealZ() - other.getRealZ());
	}

	public double getX() {
		return this.x;
	}

	public double getRealX() {
		return this.cube.getX() * Cube.SIDE_LENGTH + this.getX();
	}

	public double getY() {
		return this.y;
	}

	public double getRealY() {
		return this.cube.getY() * Cube.SIDE_LENGTH + this.getY();
		}

	public double getZ() {
		return this.z;
	}

	public double getRealZ() {
		return this.cube.getZ() * Cube.SIDE_LENGTH + this.getZ();
		}
	
	/**
	 * check wether this position is valid for a given world
	 * 
	 * @param	world
	 * 			the world to check
	 * @return	false if the given world equals null
	 * @return	false if the position is not within the boundaries of the given world
	 */
	public boolean isValidIn(World world) {
		
		if (world==null)
			return false;
		
		return !(this.getRealX() < 0 || this.getRealX() > world.getNbCubesX() 
			  || this.getRealY() < 0 || this.getRealY() > world.getNbCubesY() 
			  || this.getRealZ() < 0 || this.getRealZ() > world.getNbCubesZ());
	}
	
	/**
	 * check wether this position is valid for any object in a given world
	 * 
	 * @param	world
	 * 			the world to check
	 * @return	false if the position is not valid in the given world
	 * @return	false if the cube where the position is located is not passable
	 */
	public boolean isValidForObjectIn(World world) {
		if (! this.isValidIn(world))
			return false;
		if (! world.isPassableCube(this.getCube()))
			return false;		
		return true;
	}
	
	/**
	 * check wether this position is stable for any unit in a given world
	 * 
	 * @param	world
	 * 			the world to check
	 * @return	false if the position is not valid for any unit in the given world
	 * @return	false if all the neigbouring cubes are passable
	 */
	public boolean isStableForUnitIn(World world) {
		if (! this.isValidForObjectIn(world))
			return false;
		if (world.isPassableCube((this.getCube()).min(new Cube(0, 0, 1))) &&
			world.isPassableCube((this.getCube()).min(new Cube(0, 1, 0))) &&
			world.isPassableCube((this.getCube()).min(new Cube(0, 1, 1))) &&
			world.isPassableCube((this.getCube()).min(new Cube(1, 0, 0))) &&
			world.isPassableCube((this.getCube()).min(new Cube(1, 0, 1))) &&
			world.isPassableCube((this.getCube()).min(new Cube(1, 1, 0))) &&
			world.isPassableCube((this.getCube()).min(new Cube(1, 1, 1))) &&
			world.isPassableCube((this.getCube()).min(new Cube(0, 0, -1))) &&
			world.isPassableCube((this.getCube()).min(new Cube(0, -1, 0))) &&
			world.isPassableCube((this.getCube()).min(new Cube(0, -1, -1))) &&
			world.isPassableCube((this.getCube()).min(new Cube(-1, 0, 0))) &&
			world.isPassableCube((this.getCube()).min(new Cube(-1, 0, -1))) &&
			world.isPassableCube((this.getCube()).min(new Cube(-1, -1, 0))) &&
			world.isPassableCube((this.getCube()).min(new Cube(-1, -1, -1))) )
			return false;
		
		return true;
	}
	
	
	/**
	 * check wether this position is stable for any material in a given world
	 * 
	 * @param	world
	 * 			the world to check
	 * @return	false if the position is not valid for any object in the given world
	 * @return	false if the cube of the position is not directly above a solid cube and 
	 * 				the z-coordinate of the cube is not 0
	 */
	public boolean isStableForMaterialIn(World world) {
		if (! this.isValidForObjectIn(world))
			return false;
		Cube cubeBelow = new Cube(this.getCube().getX(), 
								this.getCube().getY(), 
								this.getCube().getZ()-1);
		if ( (this.getCube().getZ() != 0) &&
			 (world.isPassableCube(cubeBelow)) )
			return false;	
		return true;
	}
	
}
