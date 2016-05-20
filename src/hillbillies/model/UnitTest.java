package hillbillies.model;

import static org.junit.Assert.*;
import org.junit.Test;

import hillbillies.part2.listener.DefaultTerrainChangeListener;
import ogp.framework.util.Util;

/**
 * a test suite for the class Unit
 * 
 * @author Ellen & Marte
 */
public class UnitTest {

	@Test
	public void constructor_LegalCase() {
		Unit testUnit = new Unit(3.2, 1.3, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		assertTrue(Util.fuzzyEquals(3.5, testUnit.getPosition().getRealX()));
		assertTrue(Util.fuzzyEquals(1.5, testUnit.getPosition().getRealY()));
		assertTrue(Util.fuzzyEquals(5.5, testUnit.getPosition().getRealZ()));

		assertEquals("James O'Hara", testUnit.getName());
		assertEquals(50, testUnit.getStrength());
		assertEquals(50, testUnit.getAgility());
		assertEquals(25, testUnit.getToughness());
		assertEquals(55, testUnit.getWeight());

		assertTrue(Util.fuzzyEquals(Math.PI / 2.0, testUnit.getOrientation()));
		assertFalse(testUnit.canStartDefaultBehaviour());

		assertTrue(Util.fuzzyEquals(28, testUnit.getHitpoints()));
		assertTrue(Util.fuzzyEquals(28, testUnit.getStaminaPoints()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void Constructor_IllegalPositionX() throws Exception {
		Unit testUnit = new Unit(6.1, 1.1, 4.9, "James O'Hara", 50, 50, 25, 55, true);
		World testWorld = new World(new int[5][5][5], new DefaultTerrainChangeListener());
		testWorld.addUnit(testUnit);
	}

	@Test(expected = IllegalArgumentException.class)
	public void Constructor_IllegalPositionY() throws Exception {
		new Unit(3.1, -1.1, 5.9, "James O'Hara", 50, 50, 25, 55, true);
	}

	@Test(expected = IllegalArgumentException.class)
	public void Constructor_IllegalPositionZ() throws Exception {
		Unit testUnit = new Unit(3.1, 1.1, 105.9, "James O'Hara", 50, 50, 25, 55, true);
		World testWorld = new World(new int[5][5][5], new DefaultTerrainChangeListener());
		testWorld.addUnit(testUnit);
	}

	@Test(expected = IllegalArgumentException.class)
	public void Constructor_IllegalName() throws Exception {
		new Unit(3.1, 1.1, 5.9, "james O'Hara", 50, 50, 25, 55, true);
	}

	@Test
	public void Constructor_IllegalInitialStrengthLow() {
		Unit testUnit = new Unit(3.1, 1.1, 5.9, "James O'Hara", 24, 50, 25, 55, true);
		assertEquals(testUnit.getStrength(), 25);
	}

	@Test
	public void Constructor_IllegalStrengthLow() {
		Unit testUnit = new Unit(3.1, 1.1, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		testUnit.setStrength(-1);
		assertEquals(testUnit.getStrength(), 1);
	}

	@Test
	public void Constructor_IllegalInitialStrengthHigh() {
		Unit testUnit = new Unit(3.1, 1.1, 5.9, "James O'Hara", 102, 50, 25, 55, true);
		assertEquals(testUnit.getStrength(), 100);
	}

	@Test
	public void Constructor_IllegalStrengthHigh() {
		Unit testUnit = new Unit(3.1, 1.1, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		testUnit.setStrength(250);
		assertEquals(testUnit.getStrength(), 200);
	}

	@Test
	public void Constructor_IllegalInitialAgilityLow() {
		Unit testUnit = new Unit(3.1, 1.1, 5.9, "James O'Hara", 50, 20, 25, 55, true);
		assertEquals(testUnit.getAgility(), 25);
	}

	@Test
	public void Constructor_IllegalAgilityLow() {
		Unit testUnit = new Unit(3.1, 1.1, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		testUnit.setAgility(0);
		assertEquals(testUnit.getAgility(), 1);
	}

	@Test
	public void Constructor_IllegalInitialAgilityHigh() {
		Unit testUnit = new Unit(3.1, 1.1, 5.9, "James O'Hara", 50, 105, 25, 55, true);
		assertEquals(testUnit.getAgility(), 100);
	}

	@Test
	public void Constructor_IllegalAgilityHigh() {
		Unit testUnit = new Unit(3.1, 1.1, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		testUnit.setAgility(250);
		assertEquals(testUnit.getAgility(), 200);
	}

	@Test
	public void Constructor_IllegalInitialToughnessLow() {
		Unit testUnit = new Unit(3.1, 1.1, 5.9, "James O'Hara", 50, 50, 20, 55, true);
		assertEquals(testUnit.getToughness(), 25);
	}

	@Test
	public void Constructor_IllegalToughnessLow() {
		Unit testUnit = new Unit(3.1, 1.1, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		testUnit.setToughness(0);
		assertEquals(testUnit.getToughness(), 1);
	}

	@Test
	public void Constructor_IllegalInitialToughnessHigh() {
		Unit testUnit = new Unit(3.1, 1.1, 5.9, "James O'Hara", 50, 50, 125, 55, true);
		assertEquals(testUnit.getToughness(), 100);
	}

	@Test
	public void Constructor_IllegalToughnessHigh() {
		Unit testUnit = new Unit(3.1, 1.1, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		testUnit.setToughness(250);
		assertEquals(testUnit.getToughness(), 200);
	}

	@Test
	public void Constructor_IllegalInitialWeightLow() {
		Unit testUnit = new Unit(3.1, 1.1, 5.9, "James O'Hara", 50, 53, 25, 20, true);
		assertEquals(testUnit.getWeight(), 52);
	}

	@Test
	public void Constructor_IllegalWeightLow() {
		Unit testUnit = new Unit(3.1, 1.1, 5.9, "James O'Hara", 50, 55, 25, 55, true);
		testUnit.setWeight(0);
		assertEquals(testUnit.getWeight(), 53);
	}

	@Test
	public void Constructor_IllegalInitialWeightHigh() {
		Unit testUnit = new Unit(3.1, 1.1, 5.9, "James O'Hara", 50, 50, 125, 55, true);
		assertEquals(testUnit.getToughness(), 100);
	}

	@Test
	public void Constructor_IllegalWeightHigh() {
		Unit testUnit = new Unit(3.1, 1.1, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		testUnit.setToughness(250);
		assertEquals(testUnit.getToughness(), 200);
	}
	
	@Test
	public void getPosition() {
		Unit testUnit = new Unit(3.1, 1.1, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		assertEquals(testUnit.getPosition(), new Position(3.5, 1.5, 5.5));
	}
	
	@Test
	public void getCube() {
		Unit testUnit = new Unit(3.1, 1.1, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		assertEquals(testUnit.getCube(), new Cube(3, 1, 5));
	}
	
	@Test
	public void getName() {
		Unit testUnit = new Unit(3.1, 1.1, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		assertEquals(testUnit.getName(), "James O'Hara");
	}
	
	@Test
	public void setName_LegalCase() {
		Unit testUnit = new Unit(3.1, 1.1, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		testUnit.setName("James'\"");
		assertEquals(testUnit.getName(), "James'\"");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setName_IllegalCase_NoCapital() {
		Unit testUnit = new Unit(3.1, 1.1, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		testUnit.setName("james");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setName_IllegalCase_Null() {
		Unit testUnit = new Unit(3.1, 1.1, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		testUnit.setName(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setName_IllegalCase_TooSmall() {
		Unit testUnit = new Unit(3.1, 1.1, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		testUnit.setName("j");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setName_IllegalCharacter() {
		Unit testUnit = new Unit(3.1, 1.1, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		testUnit.setName("James.O'Hara");
	}
	
	@Test
	public void getStrength_() {
		Unit testUnit = new Unit(3.1, 1.1, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		assertEquals(testUnit.getStrength(), 50);
	}

	@Test
	public void setStrength_IllegalWeight() {
		Unit testUnit = new Unit(3.2, 1.3, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		testUnit.setStrength(100);
		assertEquals(testUnit.getWeight(), 75);
	}
	
	@Test
	public void getAgility_() {
		Unit testUnit = new Unit(3.1, 1.1, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		assertEquals(testUnit.getAgility(), 50);
	}

	@Test
	public void setAgility_IllegalWeight() {
		Unit testUnit = new Unit(3.2, 1.3, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		testUnit.setAgility(100);
		assertEquals(testUnit.getWeight(), 75);
	}

	@Test
	public void getToughness_() {
		Unit testUnit = new Unit(3.1, 1.1, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		assertEquals(testUnit.getToughness(), 25);
	}

	@Test
	public void setToughness_IllegalHitpoints() {
		Unit testUnit = new Unit(3.2, 1.3, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		//na setToughness(10) is staminaPoints = 28 illegal, hitpoints moet 11 worden
		testUnit.setToughness(10);
		assertEquals((int) testUnit.getHitpoints(), 11);
	}

	@Test
	public void setToughness_IllegalStaminaPoints() {
		Unit testUnit = new Unit(3.2, 1.3, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		testUnit.setToughness(10);
		assertEquals((int) testUnit.getStaminaPoints(), 11);
	}

	@Test
	public void getWeight() {
		Unit testUnit = new Unit(3.1, 1.1, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		assertEquals(testUnit.getWeight(), 55);
	}
	
	@Test
	public void setWeight_IllegalHitpoints() {
		Unit testUnit = new Unit(3.2, 1.3, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		//na setWeight(50) is hitpoints = 28 illegal, hitpoints moet 25 worden
		testUnit.setWeight(50);
		assertEquals((int) testUnit.getHitpoints(), 25);
	}

	@Test
	public void setWeight_IllegalStaminaPoints() {
		Unit testUnit = new Unit(3.2, 1.3, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		testUnit.setWeight(50);
		assertEquals((int) testUnit.getStaminaPoints(), 25);
	}
	
	@Test
	public void getHitpoints() {
		Unit testUnit = new Unit(3.1, 1.1, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		assertEquals((int)testUnit.getHitpoints(), 28);
	}
	
	@Test
	public void getMaxHitpoints() {
		Unit testUnit = new Unit(3.2, 1.3, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		assertEquals(testUnit.getMaxHitpoints(), 28);
		testUnit.setToughness(30);
		assertEquals(testUnit.getMaxHitpoints(), 33);
	}
	
	@Test
	public void getStaminaPoints() {
		Unit testUnit = new Unit(3.1, 1.1, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		assertEquals((int)testUnit.getStaminaPoints(), 28);
	}
	
	@Test
	public void getMaxStaminaPoints() {
		Unit testUnit = new Unit(3.2, 1.3, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		assertEquals(testUnit.getMaxStaminaPoints(), 28);
		testUnit.setToughness(30);
		assertEquals(testUnit.getMaxStaminaPoints(), 33);
	}
	
	@Test
	public void getOrientation() {
		Unit testUnit = new Unit(3.2, 1.3, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		assertTrue(Util.fuzzyEquals(testUnit.getOrientation(), Math.PI/2));
	}
	
	@Test
	public void getExperiencePoints() {
		Unit testUnit = new Unit(3.2, 1.3, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		assertEquals(testUnit.getExperiencePoints(), 0);
	}
	
	@Test
	public void getWorld() {
		Unit testUnit = new Unit(3.2, 1.3, 0.9, "James O'Hara", 50, 50, 25, 55, true);
		int[][][] terrainTypes = new int[5][5][5];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		testWorld.addUnit(testUnit);
		assertEquals(testUnit.getWorld(), testWorld);
	}

	@Test
	public void getFaction() {
		int[][][] terrainTypes = new int[5][5][5];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		
		Unit testUnit1 = new Unit(3.2, 1.3, 0.9, "James O'Hara", 50, 50, 25, 55, true);
		testWorld.addUnit(testUnit1);
		
		for (int i=1; i<World.MAX_FACTIONS; i++) {
			Unit testUnit = new Unit(4.2, 1.3, 0.9, "James O'Hara", 50, 50, 25, 55, true);
			testWorld.addUnit(testUnit);
		}
		Unit testUnit6 = new Unit(3.2, 1.3, 0.9, "James O'Hara", 50, 50, 25, 55, true);
		testWorld.addUnit(testUnit6);
		
		//get faction with two units
		Faction testFaction = null;
		for (Faction faction : testWorld.getAllActiveFactions()) {
			if (faction.getNbUnits() == 2)
				testFaction = faction;
		}

		Unit testUnitFromFaction1 = testFaction.getAllUnits().iterator().next();
		Unit testUnitFromFaction2 = testFaction.getAllUnits().iterator().next();
		assertEquals(testUnitFromFaction1.getFaction(), testUnitFromFaction2.getFaction());	
	}

	@Test
	public void hasLog() {
		int[][][] terrainTypes = new int[5][5][5];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		Log testLog = new Log();
		testWorld.addMaterial(testLog, new Position(0.1,0.1,0.1));
		
		Unit testUnit = new Unit(1.2, 0.3, 0.9, "James O'Hara", 50, 50, 25, 55, true);
		testWorld.addUnit(testUnit);
		testUnit.workAt(new Cube(0,0,0));
		
		//na 10 seconden (50*0.2) moet de work order klaar zijn
		for (int i=0; i<50; i++) {
			testWorld.advanceTime((float)0.2);
		}
		
		assertTrue(testUnit.hasLog());
	}

	@Test
	public void hasBoulder() {
		int[][][] terrainTypes = new int[5][5][5];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		Boulder testBoulder = new Boulder();
		testWorld.addMaterial(testBoulder, new Position(0.1,0.1,0.1));
		
		Unit testUnit = new Unit(1.2, 0.3, 0.9, "James O'Hara", 50, 50, 25, 55, true);
		testWorld.addUnit(testUnit);
		testUnit.workAt(new Cube(0,0,0));
		
		//na 10 seconden (50*0.2) moet de work order klaar zijn
		for (int i=0; i<50; i++) {
			testWorld.advanceTime((float)0.2);
		}
		
		assertTrue(testUnit.hasBoulder());	}

	@Test
	public void getNbMaterials() {
		
		int[][][] terrainTypes = new int[5][5][5];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		Boulder testBoulder = new Boulder();
		testWorld.addMaterial(testBoulder, new Position(0.1,0.1,0.1));
		
		Unit testUnit = new Unit(1.2, 0.3, 0.9, "James O'Hara", 50, 50, 25, 55, true);
		testWorld.addUnit(testUnit);
		testUnit.workAt(new Cube(0,0,0));
		
		assertTrue(testUnit.getNbMaterials()==0);
		
		//na 10 seconden (50*0.2) moet de work order klaar zijn
		for (int i=0; i<50; i++) {
			testWorld.advanceTime((float)0.2);
		}
		
		assertTrue(testUnit.getNbMaterials()==1);
	}
	
	@Test
	public void nextActivity_() {
		//TODO
	}

	@Test
	public void isAttacking() {
		int[][][] terrainTypes = new int[5][5][5];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		
		Unit testUnit1 = new Unit(1.2, 0.3, 0.9, "James O'Hara", 50, 50, 25, 55, true);
		testWorld.addUnit(testUnit1);
		Unit testUnit2 = new Unit(1.2, 1.3, 0.9, "James O'Hara", 50, 50, 25, 55, true);
		testWorld.addUnit(testUnit2);
		
		testUnit1.attack(testUnit2);
		assertTrue(testUnit1.isAttacking());
	}

	@Test
	public void isMoving() {
		int[][][] terrainTypes = new int[5][5][5];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		
		Unit testUnit = new Unit(1.2, 0.3, 0.9, "James O'Hara", 50, 50, 25, 55, true);
		testWorld.addUnit(testUnit);
		
		testUnit.moveToAdjacent(1, 0, 0);
		assertTrue(testUnit.isMoving());
	}

	@Test
	public void isWalking_() {
		int[][][] terrainTypes = new int[5][5][5];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		
		Unit testUnit = new Unit(1.2, 0.3, 0.9, "James O'Hara", 50, 50, 25, 55, true);
		testWorld.addUnit(testUnit);
		
		testUnit.moveToAdjacent(1, 0, 0);
		assertTrue(testUnit.isWalking());
	}

	@Test
	public void isSprinting_() {
		int[][][] terrainTypes = new int[5][5][5];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		
		Unit testUnit = new Unit(1.2, 0.3, 0.9, "James O'Hara", 50, 50, 25, 55, true);
		testWorld.addUnit(testUnit);
		
		testUnit.moveToAdjacent(1, 0, 0);
		testUnit.startSprinting();
		assertTrue(testUnit.isSprinting());
	}

	@Test
	public void isWorking_() {
		int[][][] terrainTypes = new int[5][5][5];
		terrainTypes[0][0][0] = 1;
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		
		Unit testUnit = new Unit(1.2, 0.3, 0.9, "James O'Hara", 50, 50, 25, 55, true);
		testWorld.addUnit(testUnit);
		
		testUnit.workAt(new Cube(0,0,0));
		assertTrue(testUnit.isWorking());
	}

	@Test
	public void isResting() {
		int[][][] terrainTypes = new int[5][5][5];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		
		Unit testUnit = new Unit(1.2, 0.3, 0.9, "James O'Hara", 50, 50, 25, 55, true);
		testWorld.addUnit(testUnit);
		
		testUnit.rest();
		assertTrue(testUnit.isResting());
	}

	@Test
	public void advanceTime_attacking() {
		int[][][] terrainTypes = new int[5][5][5];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		
		Unit testUnit1 = new Unit(0.2, 0.3, 0.9, "Ellen", 50, 50, 25, 55, false);
		testWorld.addUnit(testUnit1);
		Unit testUnit2 = new Unit(1.2, 0.3, 0.9, "Marte", 50, 50, 25, 55, false);
		testWorld.addUnit(testUnit2);
		
		testUnit1.attack(testUnit2);
		
		//na 1s (0.2*5) moet het vechten gedaan zijn
		for (int i=0;i<5;i++) {
			testWorld.advanceTime((float)0.2);
		}
		
		//als de busyTime op is, moet de volgende activity in gang gezet worden
		assertTrue(testUnit1.isBeingUseless());
	}
	
	@Test
	public void advanceTime_falling() {
		int[][][] terrainTypes = new int[5][5][5];
		terrainTypes[0][0][0] = 1;
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		
		Unit worker = new Unit(1.2, 0.3, 0.9, "Ellen", 50, 50, 25, 55, false);
		testWorld.addUnit(worker);
		Unit faller = new Unit(0.2, 0.3, 1.9, "Marte", 50, 50, 25, 55, false);
		testWorld.addUnit(faller);
		
		worker.workAt(new Cube(0,0,0));
		
		//na 10s (0.2*50) moet het werken gedaan zijn
		for (int i=0;i<50;i++)
			testWorld.advanceTime((float)0.2);
		
		//als het werken gedaan is, collapset de cube en valt de andere unit
		testWorld.advanceTime((float)0.000001);
		assertTrue(faller.isFalling());
		testWorld.advanceTime((float)0.2);
		assertTrue(Util.fuzzyEquals(faller.getPosition().getRealZ(), 0.5, 1e-5));
		assertTrue(faller.getHitpoints() == 18);
	}
	
	
	@Test
	public void advanceTime_moving_sprinting() {
		//TODO
	}
	
	@Test
	public void advanceTime_moving_sprintingNegativeStamina() {
		//TODO
	}
	
	@Test
	public void advanceTime_moving() {
		//TODO
	}
	
	@Test
	public void advanceTime_working() {
		//TODO
	}
	
	@Test
	public void advanceTime_resting() {
		//TODO
	}
	
	@Test
	public void advanceTime_actingDefault() {
		//TODO
	}
	
	@Test
	public void advanceTime_initiateFalling() {
		//TODO
	}
	
	@Test
	public void advanceTime_levelUp() {
		//TODO
	}
	
	@Test
	public void advanceTime_die() {
		//TODO
	}
	
	@Test
	public void advanceTime_LegalSeconds(){
		Unit testUnit = new Unit(3.2, 1.3, 0.9, "James O'Hara", 50, 50, 25, 55, true);
		int[][][] terrainTypes = new int[5][5][5];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		testWorld.addUnit(testUnit);
		testUnit.advanceTime((float) 0.2);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void advanceTime_TooBigSeconds(){
		Unit testUnit = new Unit(3.2, 1.3, 0.9, "James O'Hara", 50, 50, 25, 55, true);
		int[][][] terrainTypes = new int[5][5][5];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		testWorld.addUnit(testUnit);
		testUnit.advanceTime((float) 0.3);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void advanceTime_TooSmallSeconds(){
		Unit testUnit = new Unit(3.2, 1.3, 0.9, "James O'Hara", 50, 50, 25, 55, true);
		int[][][] terrainTypes = new int[5][5][5];
		World testWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		testWorld.addUnit(testUnit);
		testUnit.advanceTime((float) -0.00001);
	}
	
	@Test
	public void moveToAdjacent_() {
		//TODO
	}
	
	@Test
	public void getMovementSpeed_() {
		//TODO
	}
	
	@Test
	public void moveTo_() {
		//TODO
	}
	
	@Test
	public void startSprinting_() {
		//TODO
	}
	
	@Test
	public void stopSprinting_() {
		//TODO
	}
	
	@Test
	public void startDefaultBehaviour_() {
		//TODO
	}
	
	@Test
	public void stopDefaultBehaviour_() {
		//TODO
	}
	
	@Test
	public void canStartDefaultBehaviour_() {
		//TODO
	}
	
	@Test
	public void workAt_() {
		//TODO
	}
	
	@Test
	public void work_() {
		//TODO
	}
	
	@Test
	public void rest_() {
		//TODO
	}
	
	@Test
	public void attack_() {
		//TODO
	}
	
	@Test
	public void defend_() {
		//TODO
	}
	
	@Test
	public void isDead_() {
		//TODO
	}
}
