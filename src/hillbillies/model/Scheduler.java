package hillbillies.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;

import be.kuleuven.cs.som.annotate.*;

/**
 * @invar Each scheduler must have proper tasks. | hasProperTasks()
 * @invar Each scheduler can have its faction as faction. |
 *        canHaveAsFaction(this.getFaction()
 */
public class Scheduler {

	/**
	 * Initialize this new scheduler with given faction.
	 * 
	 * @param faction
	 *            The faction for this new scheduler.
	 * @post The faction of this new scheduler is equal to the given faction. |
	 *       new.getFaction() == faction
	 * @throws IllegalArgumentException
	 *             This new scheduler cannot have the given faction as its
	 *             faction. | ! canHaveAsFaction(this.getFaction()))
	 */
	public Scheduler(Faction faction) throws IllegalArgumentException {
		if (!canHaveAsFaction(faction))
			throw new IllegalArgumentException();
		this.faction = faction;
	}

	/**
	 * Check whether this scheduler has the given task as one of its tasks.
	 * 
	 * @param task
	 *            The task to check.
	 */
	@Basic
	@Raw
	public boolean hasAsTask(@Raw Task task) {
		return tasks.contains(task);
	}

	/**
	 * Check whether this scheduler can have the given task as one of its tasks.
	 * 
	 * @param task
	 *            The task to check.
	 * @return True if and only if the given task is effective and that task is
	 *         a valid task for a scheduler. | result == | (task != null) && |
	 *         Task.isValidScheduler(this)
	 */
	@Raw
	public boolean canHaveAsTask(Task task) {
		return (task != null);
	}

	/**
	 * Check whether this scheduler has proper tasks attached to it.
	 * 
	 * @return True if and only if this scheduler can have each of the tasks
	 *         attached to it as one of its tasks, and if each of these tasks
	 *         references this scheduler as the scheduler to which they are
	 *         attached. | for each task in Task: | if (hasAsTask(task)) | then
	 *         canHaveAsTask(task) && | (task.getScheduler() == this)
	 */
	public boolean hasProperTasks() {
		for (Task task : tasks) {
			if (!canHaveAsTask(task))
				return false;
		}
		return true;
	}

	/**
	 * Return the number of tasks associated with this scheduler.
	 *
	 * @return The total number of tasks collected in this scheduler. | result
	 *         == | card({task:Task | hasAsTask({task)})
	 */
	public int getNbTasks() {
		return tasks.size();
	}

	/**
	 * Add the given task to the set of tasks of this scheduler.
	 * 
	 * @param task
	 *            The task to be added.
	 * @pre The given task is effective and already references this scheduler. |
	 *      (task != null) && (task.getScheduler() == this)
	 * @post This scheduler has the given task as one of its tasks. |
	 *       new.hasAsTask(task)
	 */
	public void addTask(@Raw Task task) {
		assert canHaveAsTask(task);
		tasks.add(task);
		task.getAllSchedulers().add(this);
	}

	/**
	 * Remove the given task from the set of tasks of this scheduler.
	 * 
	 * @param task
	 *            The task to be removed.
	 * @pre This scheduler has the given task as one of its tasks, and the given
	 *      task does not reference any scheduler. | this.hasAsTask(task) && |
	 *      (task.getScheduler() == null)
	 * @post This scheduler no longer has the given task as one of its tasks. |
	 *       ! new.hasAsTask(task)
	 * @throws IllegalStateException
	 * 			if this scheduler doesn't have the given task
	 * 			| !hasAsTask(task)
	 */
	@Raw
	public void removeTask(Task task) throws IllegalStateException {
		if (this.hasAsTask(task))
			throw new IllegalStateException();
		tasks.remove(task);
		task.getAllSchedulers().remove(this);
	}

	public Set<Task> getAllTasks() {
		return this.tasks;
	}
	
	/**
	 * Replace the original task by the replacement task
	 * @param original
	 * 		the task to replace
	 * @param replacement
	 * 		the task that replaces the other task
	 * @throws IllegalStateException
	 * 			if this scheduler doesn't have the original task as task
	 * 			| !hasAsTask(original)
	 * @effect if the original task is being executed, it will be stopped
	 * 		| if (original.isBeingExecuted()
	 * 		|	then original.getUnit().setTask(null) && original.getUnit().nextActivity()
	 * @effect the original task is removed and the replacement task is placed in this scheduler
	 * 		| removeTask(original) && addTask(replacement)
	 */
	public void replaceTask(Task original, Task replacement) throws IllegalStateException {
		if (!this.hasAsTask(original))
			throw new IllegalStateException();
		if (original.isBeingExecuted()) {
			original.getUnit().setTask(null);
			original.getUnit().nextActivity();
		}
		this.removeTask(original);
		this.addTask(replacement);

	}

