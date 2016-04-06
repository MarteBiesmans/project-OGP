package hillbillies.model;

import java.util.*;

import be.kuleuven.cs.som.annotate.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @invar Each world has a valid array terrainTypes.
 * @invar Each world must have proper materials. | hasProperMaterials()
 * 
 * @author Ellen & Marte
 */
public class World extends TimeVariableObject {

	public static int MAX_FACTIONS = 5;
	public static int MAX_UNITS = 100;

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
	 * @post This new world has no materials yet. | new.getNbMaterials() == 0
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
	 * @return false if the given array contains integers not in [0,3]
	 */
	private boolean canHaveAsTerrainTypes(int[][][] terrainTypes) {
		
		//TODO toch niet cubical :(
		if ((terrainTypes.length != terrainTypes[0].length) || (terrainTypes.length != terrainTypes[0][0].length))
			return false;

		// iterate over the array terrainTypes
		for (int x = 0; x < terrainTypes.length; x++)
			for (int y = 0; y < terrainTypes[x].length; y++)
				for (int z = 0; z < terrainTypes[x][y].length; z++)
					// check if the value is legal
					if ((terrainTypes[x][y][z] != 0) && (terrainTypes[x][y][z] != 1) && (terrainTypes[x][y][z] != 2)
							&& (terrainTypes[x][y][z] != 3))
						return false;
		return true;
	}

	/**
	 * return the 3D-array terrainTypes of this world
	 */
	@Basic
	int[][][] getTerrainTypesArray() {
		return this.terrainTypes;
	}

	public void setTerrainType(Cube cube, TerrainType type) {
		if (type == TerrainType.ROCK)
			this.terrainTypes[cube.getX()][cube.getY()][cube.getZ()] = 1;
		else if (type == TerrainType.WOOD)
			this.terrainTypes[cube.getX()][cube.getY()][cube.getZ()] = 2;
		else if (type == TerrainType.WORKSHOP)
			this.terrainTypes[cube.getX()][cube.getY()][cube.getZ()] = 3;
		else
			this.terrainTypes[cube.getX()][cube.getY()][cube.getZ()] = 4;
	}

	/**
	 * Variable registering the terrain types of this world in a
	 * three-dimensional array.
	 */
	private int[][][] terrainTypes;

	/**
	 * Return the number of cubes in the X direction of this world.
	 */
	@Immutable
	public int getNbCubesX() {
		//TODO toch niet cubical :(
		return this.getTerrainTypesArray().length;
	}
	
	/**
	 * Return the number of cubes in the Y direction of this world.
	 */
	@Immutable
	public int getNbCubesY() {
		//TODO toch niet cubical :(
		return this.getTerrainTypesArray().length;
	}
	
	/**
	 * Return the number of cubes in the Z direction of this world.
	 */
	@Immutable
	public int getNbCubesZ() {
		//TODO toch niet cubical :(
		return this.getTerrainTypesArray().length;
	}

	/**
	 * Return the integer associated with the terrain type of the given cube.
	 * 
	 * @param	cube
	 * 			the cube to check
	 * @throws	IllegalArgumentException
	 * 			the given cube is not within the boundaries of this world
	 */
	public int getTerrainTypeInt(Cube cube) throws IllegalArgumentException {
		if (!cube.isValidIn(this))
			throw new IllegalArgumentException();		
		return this.getTerrainTypesArray()[cube.getX()][cube.getY()][cube.getZ()];
	}

	
	/**
	 * Return the terrain type of the given cube.
	 * 
	 * @param	cube
	 * 			the cube to check
	 * @throws	IllegalArgumentException
	 * 			the given cube is not within the boundaries of this world
	 */
	public TerrainType getTerrainType(Cube cube) throws IllegalArgumentException {
		if (!cube.isValidIn(this))
			throw new IllegalArgumentException();
		
		int terrainType = this.getTerrainTypeInt(cube);
		if (terrainType == 1)
			return TerrainType.ROCK;
		if (terrainType == 2)
			return TerrainType.WOOD;
		if (terrainType == 3)
			return TerrainType.WORKSHOP;
		else
			return TerrainType.AIR;
	}

