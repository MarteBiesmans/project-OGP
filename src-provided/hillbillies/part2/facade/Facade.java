package hillbillies.part2.facade;

import java.util.Set;

import hillbillies.model.*;
import hillbillies.part2.listener.TerrainChangeListener;
import ogp.framework.util.ModelException;

public class Facade extends hillbillies.part1.facade.Facade implements IFacade {

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
		try{
			return world.getNbCubes();
		}
		catch (Throwable e){
			throw new ModelException();
		}
	}

	@Override
	public int getNbCubesZ(World world) throws ModelException {
		try{
			return world.getNbCubes();
		}
		catch (Throwable e){
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

	/**
	 * This method is no longer necessary, and is replaced by the
	 * {@link #workAt(Unit, int[])} method.
	 */
	@Override
	@Deprecated
	public void work(Unit unit) throws ModelException {
		throw new NoSuchMethodError("This method no longer needs to be supported");
	}

	/**
	 * This method is no longer necessary, and is replaced by the
	 * {@link #advanceTime(World, double)} method.
	 */
	@Override
	@Deprecated
	public void advanceTime(Unit unit, double dt) throws ModelException {
		throw new NoSuchMethodError("This method no longer needs to be supported");
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