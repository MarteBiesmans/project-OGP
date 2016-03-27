package hillbillies.model;

public enum TerrainType {
	AIR, ROCK, TREE, WORKSHOP;
	
	public boolean isPassable(){
		if ((this == TerrainType.AIR) || (this == TerrainType.WORKSHOP))
			return true;
		return false;
	}
	
}
