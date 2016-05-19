package hillbillies.model;

public abstract class PositionObject {
	
	public abstract Position getPosition();

	public Cube getCube() {
		if (position == null)
			return null;
		else
			return position.getCube();
	}

	public abstract void setPosition();

	private Position position;

}
