package hillbillies.part2.facade;

import java.util.Set;

import hillbillies.model.*;
import hillbillies.part2.listener.TerrainChangeListener;
import ogp.framework.util.ModelException;

public class Facade extends hillbillies.part1.facade.Facade implements IFacade {
	
	public World createWorld(int[][][] terrainTypes, TerrainChangeListener modelListener) throws ModelException{
		try{
			return new World(terrainTypes);
		}
		catch (Throwable e){
			throw new ModelException();
		}
	}

	
	public int getNbCubesX(World world) throws ModelException{
		try{
			return world.getNbCubes();
		}
		catch (Throwable e){
			throw new ModelException();
		}
	}

	
	public int getNbCubesY(World world) throws ModelException{
		try{
			return world.getNbCubes();
		}
		catch (Throwable e){
			throw new ModelException();
		}
	}

	
	public int getNbCubesZ(World world) throws ModelException{
		try{
			return world.getNbCubes();
		}
		catch (Throwable e){
			throw new ModelException();
		}
	}

	
	public void advanceTime(World world, double dt) throws ModelException{}

	
	public int getCubeType(World world, int x, int y, int z) throws ModelException{
		try{
			Cube cube = new Cube(x,y,z,world);
			return world.getTerrainTypeInt(cube);
		}
		catch (Throwable e) {
			throw new ModelException();
		}
	}

	
	public void setCubeType(World world, int x, int y, int z, int value) throws ModelException{}

	
	public boolean isSolidConnectedToBorder(World world, int x, int y, int z) throws ModelException{}

	/* UNITS */

	public Unit spawnUnit(World world, boolean enableDefaultBehavior) throws ModelException{}


	public void addUnit(Unit unit, World world) throws ModelException{}

	
	public Set<Unit> getUnits(World world) throws ModelException{}

	
	public boolean isCarryingLog(Unit unit) throws ModelException{}

	
	public boolean isCarryingBoulder(Unit unit) throws ModelException{}

	
	public boolean isAlive(Unit unit) throws ModelException{}

	
	public int getExperiencePoints(Unit unit) throws ModelException{}

	
	public void workAt(Unit unit, int x, int y, int z) throws ModelException{}

	
	@Override
	@Deprecated
	public void work(Unit unit) throws ModelException {
		throw new NoSuchMethodError("This method no longer needs to be supported");
	}

	
	@Override
	@Deprecated
	public void advanceTime(Unit unit, double dt) throws ModelException {
		throw new NoSuchMethodError("This method no longer needs to be supported");
	}

	/* FACTIONS */
	public Faction getFaction(Unit unit) throws ModelException{}

	
	public Set<Unit> getUnitsOfFaction(Faction faction) throws ModelException{}

	
	public Set<Faction> getActiveFactions(World world) throws ModelException{}

	
	/* BOULDERS */
	public double[] getPosition(Boulder boulder) throws ModelException{}

	
	public Set<Boulder> getBoulders(World world) throws ModelException{}

	
	/* LOGS */
	public double[] getPosition(Log log) throws ModelException{}

	
	public Set<Log> getLogs(World world) throws ModelException{}

}
