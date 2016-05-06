//TODO	comments (formal!)
package hillbillies.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.programs.statements.Statement;

/**
 * @invar The name of each task must be a valid name for any task. |
 *        isValidName(getName())
 * @invar The priority of each task must be a valid priority for any task. |
 *        isValidPriority(getPriority())
 * @invar The activities of each task must be a valid activities for any task. |
 *        isValidActivities(getActivities())
 * @invar The unit of each task must be a valid unit for any task. |
 *        isValidUnit(getUnit())
 * @invar Each task must have proper schedulers. | hasProperSchedulers()
 */
public class Task {

	/**
	 * Initialize this new task with given name, priority and activities.
	 * This new task will be intitialized as a non-terminated task with no unit yet.
	 * and with no schedulers yet.
	 *
	 * @param name
	 *            The name for this new task.
	 * @effect The name of this new task is set to the given name. |
	 *         this.setName(name)
	 * @param priority
	 *            The priority for this new task.
	 * @effect The priority of this new task is set to the given priority. |
	 *         this.setPriority(priority)
	 * @param activities
	 *            The activities for this new task.
	 * @effect The activities of this new task is set to the given activities. |
	 *         this.setActivities(activities)
	 * @post This new task has no schedulers yet. | new.getNbSchedulers() == 0
	 */
	public Task(String name, int priority, Statement activities, Cube cube) throws IllegalArgumentException {
		this.setName(name);
		this.setPriority(priority);
		this.setActivities(activities);
		this.setUnit(null);
		this.cube = cube;
	}
	
	//TODO: fancy maken -> gebruik template
	private Cube cube;

	/**
	 * Return the name of this task.
	 */
	@Basic
	@Raw
	public String getName() {
		return this.name;
	}

	/**
	 * Check whether the given name is a valid name for any task.
	 * 
	 * @param name
	 *            The name to check.
	 * @return true if the given name is not null
	 * 			| result == (name != null)
	 */
	public static boolean isValidName(String name) {
		return (name != null);
	}

	/**
	 * Set the name of this task to the given name.
	 * 
	 * @param name
	 *            The new name for this task.
	 * @post The name of this new task is equal to the given name. |
	 *       new.getName() == name
	 * @throws IllegalArgumentException
	 *             The given name is not a valid name for any task. | !
	 *             isValidName(getName())
	 */
	@Raw
	public void setName(String name) throws IllegalArgumentException {
		if (!isValidName(name))
			throw new IllegalArgumentException();
		this.name = name;
	}

	/**
	 * Variable registering the name of this task.
	 */
	private String name;

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
	 * @return true
	 * | result == true
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
	public List<Statement> getActivities() {
		return this.activities;
	}

	/**
	 * Check whether the given activities is a valid activities for any task.
	 * 
	 * @param activities
	 *            The activities to check.
	 * @return false if the given list of activities is null or empty.
	 * | result == (!activities == null || activities.size != 0)
	 */
	public static boolean isValidActivities(List<Statement> activities) {
		return activities == null || activities.size() != 0;
	}

	/**
	 * Set the activities of this task to the given activities.
	 * 
	 * @param activities
	 *            The new activities for this task.
	 * @post The activities of this new task is equal to the given activities. |
	 *       new.getActivities() == activities
	 * @throws IllegalArgumentException
	 *             The given activities is not a valid activities for any task.
	 *             | ! isValidActivities(getActivities())
	 */
	@Raw
	public void setActivities(List<Statement> activities) throws IllegalArgumentException {
		if (!isValidActivities(activities))
			throw new IllegalArgumentException();
		this.activities = activities;
	}

	/**
	 * Variable registering the activities of this task.
	 */
	private List<Statement> activities;

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
	 * @return true
	 * | result == true
	 */
	public static boolean isValidUnit(Unit unit) {
		return true;
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
	
	public static boolean isValidScheduler(Scheduler scheduler) {
		return true;
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
		return (scheduler != null) && (Scheduler.isValidTask(this));
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
			if (!scheduler.hasAsTask(this))
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
		assert (scheduler != null) && (scheduler.hasAsTask(this));
		schedulers.add(scheduler);
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
		assert this.hasAsScheduler(scheduler) && (!scheduler.hasAsTask(this));
		schedulers.remove(scheduler);
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

}
