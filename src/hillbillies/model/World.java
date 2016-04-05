package hillbillies.model;

import java.util.*;

import be.kuleuven.cs.som.annotate.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @invar	Each world has a valid array terrainTypes.
 * @invar	Each faction in a world should always be active, i.e. non-empty
 * 
 * @author Ellen & Marte
 */
public class World extends TimeVariableObject {
	
	public static int MAX_FACTIONS = 5;
	public static int MAX_UNITS_IN_WORLD = 100;
	
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
	 * @param	terrainTypes
	 * 			The array to check.
	 * @return	false if the given array terrainTypes is not cubical
	 * @return	false if the given array contains integers not in [0,3]
	 */
	private boolean canHaveAsTerrainTypes(int[][][] terrainTypes) {
		if ((terrainTypes.length != terrainTypes[0].length) || (terrainTypes.length != terrainTypes[0][0].length))
			return false;
		
		//iterate over the array terrainTypes
		for(int x=0;x<terrainTypes.length;x++)
			for(int y=0;y<terrainTypes[x].length;y++)
				for(int z=0;z<terrainTypes[x][y].length;z++)
					//check if the value is legal
					if ( (terrainTypes[x][y][z]!= 0) && 
						 (terrainTypes[x][y][z]!= 1) &&
						 (terrainTypes[x][y][z]!= 2) &&
						 (terrainTypes[x][y][z]!= 3) )
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
	
	
	public boolean isPassableCube(Cube cube) {
		return this.getTerrainType(cube).isPassable();
	}
	
	// public Material[] getMaterialsInCube(Cube cube){}
	
	
	
	//VERSIE MARTE//
//	public void addUnit(Unit unit) {
//		if (!canAddAsUnit(unit))
//			throw new IllegalArgumentException();
//		this.units.add(unit);
//
//		try {
//			unit.setWorld(this);
//		} catch (IllegalArgumentException e) {
//			this.units.remove(unit);
//			throw e;
//		}
//	}
//
//	public void removeUnit(Unit unit) {
//		if (!this.hasAsUnit(unit))
//			throw new IllegalArgumentException();
//		this.units.remove(unit);
//
//		try {
//			unit.setFaction(null);
//		} catch (IllegalArgumentException e) {
//			this.units.add(unit);
//			throw e;
//		}
//	}
//
//	public boolean canAddAsUnit(Unit unit) {
//		if (unit.isDead())
//			return false;
//		if (unit.getWorld() != null)
//			return false;
//		if (this.units.size() == MAX_UNITS)
//			return false;
//		return true;
//	}
//
//	public boolean hasAsUnit(Unit unit) {
//		if (unit == null)
//			return false;
//		return this.units.contains(unit);
//	}
//
//	public int getNbUnits() {
//		return this.units.size();
//	}
//	
//	public boolean hasProperUnits() {
//		for (Unit unit: this.units)
//			if (unit == null || unit.isDead() || unit.getWorld() != this)
//				return false;
//		return true;
//	}
//
//	private final Set<Unit> units;
//
//	public void addFaction(Faction faction) {
//		if (!canAddAsFaction(faction))
//			throw new IllegalArgumentException();
//		this.factions.add(faction);
//
//		try {
//			faction.setWorld(this);
//		} catch (IllegalArgumentException e) {
//			this.factions.remove(faction);
//			throw e;
//		}
//	}
//
//	public void removeFaction(Faction faction) {
//		if (!this.hasAsFaction(faction))
//			throw new IllegalArgumentException();
//		this.factions.remove(faction);
//
//		try {
//			faction.setWorld(null);
//		} catch (IllegalArgumentException e) {
//			this.factions.add(faction);
//			throw e;
//		}
//	}
//
//	public boolean canAddAsFaction(Faction faction) {
//		if (faction.getWorld() != null)
//			return false;
//		if (this.getNbActiveFactions() == MAX_FACTIONS && faction.getNbUnits() > 0)
//			return false;
//		return true;
//	}
//
//	public int getNbActiveFactions() {
//		int counter = 0;
//		for (Faction faction : this.factions)
//			if (faction.getNbUnits() > 0)
//				counter += 1;
//		return counter;
//	}
//
//	public boolean hasAsFaction(Faction faction) {
//		if (faction == null)
//			return false;
//		return this.factions.contains(faction);
//	}
//
//	public int getNbFactions() {
//		return this.factions.size();
//	}
	

	
	
	
	
	
	//VERSIE ELLEN//
	
	
	//MATERIALS//
	
	/**
	 * add a given material to this world
	 * 
	 * @param	material
	 * 			the material to add
	 * @throws	IllegalArgumentException
	 * 			the given material can not belong to this world
	 */
	public void addAsMaterial(Material material) throws IllegalArgumentException {
		if (!this.canHaveAsMaterial(material))
			throw new IllegalArgumentException();
		MaterialsInWorld.add(material);
	}
	
	/**
	 * remove the given material from this world
	 * 
	 * @param	material
	 * 			the material to remove
	 */
	public void removeAsMaterial(Material material) {
		MaterialsInWorld.remove(material);
	}
	
	/**
	 * Check if the given material can belong to this world.
	 * 
	 * @param material
	 * @return	false if the given material is null
	 * @return	false if the given material doesn't have a position (but only an owner)
	 * @return	false if the position of the material is not a passable cube
	 */
	public boolean canHaveAsMaterial(Material material) {
		if (material == null)
			return false;
		if (material.getPosition() == null)
			return false;
		if (!this.isPassableCube(material.getPosition().getCube()))
			return false;
		return true;
	}
	
	/**
	 * a set containing all the materials in this world.
	 */
	private Set<Material> MaterialsInWorld;
	
	/**
	 * get all materials in one cube of the game world presented as a set
	 * 
	 * @param	cube
	 * 			the cube to check
	 * @return	a set containing all the materials in the given cube
	 */
	public Set<Material> getMaterialsInCube(Cube cube){
		Set<Material> result = new HashSet<Material>();
		
		Iterator<Material> it = MaterialsInWorld.iterator();
		while (it.hasNext()) {
			Material material = it.next();
			if (material.getPosition().getCube() == cube)
				result.add(material);
		}
		
		return result;
	}
	
	
	
	
	
	
	
	
	//UNITS//
	
	/**
	 * add a given unit to this world if the maximum number is not yet reached
	 * 
	 * @param	unit
	 * 			the unit to add
	 * @throws	IllegalArgumentException
	 * 			the given unit can not belong to this world
	 */
	public void addAsUnit(Unit unit) throws IllegalArgumentException {
		
		//op voorhand dit controleren, want mag geen exceptions geven
		if ((this.getNbOfUnits() == MAX_UNITS_IN_WORLD) 
				|| ((this.getNbOfFactions() == MAX_FACTIONS)))
			return;
		
		if (!this.canHaveAsUnit(unit))
			throw new IllegalArgumentException();
		
		//moet in deze volgorde, want als er al te veel factions zijn, geeft addAsFaction al
		//een error en wordt de unit niet toegevoegd.
		this.addAsFaction(unit.getFaction());
		UnitsInWorld.add(unit);
	}
	
	/**
	 * remove the given unit from this world
	 * 
	 * @param	unit
	 * 			the unit to remove
	 */
	public void removeAsUnit(Unit unit) {
		UnitsInWorld.remove(unit);
		unit.getFaction().removeUnit(unit);
		if (unit.getFaction().isEmpty())
			this.removeAsFaction(unit.getFaction());
		
	}
	
	/**
	 * Check if the given unit can belong to this world.
	 * 
	 * @param	unit
	 * 			the unit to check
	 * @return	false if the given unit equals null
	 * @return	false if the maximum number of units is already reached
	 * @return	false if the cube where the given unit is located is not passable
	 */
	public boolean canHaveAsUnit(Unit unit) {
		if (unit==null)
			return false;
		if (this.getNbOfUnits() == MAX_UNITS_IN_WORLD)
			return false;
		if (!this.isPassableCube(unit.getCube()))
			return false;
		return true;
	}
	
	/**
	 * Returns the number of units in this world.
	 */
	public int getNbOfUnits() {
		return UnitsInWorld.size();
	}
	
	private Set<Unit> UnitsInWorld;

	
	//is deze functie nodig? in de opgave staat dat alle objects in een cube moeten opgelijst kunnen worden
	//is een unit een 'object'?
	/**
	 * get all units in one cube of the game world presented as a set
	 * 
	 * @param	cube
	 * 			the cube to check
	 * @return	a set containing all the units in the given cube
	 */
	public Set<Unit> getUnitsInCube(Cube cube) {
		Set<Unit> result = new HashSet<Unit>();
		
		Iterator<Unit> it = UnitsInWorld.iterator();
		while (it.hasNext()) {
			Unit unit = it.next();
			if (unit.getCube() == cube)
				result.add(unit);
		}
		
		return result;
	}
	
	/**
	 * return all units present in this world
	 */
	public Set<Unit> getAllUnits() {
		Set<Unit> result = new HashSet<Unit>();
		
		Iterator<Faction> it = this.getAllFactions().iterator();
		while (it.hasNext()) {
			Faction faction = it.next();
				result.addAll(faction.getAllUnits());
		}
		
		return result;
	}
	
	/**
	 * Return all the units in this world present in a given faction.
	 * @param	faction
	 * 			the faction to which the returned units belong
	 */
	public Set<Unit> getAllUnits(Faction faction) {
		return faction.getAllUnits();
	}
	
	public void spawnUnit() {
		//TODO
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//FACTIONS//
	
	/**
	 * add a given faction to this world if the maximum number is not yet reached and won't
	 * be exceeded after adding the faction
	 * 
	 * @param	faction
	 * 			the faction to add
	 * @throws	IllegalArgumentException
	 * 			the given faction can not belong to this world
	 */
	public void addAsFaction(Faction faction) {
		//op voorhand dit controleren, want mag geen exceptions geven
		if ((this.getNbOfUnits() + faction.getNbOfUnits() > MAX_UNITS_IN_WORLD) 
				|| ((this.getNbOfFactions() == MAX_FACTIONS)))
			return;
		
		if (!this.canHaveAsFaction(faction))
			throw new IllegalArgumentException();
		
		this.FactionsInWorld.add(faction);
		
		//TODO nu worden alle units één voor één toegevoegd, maar wat als de 10e unit een fout geeft
		// dan is de rest al wel toegevoegd? (mss met try-catch)
		Iterator<Unit> it = faction.getAllUnits().iterator();
		while (it.hasNext()) {
			Unit unit = it.next();
			this.addAsUnit(unit);
		}
		
		
	}
	
	/**
	 * remove the given faction from this world, including all it's units
	 * 
	 * @param	faction
	 * 			the faction to remove
	 */
	public void removeAsFaction(Faction faction) {
		if (!faction.isEmpty()) {
			Iterator<Unit> it = faction.getAllUnits().iterator();
			while (it.hasNext()) {
				Unit unit = it.next();
				this.removeAsUnit(unit);
			}
		}
		
		//else, want als er units in de faction zaten is samen met de laatste unit
		// ook de faction verwijderd
		else
			this.FactionsInWorld.remove(faction);
	}
	
	/**
	 * Check if the given faction can belong to this world.
	 * 
	 * @param	faction
	 * 			the faction to check
	 * @return	false if the given faction equals null
	 * @return	false if the maximum number of factions is already reached
	 */
	public boolean canHaveAsFaction(Faction faction) {
		if (faction==null)
			return false;
		if (this.getNbOfFactions() == MAX_FACTIONS)
			return false;
		return true;
	}
	
	/**
	 * return de number of factions in this world
	 */
	public int getNbOfFactions() {
		return FactionsInWorld.size();
	}
	
	/**
	 * return all the factions present in this world as a set
	 */
	public Set<Faction> getAllFactions() {
		return FactionsInWorld;
	}
	
	private Set<Faction> FactionsInWorld;
	
	public boolean hasProperFactions() {
		for (Faction faction: this.FactionsInWorld)
			if (faction == null || faction.getWorld() != this)
				return false;
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//OTHERS//
	
	public void advanceTime(float seconds) throws IllegalArgumentException {
		// if (seconds < 0 || seconds >= 0.2)
		// throw new IllegalArgumentException();

		this.busyTimeMin(seconds);
		
		//TODO
	}
}