	/**
	 * checks whether all the given tasks are part of this scheduler
	 * @param tasks
	 * 			the tasks to check to be in this scheduler
	 * @return true if all tasks are part of this scheduler
	 * 			| for each task in tasks
	 * 			|	hasAsTask(task)
	 * @return false if there is at least one task that is not part of this scheduler
	 */
	public boolean areTasksPartOf(Collection<Task> tasks) {
		for (Task task : tasks) {
			if (!this.hasAsTask(task))
				return false;
		}
		return true;
	}

	/**
	 * returns an iterator over the tasks of this scheduler sorted by descending priority
	 * @return
	 */
	public Iterator<Task> getAllTasksIterator() {
		return new Iterator<Task>() {
			public boolean hasNext() {
				return (index < sortedTasks.size() - 1);
			}

			public Task next() {
				index += 1;
				return sortedTasks.get(index);
			}

			private ArrayList<Task> sortedTasks = getSortedTasks();
			private int index = -1;
		};
	}

	/**
	 * returns a list of sorted tasks descending by priority
	 * @return
	 */
	public ArrayList<Task> getSortedTasks() {
		ArrayList<Task> result = new ArrayList<Task>(this.tasks);
		result.sort((a, b) -> a.compareTo(b));
		return result;
	}

	/**
	 * returns the task in this scheduler with the highest priority that is not being executed
	 * @return
	 */
	public Task getHighestPriorityTaskNotExecuted() {
		for (Task task : getSortedTasks()) {
			if (!task.isBeingExecuted())
				return task;
		}
		return null;
	}

	/**
	 * assign the given task to the given unit.
	 * @param task
	 * 			the task to assign to the unit
	 * @param unit
	 * 			the unit the task will be assigned to
	 * @effect if the task is being executed, it will be stopped
	 * @effect if the unit is executing a task, it will be stopped
	 * @effect the unit and task will be assigned to eachother
	 * 		|unit.setTask(task)
	 * 		|task.setUnit(unit)
	 */
	public void assignTaskToUnit(Task task, Unit unit) {
		if (task.isBeingExecuted() && task.getUnit() != unit) {
			task.getUnit().setTask(null);
			task.getUnit().nextActivity();
		}
		if (unit.getTask() != null) {
			unit.getTask().setPriority(unit.getTask().getPriority() - 1);
			unit.setTask(null);
		}
		unit.setTask(task);
		task.setUnit(unit);
	}

	/**
	 * the given unit will stop doing the given task
	 * @param task
	 * 			the task the unit should stop doing
	 * @param unit
	 * 			the unit that should stop doing the task
	 * @throws IllegalStateException
	 * 			if the given task is not assigned to the given unit
	 * 			| (task.getUnit() != unit || unit.getTask() != task)
	 * @effect the unit will be doing the next activity and the task will be in this scheduler
	 * 			the priority of the task will be lowered.
	 * 			| unit.setTask(null) && unit.nextActivity() && task.setUnit(null) && task.setPriority(task.getPriority - 1)
	 */
	public void resetTaskToUnit(Task task, Unit unit) throws IllegalStateException {
		if (task.getUnit() != unit || unit.getTask() != task)
			throw new IllegalStateException();
		unit.setTask(null);
		unit.nextActivity();
		task.setUnit(null);
		task.setPriority(task.getPriority() - 1);
	}

	/**
	 * return all tasks that satisfy the given condition
	 * @param condition
	 * 			the condition all returned tasks should satisfy
	 * @return all tasks that satisfy the given condition
	 */
	public Set<Task> getAllTasksThatSatisfy(Predicate<Task> condition) {
		Set<Task> tasksSoFar = new HashSet<Task>(getAllTasks());
		tasksSoFar.stream().filter(condition);
		return tasksSoFar;
	}

	/**
	 * Variable referencing a set collecting all the tasks of this scheduler.
	 * 
	 * @invar The referenced set is effective. | tasks != null
	 * @invar Each task registered in the referenced list is effective and not
	 *        yet terminated. | for each task in tasks: | ( (task != null) && |
	 *        (! task.isTerminated()) )
	 */
	private final Set<Task> tasks = new HashSet<Task>();

	/**
	 * Return the faction of this scheduler.
	 */
	@Basic
	@Raw
	@Immutable
	public Faction getFaction() {
		return this.faction;
	}

	/**
	 * Check whether this scheduler can have the given faction as its faction.
	 * 
	 * @param faction
	 *            The faction to check.
	 * @return | result == (faction != null)
	 */
	@Raw
	public boolean canHaveAsFaction(Faction faction) {
		return faction != null;
	}

	/**
	 * Variable registering the faction of this scheduler.
	 */
	private final Faction faction;

}
