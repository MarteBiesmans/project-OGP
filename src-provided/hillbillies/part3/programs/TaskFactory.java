package hillbillies.part3.programs;

import java.util.List;

import hillbillies.model.*;
import hillbillies.model.programs.statements.*;

public class TaskFactory implements ITaskFactory<Expression, Statement, Task> {
	/* TASKS */

	/**
	 * Create a list of tasks from the given arguments.
	 * 
	 * @param name
	 *            The name of the task
	 * @param priority
	 *            The initial priority of the task
	 * @param activity
	 *            The activity of the task. Most likely this is a sequence
	 *            statement.
	 * @param selectedCubes
	 *            A list of cube coordinates (each represented as an array {x,
	 *            y, z}) that were selected by the player in the GUI.
	 * @return A list of new task instances. One task instance should be created
	 *         for each selectedCube coordinate. If selectedCubes is empty and
	 *         the 'selected' expression does not occur in the activity, a list
	 *         with exactly one Task instance should be returned.
	 */
	public List<Task> createTasks(String name, int priority, Statement activity, List<int[]> selectedCubes) {
		//TODO
		return null;
	}

	/* STATEMENTS */

	/**
	 * Create a statement that represents the assignment of a variable.
	 * 
	 * @param variableName
	 *            The name of the assigned variable
	 * @param value
	 *            An expression that evaluates to the assigned value
	 */
	public Statement createAssignment(String variableName, Expression value, SourceLocation sourceLocation) {
		//TODO
		return null;
	}

	/**
	 * Create a statement that represents a while loop.
	 * 
	 * @param condition
	 *            The condition of the while loop
	 * @param body
	 *            The body of the loop (most likely this is a sequence
	 *            statement).
	 */
	public Statement createWhile(Expression condition, Statement body, SourceLocation sourceLocation) {
		//TODO
		return null;
	}

	/**
	 * Create an if-then-else statement.
	 * 
	 * @param condition
	 *            The condition of the if statement
	 * @param ifBody
	 *            * The body of the if-part, which must be executed when the
	 *            condition evaluates to true
	 * @param elseBody
	 *            The body of the else-part, which must be executed when the
	 *            condition evaluates to false. Can be null if no else clause is
	 *            specified.
	 */
	public Statement createIf(Expression condition, Statement ifBody, Statement elseBody, SourceLocation sourceLocation) {
		//TODO
		return null;
	}

	/**
	 * Create a break statement that immediately terminates the enclosing loop.
	 * 
	 * @param sourceLocation
	 * 
	 * @note Students working alone may return null.
	 */
	public Statement createBreak(SourceLocation sourceLocation){
		//TODO
		return null;
	}

	/**
	 * Create a print statement that prints the value obtained by evaluating the
	 * given expression.
	 * 
	 * @param value
	 *            The expression to evaluate and print
	 */
	public Statement createPrint(Expression value, SourceLocation sourceLocation){
		//TODO
		return null;
	}

	/**
	 * Create a sequence of statements.
	 * 
	 * @param statements
	 *            The statements that must be executed in the given order.
	 */
	public Statement createSequence(List<S> statements, SourceLocation sourceLocation){
		//TODO
		return null;
	}

	/**
	 * Create a moveTo statement
	 * 
	 * @param position
	 *            The position to which to move
	 */
	public Statement createMoveTo(Expression position, SourceLocation sourceLocation){
		//TODO
		return null;
	}

	/**
	 * Create a work statement
	 * 
	 * @param position
	 *            The position on which to work
	 */
	public Statement createWork(Expression position, SourceLocation sourceLocation){
		//TODO
		return null;
	}

	/**
	 * Create a follow statement
	 * 
	 * @param unit
	 *            The unit to follow
	 */
	public Statement createFollow(Expression unit, SourceLocation sourceLocation){
		//TODO
		return null;
	}

	/**
	 * Create an attack statement
	 * 
	 * @param unit
	 *            The unit to attack
	 */
	public Statement createAttack(Expression unit, SourceLocation sourceLocation){
		//TODO
		return null;
	}

	/* EXPRESSIONS */

	/**
	 * Create an expression that evaluates to the current value of the given
	 * variable.
	 * 
	 * @param variableName
	 *            The name of the variable to read.
	 */
	public Expression createReadVariable(String variableName, SourceLocation sourceLocation){
		//TODO
		return null;
	}

	/**
	 * Create an expression that evaluates to true when the given position
	 * evaluates to a solid position; false otherwise.
	 * 
	 * @param position
	 *            The position expression
	 */
	public Expression createIsSolid(Expression position, SourceLocation sourceLocation){
		//TODO
		return null;
	}

	/**
	 * Create an expression that evaluates to true when the given position
	 * evaluates to a passable position; false otherwise.
	 * 
	 * @param position
	 *            The position expression
	 */
	public Expression createIsPassable(Expression position, SourceLocation sourceLocation){
		//TODO
		return null;
	}

