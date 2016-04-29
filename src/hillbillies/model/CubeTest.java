package hillbillies.model;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import hillbillies.part2.listener.DefaultTerrainChangeListener;

/**
 * an incomplete test suite for the class Cube
 * @author Ellen
 *
 */
public class CubeTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void constructor_IllegalCase() {
		new Cube(-3, 4, 5);
	}
	
	@Test
	public void isValidIn_IllegalCaseX() {
		World testWorld = new World(new int[50][50][50], new DefaultTerrainChangeListener());
		Cube testCube = new Cube(50,10,10);
		assertEquals(testCube.isValidIn(testWorld), false);
	}
	
	@Test
	public void isValidIn_IllegalCaseY() {
		World testWorld = new World(new int[50][50][50], new DefaultTerrainChangeListener());
		Cube testCube = new Cube(10,50,10);
		assertEquals(testCube.isValidIn(testWorld), false);
	}
	
	@Test
	public void isValidIn_IllegalCaseZ() {
		World testWorld = new World(new int[50][50][50], new DefaultTerrainChangeListener());
		Cube testCube = new Cube(10,10,50);
		assertEquals(testCube.isValidIn(testWorld), false);
	}
	
	@Test
	public void getAllNeighbouringCubes_centre() {
		World testWorld = new World(new int[50][50][50], new DefaultTerrainChangeListener());
		Cube testCube = new Cube(25,25,25);
		Set<Cube> calculatedNeighbouringCubes = testCube.getAllAdjacentCubes(testWorld);
		Set<Cube> neighbouringCubes = new HashSet<Cube>();
		
		//deel 1
		neighbouringCubes.add(new Cube(26,24,24));
		neighbouringCubes.add(new Cube(26,24,25));
		neighbouringCubes.add(new Cube(26,24,26));
		
		neighbouringCubes.add(new Cube(26,25,24));
		neighbouringCubes.add(new Cube(26,25,25));
		neighbouringCubes.add(new Cube(26,25,26));
		
		neighbouringCubes.add(new Cube(26,26,24));
		neighbouringCubes.add(new Cube(26,26,25));
		neighbouringCubes.add(new Cube(26,26,26));
		
		//deel 2
		neighbouringCubes.add(new Cube(25,24,24));
		neighbouringCubes.add(new Cube(25,24,25));
		neighbouringCubes.add(new Cube(25,24,26));
		
		neighbouringCubes.add(new Cube(25,25,24));
		neighbouringCubes.add(new Cube(25,25,26));
		
		neighbouringCubes.add(new Cube(25,26,24));
		neighbouringCubes.add(new Cube(25,26,25));
		neighbouringCubes.add(new Cube(25,26,26));
		
		//deel 3
		neighbouringCubes.add(new Cube(24,24,24));
		neighbouringCubes.add(new Cube(24,24,25));
		neighbouringCubes.add(new Cube(24,24,26));
		
		neighbouringCubes.add(new Cube(24,25,24));
		neighbouringCubes.add(new Cube(24,25,25));
		neighbouringCubes.add(new Cube(24,25,26));
		
		neighbouringCubes.add(new Cube(24,26,24));
		neighbouringCubes.add(new Cube(24,26,25));
		neighbouringCubes.add(new Cube(24,26,26));
		
		
		assertEquals(calculatedNeighbouringCubes, neighbouringCubes);
		}
	
	@Test
	public void getAllNeighbouringCubes_BottomCorner() {
		World testWorld = new World(new int[50][50][50], new DefaultTerrainChangeListener());
		Cube testCube = new Cube(0,0,0);
		Set<Cube> calculatedNeighbouringCubes = testCube.getAllAdjacentCubes(testWorld);
		Set<Cube> neighbouringCubes = new HashSet<Cube>();
		
		//z=0
		neighbouringCubes.add(new Cube(0,1,0));
		neighbouringCubes.add(new Cube(1,0,0));
		neighbouringCubes.add(new Cube(1,1,0));
		
		//z=1
		neighbouringCubes.add(new Cube(0,0,1));
		neighbouringCubes.add(new Cube(0,1,1));
		neighbouringCubes.add(new Cube(1,0,1));
		neighbouringCubes.add(new Cube(1,1,1));
		
		assertEquals(calculatedNeighbouringCubes, neighbouringCubes);
		
	}
	
	@Test
	public void getAllNeighbouringCubes_TopCorner() {
		World testWorld = new World(new int[50][50][50], new DefaultTerrainChangeListener());
		Cube testCube = new Cube(49,49,49);
		Set<Cube> calculatedNeighbouringCubes = testCube.getAllAdjacentCubes(testWorld);
		Set<Cube> neighbouringCubes = new HashSet<Cube>();
		
		//z=49
		neighbouringCubes.add(new Cube(49,48,49));
		neighbouringCubes.add(new Cube(48,49,49));
		neighbouringCubes.add(new Cube(48,48,49));
		
		//z=48
		neighbouringCubes.add(new Cube(49,49,48));
		neighbouringCubes.add(new Cube(49,48,48));
		neighbouringCubes.add(new Cube(48,49,48));
		neighbouringCubes.add(new Cube(48,48,48));
		
		assertEquals(calculatedNeighbouringCubes, neighbouringCubes);
	}
	
	@Test
	public void isSameOrAdjacentCube() {
		Cube cube1 = new Cube(5,5,5);
		Cube cube2 = new Cube(5,5,6);
		Cube cube3 = new Cube(5,5,7);
		Cube cube4 = new Cube(5,4,4);
		assertTrue(cube1.isSameOrAdjacentCube(cube1));
		assertTrue(cube1.isSameOrAdjacentCube(cube2));
		assertFalse(cube1.isSameOrAdjacentCube(cube3));
		assertTrue(cube1.isSameOrAdjacentCube(cube4));
	}
	
	@Test
	public void isSameOrDirectlyAdjacentCube() {
		Cube cube1 = new Cube(5,5,5);
		Cube cube2 = new Cube(5,5,6);
		Cube cube3 = new Cube(5,5,7);
		Cube cube4 = new Cube(5,4,4);
		assertTrue(cube1.isSameOrDirectlyAdjacentCube(cube1));
		assertTrue(cube1.isSameOrDirectlyAdjacentCube(cube2));
		assertFalse(cube1.isSameOrDirectlyAdjacentCube(cube3));
		assertFalse(cube1.isSameOrDirectlyAdjacentCube(cube4));
	}
	
	
	@Test
	public void getAllAdjacentCubes_centre() {
		World testWorld = new World(new int[50][50][50], new DefaultTerrainChangeListener());
		Cube testCube = new Cube(25,25,25);
		Set<Cube> calculatedAdjacentCubes = testCube.getAllDirectlyAdjacentCubes(testWorld);
		Set<Cube> adjacentCubes = new HashSet<Cube>();
		
		adjacentCubes.add(new Cube(24,25,25));
		adjacentCubes.add(new Cube(26,25,25));
		
		adjacentCubes.add(new Cube(25,24,25));
		adjacentCubes.add(new Cube(25,26,25));

		adjacentCubes.add(new Cube(25,25,24));
		adjacentCubes.add(new Cube(25,25,26));		
		
		assertEquals(calculatedAdjacentCubes, adjacentCubes);
	}
	
	@Test
	public void getAllAdjacentCubes_BottomBorder() {
		World testWorld = new World(new int[50][50][50], new DefaultTerrainChangeListener());
		Cube testCube = new Cube(0,0,0);
		Set<Cube> calculatedAdjacentCubes = testCube.getAllDirectlyAdjacentCubes(testWorld);
		Set<Cube> adjacentCubes = new HashSet<Cube>();
		
		//z=0
		adjacentCubes.add(new Cube(0,1,0));
		adjacentCubes.add(new Cube(1,0,0));
		
		//z=1
		adjacentCubes.add(new Cube(0,0,1));
		
		assertEquals(calculatedAdjacentCubes, adjacentCubes);
		
	}
	
	@Test
	public void getAllAdjacentCubes_TopBorder() {
		World testWorld = new World(new int[50][50][50], new DefaultTerrainChangeListener());
		Cube testCube = new Cube(49,49,49);
		Set<Cube> calculatedAdjacentCubes = testCube.getAllDirectlyAdjacentCubes(testWorld);
		Set<Cube> adjacentCubes = new HashSet<Cube>();
		
		//z=49
		adjacentCubes.add(new Cube(49,48,49));
		adjacentCubes.add(new Cube(48,49,49));
		
		//z=48
		adjacentCubes.add(new Cube(49,49,48));
		
		assertEquals(calculatedAdjacentCubes, adjacentCubes);
	}
}
