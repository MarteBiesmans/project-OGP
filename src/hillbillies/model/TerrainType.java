package hillbillies.model;

/**
 * every terrainType is associated with an integer The integer types are as
 * follows: 0: air 1: rock 2: wood 3: workshop
 * 
 * @author Ellen
 *
 */
public enum TerrainType {
	AIR, ROCK, WOOD, WORKSHOP;

	public boolean isPassable() {
		return ((this == TerrainType.AIR) || (this == TerrainType.WORKSHOP));
	}

	public int getAssociatedInt() {
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
