package hillbillies.model;

import static org.junit.Assert.*;

import org.junit.Test;

import hillbillies.part2.listener.DefaultTerrainChangeListener;
import ogp.framework.util.Util;

/***
 * a test suite for the class Material
 * @author Ellen & Marte
 */
public class MaterialTest {
	
	@Test
	public void getPosition_LegalCase() {
		World testWorld = new World(new int[5][5][5], new DefaultTerrainChangeListener());
		Boulder testBoulder = new Boulder();
		Position testPosition = new Position(0.2,0.5,0.8);
		testWorld.addMaterial(testBoulder, testPosition);
		assertEquals(testPosition, testBoulder.getPosition());		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void advanceTime_IllegalSecondsPositive(){
		int[][][] TerrainTypes = new int[5][5][5];
		World testWorld = new World(TerrainTypes, new DefaultTerrainChangeListener());
		Boulder testBoulder = new Boulder();
		Position testPosition = new Position(0.2,0.5,0.8);
		testWorld.addMaterial(testBoulder, testPosition);
		testBoulder.advanceTime((float) 0.3);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void advanceTime_IllegalSecondsNegative(){
		int[][][] TerrainTypes = new int[5][5][5];
		World testWorld = new World(TerrainTypes, new DefaultTerrainChangeListener());
		Boulder testBoulder = new Boulder();
		Position testPosition = new Position(0.2,0.5,0.8);
		testWorld.addMaterial(testBoulder, testPosition);
		testBoulder.advanceTime((float) -0.1);
	}
	
	@Test
	public void advanceTime_notFalling_HasOwner() {
		int[][][] TerrainTypes = new int[5][5][5];
		World testWorld = new World(TerrainTypes, new DefaultTerrainChangeListener());
		Boulder testBoulder = new Boulder();
		Position testPosition = new Position(0.2,0.5,0.8);
		Unit testUnit = new Unit(3.2, 1.3, 1.9, "James O'Hara", 50, 50, 25, 55, true);
		testWorld.addMaterial(testBoulder, testPosition);
		testUnit.addMaterial(testBoulder);
		
		testBoulder.advanceTime((float) 0.19);
		assertEquals(new Position(3.2, 1.3, 1.9), testBoulder.getPosition());
	}
	
	@Test
	public void advanceTime_notFalling_ZIsZero() {
		int[][][] TerrainTypes = new int[5][5][5];
		World testWorld = new World(TerrainTypes, new DefaultTerrainChangeListener());
		Boulder testBoulder = new Boulder();
		Position testPosition = new Position(0.2,0.5,0.8);
		testWorld.addMaterial(testBoulder, testPosition);
		
		testBoulder.advanceTime((float) 0.19);
		assertEquals(testPosition, testBoulder.getPosition());
	}
	
	@Test
	public void advanceTime_notFalling_CubeBelowIsSolid() {
		int[][][] TerrainTypes = new int[5][5][5];
		TerrainTypes[0][0][0] = 1;
		World testWorld = new World(TerrainTypes, new DefaultTerrainChangeListener());
		Boulder testBoulder = new Boulder();
		Position testPosition = new Position(0.2,0.5,1.8);
		testWorld.addMaterial(testBoulder, testPosition);
		
		testBoulder.advanceTime((float) 0.19);
		assertEquals(testPosition, testBoulder.getPosition());
	}
	
	@Test
	public void advanceTime_Falling() {
		int[][][] TerrainTypes = new int[5][5][5];
		World testWorld = new World(TerrainTypes, new DefaultTerrainChangeListener());
		Boulder testBoulder = new Boulder();
		Position testPosition = new Position(0.2,0.5,1.8);
		testWorld.addMaterial(testBoulder, testPosition);
		
		testBoulder.advanceTime((float) 0.19);
		assertTrue(Util.fuzzyEquals(testBoulder.getPosition().getRealX(), 0.2));
		assertTrue(Util.fuzzyEquals(testBoulder.getPosition().getRealY(), 0.5));
		assertTrue(Util.fuzzyEquals(testBoulder.getPosition().getRealZ(), 1.23));
	}

}
