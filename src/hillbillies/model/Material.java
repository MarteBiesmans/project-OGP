package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * @invar  The position of each material must be a valid position for any
 *         material.
 * @invar  Each Material can have its weight as weight.
 *         
 * @author Ellen & Marte
 *
 */
public abstract class Material {

	/**
	 * Initialize this new material with given position.
	 *
	 * @param	position
	 *          The position for this new material.
	 * @param	world
	 * 			The world where this material is located.
	 * @effect	The position of this new material is set to the given position.
	 * @effect	The world where this new material is located is set to the given world.
	 * @effect	The owner of this new material is set to null.
	 * @effect	The weight of this new material is given a random value in the range [10, 50].
	 */
	public Material(World world, Position position) throws IllegalArgumentException {
		this.world = world;
		this.owner = null;
		
		if (!this.canHaveAsPosition(position))
			throw new IllegalArgumentException();
		this.setPosition(position);
		
		//create random weight between 10 and 50
		//Min + (int)(Math.random() * ((Max - Min) + 1))
		//TODO kan dit 51 worden door afrondingsfouten?
		this.weight = 10 + (int)(Math.random() * ((50 - 10) + 1));
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
	 * Check whether the given position is a valid position for this material.
	 * 
	 * @param	position
	 * 			The position to check.
	 * @return	false if the cube of the position is not passable
	 * @return	false if the cube of the position is not directly above a solid cube or if the z-coordinate is not 0
	 */
	public boolean canHaveAsPosition(Position position) {
		if ( ! this.getWorld().getTerrainType(position.getCube()).isPassable() )
			return false;
		if (position.getCube().getZ() == 0)
			return true;
		
		Cube cubeBelow = new Cube(position.getCube().getX(), position.getCube().getY(), position.getCube().getZ()-1);
		if ( this.getWorld().getTerrainType(cubeBelow).isPassable() )
			return false;
		return true;
	}

	/**
	 * Set the position of this material to the given position.
	 * 
	 * @param	position
	 *          The new position for this material.
	 * @post	The position of this new material is equal to the given position.
	 * @throws	IllegalArgumentException
	 *          The given position is not a valid position for this material.
	 */
	@Raw
	public void setPosition(Position position) throws IllegalArgumentException {
		if (!canHaveAsPosition(position))
			throw new IllegalArgumentException();
		this.position = position;
	}

	/**
	 * Variable registering the position of this material.
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
	 * @param	owner
	 * 			The owner to check.
	 * @return	false if the owner is already carrying a maximum of materials TODO is er een max? hoeveel?
	 */
	public boolean canHaveAsOwner(Unit owner) {
		return true;
	}

	/**
	 * Set the unit carrying this material to the given owner.
	 * 
	 * @param	owner
	 *          The new owner for this material.
	 * @post	The unit carrying this  material is equal to the given owner.
	 * @post	The position of this material is null.
	 * @throws	IllegalArgumentException
	 *          The given owner is not a valid owner for this material.
	 */
	@Raw
	public void setOwner(Unit owner) throws IllegalArgumentException {
		if (!canHaveAsOwner(owner))
			throw new IllegalArgumentException();
		this.setPosition(null);
		this.owner = owner;
	}

	/**
	 * Variable registering the owner carrying this material.
	 */
	private Unit owner;
	
	
	/**
	 * Return the world where this material is located.
	 */
	@Raw
	public World getWorld(){
		return this.world;
	}
	
	/**
	 * Variable registering the world where this material is located.
	 */
	private final World world;
	
	
	public void advanceTime(float seconds) throws IllegalArgumentException {
		if (seconds < 0 || seconds >= 0.2)
			throw new IllegalArgumentException();
		
		//TODO 
	}
	

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
	
	
}
