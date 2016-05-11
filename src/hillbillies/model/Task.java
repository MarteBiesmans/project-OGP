//TODO	comments (formal!)
package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.programs.statements.Statement;

/**
 * @invar Each task can have its name as name. | canHaveAsName(this.getName())
 * @invar The priority of each task must be a valid priority for any task. |
 *        isValidPriority(getPriority())
 * @invar Each task can have its activities as activities. |
 *        canHaveAsActivities(this.getActivities())
 * @invar Each task can have its cube as cube. | canHaveAsCube(this.getCube())
 * @invar The unit of each task must be a valid unit for any task. |
 *        isValidUnit(getUnit())
 */
public class Task {

	/**
	 * Initialize this new task with given name, priority, activities and cube.
	 * This new task will be intitialized as a non-terminated task with no unit
	 * yet.
	 *
	 * @param name
	 *            The name for this new task.
	 * @post The name of this new task is equal to the given name. |
	 *       new.getName() == name
	 * @throws IllegalArgumentException
	 *             This new task cannot have the given name as its name. | !
	 *             canHaveAsName(this.getName())
	 * @param priority
	 *            The priority for this new task.
	 * @effect The priority of this new task is set to the given priority. |
	 *         this.setPriority(priority)
	 * @param activities
	 *            The activities for this new task.
	 * @post The activities of this new task is equal to the given activities. |
	 *       new.getActivities() == activities
	 * @throws IllegalArgumentException
	 *             This new task cannot have the given activities as its
	 *             activities. | ! canHaveAsActivities(this.getActivities())
	 * @param cube
	 *            The cube for this new task.
	 * @post The cube of this new task is equal to the given cube. |
	 *       new.getCube() == cube
	 * @throws IllegalArgumentException
	 *             This new task cannot have the given cube as its cube. | !
	 *             canHaveAsCube(this.getCube())
	 */
	public Task(String name, int priority, Statement activities, Cube cube) throws IllegalArgumentException {
		if (!canHaveAsName(name))
			throw new IllegalArgumentException();
		this.name = name;
		this.setPriority(priority);
		if (!canHaveAsActivities(activities))
			throw new IllegalArgumentException();
		this.activities = activities;
		if (!canHaveAsCube(cube))
			throw new IllegalArgumentException();
		this.cube = cube;
		this.setUnit(null);
	}

	/**
	 * Return the name of this task.
	 */
	@Basic
	@Raw
	@Immutable
	public String getName() {
		return this.name;
	}

	/**
	 * Check whether this task can have the given name as its name.
	 * 
	 * @param name
	 *            The name to check.
	 * @return | result == true
	 */
	@Raw
	public boolean canHaveAsName(String name) {
		return true;
	}

	/**
	 * Variable registering the name of this task.
	 */
	private final String name;

	/**
	 * Return the priority of this task.
	 */
	@Basic
	@Raw
	public int getPriority() {
		return this.priority;
	}

	/**
	 * Check whether the given priority is a valid priority for any task.
	 * 
	 * @param priority
	 *            The priority to check.
	 * @return true | result == true
	 */
	public static boolean isValidPriority(int priority) {
		return true;
	}

	/**
	 * Set the priority of this task to the given priority.
	 * 
	 * @param priority
	 *            The new priority for this task.
	 * @post The priority of this new task is equal to the given priority. |
	 *       new.getPriority() == priority
	 * @throws IllegalArgumentException
	 *             The given priority is not a valid priority for any task. | !
	 *             isValidPriority(getPriority())
	 */
	@Raw
	public void setPriority(int priority) throws IllegalArgumentException {
		if (!isValidPriority(priority))
			throw new IllegalArgumentException();
		this.priority = priority;
	}

	/**
	 * Variable registering the priority of this task.
	 */
	private int priority;

	/**
	 * Return the activities of this task.
	 */
	@Basic
	@Raw
	@Immutable
	public Statement getActivities() {
		return this.activities;
	}

	/**
	 * Check whether this task can have the given activities as its activities.
	 * 
	 * @param activities
	 *            The activities to check.
	 * @return | result == (activities != null)
	 */
	@Raw
	public boolean canHaveAsActivities(Statement activities) {
		return (activities != null);
	}

	/**
	 * Variable registering the activities of this task.
	 */
	private final Statement activities;

	/**
	 * Return the cube of this task.
	 */
	@Basic
	@Raw
	@Immutable
	public Cube getCube() {
		return this.cube;
	}

	/**
	 * Check whether this task can have the given cube as its cube.
	 * 
	 * @param cube
	 *            The cube to check.
	 * @return | if (cube == null) | then result == false
	 * @return |if (getUnit() != null && getUnit().getWorld() != null) | then
	 *         retsult == (cube.isValidIn(getUnit().getWorld()) | else result ==
	 *         true;
	 */
	@Raw
	public boolean canHaveAsCube(Cube cube) {
		if (cube == null)
			return false;
		if (getUnit() != null && getUnit().getWorld() != null) {
			return (cube.isValidIn(getUnit().getWorld()));
		}
		return true;
	}

	/**
	 * Variable registering the cube of this task.
	 */
	private final Cube cube;

	/**
	 * Return the unit of this task.
	 */
	@Basic
	@Raw
	public Unit getUnit() {
		return this.unit;
	}

	/**
	 * Check whether the given unit is a valid unit for any task.
	 * 
	 * @param unit
	 *            The unit to check.
	 * @return | restult == (unit != null)
	 */
	public static boolean isValidUnit(Unit unit) {
		return (unit != null);
	}

	/**
	 * Set the unit of this task to the given unit.
	 * 
	 * @param unit
	 *            The new unit for this task.
	 * @post The unit of this new task is equal to the given unit. |
	 *       new.getUnit() == unit
	 * @throws IllegalArgumentException
	 *             The given unit is not a valid unit for any task. | !
	 *             isValidUnit(getUnit())
	 */
	@Raw
	public void setUnit(Unit unit) throws IllegalArgumentException {
		if (!isValidUnit(unit))
			throw new IllegalArgumentException();
		this.unit = unit;
	}

	/**
	 * Variable registering the unit of this task.
	 */
	private Unit unit;

}
