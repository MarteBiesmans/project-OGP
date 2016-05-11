package hillbillies.model;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import hillbillies.part2.listener.DefaultTerrainChangeListener;

/**
 * a test suite for the class World
 */
public class WorldTest {
	

	@Test
	public void constructorWorld_LegalTerrainTypes() {
		int[][][] terrainTypes = new int[5][5][5];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		assertArrayEquals(terrainTypes, testWorld.getTerrainTypesArray());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void constructorWorld_IllegalNumberInTerrainTypes() {
		int[][][] terrainTypes = new int[5][5][5];
		terrainTypes[4][4][4] = 4;
		new World(terrainTypes, new DefaultTerrainChangeListener());
	}
	
	@Test
	public void getNbCubesX() {
		int[][][] terrainTypes = new int[6][5][5];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		assertEquals(testWorld.getNbCubesX(), 6);
	}
	
	@Test
	public void getNbCubesY() {
		int[][][] terrainTypes = new int[5][6][5];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		assertEquals(testWorld.getNbCubesY(), 6);
	}
	
	@Test
	public void getNbCubesZ() {
		int[][][] terrainTypes = new int[5][5][6];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		assertEquals(testWorld.getNbCubesZ(), 6);
	}
		
	@Test (expected = IllegalArgumentException.class)
	public void getTerrainTypeInt_IllegalCube() {
		int[][][] terrainTypes = new int[5][5][5];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		testWorld.getTerrainTypeInt(new Cube(5,5,5));
	}
	
	@Test
	public void setTerrainType_LegalCaseSolidToPassable() {
		int[][][] terrainTypes = new int[5][5][5];
		terrainTypes[0][0][0] = 1;
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		Cube testCube = new Cube(0,0,0);
		testWorld.setTerrainType(testCube, TerrainType.AIR);
		assertEquals(testWorld.getTerrainType(testCube), TerrainType.AIR);
	}
	
	@Test
	public void setTerrainType_LegalCasePassableToSolid() {
		int[][][] terrainTypes = new int[5][5][5];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		Cube testCube = new Cube(0,0,0);
		testWorld.setTerrainType(testCube, TerrainType.ROCK);
		assertEquals(testWorld.getTerrainType(testCube), TerrainType.ROCK);
	}
	
	@Test
	public void setTerrainType_LegalCaseSolidToSolid() {
		int[][][] terrainTypes = new int[5][5][5];
		terrainTypes[0][0][0] = 1;
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		Cube testCube = new Cube(0,0,0);
		testWorld.setTerrainType(testCube, TerrainType.WOOD);
		assertEquals(testWorld.getTerrainType(testCube), TerrainType.WOOD);
	}
	
	@Test
	public void setTerrainType_LegalCasePassableToPassable() {
		int[][][] terrainTypes = new int[5][5][5];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		Cube testCube = new Cube(0,0,0);
		testWorld.setTerrainType(testCube, TerrainType.WORKSHOP);
		assertEquals(testWorld.getTerrainType(testCube), TerrainType.WORKSHOP);
	}
	
	@Test
	public void setTerrainType_Collapse() {
		int[][][] terrainTypes = new int[5][5][5];
		terrainTypes[1][1][0] = 1;
		terrainTypes[1][1][1] = 1;
		terrainTypes[2][1][1] = 1;
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		Cube testCube1 = new Cube(1,1,0);
		Cube testCube2 = new Cube(1,1,1);
		Cube testCube3 = new Cube(2,1,1);
		testWorld.setTerrainType(testCube1, TerrainType.AIR);
		assertEquals(testWorld.getTerrainType(testCube2), TerrainType.AIR);
		assertEquals(testWorld.getTerrainType(testCube3), TerrainType.AIR);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setTerrainType_IllegalCube() {
		int[][][] terrainTypes = new int[5][5][5];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		Cube testCube = new Cube(5,5,5);
		testWorld.setTerrainType(testCube, TerrainType.ROCK);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void collapse_NullCube(){
		int[][][] terrainTypes = new int[5][5][5];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		testWorld.collapse(null);
	}
	
	@Test
	public void collapse_PassableCube(){
		int[][][] terrainTypes = new int[5][5][5];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		testWorld.collapse(new Cube(0,0,0));
	}
	
	@Test
	public void collapse_LegalCaseRock(){
		Set<Boulder> boulders = new HashSet<Boulder>();
		
		for (int x = 0; x < 100; x++) {
			int[][][] terrainTypes = new int[1][1][1];
			terrainTypes[0][0][0] = 1;
			World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
			Cube testCube = new Cube(0,0,0);
			testWorld.collapse(testCube);
			boulders.addAll(testWorld.getAllBoulders());
		}
		assertFalse(boulders.isEmpty());
	}
	
	@Test
	public void collapse_LegalCaseWood(){
		Set<Log> logs = new HashSet<Log>();
		
		for (int x = 0; x < 100; x++) {
			int[][][] terrainTypes = new int[1][1][1];
			terrainTypes[0][0][0] = 2;
			World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
			Cube testCube = new Cube(0,0,0);
			testWorld.collapse(testCube);
			logs.addAll(testWorld.getAllLogs());
		}
		assertFalse(logs.isEmpty());
	}
	
	@Test
	public void collapse_InitiatesNewCollapses(){
		int[][][] terrainTypes = new int[5][5][5];
		terrainTypes[1][1][0] = 2;
		terrainTypes[1][1][1] = 2;
		terrainTypes[2][1][1] = 1;
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		Cube testCube1 = new Cube(1,1,0);
		Cube testCube2 = new Cube(1,1,1);
		Cube testCube3 = new Cube(2,1,1);
		testWorld.collapse(testCube1);
		assertEquals(testWorld.getTerrainType(testCube2), TerrainType.AIR);
		assertEquals(testWorld.getTerrainType(testCube3), TerrainType.AIR);
	}
	
	@Test
	public void getAllWorkshops_Full() {
		int[][][] terrainTypes = new int[5][5][5];
		terrainTypes[1][1][0] = 3;
		terrainTypes[1][1][1] = 3;
		terrainTypes[2][1][1] = 3;
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		
		Set<Cube> workshops = new HashSet<Cube>();
		workshops.add(new Cube(1,1,0));
		workshops.add(new Cube(1,1,1));
		workshops.add(new Cube(2,1,1));
		
		assertEquals(testWorld.getAllWorkshops(), workshops);
	}
	
	@Test
	public void getAllWorkshops_Empty() {
		int[][][] terrainTypes = new int[5][5][5];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		
		Set<Cube> workshops = new HashSet<Cube>();
		
		assertEquals(testWorld.getAllWorkshops(), workshops);
	}
	
	@Test
	public void addUnit_FullWorld(){
		int[][][] terrainTypes = new int[5][5][5];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		for (int x = 0; x < World.MAX_UNITS; x++)
			testWorld.spawnUnit(true);
		assertEquals(testWorld.getNbUnits(), World.MAX_UNITS);
		Unit testUnit = new Unit(3.2, 1.3, 0.5, "James O'Hara", 50, 50, 25, 55, true);
		testWorld.addUnit(testUnit);
		assertEquals(testWorld.getNbUnits(), World.MAX_UNITS);
	}
	
//	@Test
//	public void addUnit_FullFactions(){
//		//TODO
//	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addUnit_IllegalPosition1(){
		int[][][] terrainTypes = new int[5][5][5];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		Unit testUnit = new Unit(3.2, 1.3, 2.1, "James O'Hara", 50, 50, 25, 55, true);
		testWorld.addUnit(testUnit);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addUnit_IllegalPosition2(){
		int[][][] terrainTypes = new int[5][5][5];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		Unit testUnit = new Unit(3.2, 1.3, 6.5, "James O'Hara", 50, 50, 25, 55, true);
		testWorld.addUnit(testUnit);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addUnit_DeadUnit(){
		int[][][] terrainTypes = new int[5][5][5];
		World testWorld1 = new World(terrainTypes, new DefaultTerrainChangeListener());
		World testWorld2 = new World(terrainTypes, new DefaultTerrainChangeListener());
		Unit testUnit = new Unit(3.2, 1.3, 0.5, "James O'Hara", 50, 50, 25, 55, true);
		testWorld1.addUnit(testUnit);
		testUnit.die();
		testWorld2.addUnit(testUnit);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addUnit_NotNullWorld(){
		int[][][] terrainTypes = new int[5][5][5];
		World testWorld1 = new World(terrainTypes, new DefaultTerrainChangeListener());
		World testWorld2 = new World(terrainTypes, new DefaultTerrainChangeListener());
		Unit testUnit = new Unit(3.2, 1.3, 6.5, "James O'Hara", 50, 50, 25, 55, true);
		testWorld1.addUnit(testUnit);
		testWorld2.addUnit(testUnit);		
	}
	
	@Test
	public void addUnit_LegalCaseNewFaction(){
		int[][][] terrainTypes = new int[5][5][5];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		Unit testUnit = new Unit(3.2, 1.3, 0.5, "James O'Hara", 50, 50, 25, 55, true);
		testWorld.addUnit(testUnit);
		assertEquals(testWorld.getNbActiveFactions(),1);
	}
	
	@Test
	public void addUnit_LegalCaseMaxFactions(){
		int[][][] terrainTypes = new int[5][5][5];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		
		for (int x = 0; x < World.MAX_FACTIONS; x++)
			testWorld.spawnUnit(true);
		assertEquals(testWorld.getNbActiveFactions(), World.MAX_FACTIONS);
		
		for (int x = 0; x < World.MAX_FACTIONS; x++){
			testWorld.spawnUnit(true);
		}
		
		//check if units are added to an existing faction
		assertEquals(testWorld.getNbActiveFactions(), World.MAX_FACTIONS);
		//check if units are added to the smallest faction
		for (Faction faction: testWorld.getAllActiveFactions())
			assertEquals(faction.getNbUnits(), 2);
	}
	
	@Test
	public void getAllUnits_LegalCase(){
		int[][][] terrainTypes = new int[5][5][5];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		Set<Unit> allTestUnits = new HashSet<Unit>();
				
		for (int x = 0; x < 4; x++) {
			Unit testUnit = new Unit(x, 1.3, 0.5, "James O'Hara", 50, 50, 25, 55, true);
			testWorld.addUnit(testUnit);
			allTestUnits.add(testUnit);
		}
		assertEquals(testWorld.getAllUnits(), allTestUnits);		
	}
	
	@Test
	public void getAllUnits_LegalCaseEmpty(){
		int[][][] terrainTypes = new int[5][5][5];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		Set<Unit> allTestUnits = new HashSet<Unit>();
		assertEquals(testWorld.getAllUnits(), allTestUnits);
	}
	
	@Test
	public void spawnUnit(){
		int[][][] terrainTypes = new int[5][5][5];
		terrainTypes[2][2][0] = 2;
		terrainTypes[2][2][1] = 2;
		terrainTypes[2][2][2] = 2;
		terrainTypes[0][0][0] = 1;
		
		for (int x = 0; x < 100; x++) {
			World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
			Unit testUnit = testWorld.spawnUnit(true);
			assertTrue(testUnit.isValidUnit());
		}
	}

	@Test
	public void getAllActiveFactions(){
		int[][][] terrainTypes = new int[5][5][5];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		Set<Faction> allTestFactions = new HashSet<Faction>();
		
		Faction testFaction = new Faction();
		testWorld.addFaction(testFaction);
		allTestFactions.add(testFaction);
		
		assertEquals(testWorld.getAllActiveFactions(), new HashSet<Faction>() );
		
		Unit testUnit = new Unit(2.5, 1.3, 0.5, "James O'Hara", 50, 50, 25, 55, true);
		testFaction.addUnit(testUnit);
		assertEquals(testWorld.getAllActiveFactions(), allTestFactions);
	}
	
	
	@Test
	public void getAllLogs(){
		int[][][] terrainTypes = new int[10][10][10];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		Set<Material> allTestLogs = new HashSet<Material>();
		assertTrue(testWorld.getAllLogs().isEmpty());

		Boulder testBoulder = new Boulder();
		Position testPosition1 = new Position(0.2,0.5,0.8);
		testWorld.addMaterial(testBoulder, testPosition1);
		
		Log testLog = new Log();
		Position testPosition2 = new Position(0.3,0.5,0.8);
		testWorld.addMaterial(testLog, testPosition2);
		allTestLogs.add(testLog);
		
		assertEquals(testWorld.getAllLogs(), allTestLogs);
	}
	
	@Test
	public void getAllBoulders(){
		int[][][] terrainTypes = new int[10][10][10];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		Set<Material> allTestBoulders = new HashSet<Material>();
		assertTrue(testWorld.getAllBoulders().isEmpty());

		Boulder testBoulder = new Boulder();
		Position testPosition1 = new Position(0.2,0.5,0.8);
		testWorld.addMaterial(testBoulder, testPosition1);
		allTestBoulders.add(testBoulder);
		
		Log testLog = new Log();
		Position testPosition2 = new Position(0.3,0.5,0.8);
		testWorld.addMaterial(testLog, testPosition2);
		
		assertEquals(testWorld.getAllBoulders(), allTestBoulders);
	}
	
	@Test
	public void advanceTime_LegalSeconds(){
		int[][][] terrainTypes = new int [5][5][5];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		float seconds = (float)0.2;
		testWorld.advanceTime(seconds);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void advanceTime_TooBigSeconds(){
		int[][][] terrainTypes = new int [5][5][5];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		testWorld.advanceTime((float)0.3);
		throw new IllegalArgumentException();
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void advanceTime_TooSmallSeconds(){
		int[][][] terrainTypes = new int [5][5][5];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		testWorld.advanceTime((float)-0.000000001);
		throw new IllegalArgumentException();
	}
}
