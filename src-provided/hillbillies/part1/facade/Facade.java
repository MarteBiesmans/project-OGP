//TODO overal try-catch gebruiken
package hillbillies.part1.facade;

import hillbillies.model.Cube;
import hillbillies.model.Unit;
import hillbillies.part1.facade.IFacade;
import ogp.framework.util.ModelException;

public class Facade implements IFacade {
	
	
	/* Unit creation */
	/**
	 * Create a new unit with the given attributes.
	 * 
	 * @param name
	 *            The name of the unit.
	 * @param initialPosition
	 *            The initial position of the unit, as an array with 3 elements
	 *            {x, y, z}.
	 * @param weight
	 *            The initial weight of the unit
	 * @param agility
	 *            The initial agility of the unit
	 * @param strength
	 *            The initial strength of the unit
	 * @param toughness
	 *            The initial toughness of the unit
	 * @param enableDefaultBehavior
	 *            Whether the default behavior of the unit is enabled
	 * 
	 * @return The generated unit
	 * 
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public Unit createUnit(String name, int[] initialPosition, int weight, int agility, int strength, int toughness,
			boolean enableDefaultBehavior) throws ModelException {
		try{
			return new Unit((double) initialPosition[0], (double) initialPosition[1], (double) initialPosition[2],
				name, strength, agility, toughness, weight, enableDefaultBehavior);
		}
		catch (Throwable e){
			throw new ModelException();
		}	
	}

	/* Position */
	/**
	 * Get the precise coordinate of the given unit.
	 * 
	 * @param unit
	 *            The unit for which to return the position.
	 * @return The coordinate of the center of the unit, as an array with 3
	 *         doubles {x, y, z}.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public double[] getPosition(Unit unit) throws ModelException {
		try{
			return new double[] {unit.getPosition().getRealX(), 
					unit.getPosition().getRealY(), unit.getPosition().getRealZ()};
		}
		
		catch (Throwable e){
			throw new ModelException();
		}
	}

	/**
	 * Get the coordinate of the cube occupied by the given unit.
	 * 
	 * @param unit
	 *            The unit for which to return the cube coordinate.
	 * @return The coordinate of the cube in which the center of the unit lies,
	 *         as an array with 3 integers {x, y, z}.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public int[] getCubeCoordinate(Unit unit) throws ModelException {
		try{
			return new int[] {unit.getCube().getX(), unit.getCube().getY(), unit.getCube().getZ()};
		}
		
		catch (Throwable e){
			throw new ModelException();
		}	
	}

	/* Name */
	/**
	 * Get the current name of the given unit.
	 * 
	 * @param unit
	 *            The unit for which to return the name.
	 * @return The current name of the unit.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public String getName(Unit unit) throws ModelException {
		try{
			return unit.getName();
		}
		
		catch (Throwable e){
			throw new ModelException();
		}	
	}

	/**
	 * Set the name of the given unit to the given value.
	 * 
	 * @param unit
	 *            The unit whose name to change.
	 * @param newName
	 *            The new name for the unit.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public void setName(Unit unit, String newName) throws ModelException {
		try{
			unit.setName(newName);
		}
		
		catch (Throwable e){
			throw new ModelException();
		}	
	}

	/* Attributes */

	/**
	 * Return the weight attribute of the given unit.
	 * 
	 * @param unit
	 *            The unit for which to return the attribute's value
	 * @return The current weight of the unit
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public int getWeight(Unit unit) throws ModelException {
		return unit.getWeight();	
	}

	/**
	 * Sets the weight attribute's value of the given unit to the given value.
	 * 
	 * @param unit
	 *            The unit for which to change the attribute's value
	 * @param newValue
	 *            The new weight
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public void setWeight(Unit unit, int newValue) throws ModelException {
		unit.setWeight(newValue);
	}

	/**
	 * Return the strength attribute of the given unit.
	 * 
	 * @param unit
	 *            The unit for which to return the attribute's value
	 * @return The current strength of the unit
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public int getStrength(Unit unit) throws ModelException {
		return unit.getStrength();
	}

	/**
	 * Sets the strength attribute's value of the given unit to the given value.
	 * 
	 * @param unit
	 *            The unit for which to change the attribute's value
	 * @param newValue
	 *            The new strength
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public void setStrength(Unit unit, int newValue) throws ModelException {
		unit.setStrength(newValue);
	}

	/**
	 * Return the agility attribute of the given unit.
	 * 
	 * @param unit
	 *            The unit for which to return the attribute's value
	 * @return The current agility of the unit
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public int getAgility(Unit unit) throws ModelException {
		return unit.getAgility();
	}

	/**
	 * Sets the agility attribute's value of the given unit to the given value.
	 * 
	 * @param unit
	 *            The unit for which to change the attribute's value
	 * @param newValue
	 *            The new agility
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public void setAgility(Unit unit, int newValue) throws ModelException {
		unit.setAgility(newValue);
	}

	/**
	 * Return the toughness attribute of the given unit.
	 * 
	 * @param unit
	 *            The unit for which to return the attribute's value
	 * @return The current toughness of the unit
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public int getToughness(Unit unit) throws ModelException {
			return unit.getToughness();
	}

	/**
	 * Sets the toughness attribute's value of the given unit to the given
	 * value.
	 * 
	 * @param unit
	 *            The unit for which to change the attribute's value
	 * @param newValue
	 *            The new toughness
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public void setToughness(Unit unit, int newValue) throws ModelException {
		unit.setToughness(newValue);
	}

	/* Points */

