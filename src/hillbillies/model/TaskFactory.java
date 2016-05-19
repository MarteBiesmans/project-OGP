package hillbillies.model;

import java.util.ArrayList;
import java.util.List;

import hillbillies.model.programs.expressions.*;
import hillbillies.model.programs.statements.*;
import hillbillies.model.programs.type.*;
import hillbillies.part3.programs.ITaskFactory;
import hillbillies.part3.programs.SourceLocation;

public class TaskFactory implements ITaskFactory<IExpression<?>, Statement, Task> {

	public TaskFactory() {
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
	public Statement createAssignment(String variableName, IExpression<?> value, SourceLocation sourceLocation) {
		return new AssignmentStatement(variableName, value);
	}

	@Override
	public Statement createWhile(IExpression<?> condition, Statement body, SourceLocation sourceLocation) {
		return new WhileStatement((IBooleanExpression) condition, body);
	}

	@Override
	public Statement createIf(IExpression<?> condition, Statement ifBody, Statement elseBody,
			SourceLocation sourceLocation) {
		return new IfStatement((BooleanExpression) condition, ifBody, elseBody);
	}

	@Override
	public Statement createBreak(SourceLocation sourceLocation) {
		return new BreakStatement();
	}

	@Override
	public Statement createPrint(IExpression<?> value, SourceLocation sourceLocation) {
		return new PrintStatement(value);
	}

	@Override
	public Statement createSequence(List<Statement> statements, SourceLocation sourceLocation) {
		return new SequenceOfStatements(statements);
	}

	@Override
	public Statement createMoveTo(IExpression<?> position, SourceLocation sourceLocation) {
		return new MoveToStatement((ICubeExpression) position);
	}

	@Override
	public Statement createWork(IExpression<?> position, SourceLocation sourceLocation) {
		return new WorkStatement((ICubeExpression) position);
	}

	@Override
	public Statement createFollow(IExpression<?> unit, SourceLocation sourceLocation) {
		return new FollowStatement((IUnitExpression) unit);
	}

	@Override
	public Statement createAttack(IExpression<?> unit, SourceLocation sourceLocation) {
		return new AttackStatement((IUnitExpression) unit);
	}

	@Override
	public  VariableExpression<?> createReadVariable(String variableName, SourceLocation sourceLocation) {
		return new VariableExpression(variableName);
	}

	@Override
	public IsSolidExpression createIsSolid(IExpression<?> position, SourceLocation sourceLocation) {
		return new IsSolidExpression((CubeExpression) position);
	}

	@Override
	public Expression<BooleanType> createIsPassable(IExpression<?> position, SourceLocation sourceLocation) {
		return new IsPassableExpression((CubeExpression) position);
	}

	@Override
	public Expression<BooleanType> createIsFriend(IExpression<?> unit, SourceLocation sourceLocation) {
		return new IsFriendExpression((UnitExpression) unit);
	}

	@Override
	public Expression<BooleanType> createIsEnemy(IExpression<?> unit, SourceLocation sourceLocation) {
		return new IsEnemyExpression((UnitExpression) unit);
	}

	@Override
	public Expression<BooleanType> createIsAlive(IExpression<?> unit, SourceLocation sourceLocation) {
		return new IsAliveExpression((UnitExpression) unit);
	}

	@Override
	public Expression<BooleanType> createCarriesItem(IExpression<?> unit, SourceLocation sourceLocation) {
		return new CarriesItemExpression((UnitExpression) unit);
	}

	@Override
	public Expression<BooleanType> createNot(IExpression<?> expression, SourceLocation sourceLocation) {
		return new NotExpression((BooleanExpression) expression);
	}

	@Override
	public Expression<BooleanType> createAnd(IExpression<?> left, IExpression<?> right, SourceLocation sourceLocation) {
		return new AndExpression((BooleanExpression) left, (BooleanExpression) right);
	}

	@Override
	public Expression<BooleanType> createOr(IExpression<?> left, IExpression<?> right, SourceLocation sourceLocation) {
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
	public Expression<CubeType> createNextToPosition(IExpression<?> position, SourceLocation sourceLocation) {
		return new NextToExpression((CubeExpression) position);
	}

	@Override
	public Expression<CubeType> createPositionOf(IExpression<?> unit, SourceLocation sourceLocation) {
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
