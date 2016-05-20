//TODO	comments (formal!)
package hillbillies.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
 * @invar Each task must have proper schedulers. | hasProperSchedulers()
 */
public class Task implements Comparable<Task> {

	/**
	 * Initialize this new task with given name, priority, activities and cube.
	 * This new task will be intitialized as a non-terminated task with no unit
	 * yet and with no schedulers yet.
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
	 * @post This new task has no schedulers yet. | new.getNbSchedulers() == 0
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

	public boolean isWellFormed() {
		return getActivities().isWellFormed() && getActivities().containsActionStatement();
	}

	@Override
	public int compareTo(Task other) {
		return Integer.compare(other.getPriority(), this.getPriority());
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
		return (name != null);
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

	public boolean hasBeenFullyExecuted() {
		return activities.hasBeenFullyExecuted();
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
		if (unit != null) {
			if (!isValidUnit(unit))
				throw new IllegalArgumentException();
		}
		this.unit = unit;
	}

	public boolean isBeingExecuted() {
		return (this.getUnit() != null);
	}

	/**
	 * Variable registering the unit of this task.
	 */
	private Unit unit;

	/**
	 * Check whether this task has the given scheduler as one of its schedulers.
	 * 
	 * @param scheduler
	 *            The scheduler to check.
	 */
	@Basic
	@Raw
	public boolean hasAsScheduler(@Raw Scheduler scheduler) {
		return schedulers.contains(scheduler);
	}

	/**
	 * Check whether this task can have the given scheduler as one of its
	 * schedulers.
	 * 
	 * @param scheduler
	 *            The scheduler to check.
	 * @return True if and only if the given scheduler is effective and that
	 *         scheduler is a valid scheduler for a task. | result == |
	 *         (scheduler != null) && | Scheduler.isValidTask(this)
	 */
	@Raw
	public boolean canHaveAsScheduler(Scheduler scheduler) {
		return (scheduler != null);
	}

	/**
	 * Check whether this task has proper schedulers attached to it.
	 * 
	 * @return True if and only if this task can have each of the schedulers
	 *         attached to it as one of its schedulers, and if each of these
	 *         schedulers references this task as the task to which they are
	 *         attached. | for each scheduler in Scheduler: | if
	 *         (hasAsScheduler(scheduler)) | then canHaveAsScheduler(scheduler)
	 *         && | (scheduler.getTask() == this)
	 */
	public boolean hasProperSchedulers() {
		for (Scheduler scheduler : schedulers) {
			if (!canHaveAsScheduler(scheduler))
				return false;
			if (scheduler.hasAsTask(this))
				return false;
		}
		return true;
	}

	/**
	 * Return the number of schedulers associated with this task.
	 *
	 * @return The total number of schedulers collected in this task. | result
	 *         == | card({scheduler:Scheduler | hasAsScheduler({scheduler)})
	 */
	public int getNbSchedulers() {
		return schedulers.size();
	}

	/**
	 * Add the given scheduler to the set of schedulers of this task.
	 * 
	 * @param scheduler
	 *            The scheduler to be added.
	 * @pre The given scheduler is effective and already references this task. |
	 *      (scheduler != null) && (scheduler.getTask() == this)
	 * @post This task has the given scheduler as one of its schedulers. |
	 *       new.hasAsScheduler(scheduler)
	 */
	public void addScheduler(@Raw Scheduler scheduler) {
		assert canHaveAsScheduler(scheduler);
		schedulers.add(scheduler);
		scheduler.getAllTasks().add(this);
	}

	/**
	 * Remove the given scheduler from the set of schedulers of this task.
	 * 
	 * @param scheduler
	 *            The scheduler to be removed.
	 * @pre This task has the given scheduler as one of its schedulers, and the
	 *      given scheduler does not reference any task. |
	 *      this.hasAsScheduler(scheduler) && | (scheduler.getTask() == null)
	 * @post This task no longer has the given scheduler as one of its
	 *       schedulers. | ! new.hasAsScheduler(scheduler)
	 */
	@Raw
	public void removeScheduler(Scheduler scheduler) {
		assert this.hasAsScheduler(scheduler);
		schedulers.remove(scheduler);
		scheduler.getAllTasks().remove(this);
		if (this.getUnit().getFaction() == scheduler.getFaction())
			;
		// TODO: stop met uitvoeren van deze task
	}

	public Set<Scheduler> getAllSchedulers() {
		return this.schedulers;
	}

	/**
	 * Variable referencing a set collecting all the schedulers of this task.
	 * 
	 * @invar The referenced set is effective. | schedulers != null
	 * @invar Each scheduler registered in the referenced list is effective and
	 *        not yet terminated. | for each scheduler in schedulers: | (
	 *        (scheduler != null) && | (! scheduler.isTerminated()) )
	 */
	private final Set<Scheduler> schedulers = new HashSet<Scheduler>();

	/**
	 * Set a global variable of this program referenced by a given name to a
	 * given basic expression.
	 * 
	 * @param name
	 *            The name to reference the (new) global variable by.
	 * @param value
	 *            The value to set the (new) global value to in the form of a
	 *            basic expression.
	 */
	public void setGlobalVariable(String name, Object value) {
		this.getGlobalVariables().put(name, value);
	}

	public void removeGlobalVariable(String name) {
		if (!hasGlobalVariable(name)) {
			System.err.println("Variable with the name '" + name + "' doesn't exist!");
		}
		this.getGlobalVariables().remove(name);
	}

	/**
	 * Get the basic expression containing the value of a global variable of
	 * this program by name.
	 * 
	 * @param name
	 *            The name of the global variable.
	 * @return The basic expression containing the value of the global variable.
	 */
	// Public for the purpose of testing. Otherwise protected.
	public Object getGlobalVariable(String name) {
		if (!hasGlobalVariable(name)) {
			System.err.println("Variable with the name '" + name + "' doesn't exist!");
		}
		return getGlobalVariables().get(name);
	}

	/**
	 * Check if there exists a global variable in this program with the given
	 * name.
	 * 
	 * @param name
	 *            The name to check on.
	 * @return True if such a variable exists, false otherwise.
	 */
	protected boolean hasGlobalVariable(String name) {
		return getGlobalVariables().containsKey(name);
	}

	/**
	 * Return the map referencing the names and respective basic expressions
	 * containing the values of the global variables of this program.
	 */
	private Map<String, Object> getGlobalVariables() {
		return globalVariables;
	}

	/**
	 * A map referencing the names and respective basic expressions containing
	 * the values of the global variables of this program.
	 */
	private final Map<String, Object> globalVariables = new HashMap<String, Object>();

	public void execute(Counter counter) {
		if (getActivities().hasBeenFullyExecuted()) {
			getUnit().getFaction().getScheduler().removeTask(this);
			this.setUnit(null);
		} else {
			while (getActivities().canExecute(this, counter) && !getUnit().isDead()) {
				getActivities().execute(this, counter);
			}
		}
	}
}