	public boolean isPassableCube(Cube cube) {
		return this.getTerrainType(cube).isPassable();
	}

	// UNITS//

	// TODO: methodes uitwerken
	public Set<Boulder> getBouldersIn(Cube cube) {
		return null;
	}

	public Set<Log> getLogsIn(Cube cube) {
		return null;
	}

	// public Material[] getMaterialsInCube(Cube cube){}

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

	// public Material[] getMaterialsInCube(Cube cube){}

	public void addUnit(Unit unit) {
		if (canAddAsUnit(unit)) {
			Faction unitsFaction = null;
			// if the max number of factions in this world is not reached, make
			// a new faction
			if (this.getNbActiveFactions() != MAX_FACTIONS) {
				unitsFaction = new Faction();
				this.addFaction(unitsFaction);
				// else, search for the active faction in this world with the
				// least units
			} else
				for (Faction faction : this.factions) {
					if (unitsFaction == null)
						unitsFaction = faction;
					if (faction.getNbUnits() > 0 && faction.getNbUnits() < unitsFaction.getNbUnits())
						unitsFaction = faction;
				}

			unitsFaction.addUnit(unit);

			this.units.add(unit);
			try {
				unit.setWorld(this);
			} catch (IllegalArgumentException e) {
				this.units.remove(unit);
				throw e;
			}
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
		for (Unit unit : this.units)
			if (unit == null || unit.isDead() || unit.getWorld() != this)
				return false;
		return true;
	}

	// is deze functie nodig? in de opgave staat dat alle objects in een cube
	// moeten opgelijst
	// kunnen worden, maar is een unit een 'object'?
	/**
	 * get all units in one cube of the game world presented as a set
	 * 
	 * @param	cube
	 * 			the cube to check
	 * @return	a set containing all the units in the given cube
	 * @throws	IllegalArgumentException
	 * 			the given cube is not within the boundaries of this world
	 */
	public Set<Unit> getUnitsInCube(Cube cube) throws IllegalArgumentException {
		if (!cube.isValidIn(this))
			throw new IllegalArgumentException();
		
		Set<Unit> result = new HashSet<Unit>();

		Iterator<Unit> it = units.iterator();
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

	// TODO: ik vind dit een heel vage functie, kan zijn dat ik de opgave niet goed
	// begrepen heb
	// => zou dat niet gewoon een functie in Faction moeten zijn? zodat ge kunt doen
	// faction.getAllUnits() ?
	/**
	 * Return all the units in this world present in a given faction.
	 * 
	 * @param faction
	 *            the faction to which the returned units belong
	 */
	public Set<Unit> getAllUnits(Faction faction) {
		return faction.getAllUnits();
	}

	public void spawnUnit() {
		// TODO
	}

	private final Set<Unit> units;

	// FACTIONS//

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

	/**
	 * return all the factions present in this world as a set
	 */
	public Set<Faction> getAllFactions() {
		return factions;
	}

	public boolean hasProperFactions() {
		for (Faction faction : this.factions)
			if (faction == null || faction.getWorld() != this)
				return false;
		return true;
	}

	private Set<Faction> factions;

	// MATERIALS//

	/**
	 * Add the given material to the set of materials of this world.
	 * 
	 * @param material
	 *            The material to be added.
	 * @pre The given material is effective and already references this world. |
	 *      (material != null) && (material.getWorld() == this)
	 * @post This world has the given material as one of its materials. |
	 *       new.hasAsMaterial(material)
	 */
	public void addMaterial(@Raw Material material) {
		if (!canHaveAsMaterial(material))
			throw new IllegalArgumentException();
		this.materials.add(material);

		try {
			material.setWorld(this);
		} catch (IllegalArgumentException e) {
			this.materials.remove(material);
			throw e;
		}
	}

	/**
	 * Remove the given material from the set of materials of this world.
	 * 
	 * @param material
	 *            The material to be removed.
	 * @pre This world has the given material as one of its materials, and the
	 *      given material does not reference any world. |
	 *      this.hasAsMaterial(material) && | (material.getWorld() == null)
	 * @post This world no longer has the given material as one of its
	 *       materials. | ! new.hasAsMaterial(material)
	 */
	@Raw
	public void removeMaterial(Material material) {
		if (!this.hasAsMaterial(material))
			throw new IllegalArgumentException();
		this.materials.remove(material);
	
		try {
			material.setWorld(null);
		} catch (IllegalArgumentException e) {
			this.materials.add(material);
			throw e;
		}
	}

	/**
	 * Check if the given material can belong to this world.
	 * 
	 * @param material
	 * @return false if the given material is null
	 * @return false if the given material doesn't have a position (but only an
	 *         owner)
	 * @return false if the position of the material is not a passable cube
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

	/**
	 * get all logs in one cube of the game world presented as a set
	 * 
	 * @param	cube
	 * 			the cube to check
	 * @return	a set containing all the logs in the given cube
	 * @throws	IllegalArgumentException
	 * 			the given cube is not within the boundaries of this world
	 */
	public Set<Material> getLogsInCube(Cube cube) throws IllegalArgumentException {
		if (!cube.isValidIn(this))
			throw new IllegalArgumentException();
		
		Set<Material> result = new HashSet<Material>();
		
		Iterator<Material> it = materials.iterator();
		while (it.hasNext()) {
			Material material = it.next();
			if ( (material.getPosition().getCube() == cube) && (Log.class.isInstance(material)) )
				result.add(material);
		}
		
		return result;
	}
	
	/**
	 * get all boulders in one cube of the game world presented as a set
	 * 
	 * @param	cube
	 * 			the cube to check
	 * @return	a set containing all the boulders in the given cube
	 * @throws	IllegalArgumentException
	 * 			the given cube is not within the boundaries of this world
	 */
	public Set<Material> getBouldersInCube(Cube cube) throws IllegalArgumentException {
		if (!cube.isValidIn(this))
			throw new IllegalArgumentException();
		Set<Material> result = new HashSet<Material>();

		Iterator<Material> it = materials.iterator();
		while (it.hasNext()) {
			Material material = it.next();
			if ( (material.getPosition().getCube() == cube) && (Boulder.class.isInstance(material)) )
				result.add(material);
		}

		return result;
	}
	
	public Set<Log> getAllLogs() {
		//TODO
		return null;
	}
	
	public Set<Boulder> getAllBoulders() {
		//TODO
		return null;
	}
	
	/**
	 * Check whether this world has the given material as one of its materials.
	 * 
	 * @param material
	 *            The material to check.
	 */
	@Basic
	@Raw
	public boolean hasAsMaterial(@Raw Material material) {
		return materials.contains(material);
	}

	/**
	 * Check whether this world has proper materials attached to it.
	 * 
	 * @return True if and only if this world can have each of the materials
	 *         attached to it as one of its materials, and if each of these
	 *         materials references this world as the world to which they are
	 *         attached. | for each material in Material: | if
	 *         (hasAsMaterial(material)) | then canHaveAsMaterial(material) && |
	 *         (material.getWorld() == this)
	 */
	public boolean hasProperMaterials() {
		for (Material material : materials) {
			if (!canHaveAsMaterial(material))
				return false;
			if (material.getWorld() != this)
				return false;
		}
		return true;
	}

	/**
	 * Return the number of materials associated with this world.
	 *
	 * @return The total number of materials collected in this world. | result
	 *         == | card({material:Material | hasAsMaterial({material)})
	 */
	public int getNbMaterials() {
		return materials.size();
	}

	/**
	 * Variable referencing a set collecting all the materials of this world.
	 * 
	 * @invar The referenced set is effective. | materials != null
	 * @invar Each material registered in the referenced list is effective and
	 *        not yet terminated. | for each material in materials: | (
	 *        (material != null) && | (! material.isTerminated()) )
	 */
	private final Set<Material> materials = new HashSet<Material>();
	
	
	
	
	
	
	
	//OTHERS//
	
	
	public void advanceTime(float seconds) throws IllegalArgumentException {
		// if (seconds < 0 || seconds >= 0.2)
		// throw new IllegalArgumentException();
		//	 this.busyTimeMin(seconds);
		// TODO
	}
}