	/**
	 * Return the maximum number of hitpoints for the given unit.
	 * 
	 * @param unit
	 *            The unit for which to return the maximum number of hitpoints
	 * @return The maximum number of hitpoints for the given unit.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public int getMaxHitPoints(Unit unit) throws ModelException {
		return unit.getMaxHitpoints();
	}

	/**
	 * Return the current number of hitpoints for the given unit.
	 * 
	 * @param unit
	 *            The unit for which to return the current number of hitpoints
	 * @return The current number of hitpoints for the given unit.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public int getCurrentHitPoints(Unit unit) throws ModelException {
		return (int) unit.getHitpoints();
	}

	/**
	 * Return the maximum number of stamina points for the given unit.
	 * 
	 * @param unit
	 *            The unit for which to return the maximum number of stamina
	 *            points
	 * @return The maximum number of stamina points for the given unit.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public int getMaxStaminaPoints(Unit unit) throws ModelException {
		return unit.getMaxStaminaPoints();
	}

	/**
	 * Return the current number of stamina points for the given unit.
	 * 
	 * @param unit
	 *            The unit for which to return the current number of stamina
	 *            points
	 * @return The current number of stamina points for the given unit.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public int getCurrentStaminaPoints(Unit unit) throws ModelException {
		return (int) unit.getStaminaPoints();
	}

	/* Time */

	/**
	 * Advance the state of the given unit by the given time period.
	 * 
	 * @param unit
	 *            The unit for which to advance the time
	 * @param dt
	 *            The time period, in seconds, by which to advance the unit's
	 *            state.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public void advanceTime(Unit unit, double dt) throws ModelException {
//		try{
			unit.advanceTime((float) dt);
//		}
//		
//		catch (Throwable e){
//			throw new ModelException();
//		}	
	}

	/* Basic movement */

	/**
	 * Move the given unit to an adjacent cube.
	 * 
	 * @param unit
	 *            The unit to move
	 * @param dx
	 *            The amount of cubes to move in the x-direction; should be -1,
	 *            0 or 1.
	 * @param dy
	 *            The amount of cubes to move in the y-direction; should be -1,
	 *            0 or 1.
	 * @param dz
	 *            The amount of cubes to move in the z-direction; should be -1,
	 *            0 or 1.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public void moveToAdjacent(Unit unit, int dx, int dy, int dz) throws ModelException {
		try{
			unit.moveToAdjacent(dx, dy, dz);
		}
		
		catch (Throwable e){
			throw new ModelException();
		}	
	}

	/**
	 * Return the current speed of the given unit.
	 * 
	 * @param unit
	 *            The unit for which to retrieve the speed.
	 * @return The speed of the given unit.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public double getCurrentSpeed(Unit unit) throws ModelException {
		return unit.getMovementSpeed();
	}

	/**
	 * Return whether the given unit is currently moving.
	 * 
	 * @param unit
	 *            The unit for which to retrieve the state.
	 * @return true if the unit is currently moving; false otherwise
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public boolean isMoving(Unit unit) throws ModelException {
		return unit.isMoving();
	}

	/**
	 * Enable sprinting mode for the given unit.
	 * 
	 * @param unit
	 *            The unit which should start sprinting.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public void startSprinting(Unit unit) throws ModelException {
		if (! unit.isSprinting())
			unit.startSprinting();
		else
			throw new ModelException();
	}


	/**
	 * Disable sprinting mode for the given unit.
	 * 
	 * @param unit
	 *            The unit which should stop sprinting.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public void stopSprinting(Unit unit) throws ModelException {
		if (unit.isSprinting())
			unit.stopSprinting();
		else
			throw new ModelException();
	}
		

	/**
	 * Return whether the given unit is currently sprinting.
	 * 
	 * @param unit
	 *            The unit for which to retrieve the state.
	 * @return true if the unit is currently sprinting; false otherwise
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public boolean isSprinting(Unit unit) throws ModelException {
		return unit.isSprinting();
	}


	/* Orientation */

