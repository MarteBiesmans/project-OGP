package hillbillies.model;

import static org.junit.Assert.*;
import org.junit.Test;

public class WorldTest {

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
	
	@Test
	public void getNbCubes() {
		int[][][] terrainTypes = new int[50][50][50];
		World testWorld = new World(terrainTypes);
		assertEquals(testWorld.getNbCubes(), 50);
	}
}
