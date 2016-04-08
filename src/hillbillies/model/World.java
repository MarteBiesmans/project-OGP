package hillbillies.model;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import hillbillies.util.ConnectedToBorder;

import be.kuleuven.cs.som.annotate.*;

/**
 * 
 * @invar Each world has a valid array terrainTypes.
 * @invar Each world must have proper materials. | hasProperMaterials()
 * 
 * @author Ellen & Marte
 */
public class World extends TimeVariableObject {

	private static final Random RANDOM_GEN = new Random();
	static final int MAX_FACTIONS = 5;
	static final int MAX_UNITS = 100;

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
	 * @post This new world has no units yet.
	 * @post This new world has no factions yet.
	 * @post this new world can have materials if some part of the world
	 *       specified in terrainTypes should cave in
	 * @effect initialize the private instance connectedUtil to be able to use
	 *         the given algorithms in ConnectedToBorder
	 * @throws IllegalArgumentException
	 *             terrainTypes is not valid for this world
	 */
	public World(int[][][] terrainTypes) throws IllegalArgumentException {
		if (!canHaveAsTerrainTypes(terrainTypes))
			throw new IllegalArgumentException();

		this.terrainTypes = terrainTypes;

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
	private final ConnectedToBorder connectedUtil;

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

	public Set<Cube> getAllCubes() {
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
		// TODO checken of er units moeten vallen -> marte is dit aan het doen
		// voor als er geworkt wordt op een cube, kijk daar af ;)
		// + uitleggen aan marte: hoe wordt deze functie precies opgeroepen?
		if (cube == null)
			throw new IllegalArgumentException();
		if (cube.isPassableIn(this))
			return;

		TerrainType oldType = this.getTerrainType(cube);
		this.setTerrainType(cube, TerrainType.AIR);

		double probability = RANDOM_GEN.nextDouble();
		if (probability < 0.25) {
			if (oldType == TerrainType.WOOD)
				this.addMaterial(new Log(this, cube.getCenter()));
			else if (oldType == TerrainType.ROCK)
				this.addMaterial(new Boulder(this, cube.getCenter()));
		}
	}

	// UNITS//

	void removeUnit(Unit unit) {
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

	public void addUnit(Unit unit) throws IllegalArgumentException {
		if (!unit.getPosition().isValidForObjectIn(this))
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

	private boolean canAddAsUnit(Unit unit) {
		if (unit.isDead())
			return false;
		if (unit.getWorld() != null)
			return false;
		if (this.units.size() == MAX_UNITS)
			return false;
		return true;
	}

	boolean hasAsUnit(Unit unit) {
		if (unit == null)
			return false;
		return this.units.contains(unit);
	}

	int getNbUnits() {
		return this.units.size();
	}

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

	boolean canAddAsFaction(Faction faction) {
		if (faction.getWorld() != null)
			return false;
		if (this.getNbActiveFactions() == MAX_FACTIONS && faction.getNbUnits() > 0)
			return false;
		return true;
	}

	// TODO ik dacht om deze invariant te gebruiken en dus als een faction leeg
	// is, die direct weg te halen
	// uit de world (faction.world wordt dan null)
	// @invar Each faction in a world should always be active, i.e. non-empty
	// -> zie txt bestand opmerkingen, dat kan in principe, zou geheugen
	// besparen, maar de rvaag is of dat ooit problemen met iets gaat geven
	int getNbActiveFactions() {
		int counter = 0;
		for (Faction faction : this.factions)
			if (faction.getNbUnits() > 0)
				counter += 1;
		return counter;
	}

	boolean hasAsFaction(Faction faction) {
		if (faction == null)
			return false;
		return this.factions.contains(faction);
	}

	int getNbFactions() {
		return this.factions.size();
	}

	/**
	 * return all the factions present in this world as a set
	 */
	Set<Faction> getAllFactions() {
		return factions;
	}

	boolean hasProperFactions() {
		for (Faction faction : this.factions)
			if (faction == null || faction.getWorld() != this)
				return false;
		return true;
	}

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
	void addMaterial(@Raw Material material) {
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
	boolean canHaveAsMaterial(Material material) {
		if (material == null)
			return false;
		if (material.getPosition() == null)
			return false;
		if (!material.getPosition().getCube().isPassableIn(this))
			return false;
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

		Iterator<Material> it = materials.iterator();
		while (it.hasNext()) {
			Material material = it.next();
			if ((material.getPosition().getCube() == cube) && (Log.class.isInstance(material)))
				// cast mag gebruikt worden, want je hebt net gecontroleerd of
				// het een log is
				result.add((Log) material);
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

		Iterator<Material> it = materials.iterator();
		while (it.hasNext()) {
			Material material = it.next();
			if ((material.getPosition().getCube() == cube) && (Boulder.class.isInstance(material)))
				// cast mag gebruikt worden, want je hebt net gecontroleerd of
				// het een boulder is
				result.add((Boulder) material);
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
	 *         attached. | for each material in Material: | if
	 *         (hasAsMaterial(material)) | then canHaveAsMaterial(material) && |
	 *         (material.getWorld() == this)
	 */
	boolean hasProperMaterials() {
		for (Material material : this.getAllMaterials()) {
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
	int getNbMaterials() {
		return this.getAllMaterials().size();
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

	// OTHERS//

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
