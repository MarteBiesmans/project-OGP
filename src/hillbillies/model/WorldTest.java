package hillbillies.model;

import static org.junit.Assert.*;
import org.junit.Test;

import hillbillies.part2.listener.DefaultTerrainChangeListener;

public class WorldTest {
	

	@Test
	public void constructorWorld_LegalTerrainTypes() {
		int[][][] terrainTypes = new int[50][50][50];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		assertArrayEquals(terrainTypes, testWorld.getTerrainTypesArray());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void constructorWorld_IllegalNumberInTerrainTypes() {
		int[][][] terrainTypes = new int[50][50][50];
		terrainTypes[49][49][49] = 4;
		new World(terrainTypes, new DefaultTerrainChangeListener());
	}
	
	@Test
	public void getNbCubesX() {
		int[][][] terrainTypes = new int[60][50][50];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		assertEquals(testWorld.getNbCubesX(), 60);
	}
	
	@Test
	public void getNbCubesY() {
		int[][][] terrainTypes = new int[50][60][50];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		assertEquals(testWorld.getNbCubesY(), 60);
	}
	
	@Test
	public void getNbCubesZ() {
		int[][][] terrainTypes = new int[50][50][60];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		assertEquals(testWorld.getNbCubesZ(), 60);
	}
		
	@Test (expected = IllegalArgumentException.class)
	public void getTerrainTypeInt_IllegalCube() {
		int[][][] terrainTypes = new int[50][50][50];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		testWorld.getTerrainTypeInt(new Cube(100,100,100));
	
	
	}
}
