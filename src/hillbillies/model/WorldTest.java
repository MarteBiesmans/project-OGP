package hillbillies.model;

import static org.junit.Assert.*;
import org.junit.Test;

public class WorldTest {
	
	/**
	 * return an empty testWorld filled with air except half of the lowest layer is rock
	 */
	public World newTestWorld() {
		int[][][] terrainTypes = new int[50][50][50];

		for(int x=0;x<terrainTypes.length/2;x++)
			for(int y=0;y<terrainTypes[x].length;y++)
				terrainTypes[x][y][0] = 1;
					
		return new World(terrainTypes);
	}

	@Test
	public void constructorWorld_LegalTerrainTypes() {
		int[][][] terrainTypes = new int[50][50][50];
		World testWorld = new World(terrainTypes);
		assertArrayEquals(terrainTypes, testWorld.getTerrainTypesArray());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void constructorWorld_IllegalDimensionsTerrainTypes1() {
		int[][][] terrainTypes = new int[50][50][60];
		new World(terrainTypes);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void constructorWorld_IllegalDimensionsTerrainTypes2() {
		int[][][] terrainTypes = new int[50][60][50];
		new World(terrainTypes);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void constructorWorld_IllegalDimensionsTerrainTypes3() {
		int[][][] terrainTypes = new int[60][50][50];
		new World(terrainTypes);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void constructorWorld_IllegalNumberInTerrainTypes() {
		int[][][] terrainTypes = new int[50][50][50];
		terrainTypes[49][49][49] = 4;
		new World(terrainTypes);
	}
	
	@Test
	public void getNbCubesX() {
		int[][][] terrainTypes = new int[50][50][50];
		World testWorld = new World(terrainTypes);
		assertEquals(testWorld.getNbCubesX(), 50);
	}
}
