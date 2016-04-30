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

	public static final double EPSILON = 1e-6;

	@Test
	public void constructorUnit_LegalCase() {
		Unit testUnit = new Unit(3.2, 1.3, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		assertTrue(Util.fuzzyEquals(3.2, testUnit.getPosition().getRealX()));
		assertTrue(Util.fuzzyEquals(1.3, testUnit.getPosition().getRealY()));
		assertTrue(Util.fuzzyEquals(5.9, testUnit.getPosition().getRealZ()));

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
	public void getPosition_() {
		//TODO
	}
	
	@Test
	public void getCube_() {
		//TODO
	}
	
	@Test
	public void getName_() {
		//TODO
	}
	
	@Test
	public void setName_() {
		//TODO
	}
	
	@Test
	public void getStrength_() {
		//TODO
	}

	@Test
	public void setStrength_IllegalWeight() {
		Unit testUnit = new Unit(3.2, 1.3, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		testUnit.setStrength(100);
		assertEquals(testUnit.getWeight(), 75);
	}
	
	@Test
	public void getAgility_() {
		//TODO
	}

	@Test
	public void setAgility_IllegalWeight() {
		Unit testUnit = new Unit(3.2, 1.3, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		testUnit.setAgility(100);
		assertEquals(testUnit.getWeight(), 75);
	}

	@Test
	public void getWeight_() {
		//TODO
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
	public void getToughness_() {
		//TODO
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
	public void getHitpoints_() {
		//TODO
	}
	
	@Test
	public void getMaxHitpoints_Test() {
		Unit testUnit = new Unit(3.2, 1.3, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		assertEquals(testUnit.getMaxHitpoints(), 28);
	}
	
	@Test
	public void getStaminaPoints_() {
		//TODO
	}
	
	@Test
	public void getMaxStaminaPoints_() {
		//TODO
	}
	
	@Test
	public void getOrientation_() {
		//TODO
	}
	
	@Test
	public void setOrientation_() {
		Unit testUnit = new Unit(3.2, 1.3, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		testUnit.setOrientation(-Math.PI);
		assertTrue(Util.fuzzyEquals(testUnit.getOrientation(), Math.PI));
		testUnit.setOrientation(4*Math.PI);
		assertTrue(Util.fuzzyEquals(testUnit.getOrientation(), 0.0));
	}
	
	@Test
	public void getExperiencePoints_() {
		//TODO
	}
	
	@Test
	public void advanceTime_() {
		//TODO
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
	public void isAttacking_() {
		//TODO
	}
	
	@Test
	public void isMoving_() {
		//TODO
	}
	
	@Test
	public void isWalking_() {
		//TODO
	}
	
	@Test
	public void isSprinting_() {
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
	public void isWorking_() {
		//TODO
	}
	
	@Test
	public void isResting() {
		//TODO
	}
	
	@Test
	public void isFalling() {
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
	public void getFaction_() {
		//TODO
	}
	
	@Test
	public void hasLog_() {
		//TODO
	}
	
	@Test
	public void hasBoulder_() {
		//TODO
	}
	
	@Test
	public void isDead_() {
		//TODO
	}
}
