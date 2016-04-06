package hillbillies.model;

import java.util.Random;

import be.kuleuven.cs.som.annotate.*;

/**
 * @invar  The position of each material must be a valid position for any
 *         material.
 * @invar  Each Material can have its weight as weight.
 * @invar	Each material that is not carried by a unit is located in a passable cube
 * @invar The world of each material must be a valid world for any material. |
 *        isValidWorld(getWorld())
 * 
 * @author Ellen & Marte
 *
 */
public abstract class Material extends TimeVariableObject {

	private static final Random RANDOM_GEN = new Random();

	/**
	 * Initialize this new material with given position.
	 *
	 * @param position
	 *            The position for this new material.
	 * @param world
	 *            The world where this material is located.
	 * @effect The position of this new material is set to the given position.
	 * @effect The world where this new material is located is set to the given
	 *         world.
	 * @effect The owner of this new material is set to null.
	 * @effect The weight of this new material is given a random value in the
	 *         range [10, 50].
	 */
	public Material(World world, Position position) throws IllegalArgumentException {
		this.world = world;
		this.owner = null;

		if (!this.canHaveAsPosition(position))
			throw new IllegalArgumentException();
		this.setPosition(position);

		// create random weight between 10 and 50
		// Min + RANDOM_GEN.nextInt(Max - Min) + 1)
		// TODO: todo is opgelost dmv RANDOM_GEN ipv Math.random
		this.weight = 10 + RANDOM_GEN.nextInt(41);
	}

	/**
	 * Return the position of this material.
	 */
	@Basic
	@Raw
	public Position getPosition() {
		return this.position;
	}

	/**
	 * Check whether the given position is a valid initial position for this material.
	 * 
	 * @param	position
	 * 			The position to check.
	 * @return	false if the cube of the position is not passable
	 */
	public boolean canHaveAsPosition(Position position) {
		if (!this.getWorld().getTerrainType(position.getCube()).isPassable())
			return false;
		return true;
	}
	
	/**
	 * Check whether this material should fall.
	 * 
	 * @return	true if the cube of the position is not directly above a solid cube
	 * @return	false if the z-coordinate is 0
	 * @return	false if the material is carried by a unit, thus position equals null
	 */
	public boolean shouldFall() {
		if (this.getPosition()==null)
			return false;
		
		if (this.getPosition().getCube().getZ() == 0)
			return false;
		Cube cubeBelow = new Cube(this.getPosition().getCube().getX(), 
								this.getPosition().getCube().getY(), 
								this.getPosition().getCube().getZ()-1);
		if ( this.getWorld().getTerrainType(cubeBelow).isPassable() )
			return true;
		
		return false;
	}
	

	/**
	 * Set the position of this material to the given position.
	 * 
	 * @param position
	 *            The new position for this material.
	 * @post The position of this new material is equal to the given position.
	 * @throws IllegalArgumentException
	 *             The given position is not a valid position for this material.
	 */
	@Raw
	public void setPosition(Position position) throws IllegalArgumentException {
		if (!canHaveAsPosition(position))
			throw new IllegalArgumentException();
		this.position = position;
	}

	public void setPosition(Position position, World world) {
		this.owner = null;
		world.addMaterial(this);
		this.setPosition(position);
	}

	/**
	 * Variable registering the position of this material,
	 * can be null if it is being carried
	 */
	private Position position;

	/**
	 * Return the owner carrying this material.
	 */
	@Basic
	@Raw
	public Unit getOwner() {
		return this.owner;
	}

	/**
	 * Check whether the given owner is a valid owner for this material.
	 * 
	 * @param owner
	 *            The owner to check.
	 * @return false if the owner is already carrying a maximum of materials
	 *         TODO is er een max? hoeveel?
	 */
	public boolean canHaveAsOwner(Unit owner) {
		return true;
	}

