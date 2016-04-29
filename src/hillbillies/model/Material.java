//TODO comments checken
package hillbillies.model;

import java.util.Random;
import ogp.framework.util.*;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class of materials belonging to a world or to a unit.
 * 
 * @invar The position of each material must be a valid position for any
 *        material.
 * @invar Each Material can have its weight as weight.
 * @invar Each material that is not carried by a unit is located in a passable
 *        cube
 * @invar The world of each material must be a valid world for any material. |
 *        isValidWorld(getWorld())
 * 
 * @author Ellen & Marte
 *
 */
public abstract class Material extends TimeVariableObject {

	private static final Random RANDOM_GEN = new Random();

	/**
	 * create a new Material.
	 *
	 * @post	The position of this new material equals null.
	 * @post	The world of this new material equals null.
	 * @post	The owner of this new material equals null.
	 * @post	The weight of this new material equals a random value in [10, 50].
	 */
	protected Material() {
		this.world = null;
		this.setPosition(null);
		this.owner = null;

		// create random weight between 10 and 50
		// Min + RANDOM_GEN.nextInt(Max - Min) + 1)
		this.weight = 10 + RANDOM_GEN.nextInt(41);
	}

	/**
	 * Return the position of this material.
	 */
	@Basic
	@Raw
	public Position getPosition() {
		if (this.getOwner() != null)
			return this.getOwner().getPosition();
		return this.position;
	}

	/**
	 * Check whether the given position is a valid initial position for this
	 * material.
	 * 
	 * @param position
	 *            The position to check.
	 * @return false if the position is null or the cube of the position is not
	 *         passable
	 */
	protected boolean canHaveAsPosition(Position position) {
		if (position == null || !position.isValidForObjectIn(this.getWorld()))
			return false;
		return true;
	}

	/**
	 * Check whether this material should fall.
	 * 
	 * @return true if the cube of the position is not directly above a solid
	 *         cube
	 * @return false if the z-coordinate is 0
	 * @return false if the material is carried by a unit
	 * @return	false if the position of the material equals null
	 */
	protected boolean shouldFall() {
		if (this.getOwner() != null)
			return false;
		if (this.getPosition() == null)
			return false;
		if (this.getPosition().getCube().getZ() == 0)
			return false;
		Cube cubeBelow = new Cube(this.getPosition().getCube().getX(), this.getPosition().getCube().getY(),
				this.getPosition().getCube().getZ() - 1);
		if (this.getWorld().getTerrainType(cubeBelow).isPassable())
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
	 *             The given position is not a valid position for this material,
	 *             except for when the position is null.
	 */
	@Raw
	protected void setPosition(Position position) throws IllegalArgumentException {
		if (position != null) {
			if (!canHaveAsPosition(position)) {
				throw new IllegalArgumentException();
			}
		}
		this.position = position;
	}

	/**
	 * Variable registering the position of this material, can be null if it is
	 * being carried
	 */
	private Position position;

	/**
	 * Return the owner carrying this material.
	 */
	@Basic
	@Raw
	protected Unit getOwner() {
		return this.owner;
	}

	/**
	 * Check whether the given owner is a valid owner for this material.
	 * 
	 * @param owner
	 *            The owner to check.
	 * @return false if owner is null
	 * @return false if the material already has a owner
	 * @return false if the owner doesn't own the material
	 */
	protected boolean canHaveAsOwner(Unit owner) {
		if (this.getOwner() != null) {
			return false;
		} else if (owner == null) {
			return false;
		} else if (!owner.hasAsMaterial(this)) {
			return false;
		}
		return true;
	}

	/**
	 * Set the unit carrying this material to the given owner.
	 * 
	 * @param owner
	 *            The new owner for this material.
	 * @post The unit carrying this material is equal to the given owner.
	 * @post The position of this material is null.
	 * @post The material is deleted from the world.
	 * @throws IllegalArgumentException
	 *             The given owner is not a valid owner for this material.
	 */
	@Raw
	protected void setOwner(Unit owner) throws IllegalArgumentException {
		if (owner != null) {
			if (!canHaveAsOwner(owner))
				throw new IllegalArgumentException();
		}
		this.owner = owner;
	}

	/**
	 * Variable registering the owner carrying this material, can be null if it
	 * is not carried.
	 */
	private Unit owner;

	/**
	 * Return the world of this material.
	 */
	@Basic
	@Raw
	protected World getWorld() {
		return this.world;
	}

	/**
	 * Check whether the given world is a valid world for any material.
	 * 
	 * @param world
	 *            The world to check.
	 * @return false if the world is null
	 * @return false if the world doesn't has this material as material
	 * @return false if the material already exists in another world
	 */
	protected boolean canHaveAsWorld(World world) {
		if (world == null) {
			return false;
		} else if (!world.hasAsMaterial(this)) {
			return false;
		} else if (this.getWorld() != null) {
			return false;
		}
		return true;
		// return (world != null && world.hasAsMaterial(this) && this.getWorld()
		// == null);
	}

	/**
	 * Set the world of this material to the given world.
	 * 
	 * @param world
	 *            The new world for this material.
	 * @post The world of this new material is equal to the given world.
	 * @throws IllegalArgumentException
	 *             The given world is not a valid world for this material.
	 */
	@Raw
	protected void setWorld(World world) throws IllegalArgumentException {
		if (world != null) {
			if (!canHaveAsWorld(world))
				throw new IllegalArgumentException();
		}
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
	protected int getWeight() {
		return this.weight;
	}

	/**
	 * Variable registering the weight of this Material.
	 */
	private final int weight;

	/**
	 * update the position of this material, based on it's current properties
	 * and a given duration 'seconds' in seconds of game time
	 * 
	 * @param seconds
	 * @throws IllegalArgumentException
	 *             the seconds are not in the interval [0,0.2[
	 */
	public void advanceTime(float seconds) throws IllegalArgumentException {
		if (seconds < 0 || Util.fuzzyGreaterThanOrEqualTo((double)seconds, 0.2)      )
			 throw new IllegalArgumentException();
		if (this.shouldFall())
			falling(seconds);
	}

	/**
	 * 
	 * @param seconds
	 */
	protected void falling(float seconds) {
		Position next = new Position(this.getPosition().getRealX(), this.getPosition().getRealY(),
				this.getPosition().getRealZ() + FALLING_VELOCITY * seconds);
		this.setPosition(next);
	}
}