	/**
	 * Return the current orientation of the unit.
	 * 
	 * @param unit
	 *            The unit for which to retrieve the orientation
	 * @return The orientation of the unit, in radians.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public double getOrientation(Unit unit) throws ModelException {
		return unit.getOrientation();
	}
		

	/* Extended movement */

	/**
	 * Start moving the given unit to the given cube.
	 * 
	 * @param unit
	 *            The unit that should start moving
	 * @param cube
	 *            The coordinate of the cube to move to, as an array of integers
	 *            {x, y, z}.
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public void moveTo(Unit unit, int[] cube) throws ModelException {
		try{
			unit.moveTo(new Cube(cube[0], cube[1], cube[2]));
		}
		
		catch (Throwable e){
			throw new ModelException();
		}	
	}

	
	/* Working */
	/**
	 * Make the given unit start working.
	 * 
	 * @param unit
	 *            The unit that should start working
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	@SuppressWarnings("deprecation")
	public void work(Unit unit) throws ModelException {
		try{
			unit.work();
		}
		
		catch (Throwable e){
			throw new ModelException();
		}	
	}

	/**
	 * Return whether the given unit is currently working.
	 * 
	 * @param unit
	 *            The unit for which to retrieve the state
	 * @return true if the unit is currently working; false otherwise
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public boolean isWorking(Unit unit) throws ModelException {
		return unit.isWorking();
	}

	
	/* Attacking */
	/**
	 * Make the given unit fight with another unit.
	 * 
	 * @param attacker
	 *            The unit that initiates the fight by attacking another unit
	 * @param defender
	 *            The unit that gets attacked and should defend itself
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public void fight(Unit attacker, Unit defender) throws ModelException {
		try{
			attacker.attack(defender);
			defender.defend(attacker);
		}
		
		catch (Throwable e){
			throw new ModelException();
		}	
	}

	/**
	 * Return whether the given unit is currently attacking another unit.
	 * 
	 * @param unit
	 *            The unit for which to retrieve the state
	 * @return true if the unit is currently attacking another unit; false
	 *         otherwise
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public boolean isAttacking(Unit unit) throws ModelException {
		return unit.isAttacking();
	}

	/* Resting */

	/**
	 * Make the given unit rest.
	 * 
	 * @param unit
	 *            The unit that should start resting
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public void rest(Unit unit) throws ModelException {
		try{
			unit.rest();
		}
		
		catch (Throwable e){
			throw new ModelException();
		}	
	}

	/**
	 * Return whether the given unit is currently resting.
	 * 
	 * @param unit
	 *            The unit for which to retrieve the atate
	 * @return true if the unit is currently resting; false otherwise
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public boolean isResting(Unit unit) throws ModelException {
		return unit.isResting();
	}

	/* Default behavior */

	/**
	 * Enable or disable the default behavior of the given unit.
	 * 
	 * @param unit
	 *            The unit for which to enable or disable the default behavior
	 * @param value
	 *            true if the default behavior should be enabled; false
	 *            otherwise
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public void setDefaultBehaviorEnabled(Unit unit, boolean value) throws ModelException {
		if (value)
			unit.startDefaultBehaviour();
		else
			unit.stopDefaultBehaviour();
	}

	/**
	 * Returns whether the default behavior of the given unit is enabled.
	 * 
	 * @param unit
	 *            The unit for which to retrieve the default behavior state.
	 * @return true if the default behavior is enabled; false otherwise
	 * @throws ModelException
	 *             A precondition was violated or an exception was thrown.
	 */
	public boolean isDefaultBehaviorEnabled(Unit unit) throws ModelException {
		return (! unit.canStartDefaultBehaviour());
	}
}
