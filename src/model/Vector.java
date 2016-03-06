package model;

public abstract class Vector<Type extends Number> {
	
	private Type x;
	private Type y;
	private Type z;
	
	public Vector(Type x, Type y, Type z) {
		this.setValues(x, y, z);
	}
	
	public boolean isValidValues(Type x, Type y, Type z) {
		return isValidX(x) && isValidY(y) && isValidZ(z);
	}
	
	public void setValues(Type x, Type y, Type z) {
		if (!isValidValues(x, y, z)) {
			throw new IllegalArgumentException();
		}
		
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	protected Type getX() {
		return this.x;
	}
	
	public abstract boolean isValidX(Type x);
	
	public void setX(Type x) {
		if (!isValidX(x)) {
			throw new IllegalArgumentException();
		}
		
		this.x = x;
	}
	
	protected Type getY() {
		return this.y;
	}
	
	public abstract boolean isValidY(Type y);
	
	public void setY(Type y) {
		if (!isValidY(y)) {
			throw new IllegalArgumentException();
		}
		
		this.y = y;
	}
	
	public abstract boolean isValidZ(Type z);
	
	protected Type getZ() {
		return this.z;
	}
	
	public void setZ(Type z) {
		if (!isValidZ(z)) {
			throw new IllegalArgumentException();
		}
		
		this.z = z;
	}
}
