package hillbillies.model;

public enum TerrainType {
	AIR, ROCK, WOOD, WORKSHOP;
	
	public boolean isPassable(){
		return ((this == TerrainType.AIR) || (this == TerrainType.WORKSHOP));
	}
	
}
