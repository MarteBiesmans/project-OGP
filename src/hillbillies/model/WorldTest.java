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
	
	@Test
	public void setTerrainType_LegalCaseSolidToPassable() {}
	
	@Test
	public void setTerrainType_LegalCasePassableToSolid() {}
	
	@Test
	public void setTerrainType_LegalCaseSolidToSolid() {}
	
	@Test
	public void setTerrainType_LegalCasePassableToPassable() {}
	
	@Test
	public void setTerrainType_Collapse() {}
	
	@Test (expected = IllegalArgumentException.class)
	public void setTerrainType_IllegalCube() {}
	
	@Test (expected = IllegalArgumentException.class)
	public void collapse_NullCube(){}
	
	@Test (expected = IllegalArgumentException.class)
	public void collapse_PassableCube(){}
	
	@Test
	public void collapse_LegalCaseRock(){}
	
	@Test
	public void collapse_LegalCaseWood(){}
	
	@Test
	public void collapse_InitiatesNewCollapses(){}
	
	@Test
	public void addUnit_FullWorld(){}
	
	@Test
	public void addUnit_FullFactions(){}
	
	@Test (expected = IllegalArgumentException.class)
	public void addUnit_IllegalPosition(){}
	
	@Test (expected = IllegalArgumentException.class)
	public void addUnit_DeadUnit(){}
	
	@Test (expected = IllegalArgumentException.class)
	public void addUnit_NotNullWorld(){}
	
	@Test
	public void addUnit_LegalCaseNewFaction(){}
	
	@Test
	public void addUnit_LegalCaseMaxFactions(){}
	
	@Test
	public void getAllUnits_LegalCase(){}
	
	@Test
	public void getAllUnits_LegalCaseEmpty(){}
	
	@Test
	public void spawnUnit(){}
	
	@Test
	public void getAllActiveFactions(){
		//empty
		//including non-active factions
	}
	
	@Test
	public void getAllMaterials(){}
	
	@Test
	public void getAllLogs(){}
	
	@Test
	public void getAllBoulders(){}
	
	@Test (expected = IllegalArgumentException.class)
	public void advanceTime_IllegalSeconds(){}
	
	
	
	
	
	
	
	
	
}
