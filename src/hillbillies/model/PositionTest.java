package hillbillies.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PositionTest {
	
	public static final double EPSILON = 1e-6;

	@Test
	public void constructorPosition_LegalCase() {
		Cube testCube = new Cube(3, 4, 5);
		Position testPosition = new Position(0.1, 0.2, 0.3, testCube);
		assertEquals(testPosition.getRealX(), 3.1, EPSILON);
		assertEquals(testPosition.getRealY(), 4.2, EPSILON);
		assertEquals(testPosition.getRealZ(), 5.3, EPSILON);
	}

	@Test
	public void constructorPosition_TooBigX() {
		Cube testCube = new Cube(3, 4, 5);
		Position testPosition = new Position(2.1, 0.2, 0.3, testCube);
		assertEquals(testPosition.getRealX(), 5.1, EPSILON);
		assertEquals(testPosition.getRealY(), 4.2, EPSILON);
		assertEquals(testPosition.getRealZ(), 5.3, EPSILON);
		assertEquals(testPosition.getCube().getX(), 5);
	}

	@Test
	public void constructorPosition_NegativeX() {
		Cube testCube = new Cube(3, 4, 5);
		Position testPosition = new Position(-0.1, 0.2, 0.3, testCube);
		assertEquals(testPosition.getRealX(), 2.9, EPSILON);
		assertEquals(testPosition.getRealY(), 4.2, EPSILON);
		assertEquals(testPosition.getRealZ(), 5.3, EPSILON);
		assertEquals(testPosition.getCube().getX(), 2);
	}

	@Test
	public void constructorPosition_TooBigNegativeX() {
		Cube testCube = new Cube(3, 4, 5);
		Position testPosition = new Position(-2.1, 0.2, 0.3, testCube);
		assertEquals(testPosition.getRealX(), 0.9, EPSILON);
		assertEquals(testPosition.getRealY(), 4.2, EPSILON);
		assertEquals(testPosition.getRealZ(), 5.3, EPSILON);
		assertEquals(testPosition.getCube().getX(), 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorPosition_ReallyTooBigNegativeX() {
		Cube testCube = new Cube(3, 4, 5);
		new Position(-5.1, 0.2, 0.3, testCube);
	}

	@Test
	public void constructorPosition_Zero() {
		Position testPosition = new Position(0.0, 0.0, 0.0);
		assertEquals(testPosition.getRealX(), 0.0, EPSILON);
		assertEquals(testPosition.getRealY(), 0.0, EPSILON);
		assertEquals(testPosition.getRealZ(), 0.0, EPSILON);
		assertEquals(testPosition.getCube().getX(), 0);
		assertEquals(testPosition.getCube().getY(), 0);
		assertEquals(testPosition.getCube().getZ(), 0);
	}
}
