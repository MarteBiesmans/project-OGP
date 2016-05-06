package hillbillies.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * test suite for the class Boulder
 */
public class BoulderTest {

	@Test
	public void constructorBoulder_LegalCase() {
		Boulder testBoulder = new Boulder();
		assertEquals(null, testBoulder.getWorld());
		assertEquals(null, testBoulder.getOwner());
		assertEquals(null, testBoulder.getPosition());
		for (int x = 0; x < 1000; x++) {
			Boulder testBoulderX = new Boulder();
			assertTrue(testBoulderX.getWeight()>=10 && testBoulderX.getWeight()<=50);
		}
	}
}
