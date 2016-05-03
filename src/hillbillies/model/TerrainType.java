package hillbillies.model;

/**
 * an enum describing the TerrainTypes in a game world
 * 
 * @note	every terrainType is associated with an integern the integer types are as
 * 			follows: 0-air 1-rock 2-wood 3-workshop
 * 
 * @author Ellen & Marte
 *
 */
public enum TerrainType {
	AIR, ROCK, WOOD, WORKSHOP;

	/**
	 * check whether this TerrainType is a passable type
	 * @return	true if this TerrainType equals air or workshop
	 */
	boolean isPassable() {
		return ((this == TerrainType.AIR) || (this == TerrainType.WORKSHOP));
	}

	
	/**
	 * get the associated integer for this TerrainType
	 * @return	1 for ROCK, 2 for WOOD, 3 for WORKSHOP, 0 for everything else (thus AIR)
	 */
	int getAssociatedInt() {
		if (this == TerrainType.ROCK)
			return 1;
		else if (this == TerrainType.WOOD)
			return 2;
		else if (this == TerrainType.WORKSHOP)
			return 3;
		else
			return 0;
	}
}
