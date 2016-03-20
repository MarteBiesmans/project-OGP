package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * @Value
 * @author Marte & Ellen
 *
 */
@Value
public class Position {


	public Position(double x, double y, double z) throws IllegalArgumentException {
		Cube cube = new Cube((int) (x / Cube.SIDE_LENGTH),
								(int) (y / Cube.SIDE_LENGTH),
								(int) (z / Cube.SIDE_LENGTH));
		x %= Cube.SIDE_LENGTH;
		y %= Cube.SIDE_LENGTH;
		z %= Cube.SIDE_LENGTH;
		
		this.cube = cube;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Position(double x, double y, double z, Cube cube) throws IllegalArgumentException, NullPointerException {
		if (cube == null)
				throw new NullPointerException();
		
		this.cube = new Cube((int) (cube.getX() + (x / Cube.SIDE_LENGTH)),
				(int) (cube.getY() + (y / Cube.SIDE_LENGTH)),
				(int) (cube.getZ() + (z / Cube.SIDE_LENGTH)));
		
		x %= Cube.SIDE_LENGTH;
		y %= Cube.SIDE_LENGTH;
		z %= Cube.SIDE_LENGTH;
		
		if (x < 0)
			x += Cube.SIDE_LENGTH;
		if (y < 0)
			y += Cube.SIDE_LENGTH;
		if (z < 0)
			z += Cube.SIDE_LENGTH;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Cube getCube() {
		return this.cube;
	}
	
	private Cube cube;
	
	private double x;
	private double y;
	private double z;
	
	public Position min(Position other) {
		return new Position(this.getRealX() - other.getRealX(),
				this.getRealY() - other.getRealY(),
				this.getRealZ() - other.getRealZ());
	}

	public double getX() {
		return this.x;
	}

	public double getRealX() {
		return this.cube.getX() * Cube.SIDE_LENGTH + this.getX();
	}

	public double getY() {
		return this.y;
	}

	public double getRealY() {
		return this.cube.getY() * Cube.SIDE_LENGTH + this.getY();
		}

	public double getZ() {
		return this.z;
	}

	public double getRealZ() {
		return this.cube.getZ() * Cube.SIDE_LENGTH + this.getZ();
		}
}
