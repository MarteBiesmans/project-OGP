package hillbillies.model;

import java.util.ArrayList;
import java.util.List;

import hillbillies.model.programs.expressions.*;
import hillbillies.model.programs.statements.*;
import hillbillies.model.programs.type.*;
import hillbillies.part3.programs.ITaskFactory;
import hillbillies.part3.programs.SourceLocation;

public class TaskFactory implements ITaskFactory<Expression<?>, Statement, Task> {

	public TaskFactory() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Task> createTasks(String name, int priority, Statement activity, List<int[]> selectedCubes) {
		List<Task> result = new ArrayList<>();
		for (int[] element : selectedCubes) {
			result.add(new Task(name, priority, activity, new Cube(element[0], element[1], element[2])));
		}
		return result;
	}

	@Override
	public Statement createAssignment(String variableName, Expression<?> value, SourceLocation sourceLocation) {
		return new AssignmentStatement(variableName, value);
	}

	@Override
	public Statement createWhile(Expression<?> condition, Statement body, SourceLocation sourceLocation) {
		return new WhileStatement((BooleanExpression) condition, body);
	}

	@Override
	public Statement createIf(Expression<?> condition, Statement ifBody, Statement elseBody,
			SourceLocation sourceLocation) {
		return new IfStatement((BooleanExpression) condition, ifBody, elseBody);
	}

	@Override
	public Statement createBreak(SourceLocation sourceLocation) {
		return new BreakStatement();
	}

	@Override
	public Statement createPrint(Expression<?> value, SourceLocation sourceLocation) {
		return new PrintStatement(value, this);
	}

	@Override
	public Statement createSequence(List<Statement> statements, SourceLocation sourceLocation) {
		return new SequenceOfStatements(statements);
	}

	@Override
	public Statement createMoveTo(Expression<?> position, SourceLocation sourceLocation) {
		return new MoveToStatement((CubeExpression) position);
	}

	@Override
	public Statement createWork(Expression<?> position, SourceLocation sourceLocation) {
		return new WorkStatement((CubeExpression) position);
	}

	@Override
	public Statement createFollow(Expression<?> unit, SourceLocation sourceLocation) {
		return new FollowStatement((UnitExpression) unit);
	}

	@Override
	public Statement createAttack(Expression<?> unit, SourceLocation sourceLocation) {
		return new AttackStatement((UnitExpression) unit);
	}

	@Override
	public Expression<?> createReadVariable(String variableName, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<BooleanType> createIsSolid(Expression<?> position, SourceLocation sourceLocation) {
		return new IsSolidExpression((CubeExpression) position);
	}

	@Override
	public Expression<BooleanType> createIsPassable(Expression<?> position, SourceLocation sourceLocation) {
		return new IsPassableExpression((CubeExpression) position);
	}

	@Override
	public Expression<BooleanType> createIsFriend(Expression<?> unit, SourceLocation sourceLocation) {
		return new IsFriendExpression((UnitExpression) unit);
	}

	@Override
	public Expression<BooleanType> createIsEnemy(Expression<?> unit, SourceLocation sourceLocation) {
		return new IsEnemyExpression((UnitExpression) unit);
	}

	@Override
	public Expression<BooleanType> createIsAlive(Expression<?> unit, SourceLocation sourceLocation) {
		return new IsAliveExpression((UnitExpression) unit);
	}

	@Override
	public Expression<BooleanType> createCarriesItem(Expression<?> unit, SourceLocation sourceLocation) {
		return new CarriesItemExpression((UnitExpression) unit);
	}

	@Override
	public Expression<BooleanType> createNot(Expression<?> expression, SourceLocation sourceLocation) {
		return new NotExpression((BooleanExpression) expression);
	}

	@Override
	public Expression<BooleanType> createAnd(Expression<?> left, Expression<?> right, SourceLocation sourceLocation) {
		return new AndExpression((BooleanExpression) left, (BooleanExpression) right);
	}

	@Override
	public Expression<BooleanType> createOr(Expression<?> left, Expression<?> right, SourceLocation sourceLocation) {
		return new OrExpression((BooleanExpression) left, (BooleanExpression) right);
	}

	@Override
	public Expression<CubeType> createHerePosition(SourceLocation sourceLocation) {
		return new HereExpression();
	}

	@Override
	public Expression<CubeType> createLogPosition(SourceLocation sourceLocation) {
		return new LogExpression();
	}

	@Override
	public Expression<CubeType> createBoulderPosition(SourceLocation sourceLocation) {
		return new BoulderExpression();
	}

	@Override
	public Expression<CubeType> createWorkshopPosition(SourceLocation sourceLocation) {
		return new WorkshopExpression();
	}

	@Override
	public Expression<CubeType> createSelectedPosition(SourceLocation sourceLocation) {
		return new SelectedExpression();
	}

	@Override
	public Expression<CubeType> createNextToPosition(Expression<?> position, SourceLocation sourceLocation) {
		return new NextToExpression((CubeExpression) position);
	}

	@Override
	public Expression<CubeType> createPositionOf(Expression<?> unit, SourceLocation sourceLocation) {
		return new PositionOfExpression((UnitExpression) unit);
	}

	@Override
	public Expression<CubeType> createLiteralPosition(int x, int y, int z, SourceLocation sourceLocation) {
		return new XYZExpression(x, y, z);
	}

	@Override
	public Expression<UnitType> createThis(SourceLocation sourceLocation) {
		return new ThisExpression();
	}

	@Override
	public Expression<UnitType> createFriend(SourceLocation sourceLocation) {
		return new FriendExpression();
	}

	@Override
	public Expression<UnitType> createEnemy(SourceLocation sourceLocation) {
		return new EnemyExpression();
	}

	@Override
	public Expression<UnitType> createAny(SourceLocation sourceLocation) {
		return new AnyExpression();
	}

	@Override
	public Expression<BooleanType> createTrue(SourceLocation sourceLocation) {
		return new TrueExpression();
	}

	@Override
	public Expression<BooleanType> createFalse(SourceLocation sourceLocation) {
		return new FalseExpression();
	}

}
