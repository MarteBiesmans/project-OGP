package hillbillies.model;

/**
 * An abstract class of timevariable objects.
 * @author Ellen & Marte
 *
 */
public abstract class TimeVariableObject {
	
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

	/**
	 * sets the busytime of this time variable object to the given seconds
	 * 
	 * @post busyTime of this time variable object will equal the given seconds
	 *       if it is a positive value, else busyTime will equal 0
	 * @param busyTime
	 */
	protected void setBusyTime(double busyTime) {
		this.busyTime = Math.max(busyTime, 0);
	}

	/**
	 * returns the busytime of this time variable object
	 */
	protected double getBusyTime() {
		return this.busyTime;
	}

	/**
	 * substracts the given seconds off of the busytime of this time variable
	 * object.
	 * 
	 * @param seconds
	 */
	protected void busyTimeMin(double seconds) {
		this.setBusyTime(this.getBusyTime() - seconds);
	}

	/**
	 * a variable representing the time needed to finish the current activity of
	 * this object
	 */
	private double busyTime;
}
