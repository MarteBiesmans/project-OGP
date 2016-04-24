package hillbillies.model;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import hillbillies.part2.listener.TerrainChangeListener;
import hillbillies.util.ConnectedToBorder;

import be.kuleuven.cs.som.annotate.*;

/**
 * a class describing a game world filled with units (devided in factions) 
 * and materials (logs and boulders)
 * 
 * @invar Each world has a valid array terrainTypes.
 * @invar Each world must have proper materials.
 * @invar Each world must have proper units.
 * @invar Each world must have proper factions.
 * 
 * @author Ellen & Marte
 */
public class World extends TimeVariableObject {

	private static final Random RANDOM_GEN = new Random();
	static final int MAX_FACTIONS = 5;
	static final int MAX_UNITS = 100;

	/**
	 * create a new game world
	 * 
	 * @pre The terrain always has the shape of a box (i.e., the array
	 *      terrainTypes[0] has the same length as terrainTypes[1] etc.).
	 * @param terrainTypes
	 *            A three-dimensional array (structured as [x][y][z]) with the
	 *            types of the terrain, encoded as integers. The integer types
	 *            are as follows: 0-air 1-rock 2-tree 3-workshop
	 * @param modelListener
	 * 			an instance to update the GUI
	 * @post The terrain types of this new world are equal to the given terrain
	 *       types.
	 * @post This new world has no units yet.
	 * @post This new world has no factions yet.
	 * @post this new world can have materials if some part of the world
	 *       specified in terrainTypes should cave in
	 * @effect initialize the private instance connectedUtil to be able to use
	 *         the given algorithms in ConnectedToBorder
	 * @throws IllegalArgumentException
	 *             terrainTypes is not valid for this world
	 */
	public World(int[][][] terrainTypes, TerrainChangeListener modelListener) throws IllegalArgumentException {
		if (!canHaveAsTerrainTypes(terrainTypes))
			throw new IllegalArgumentException();

		this.terrainTypes = terrainTypes;
		this.modelListener = modelListener;

		this.connectedUtil = new ConnectedToBorder(terrainTypes.length, terrainTypes[0].length,
				terrainTypes[0][0].length);
		// iterate over the 3D-array terrainTypes to initialize connectedUtil
		Set<int[]> caveIns = new HashSet<int[]>();
		for (int x = 0; x < terrainTypes.length; x++)
			for (int y = 0; y < terrainTypes[x].length; y++)
				for (int z = 0; z < terrainTypes[x][y].length; z++) {
					if (terrainTypes[x][y][z] == 0 || terrainTypes[x][y][z] == 3)
						caveIns.addAll(connectedUtil.changeSolidToPassable(x, y, z));
				}
		// make the caveIns collapse
		for (int[] cubeCoordinate : caveIns)
			this.collapse(new Cube(cubeCoordinate[0], cubeCoordinate[1], cubeCoordinate[2]));

	}

	// TERRAIN//

