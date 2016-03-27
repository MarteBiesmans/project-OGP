package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * 
 * @invar	Each world has a valid array terrainTypes.
 * 
 * @author Ellen & Marte
 */
public class World {
	
	/**
	 * Initialize this World with given number of cubes.
	 * 
	 * @pre		The terrain always has the shape of a box (i.e., the array terrainTypes[0] has
	 *          the same length as terrainTypes[1] etc.).
	 * @param	terrainTypes
	 *            A three-dimensional array (structured as [x][y][z]) with the
	 *            types of the terrain, encoded as integers. The integer types
	 *            are as follows:
	 *            0: air
	 *            1: rock
	 *            2: tree
	 *            3: workshop
	 * @post	The terrain types of this new world are equal to the given terrain types.
	 * @throws	IllegalArgumentException
	 * 			terrainTypes is not valid for this world
	 */
	public World(int[][][] terrainTypes) throws IllegalArgumentException {
		if (!canHaveAsTerrainTypes(terrainTypes))
			throw new IllegalArgumentException();
		this.terrainTypes = terrainTypes;
	}


	/**
	 * Check if the three-dimensional array terrainTypes is valid for this world.
	 * 
	 * @param	terrainTypes
	 * 			The array to check.
	 * @return	false if the given array terrainTypes is not cubical
	 */
	private boolean canHaveAsTerrainTypes(int[][][] terrainTypes) {
		if ((terrainTypes.length != terrainTypes[0].length)
			|| (terrainTypes.length != terrainTypes[0][0].length))
			return false;
		return true;
	}
	
	public int[][][] getTerrainTypesArray(){
		return this.terrainTypes;
	}

	/**
	 * Variable registering the terrain types of this world in a three-dimensional array.
	 */
	private int[][][] terrainTypes;
	
	
	/**
	 * Return the number of cubes on one axis of this world.
	 */
	@Immutable
	public int getNbCubes() {
		return this.getTerrainTypesArray().length;
	}

	public TerrainType getTerrainType(int x, int y, int z){
		if (this.getTerrainTypesArray()[x][y][z] == 1)
			return TerrainType.ROCK;
		if (this.getTerrainTypesArray()[x][y][z] == 2)
			return TerrainType.WOOD;
		if (this.getTerrainTypesArray()[x][y][z] == 3)
			return TerrainType.WORKSHOP;
		if (this.getTerrainTypesArray()[x][y][z] == 0)
			return TerrainType.AIR;
		else
			throw new IllegalArgumentException();
	}
	
}
