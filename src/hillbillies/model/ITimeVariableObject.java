package hillbillies.model;

public interface ITimeVariableObject {

	public static double FALLING_VELOCITY = -3;

	/**
	 * update the properties of a TimeVariableObject, based on that Object's
	 * current properties and a given duration 'seconds' in seconds of game time
	 * 
	 * @param seconds
	 * @throws IllegalArgumentException
	 *             the seconds are not in the interval [0,0.2[
	 */
	public abstract void advanceTime(float seconds);
	
}