	/**
	 * Create an expression that evaluates to true when the given unit evaluates
	 * to a unit of the same faction; false otherwise.
	 * 
	 * @param unit
	 *            The unit expression
	 */
	public Expression createIsFriend(Expression unit, SourceLocation sourceLocation){
		//TODO
		return null;
	}

	/**
	 * Create an expression that evaluates to true when the given unit evaluates
	 * to a unit of another faction; false otherwise.
	 * 
	 * @param unit
	 *            The unit expression
	 */
	public Expression createIsEnemy(Expression unit, SourceLocation sourceLocation){
		//TODO
		return null;
	}

	/**
	 * Create an expression that evaluates to true when the given unit evaluates
	 * to a unit that is alive; false otherwise.
	 * 
	 * @param unit
	 *            The unit expression
	 */
	public Expression createIsAlive(Expression unit, SourceLocation sourceLocation){
		//TODO
		return null;
	}

	/**
	 * Create an expression that evaluates to true when the given unit evaluates
	 * to a unit that carries an item; false otherwise.
	 * 
	 * @param unit
	 *            The unit expression
	 */
	public Expression createCarriesItem(Expression unit, SourceLocation sourceLocation){
		//TODO
		return null;
	}

	/**
	 * Create an expression that evaluates to true when the given expression
	 * evaluates to false, and vice versa.
	 * 
	 * @param expression
	 */
	public Expression createNot(Expression expression, SourceLocation sourceLocation){
		//TODO
		return null;
	}

	/**
	 * Create an expression that evaluates to true when both the left and right
	 * expression evaluate to true; false otherwise.
	 * 
	 * @note short-circuit: the right expression does not need to be evaluated
	 *       when the left expression evaluates to false.
	 */
	public Expression createAnd(Expression left, Expression right, SourceLocation sourceLocation){
		//TODO
		return null;
	}

	/**
	 * Create an expression that evaluates to false only when the left and right
	 * expression evaluate to false; true otherwise.
	 * 
	 * @note short-circuit: the right expression does not need to be evaluated
	 *       when the left expression evaluates to true.
	 */
	public Expression createOr(Expression left, Expression right, SourceLocation sourceLocation){
		//TODO
		return null;
	}

	/**
	 * Create an expression that evaluates to the current position of the unit
	 * that is executing the task.
	 */
	public Expression createHerePosition(SourceLocation sourceLocation){
		//TODO
		return null;
	}

	/**
	 * Create an expression that evaluates to the position of a log.
	 * 
	 * @note for groups of two students, this needs to be the log closest to the
	 *       unit that is executing the task.
	 */
	public Expression createLogPosition(SourceLocation sourceLocation){
		//TODO
		return null;
	}

	/**
	 * Create an expression that evaluates to the position of a boulder.
	 * 
	 * @note for groups of two students, this needs to be the boulder closest to
	 *       the unit that is executing the task.
	 */
	public Expression createBoulderPosition(SourceLocation sourceLocation){
		//TODO
		return null;
	}

	/**
	 * Create an expression that evaluates to the position of a workshop.
	 * 
	 * @note for groups of two students, this needs to be the workshop closest
	 *       to the unit that is executing the task.
	 */
	public Expression createWorkshopPosition(SourceLocation sourceLocation){
		//TODO
		return null;
	}

	/**
	 * Create an expression that evaluates to the position selected by the user
	 * in the GUI.
	 * 
	 * @note Students working alone may return null.
	 */
	public Expression createSelectedPosition(SourceLocation sourceLocation){
		//TODO
		return null;
	}

	/**
	 * Create an expression that evaluates to a position next to the given
	 * position.
	 * 
	 * @param position
	 * 
	 */
	public Expression createNextToPosition(Expression position, SourceLocation sourceLocation){
		//TODO
		return null;
	}

	/**
	 * Create an expression that evaluates to a static position with a given
	 * coordinate.
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public Expression createLiteralPosition(int x, int y, int z, SourceLocation sourceLocation){
		//TODO
		return null;
	}

	/**
	 * Create an expression that evaluates to the unit that is currently
	 * executing the task.
	 */
	public Expression createThis(SourceLocation sourceLocation){
		//TODO
		return null;
	}

	/**
	 * Create an expression that evaluates to a unit that is part of the same
	 * faction as the unit currently executing the task.
	 */
	public Expression createFriend(SourceLocation sourceLocation){
		//TODO
		return null;
	}

	/**
	 * Create an expression that evaluates to a unit that is not part of the
	 * same faction as the unit currently executing the task.
	 */
	public Expression createEnemy(SourceLocation sourceLocation){
		//TODO
		return null;
	}

	/**
	 * Create an expression that evaluates to any unit (other than this).
	 */
	public Expression createAny(SourceLocation sourceLocation){
		//TODO
		return null;
	}

	/**
	 * Create an expression that evaluates to true.
	 */
	public Expression createTrue(SourceLocation sourceLocation){
		//TODO
		return null;
	}

	/**
	 * Create an expression that evaluates to false.
	 */
	public Expression createFalse(SourceLocation sourceLocation){
		//TODO
		return null;
	}
}
