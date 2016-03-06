import static org.junit.Assert.*;

import org.junit.Test;

public class UnitTest {

	public static final double EPSILON = 1e-6;
			
	@Test
	public void constructor_LegalCase() {
		Unit testUnit = new Unit(3.1, 1.1, 5.9, "James O'Hara", 50, 50, 25, 55);
		assertEquals(3.1, testUnit.getPositionX(), EPSILON);
		assertEquals(1.1, testUnit.getPositionY(), EPSILON);
		assertEquals(5.9, testUnit.getPositionZ(), EPSILON);
		
		assertEquals(3, testUnit.getCubePositionX());
		assertEquals(1, testUnit.getCubePositionY());
		assertEquals(5, testUnit.getCubePositionZ());
		
		assertEquals("James O'Hara", testUnit.getName());
		assertEquals(50, testUnit.getStrength());
		assertEquals(50, testUnit.getAgility());
		assertEquals(25, testUnit.getToughness());
		assertEquals(55, testUnit.getWeight());
		
		assertEquals(Math.PI/2.0, testUnit.getOrientation(), EPSILON);
		assertEquals(Movement.NONE, testUnit.getIsMoving());
		
		assertEquals(28, testUnit.getHitpoints());
		assertEquals(28, testUnit.getStaminaPoints());
		
		
	}

}