	/**
	 * Set the unit carrying this material to the given owner.
	 * 
	 * @param owner
	 *            The new owner for this material.
	 * @post The unit carrying this material is equal to the given owner.
	 * @post The position of this material is null.
	 * @throws IllegalArgumentException
	 *             The given owner is not a valid owner for this material.
	 */
	@Raw
	public void setOwner(Unit owner) throws IllegalArgumentException {
		if (!canHaveAsOwner(owner))
			throw new IllegalArgumentException();
		this.setPosition(null);
		this.getWorld().removeMaterial(this);
		this.owner = owner;
		// TODO: this.world.remove(this) + zorgen dat deze methode this.world =
		// null zet
	}

	/**
	 * Variable registering the owner carrying this material,
	 * can be null if it is not carried.
	 */
	private Unit owner;

	/**
	 * Return the world of this material.
	 */
	@Basic
	@Raw
	public World getWorld() {
		return this.world;
	}

	/**
	 * Check whether the given world is a valid world for any material.
	 * 
	 * @param world
	 *            The world to check.
	 * @return | result ==
	 */
	public boolean canHaveAsWorld(World world) {
		return (world != null && world.hasAsMaterial(this) && this.getWorld() == null);
	}

	/**
	 * Set the world of this material to the given world.
	 * 
	 * @param world
	 *            The new world for this material.
	 * @post The world of this new material is equal to the given world. |
	 *       new.getWorld() == world
	 * @throws IllegalArgumentException
	 *             The given world is not a valid world for any material. | !
	 *             isValidWorld(getWorld())
	 */
	@Raw
	public void setWorld(World world) throws IllegalArgumentException {
		if (world != null) {
			if (canHaveAsWorld(world))
				throw new IllegalArgumentException();
		} else if ((this.getWorld() != null) && (this.getWorld().hasAsMaterial(this)))
			throw new IllegalArgumentException();
		this.world = world;
	}

	/**
	 * Variable registering the world of this material.
	 */
	private World world;


	/**
	 * Return the weight of this material.
	 */
	@Basic
	@Raw
	@Immutable
	public int getWeight() {
		return this.weight;
	}

	/**
	 * Variable registering the weight of this Material.
	 */
	private final int weight;
	
	
//	/**
//	 * return the activity of this material
//	 */
//	@Basic
//	public Activity getActivity(){
//		return activity;
//	}
//	
//	/**
//	 * check if the given activity is valid for any material
//	 * 
//	 * @return true if the activity is none or falling
//	 */
//	public boolean isValidActivity(Activity activity){
//		if ( (activity==Activity.NONE) && (activity==Activity.FALLING) )
//			return true;		
//		return false;
//	}
//	
//	
//	/**
//	 * set the activity of this material to the given activity
//	 * 
//	 * @param	activity
//	 * 			the activity to set
//	 * @throws	IllegalArgumentException
//	 * 			the given activity is not valid for materials
//	 */
//	public void setActivity(Activity activity) throws IllegalArgumentException {
//		if (!this.isValidActivity(activity))
//			throw new IllegalArgumentException();
//		this.activity = activity;
//	}
//	
//	/**
//	 * variable registering the activity of this material
//	 */
//	private Activity activity;
	
	/**
	 * update the position of this material, based on it's current properties 
	 * and a given duration 'seconds' in seconds of game time
	 * 
	 * @param seconds
	 * @throws IllegalArgumentException
	 *             the seconds are not in the interval [0,0.2[
	 */
	public void advanceTime(float seconds) throws IllegalArgumentException {
		if (seconds < 0 || seconds >= 0.2)
			throw new IllegalArgumentException();
		
		this.busyTimeMin(seconds);

		if (this.shouldFall())
			falling(seconds);
	}
	
	
	public void falling(float seconds) {		
		double zVelocity = -3.0;
		Position next = new Position(this.getPosition().getRealX(),
									 this.getPosition().getRealY(),
									 this.getPosition().getRealZ() + zVelocity * seconds);
		this.setPosition(next);
	}
}
