package hillbillies.model;

/**
 * every terrainType is associated with an integer
 * The integer types are as follows: 
 * 		0: air 
 * 		1: rock 
 * 		2: tree 
 * 		3: workshop
 * @author Ellen
 *
 */
public enum TerrainType {
	AIR, ROCK, WOOD, WORKSHOP;
	
	public boolean isPassable(){
		return ((this == TerrainType.AIR) || (this == TerrainType.WORKSHOP));
	}
	
}
