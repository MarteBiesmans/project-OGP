package hillbillies.part2.facade;

import java.util.Set;

import hillbillies.model.*;
import hillbillies.part2.listener.TerrainChangeListener;
import ogp.framework.util.ModelException;

public class Facade implements IFacade {

	@Override
	public Unit createUnit(String name, int[] initialPosition, int weight, int agility, int strength, int toughness,
			boolean enableDefaultBehavior) throws ModelException {
		try {
			return new Unit((double) initialPosition[0], (double) initialPosition[1], (double) initialPosition[2], name,
					strength, agility, toughness, weight, enableDefaultBehavior);
		} catch (Throwable e) {
			throw new ModelException();
		}
	}

	@Override
	public double[] getPosition(Unit unit) throws ModelException {
		try {
			return new double[] { unit.getPosition().getRealX(), unit.getPosition().getRealY(),
					unit.getPosition().getRealZ() };
		} catch (Throwable e) {
			throw new ModelException();
		}
	}

	@Override
	public int[] getCubeCoordinate(Unit unit) throws ModelException {
		try {
			return new int[] { unit.getCube().getX(), unit.getCube().getY(), unit.getCube().getZ() };
		} catch (Throwable e) {
			throw new ModelException();
		}
	}

	@Override
	public String getName(Unit unit) throws ModelException {
		try {
			return unit.getName();
		} catch (Throwable e) {
			throw new ModelException();
		}
	}

	@Override
	public void setName(Unit unit, String newName) throws ModelException {
		try {
			unit.setName(newName);
		} catch (Throwable e) {
			throw new ModelException();
		}
	}

	@Override
	public int getWeight(Unit unit) throws ModelException {
		return unit.getWeight();
	}

	@Override
	public void setWeight(Unit unit, int newValue) throws ModelException {
		unit.setWeight(newValue);
	}

	@Override
	public int getStrength(Unit unit) throws ModelException {
		return unit.getStrength();
	}

	@Override
	public void setStrength(Unit unit, int newValue) throws ModelException {
		unit.setStrength(newValue);

	}

	@Override
	public int getAgility(Unit unit) throws ModelException {
		return unit.getAgility();
	}

	@Override
	public void setAgility(Unit unit, int newValue) throws ModelException {
		unit.setAgility(newValue);
	}

	@Override
	public int getToughness(Unit unit) throws ModelException {
		return unit.getToughness();
	}

	@Override
	public void setToughness(Unit unit, int newValue) throws ModelException {
		unit.setToughness(newValue);
	}

	@Override
	public int getMaxHitPoints(Unit unit) throws ModelException {
		return unit.getMaxHitpoints();
	}

	@Override
	public int getCurrentHitPoints(Unit unit) throws ModelException {
		return (int) unit.getHitpoints();
	}

	@Override
	public int getMaxStaminaPoints(Unit unit) throws ModelException {
		return unit.getMaxStaminaPoints();
	}

	@Override
	public int getCurrentStaminaPoints(Unit unit) throws ModelException {
		return (int) unit.getStaminaPoints();
	}

	@Override
	public void advanceTime(Unit unit, double dt) throws ModelException {
		unit.advanceTime((float) dt);
	}

	@Override
	public void moveToAdjacent(Unit unit, int dx, int dy, int dz) throws ModelException {
		try {
			unit.moveToAdjacent(dx, dy, dz);
		} catch (Throwable e) {
			throw new ModelException();
		}
	}

	@Override
	public double getCurrentSpeed(Unit unit) throws ModelException {
		return unit.getMovementSpeed();
	}

	@Override
	public boolean isMoving(Unit unit) throws ModelException {
		return unit.isMoving();
	}

	@Override
	public void startSprinting(Unit unit) throws ModelException {
		unit.startSprinting();
	}

	@Override
	public void stopSprinting(Unit unit) throws ModelException {
		unit.stopSprinting();
	}

	@Override
	public boolean isSprinting(Unit unit) throws ModelException {
		return unit.isSprinting();
	}

	@Override
	public double getOrientation(Unit unit) throws ModelException {
		return unit.getOrientation();
	}

	@Override
	public void moveTo(Unit unit, int[] cube) throws ModelException {
		try {
			unit.moveTo(cube[0], cube[1], cube[2]);
		} catch (Throwable e) {
			throw new ModelException();
		}
	}

	@Override
	public void work(Unit unit) throws ModelException {
		try {
			unit.work();
		} catch (Throwable e) {
			throw new ModelException();
		}
	}

	@Override
	public boolean isWorking(Unit unit) throws ModelException {
		return unit.isWorking();
	}

	@Override
	public void fight(Unit attacker, Unit defender) throws ModelException {
		try {
			attacker.attack(defender);
		} catch (Throwable e) {
			throw new ModelException();
		}
	}

	@Override
	public boolean isAttacking(Unit unit) throws ModelException {
		return unit.isAttacking();
	}

	@Override
	public void rest(Unit unit) throws ModelException {
		try {
			unit.rest();
		} catch (Throwable e) {
			throw new ModelException();
		}
	}

	@Override
	public boolean isResting(Unit unit) throws ModelException {
		return unit.isResting();
	}

	@Override
	public void setDefaultBehaviorEnabled(Unit unit, boolean value) throws ModelException {
		if (value)
			unit.startDefaultBehaviour();
		else
			unit.stopDefaultBehaviour();
	}

	@Override
	public boolean isDefaultBehaviorEnabled(Unit unit) throws ModelException {
		return (!unit.canStartDefaultBehaviour());
	}

	@Override
	public World createWorld(int[][][] terrainTypes, TerrainChangeListener modelListener) throws ModelException {
		try {
			return new World(terrainTypes);
		} catch (Throwable e) {
			throw new ModelException();
		}
	}

	@Override
	public int getNbCubesX(World world) throws ModelException {
		try {
			return world.getNbCubes();
		} catch (Throwable e) {
			throw new ModelException();
		}
	}

	@Override
	public int getNbCubesY(World world) throws ModelException {
		try {
			return world.getNbCubes();
		} catch (Throwable e) {
			throw new ModelException();
		}
	}

	@Override
	public int getNbCubesZ(World world) throws ModelException {
		try {
			return world.getNbCubes();
		} catch (Throwable e) {
			throw new ModelException();
		}
	}

	@Override
	public void advanceTime(World world, double dt) throws ModelException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getCubeType(World world, int x, int y, int z) throws ModelException {
		try {
			Cube cube = new Cube(x, y, z, world);
			return world.getTerrainTypeInt(cube);
		} catch (Throwable e) {
			throw new ModelException();
		}
	}

	@Override
	public void setCubeType(World world, int x, int y, int z, int value) throws ModelException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isSolidConnectedToBorder(World world, int x, int y, int z) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Unit spawnUnit(World world, boolean enableDefaultBehavior) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUnit(Unit unit, World world) throws ModelException {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<Unit> getUnits(World world) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCarryingLog(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCarryingBoulder(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAlive(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getExperiencePoints(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void workAt(Unit unit, int x, int y, int z) throws ModelException {
		// TODO Auto-generated method stub

	}

	@Override
	public Faction getFaction(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Unit> getUnitsOfFaction(Faction faction) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Faction> getActiveFactions(World world) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] getPosition(Boulder boulder) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Boulder> getBoulders(World world) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] getPosition(Log log) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Log> getLogs(World world) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

}