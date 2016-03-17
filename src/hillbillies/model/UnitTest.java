package hillbillies.model;


import static org.junit.Assert.*;

import org.junit.Test;

public class UnitTest {

	public static final double EPSILON = 1e-6;
			
	@Test 
	public void constructorUnit_LegalCase() {
		Unit testUnit = new Unit(3.2, 1.3, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		assertEquals(3.2, testUnit.getPosition().getRealX(), EPSILON);
		assertEquals(1.3, testUnit.getPosition().getRealY(), EPSILON);
		assertEquals(5.9, testUnit.getPosition().getRealZ(), EPSILON);
		
		assertEquals("James O'Hara", testUnit.getName());
		assertEquals(50, testUnit.getStrength());
		assertEquals(50, testUnit.getAgility());
		assertEquals(25, testUnit.getToughness());
		assertEquals(55, testUnit.getWeight());
		
		assertEquals(Math.PI/2.0, testUnit.getOrientation(), EPSILON);
		assertFalse(testUnit.canStartDefaultBehaviour());
		
		assertEquals(28, testUnit.getHitpoints(), EPSILON);
		assertEquals(28, testUnit.getStaminaPoints(), EPSILON);	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void Constructor_IllegalPositionX() throws Exception {
		new Unit(53.1, 1.1, 5.9, "James O'Hara", 50, 50, 25, 55, true);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void Constructor_IllegalPositionY() throws Exception {
		new Unit(3.1, -1.1, 5.9, "James O'Hara", 50, 50, 25, 55, true);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void Constructor_IllegalPositionZ() throws Exception {
		new Unit(3.1, 1.1, 105.9, "James O'Hara", 50, 50, 25, 55, true);
	}

	@Test(expected = IllegalArgumentException.class)
	public void Constructor_IllegalName() throws Exception {
		new Unit(3.1, 1.1, 5.9, "james O'Hara", 50, 50, 25, 55, true);
	}
	
	@Test
	public void Constructor_IllegalInitialStrengthLow() {
		Unit testUnit = new Unit(3.1, 1.1, 5.9, "James O'Hara", 24, 50, 25, 55, true);
		assertEquals(testUnit.getStrength(),25);
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
		assertEquals(testUnit.getStrength(),100);
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
		assertEquals(testUnit.getAgility(),25);
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
		assertEquals(testUnit.getAgility(),100);
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
		assertEquals(testUnit.getToughness(),100);
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
		assertEquals(testUnit.getToughness(),100);
	}
	
	@Test
	public void Constructor_IllegalWeightHigh() {
		Unit testUnit = new Unit(3.1, 1.1, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		testUnit.setToughness(250);
		assertEquals(testUnit.getToughness(), 200);
	}
	
	@Test
	public void setStrength_IllegalWeight() {
		Unit testUnit = new Unit(3.2, 1.3, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		testUnit.setStrength(100);
		assertEquals(testUnit.getWeight(), 75);
	}
	
	@Test
	public void setAgility_IllegalWeight() {
		Unit testUnit = new Unit(3.2, 1.3, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		testUnit.setAgility(100);
		assertEquals(testUnit.getWeight(), 75);
	}
	
	@Test
	public void setWeight_IllegalHitpoints() {
		Unit testUnit = new Unit(3.2, 1.3, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		//strength, agility, toughness, weight
		/**
		 * hitpoints = 28
		 * setWeight(50)
		 * hitpoints 28 is nu illegal, hitpoints moet 25 worden
		 */
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
	public void setToughness_IllegalHitpoints() {
		Unit testUnit = new Unit (3.2, 1.3, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		/**
		 * staminaPoints = 28
		 * setToughness(10)
		 * hitpoints 28 is nu illegal, hitpoints moet 11 worden
		 */
		testUnit.setToughness(10);
		assertEquals((int) testUnit.getHitpoints(), 11);
	}
	
	@Test
	public void setToughness_IllegalStaminaPoints() {
		Unit testUnit = new Unit (3.2, 1.3, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		testUnit.setToughness(10);
		assertEquals((int) testUnit.getStaminaPoints(), 11);
	}
	
	@Test
	public void getMaxHitpoints_Test() {
		Unit testUnit = new Unit(3.2, 1.3, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		assertEquals(testUnit.getMaxHitpoints(), 28);
	}
	
	
	@Test (expected = IllegalArgumentException.class)
	public void constructorCube_IllegalCase() {
		new Cube(-3,4,5);
	}
	
	@Test
	public void constructorPosition_LegalCase() {
		Cube testCube = new Cube(3,4,5);
		Position testPosition = new Position(0.1,0.2,0.3, testCube);
		assertEquals(testPosition.getRealX(), 3.1, EPSILON);
		assertEquals(testPosition.getRealY(), 4.2, EPSILON);
		assertEquals(testPosition.getRealZ(), 5.3, EPSILON);
	}
	
	@Test
	public void constructorPosition_TooBigX() {
		Cube testCube = new Cube(3,4,5);
		Position testPosition = new Position(2.1,0.2,0.3, testCube);
		assertEquals(testPosition.getRealX(), 5.1, EPSILON);
		assertEquals(testPosition.getRealY(), 4.2, EPSILON);
		assertEquals(testPosition.getRealZ(), 5.3, EPSILON);
		assertEquals(testPosition.getCube().getX(), 5);
	}
	
	@Test
	public void constructorPosition_NegativeX() {
		Cube testCube = new Cube(3,4,5);
		Position testPosition = new Position(-0.1,0.2,0.3, testCube);
		System.out.println(testPosition.getRealX());
		System.out.println(testPosition.getX());
		System.out.println(testPosition.getCube().getX());
		assertEquals(testPosition.getRealX(), 2.9, EPSILON);
		assertEquals(testPosition.getRealY(), 4.2, EPSILON);
		assertEquals(testPosition.getRealZ(), 5.3, EPSILON);
		assertEquals(testPosition.getCube().getX(), 2);
	}
	
}