	/**
	 * Check if the three-dimensional array terrainTypes is valid for this
	 * world.
	 * 
	 * @param terrainTypes
	 *            The array to check.
	 * @return false if the given array contains integers not in [0,3]
	 */
	boolean canHaveAsTerrainTypes(int[][][] terrainTypes) {
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

	/**
	 * Variable registering the terrain types of this world in a
	 * three-dimensional array.
	 */
	private int[][][] terrainTypes;

	/**
	 * instance of the provided class ConnectedToBorder to be able to access
	 * these methods
	 */
	final ConnectedToBorder connectedUtil;

	/**
	 * instance of the provided class TerrainChangeListener to be able to update
	 * the GUI
	 */
	TerrainChangeListener modelListener;

	/**
	 * Return the number of cubes in the X direction of this world.
	 */
	@Immutable
	public int getNbCubesX() {
		return this.getTerrainTypesArray().length;
	}

	/**
	 * Return the number of cubes in the Y direction of this world.
	 */
	@Immutable
	public int getNbCubesY() {
		return this.getTerrainTypesArray()[0].length;
	}

	/**
	 * ) Return the number of cubes in the Z direction of this world.
	 */
	@Immutable
	public int getNbCubesZ() {
		return this.getTerrainTypesArray()[0][0].length;
	}

	/**
	 * Return a set of all cubes in this world.
	 */
	Set<Cube> getAllCubes() {
		Set<Cube> allCubes = new HashSet<Cube>();
		for (int i = 0; i < this.getNbCubesX(); i++) {
			for (int j = 0; j < this.getNbCubesY(); j++) {
				for (int k = 0; k < this.getNbCubesZ(); k++) {
					allCubes.add(new Cube(i, j, k));
				}
			}
		}
		return allCubes;
	}

	/**
	 * Return the integer associated with the terrain type of the given cube.
	 * 
	 * @param cube
	 *            the cube to check
	 * @throws IllegalArgumentException
	 *             the given cube is not within the boundaries of this world
	 */
	public int getTerrainTypeInt(Cube cube) throws IllegalArgumentException {
		if (!cube.isValidIn(this))
			throw new IllegalArgumentException();
		return this.getTerrainTypesArray()[cube.getX()][cube.getY()][cube.getZ()];
	}

	/**
	 * Return the terrain type of the given cube.
	 * 
	 * @param cube
	 *            the cube to check
	 * @throws IllegalArgumentException
	 *             the given cube is not within the boundaries of this world
	 */
	TerrainType getTerrainType(Cube cube) throws IllegalArgumentException {
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

	/**
	 * sets the terrain type of a given cube in this world to a given value
	 * 
	 * @param cube
	 *            the cube to change
	 * @param type
	 *            the terrain type to set to
	 * @effect update the private instance connectedUtil to be able to use the
	 *         given algorithms in ConnectedToBorder
	 * @effect if necessary make other cubes collapse
	 * @effect update 3D-array terrainTypes for the given cube
	 * @effect	update the GUI
	 * @throws IllegalArgumentException
	 *             the given cube is not within the boundaries of this world
	 */
	public void setTerrainType(Cube cube, TerrainType type) throws IllegalArgumentException {
		if (!cube.isValidIn(this))
			throw new IllegalArgumentException();

		// update connectedUtil and collect caveIns
		Set<int[]> caveIns = new HashSet<int[]>();

			// solid to passable
		if (!cube.isPassableIn(this) && type.isPassable())
			caveIns.addAll(connectedUtil.changeSolidToPassable(cube.getX(), cube.getY(), cube.getZ()));

			// passable to solid
		if (cube.isPassableIn(this) && !type.isPassable()) {
			connectedUtil.changePassableToSolid(cube.getX(), cube.getY(), cube.getZ());
			if (!connectedUtil.isSolidConnectedToBorder(cube.getX(), cube.getY(), cube.getZ())) {
				connectedUtil.changeSolidToPassable(cube.getX(), cube.getY(), cube.getZ());
				return;
			}
		}

		// update terrainTypes
		this.terrainTypes[cube.getX()][cube.getY()][cube.getZ()] = type.getAssociatedInt();
		modelListener.notifyTerrainChanged(cube.getX(), cube.getY(), cube.getZ());

		// make the caveIns collapse
		for (int[] cubeCoordinate : caveIns)
			this.collapse(new Cube(cubeCoordinate[0], cubeCoordinate[1], cubeCoordinate[2]));
	}

	/**
	 * make the given cube collapse with the possibility to create a material
	 * 
	 * @param cube
	 *            the cube to collapse
	 * @post nothing changes if the given cube is not solid
	 * 
	 * @throws IllegalArgumentException
	 *             the given cube equals null
	 */
	public void collapse(Cube cube) throws IllegalArgumentException {
		if (cube == null)
			throw new IllegalArgumentException();
		if (cube.isPassableIn(this))
			return;

		double probability = RANDOM_GEN.nextDouble();
		TerrainType oldType = this.getTerrainType(cube);
		this.setTerrainType(cube, TerrainType.AIR);
		if (probability < 0.25) {
			if (oldType == TerrainType.WOOD) {
				this.addMaterial(new Log(), cube.getCenter());
			} else if (oldType == TerrainType.ROCK) {
				this.addMaterial(new Boulder(), cube.getCenter());
			}
		}
	}

	// UNITS//

	/**
	 * remove a given unit out of this world if possible
	 * @param unit
	 * 			the unit to remove
	 * @post	if it is possible to set the world of the given unit to null, the unit is removed from this world
	 * @throws	IllegalArgumentException
	 * 			it is not possible to set the world of the given unit to null
	 */
	void removeUnit(Unit unit) throws IllegalArgumentException {
		// if (!this.hasAsUnit(unit))
		// throw new IllegalArgumentException();
		// this.units.remove(unit);

		if (this.hasAsUnit(unit))
			this.units.remove(unit);

		try {
			unit.setWorld(null);
		} catch (IllegalArgumentException e) {
			this.units.add(unit);
			throw e;
		}
	}

	/**
	 * add a given unit in the smallest (non-full) faction to this (non-full) world
	 * 
	 * @param unit
	 * 			the unit to add
	 * @post	nothing changes if this world is already full
	 * @throws IllegalArgumentException
	 * 			the given unit can not be added to this world for every other 
	 * 			reason than too much units or factions in this world (e.g. illegal position)
	 * @effect	TODO
	 */
	public void addUnit(Unit unit) throws IllegalArgumentException {
		if (!this.canAddAsUnit(unit))
			//TODO als factions vol zijn (kan met de huidige constanten niet gebeuren, maar adaptibility)
			if (this.units.size() == MAX_UNITS)
				return;
			else
				throw new IllegalArgumentException();
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

	/**
	 * check whether a given unit can be added to this world
	 * 
	 * @param unit
	 * 			the unit to check
	 * @return	false if the unit is dead
	 * @return	false if the unit already belongs to a world
	 * @return	false if this world already has reached the limit of units
	 * @return	false if the position of the given unit is not valid for a unit in this world
	 */
	private boolean canAddAsUnit(Unit unit) {
		if (unit.isDead())
			return false;
		if (unit.getWorld() != null)
			return false;
		if (this.units.size() == MAX_UNITS)
			return false;
		if (!unit.getPosition().isStableForUnitIn(this))
			return false;
		return true;
	}

	/**
	 * check whether this world contains a given unit
	 * 
	 * @param unit
	 * 			the unit to check
	 * @return	false if the given unit is null
	 * @return	false if the given unit doesn't belong to the set of all units in this world
	 */
	boolean hasAsUnit(Unit unit) {
		if (unit == null)
			return false;
		return this.units.contains(unit);
	}

	/**
	 * return the number of units in this world
	 */
	int getNbUnits() {
		return this.units.size();
	}

	/**
	 * check whether this world has proper units
	 */
	boolean hasProperUnits() {
		for (Unit unit : this.units)
			if (unit == null || unit.isDead() || unit.getWorld() != this)
				return false;
		return true;
	}

	/**
	 * get all units in one cube of the game world presented as a set
	 * 
	 * @param cube
	 *            the cube to check
	 * @return a set containing all the units in the given cube
	 * @throws IllegalArgumentException
	 *             the given cube is not within the boundaries of this world
	 */
	Set<Unit> getUnitsInCube(Cube cube) throws IllegalArgumentException {
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

	/**
	 * Return all the units in this world present in a given faction.
	 * 
	 * @param faction
	 *            the faction to which the returned units belong
	 */
	Set<Unit> getAllUnits(Faction faction) {
		return faction.getAllUnits();
	}

	private final Set<Unit> units = new HashSet<Unit>();

	/**
	 * create a random unit in this world
	 * 
	 * @param enableDefaultBehaviour
	 *            specify wether default behaviour should be enabled for this
	 *            random unit
	 * @return a new unit part of this world with random stable position, random
	 *         valid initial values, name randomly chosen between 'Ellen' and
	 *         'Marte' and default behaviour enabled depending on the given
	 *         value
	 */
	public Unit spawnUnit(boolean enableDefaultBehaviour) {
		// create random stable position
		Position position = null;
		while ((position == null) || (!position.isStableForUnitIn(this))) {
			double x = ThreadLocalRandom.current().nextDouble(0, this.getNbCubesX());
			double y = ThreadLocalRandom.current().nextDouble(0, this.getNbCubesY());
			double z = ThreadLocalRandom.current().nextDouble(0, this.getNbCubesZ());
			position = new Position(x, y, z);
		}

		// System.out.println("random positie");
		// System.out.println(position.getRealX());
		// System.out.println(position.getRealY());
		// System.out.println(position.getRealZ());

		// choose name random between Ellen and Marte
		double variable = RANDOM_GEN.nextDouble();
		String name = null;
		if (variable < 0.5)
			name = "Ellen";
		else
			name = "Marte";

		// create random initial strength, agility, toughness between 25 and 100
		// Min + RANDOM_GEN.nextInt(Max - Min) + 1)
		int strength = 25 + RANDOM_GEN.nextInt(76);
		int agility = 25 + RANDOM_GEN.nextInt(76);
		int toughness = 25 + RANDOM_GEN.nextInt(76);

		// create random initial weight between 25 and 100 that is at least
		// (strength+agility)/2
		int minimumWeight = Math.min(25, (int) Math.ceil((double) ((strength + agility) / 2.0)));
		int weight = minimumWeight + RANDOM_GEN.nextInt((100 - minimumWeight) + 1);

		Unit unit = new Unit(position.getRealX(), position.getRealY(), position.getRealZ(), name, strength, agility,
				toughness, weight, enableDefaultBehaviour);
		this.addUnit(unit);
		return unit;

	}

	// FACTIONS//

	/**
	 * add a given faction to this world
	 * @param faction
	 * 			the faction to add
	 */
	void addFaction(Faction faction) {
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

	/**
	 * remove a given faction from this world
	 * @param faction
	 * 			the faction to remove
	 */
	void removeFaction(Faction faction) {
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

	/**
	 * check whether a given faction can be added to this world
	 * @param faction
	 * 			the faction to check
	 */
	boolean canAddAsFaction(Faction faction) {
		if (faction.getWorld() != null)
			return false;
		if (this.getNbActiveFactions() == MAX_FACTIONS && faction.getNbUnits() > 0)
			return false;
		return true;
	}

	/**
	 * return the number of active factions in this world
	 */
	int getNbActiveFactions() {
		return this.getAllActiveFactions().size();
	}

	/**
	 * check whether a given faction belongs to this world
	 * @param faction
	 * 			the faction to check
	 */
	boolean hasAsFaction(Faction faction) {
		if (faction == null)
			return false;
		return this.factions.contains(faction);
	}

	/**
	 * return the number of factions in this world (including non-active)
	 */
	int getNbFactions() {
		return this.factions.size();
	}

	/**
	 * return all the factions present in this world as a set
	 */
	Set<Faction> getAllFactions() {
		return factions;
	}

	/**
	 * return all the active factions present in this world as a set
	 */
	public Set<Faction> getAllActiveFactions() {
		Set<Faction> activeFactionsSoFar = new HashSet<Faction>();
		for (Faction faction : this.getAllFactions())
			if (faction.getNbUnits() > 0)
				activeFactionsSoFar.add(faction);
		return activeFactionsSoFar;
	}

	/**
	 * check whether all factions in this world are proper
	 */
	boolean hasProperFactions() {
		for (Faction faction : this.factions)
			if (faction == null || faction.getWorld() != this)
				return false;
		return true;
	}

	/**
	 * a set with all the factions (including non-active) in this world
	 */
	private final Set<Faction> factions = new HashSet<Faction>();

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
	void addMaterial(@Raw Material material, @Raw Position position) throws IllegalArgumentException {
		if (!canHaveAsMaterialAt(material, position)) {
			throw new IllegalArgumentException();
		}
		this.materials.add(material);
		try {
			material.setWorld(this);
			material.setPosition(position);
			if (material.getOwner() != null)
				material.getOwner().removeMaterial(material);
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
	void removeMaterial(Material material) {
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
	boolean canHaveAsMaterialAt(Material material, Position position) {
		if (material == null) {
			return false;
		}
		if (position == null) {
			return false;
		}
		if (!position.isValidForObjectIn(this)) {
			return false;
		}
		return true;
	}

	/**
	 * get all logs in one cube of the game world presented as a set
	 * 
	 * @param cube
	 *            the cube to check
	 * @return a set containing all the logs in the given cube
	 * @throws IllegalArgumentException
	 *             the given cube is not within the boundaries of this world
	 */
	Set<Log> getLogsIn(Cube cube) throws IllegalArgumentException {
		if (!cube.isValidIn(this))
			throw new IllegalArgumentException();
		Set<Log> result = new HashSet<Log>();

		for (Material material: this.materials) {
			if (material.getPosition().getCube().equals(cube) && (Log.class.isInstance(material))) {
				// cast mag gebruikt worden, want je hebt net gecontroleerd of
				// het een boulder is
				result.add((Log) material);
			}
		}
		return result;
	}

	/**
	 * get all boulders in one cube of the game world presented as a set
	 * 
	 * @param cube
	 *            the cube to check
	 * @return a set containing all the boulders in the given cube
	 * @throws IllegalArgumentException
	 *             the given cube is not within the boundaries of this world
	 */
	Set<Boulder> getBouldersIn(Cube cube) throws IllegalArgumentException {
		if (!cube.isValidIn(this))
			throw new IllegalArgumentException();
		Set<Boulder> result = new HashSet<Boulder>();

		for (Material material: this.materials) {
			if (material.getPosition().getCube().equals(cube) && (Boulder.class.isInstance(material))) {
				// cast mag gebruikt worden, want je hebt net gecontroleerd of
				// het een boulder is
				result.add((Boulder) material);
			}
		}
		return result;
	}

	/**
	 * return a set containing all the materials in this world
	 */
	public Set<Material> getAllMaterials() {
		return this.materials;
	}

	/**
	 * return a set containing all the logs in this world
	 */
	public Set<Log> getAllLogs() {
		Set<Log> result = new HashSet<Log>();

		Iterator<Material> it = this.getAllMaterials().iterator();
		while (it.hasNext()) {
			Material material = it.next();
			if (Log.class.isInstance(material))
				// cast mag gebruikt worden, want je hebt net gecontroleerd of
				// het een log is
				result.add((Log) material);
		}

		return result;
	}

	/**
	 * return a set containing all the boulders in this world
	 */
	public Set<Boulder> getAllBoulders() {
		Set<Boulder> result = new HashSet<Boulder>();

		Iterator<Material> it = this.getAllMaterials().iterator();
		while (it.hasNext()) {
			Material material = it.next();
			if (Boulder.class.isInstance(material))
				// cast mag gebruikt worden, want je hebt net gecontroleerd of
				// het een boulder is
				result.add((Boulder) material);
		}

		return result;
	}

	/**
	 * Check whether this world has the given material as one of its materials.
	 * 
	 * @param material
	 *            The material to check.
	 */
	@Basic
	@Raw
	boolean hasAsMaterial(@Raw Material material) {
		return this.getAllMaterials().contains(material);
	}

	/**
	 * Check whether this world has proper materials attached to it.
	 * 
	 * @return True if and only if this world can have each of the materials
	 *         attached to it as one of its materials, and if each of these
	 *         materials references this world as the world to which they are
	 *         attached.
	 */
	boolean hasProperMaterials() {
		for (Material material : this.getAllMaterials()) {
			if (!canHaveAsMaterialAt(material, material.getPosition()))
				return false;
			if (material.getWorld() != this)
				return false;
		}
		return true;
	}

	/**
	 * Return the number of materials associated with this world.
	 *
	 * @return The total number of materials collected in this world.
	 */
	int getNbMaterials() {
		return this.getAllMaterials().size();
	}

	/**
	 * Variable referencing a set collecting all the materials of this world.
	 * 
	 * @invar The referenced set is effective.
	 * @invar Each material registered in the referenced list is effective and
	 *        not yet terminated.
	 */
	private final Set<Material> materials = new HashSet<Material>();

	// OTHERS//

	/**
	 * advance time for this world
	 * @effect	advance time for all objects (units, materials) in this world
	 */
	public void advanceTime(float seconds) throws IllegalArgumentException {
		// if (seconds < 0 || seconds >= 0.2)
		// throw new IllegalArgumentException();
		// this.busyTimeMin(seconds);

		// advanceTime voor elke unit
		for (Unit unit : this.getAllUnits())
			unit.advanceTime(seconds);

		// advanceTime voor elk material
		for (Material material : this.getAllMaterials())
			material.advanceTime(seconds);
	}
}
