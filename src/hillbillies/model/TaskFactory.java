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
	public AssignmentStatement createAssignment(String variableName, IExpression<?> value, SourceLocation sourceLocation) {
		return new AssignmentStatement(variableName, value);
	}

	@Override
	public WhileStatement createWhile(IExpression<?> condition, Statement body, SourceLocation sourceLocation) {
		return new WhileStatement((IBooleanExpression) condition, body);
	}

	@Override
	public IfStatement createIf(IExpression<?> condition, Statement ifBody, Statement elseBody,
			SourceLocation sourceLocation) {
		return new IfStatement((BooleanExpression) condition, ifBody, elseBody);
	}

	@Override
	public BreakStatement createBreak(SourceLocation sourceLocation) {
		return new BreakStatement();
	}

	@Override
	public PrintStatement createPrint(IExpression<?> value, SourceLocation sourceLocation) {
		return new PrintStatement(value);
	}

	@Override
	public SequenceOfStatements createSequence(List<Statement> statements, SourceLocation sourceLocation) {
		return new SequenceOfStatements(statements);
	}

	@Override
	public MoveToStatement createMoveTo(IExpression<?> position, SourceLocation sourceLocation) {
		return new MoveToStatement((ICubeExpression) position);
	}

	@Override
	public WorkStatement createWork(IExpression<?> position, SourceLocation sourceLocation) {
		return new WorkStatement((ICubeExpression) position);
	}

	@Override
	public FollowStatement createFollow(IExpression<?> unit, SourceLocation sourceLocation) {
		return new FollowStatement((IUnitExpression) unit);
	}

	@Override
	public AttackStatement createAttack(IExpression<?> unit, SourceLocation sourceLocation) {
		return new AttackStatement((IUnitExpression) unit);
	}

	@Override
	public  VariableExpression<?> createReadVariable(String variableName, SourceLocation sourceLocation) {
		return new VariableExpression(variableName);
	}

	@Override
	public IsSolidExpression createIsSolid(IExpression<?> position, SourceLocation sourceLocation) {
		return new IsSolidExpression((ICubeExpression) position);
	}

	@Override
	public IsPassableExpression createIsPassable(IExpression<?> position, SourceLocation sourceLocation) {
		return new IsPassableExpression((ICubeExpression) position);
	}

	@Override
	public IsFriendExpression createIsFriend(IExpression<?> unit, SourceLocation sourceLocation) {
		return new IsFriendExpression((IUnitExpression) unit);
	}

	@Override
	public IsEnemyExpression createIsEnemy(IExpression<?> unit, SourceLocation sourceLocation) {
		return new IsEnemyExpression((IUnitExpression) unit);
	}

	@Override
	public IsAliveExpression createIsAlive(IExpression<?> unit, SourceLocation sourceLocation) {
		return new IsAliveExpression((IUnitExpression) unit);
	}

	@Override
	public CarriesItemExpression createCarriesItem(IExpression<?> unit, SourceLocation sourceLocation) {
		return new CarriesItemExpression((IUnitExpression) unit);
	}

	@Override
	public NotExpression createNot(IExpression<?> expression, SourceLocation sourceLocation) {
		return new NotExpression((IBooleanExpression) expression);
	}

	@Override
	public AndExpression createAnd(IExpression<?> left, IExpression<?> right, SourceLocation sourceLocation) {
		return new AndExpression((IBooleanExpression) left, (BooleanExpression) right);
	}

	@Override
	public OrExpression createOr(IExpression<?> left, IExpression<?> right, SourceLocation sourceLocation) {
		return new OrExpression((IBooleanExpression) left, (BooleanExpression) right);
	}

	@Override
	public HereExpression createHerePosition(SourceLocation sourceLocation) {
		return new HereExpression();
	}

	@Override
	public LogExpression createLogPosition(SourceLocation sourceLocation) {
		return new LogExpression();
	}

	@Override
	public BoulderExpression createBoulderPosition(SourceLocation sourceLocation) {
		return new BoulderExpression();
	}

	@Override
	public WorkshopExpression createWorkshopPosition(SourceLocation sourceLocation) {
		return new WorkshopExpression();
	}

	@Override
	public SelectedExpression createSelectedPosition(SourceLocation sourceLocation) {
		return new SelectedExpression();
	}

	@Override
	public NextToExpression createNextToPosition(IExpression<?> position, SourceLocation sourceLocation) {
		return new NextToExpression((ICubeExpression) position);
	}

	@Override
	public PositionOfExpression createPositionOf(IExpression<?> unit, SourceLocation sourceLocation) {
		return new PositionOfExpression((IUnitExpression) unit);
	}

	@Override
	public XYZExpression createLiteralPosition(int x, int y, int z, SourceLocation sourceLocation) {
		return new XYZExpression(x, y, z);
	}

	@Override
	public ThisExpression createThis(SourceLocation sourceLocation) {
		return new ThisExpression();
	}

	@Override
	public FriendExpression createFriend(SourceLocation sourceLocation) {
		return new FriendExpression();
	}

	@Override
	public EnemyExpression createEnemy(SourceLocation sourceLocation) {
		return new EnemyExpression();
	}

	@Override
	public AnyExpression createAny(SourceLocation sourceLocation) {
		return new AnyExpression();
	}

	@Override
	public TrueExpression createTrue(SourceLocation sourceLocation) {
		return new TrueExpression();
	}

	@Override
	public FalseExpression createFalse(SourceLocation sourceLocation) {
		return new FalseExpression();
	}

}
