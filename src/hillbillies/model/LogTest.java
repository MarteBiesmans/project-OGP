package hillbillies.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LogTest {
	
	@Test
	public void constructorBoulder_LegalCase() {
		Log testLog = new Log();
		assertEquals(null, testLog.getWorld());
		assertEquals(null, testLog.getOwner());
		assertEquals(null, testLog.getPosition());
		for (int x = 0; x < 1000; x++) {
			Log testLogX = new Log();
			assertTrue(testLogX.getWeight()>=10 && testLogX.getWeight()<=50);
		}
	}

}
