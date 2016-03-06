package model;
import static org.junit.Assert.*;

import org.junit.Test;

public class UnitTest {

	public static final double EPSILON = 1e-6;
			
	@Test
	public void constructor_LegalCase() {
		Unit testUnit = new Unit(3.1, 1.1, 5.9, "James O'Hara", 50, 50, 25, 55, true);
//		assertEquals(new Position(3.1, 1.1, 5.9), testUnit.getPosition());
		
		
		assertEquals("James O'Hara", testUnit.getName());
		assertEquals(50, testUnit.getStrength());
		assertEquals(50, testUnit.getAgility());
		assertEquals(25, testUnit.getToughness());
		assertEquals(55, testUnit.getWeight());
		
		assertEquals(Math.PI/2.0, testUnit.getOrientation(), EPSILON);
//		assertFalse(testUnit.canStartDefaultBehaviour());
		
//		assertEquals(28, testUnit.getHitpoints(), EPSILON);
//		assertEquals(28, testUnit.getStaminaPoints(), EPSILON);
		
		
	}

}
