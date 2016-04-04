package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @invar Each world has a valid array terrainTypes.
 * 
 * @author Ellen & Marte
 */
public class World {

	public static int MAX_FACTIONS = 5;
	public static int MAX_UNITS = 250;

	/**
	 * Initialize this World with given number of cubes.
	 * 
	 * @pre The terrain always has the shape of a box (i.e., the array
	 *      terrainTypes[0] has the same length as terrainTypes[1] etc.).
	 * @param terrainTypes
	 *            A three-dimensional array (structured as [x][y][z]) with the
	 *            types of the terrain, encoded as integers. The integer types
	 *            are as follows: 0: air 1: rock 2: tree 3: workshop
	 * @post The terrain types of this new world are equal to the given terrain
	 *       types.
	 * @throws IllegalArgumentException
	 *             terrainTypes is not valid for this world
	 */
	public World(int[][][] terrainTypes) throws IllegalArgumentException {
		if (!canHaveAsTerrainTypes(terrainTypes))
			throw new IllegalArgumentException();
		this.terrainTypes = terrainTypes;
		this.units = new HashSet<Unit>();
		this.factions = new HashSet<Faction>();
	}

	/**
	 * Check if the three-dimensional array terrainTypes is valid for this
	 * world.
	 * 
	 * @param terrainTypes
	 *            The array to check.
	 * @return false if the given array terrainTypes is not cubical
	 */
	private boolean canHaveAsTerrainTypes(int[][][] terrainTypes) {
		if ((terrainTypes.length != terrainTypes[0].length) || (terrainTypes.length != terrainTypes[0][0].length))
			return false;
		return true;
	}

	public int[][][] getTerrainTypesArray() {
		return this.terrainTypes;
	}

	/**
	 * Variable registering the terrain types of this world in a
	 * three-dimensional array.
	 */
	private int[][][] terrainTypes;

	/**
	 * Return the number of cubes in one direction of this world.
	 */
	@Immutable
	public int getNbCubes() {
		return this.getTerrainTypesArray().length;
	}

	public int getTerrainTypeInt(Cube cube) throws IllegalArgumentException {
		return this.getTerrainTypesArray()[cube.getX()][cube.getY()][cube.getZ()];
	}

	public TerrainType getTerrainType(Cube cube) throws IllegalArgumentException {
		int terrainType = this.getTerrainTypeInt(cube);
		if (terrainType == 1)
			return TerrainType.ROCK;
		if (terrainType == 2)
			return TerrainType.TREE;
		if (terrainType == 3)
			return TerrainType.WORKSHOP;
		else
			return TerrainType.AIR;
	}

	public boolean isCubePassable(Cube cube) {
		return this.getTerrainType(cube).isPassable();
	}
	
	// public Material[] getMaterialsInCube(Cube cube){}
	
	public void addUnit(Unit unit) {
		if (!canAddAsUnit(unit))
			throw new IllegalArgumentException();
		this.units.add(unit);

		try {
			unit.setWorld(this);
		} catch (IllegalArgumentException e) {
			this.units.remove(unit);
			throw e;
		}
	}

	public void removeUnit(Unit unit) {
		if (!this.hasAsUnit(unit))
			throw new IllegalArgumentException();
		this.units.remove(unit);

		try {
			unit.setFaction(null);
		} catch (IllegalArgumentException e) {
			this.units.add(unit);
			throw e;
		}
	}

	public boolean canAddAsUnit(Unit unit) {
		if (unit.isDead())
			return false;
		if (unit.getWorld() != null)
			return false;
		if (this.units.size() == MAX_UNITS)
			return false;
		return true;
	}

	public boolean hasAsUnit(Unit unit) {
		if (unit == null)
			return false;
		return this.units.contains(unit);
	}

	public int getNbUnits() {
		return this.units.size();
	}
	
	public boolean hasProperUnits() {
		for (Unit unit: this.units)
			if (unit == null || unit.isDead() || unit.getWorld() != this)
				return false;
		return true;
	}

	private final Set<Unit> units;

	public void addFaction(Faction faction) {
		if (!canAddAsFaction(faction))
			throw new IllegalArgumentException();
		this.factions.add(faction);

		try {
			faction.setWorld(this);
		} catch (IllegalArgumentException e) {
			this.factions.remove(faction);
			throw e;
		}
	}

	public void removeFaction(Faction faction) {
		if (!this.hasAsFaction(faction))
			throw new IllegalArgumentException();
		this.factions.remove(faction);

		try {
			faction.setWorld(null);
		} catch (IllegalArgumentException e) {
			this.factions.add(faction);
			throw e;
		}
	}

	public boolean canAddAsFaction(Faction faction) {
		if (faction.getWorld() != null)
			return false;
		if (this.getNbActiveFactions() == MAX_FACTIONS && faction.getNbUnits() > 0)
			return false;
		return true;
	}

	public int getNbActiveFactions() {
		int counter = 0;
		for (Faction faction : this.factions)
			if (faction.getNbUnits() > 0)
				counter += 1;
		return counter;
	}

	public boolean hasAsFaction(Faction faction) {
		if (faction == null)
			return false;
		return this.factions.contains(faction);
	}

	public int getNbFactions() {
		return this.factions.size();
	}
	
	public boolean hasProperFactions() {
		for (Faction faction: this.factions)
			if (faction == null || faction.getWorld() != this)
				return false;
		return true;
	}

	private final Set<Faction> factions;
}
