//TODO	comments (formal!)
package hillbillies.model;

import java.util.HashSet;
import java.util.Set;

import be.kuleuven.cs.som.annotate.*;

/**
 * @invar   Each scheduler must have proper tasks.
 *        | hasProperTasks()
 */
public class Scheduler {

	/**
	 * Initialize this new scheduler as a non-terminated scheduler with 
	 * no tasks yet.
	 * 
	 * @post   This new scheduler has no tasks yet.
	 *       | new.getNbTasks() == 0
	 */
	@Raw
	public Scheduler() {
	}

	/**
	 * Check whether this scheduler has the given task as one of its
	 * tasks.
	 * 
	 * @param  task
	 *         The task to check.
	 */
	@Basic
	@Raw
	public boolean hasAsTask(@Raw Task task) {
		return tasks.contains(task);
	}

	/**
	 * Check whether this scheduler can have the given task
	 * as one of its tasks.
	 * 
	 * @param  task
	 *         The task to check.
	 * @return True if and only if the given task is effective
	 *         and that task is a valid task for a scheduler.
	 *       | result ==
	 *       |   (task != null) &&
	 *       |   Task.isValidScheduler(this)
	 */
	@Raw
	public boolean canHaveAsTask(Task task) {
		return (task != null);
	}

	/**
	 * Check whether this scheduler has proper tasks attached to it.
	 * 
	 * @return True if and only if this scheduler can have each of the
	 *         tasks attached to it as one of its tasks,
	 *         and if each of these tasks references this scheduler as
	 *         the scheduler to which they are attached.
	 *       | for each task in Task:
	 *       |   if (hasAsTask(task))
	 *       |     then canHaveAsTask(task) &&
	 *       |          (task.getScheduler() == this)
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
	 * @return  The total number of tasks collected in this scheduler.
	 *        | result ==
	 *        |   card({task:Task | hasAsTask({task)})
	 */
	public int getNbTasks() {
		return tasks.size();
	}

	/**
	 * Add the given task to the set of tasks of this scheduler.
	 * 
	 * @param  task
	 *         The task to be added.
	 * @pre    The given task is effective and already references
	 *         this scheduler.
	 *       | (task != null) && (task.getScheduler() == this)
	 * @post   This scheduler has the given task as one of its tasks.
	 *       | new.hasAsTask(task)
	 */
	public void addTask(@Raw Task task) {
		assert (task != null);
		tasks.add(task);
	}

	/**
	 * Remove the given task from the set of tasks of this scheduler.
	 * 
	 * @param  task
	 *         The task to be removed.
	 * @pre    This scheduler has the given task as one of
	 *         its tasks, and the given task does not
	 *         reference any scheduler.
	 *       | this.hasAsTask(task) &&
	 *       | (task.getScheduler() == null)
	 * @post   This scheduler no longer has the given task as
	 *         one of its tasks.
	 *       | ! new.hasAsTask(task)
	 */
	@Raw
	public void removeTask(Task task) {
		assert this.hasAsTask(task);
		tasks.remove(task);
	}

	/**
	 * Variable referencing a set collecting all the tasks
	 * of this scheduler.
	 * 
	 * @invar  The referenced set is effective.
	 *       | tasks != null
	 * @invar  Each task registered in the referenced list is
	 *         effective and not yet terminated.
	 *       | for each task in tasks:
	 *       |   ( (task != null) &&
	 *       |     (! task.isTerminated()) )
	 */
	private final Set<Task> tasks = new HashSet<Task>();
	
}
