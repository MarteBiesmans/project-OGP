package model;

import java.util.Random;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * 
 * @invar The name of each unit must be a valid name for any unit. 
 * 		| isValidName(getName())
 * @invar The position of each unit must be a valid position for any unit. 
 * 		| isValidPosition(getPosition())
 * @invar The weight of each unit must be a valid weight for any unit. 
 * 		| this.canHaveAsWeight(getWeight())
 * @invar The strength of each unit must be a valid strength for any unit. 
 * 		| isValidStrength(getStrength())
 * @invar The agility of each unit must be a valid agility for any unit. 
 * 		| isValidAgility(getAgility())
 * @invar The toughness of each unit must be a valid toughness for any unit. 
 * 		| isValidToughness(getToughness())
 * @invar The number of hitpoints of each unit must be a valid number of
 *        hitpoints for any unit. 
 *		| canHaveAsHitpoints(getNbHitpoints())
 * @invar The number of stamina points of each unit must be a valid number of
 *        stamina points for any unit. 
 *        | canHaveAsStaminaPoints(getNbStaminaPoints())
 * @invar The orientation of each unit must be a valid orientation for any unit.
 *        | isValidOrientation(getOrientation())
 * 
 * @author Ellen & Marte
 * @version 1.0
 */
public class Unit {

	private static final Random randomGen = new Random();

	/**
	 * @post The name of this new unit is equal to the given name. 
	 *		| new.getName() == name
	 * @post The strength of this new unit is equal to the given strength. 
	 *		| new.getStrength() == weight
	 * @post The agility of this new unit is equal to the given agility. 
	 *		| new.getAgility() == Agility
	 * @post The toughness of this new unit is equal to the given thoughness. 
	 *		| new.getThoughness() == thoughness
	 * @post The weight of this new unit is equal to the given weight. 
	 *		| new.getWeight() == weight
	 * @post The default behaviour is enabled if this is necessary
	 *		| new.defaultBehaviour == enableDefaultBehaviour
	 * @post The time that the unit is busy is 0 seconds. 
	 *		| new.getBusyTime == 0
	 * @post The position of the unit is equal to the given position. 
	 *		| new.getPosition == Position(x, y, z).toCube()
	 * @post The hitpoints of this new unit are equal to the given hitpoints. 
	 *		| new.getHitpoints() == hitpoints
	 * @post The stamina points of this new unit are equal to the given stamina
	 *       points. 
	 *		| new.getStaminaPoints() == staminaPoints 
	 *       TODO andere post-condities, iets met @param
	 * 
	 */
	public Unit(double x, double y, double z, String name, int strength, int agility, int toughness, int weight, boolean enableDefaultBehaviour)
			throws IllegalArgumentException {

		// name
		this.setName(name);

		// position, orientation
		Cube cube = new Cube((int) (x / Cube.SIDE_LENGTH), (int) (y / Cube.SIDE_LENGTH), (int) (z / Cube.SIDE_LENGTH));
		this.setPosition(new Position(x % Cube.SIDE_LENGTH, y % Cube.SIDE_LENGTH, z % Cube.SIDE_LENGTH, cube));

		this.setOrientation((float) (Math.PI / 2.0));

		// primary attributes
		if (strength < MIN_INIT_VAL_PRIMARY_ATTRIBUTE)
			this.setStrength(MIN_INIT_VAL_PRIMARY_ATTRIBUTE);
		else if (strength > MAX_INIT_VAL_PRIMARY_ATTRIBUTE)
			this.setStrength(MAX_INIT_VAL_PRIMARY_ATTRIBUTE);
		else
			this.setStrength(strength);

		if (agility < MIN_INIT_VAL_PRIMARY_ATTRIBUTE)
			this.setAgility(MIN_INIT_VAL_PRIMARY_ATTRIBUTE);
		else if (agility > MAX_INIT_VAL_PRIMARY_ATTRIBUTE)
			this.setAgility(MAX_INIT_VAL_PRIMARY_ATTRIBUTE);
		else
			this.setAgility(agility);

		if (weight < this.getMinimumInitValWeight())
			this.setWeight(this.getMinimumInitValWeight());
		else if (weight > MAX_INIT_VAL_PRIMARY_ATTRIBUTE)
			this.setWeight(MAX_INIT_VAL_PRIMARY_ATTRIBUTE);
		else
			this.setWeight(weight);

		if (toughness < MIN_INIT_VAL_PRIMARY_ATTRIBUTE)
			this.setToughness(MIN_INIT_VAL_PRIMARY_ATTRIBUTE);
		else if (toughness > MAX_INIT_VAL_PRIMARY_ATTRIBUTE)
			this.setToughness(MAX_INIT_VAL_PRIMARY_ATTRIBUTE);
		else
			this.setToughness(toughness);

		// hitpoints and stamina points
		this.setStaminaPoints(this.getMaxStaminaPoints());
		this.setHitpoints(this.getMaxHitpoints());

		// activity and busy time
		this.setActivity(Activity.NONE);
		this.setBusyTime(0);

		// behaviour
		this.defaultBehaviour = enableDefaultBehaviour;
	}

	/**
	 * Return the position of this unit.
	 */
	@Basic
	@Raw
	public Position getPosition() {
		return this.position;
	}

	/**
	 * Return the position of the upper left corner from the cube that is occupied by this unit.
	 * 
	 * @return the x, y en z values of the position of this unit rounded down to an integer 
	 *         |result == Position((int) this.getPosition.getRealX, 
	 *         |	(int) this.getPosition().getRealY, 
	 *         |	(int) this.getPosition().getRealZ())
	 */
	public Cube getCube() {
		return this.position.getCube();
	}

	/**
	 * Set the position of this unit to the given coordinates.
	 * 
	 * @param x
	 *            the new position on the x-axis for this unit
	 * @param y
	 *            the new position on the y-axis for this unit
	 * @param z
	 *            the new position on the z-axis for this unit
	 * @post the new position on the x-axis of this unit is equal to the given
	 *       x-coordinate. 
	 *       |new.getPosition().getXValue = x
	 * @post the new position on the y-axis of this unit is equal to the given
	 *       y-coordinate. 
	 *       |new.getPosition().getYValue() = y
	 * @post the new position on the z-axis of this unit is equal to the given
	 *       z-coordinate. 
	 *       |new.getPosition().getZValue() = z
	 * @throws IllegalArgumentException
	 *             At least one of the given coordinates is not within the
	 *             boundaries of the game world. 
	 *       | (! isValidPosition(x,y,z))
	 */
	@Raw
	public void setPosition(Position position) throws IllegalArgumentException {
		if (position.getCube() == null)
			throw new IllegalArgumentException();
		if (!isValidPosition(position))
			throw new IllegalArgumentException();
		
		this.position = position;
	}
	
	public boolean isValidPosition(Position position) {
		return ((position.getRealX()<=Cube.X_MAX) && (position.getRealX()>=Cube.X_MIN) &&
				(position.getRealY()<=Cube.Y_MAX) && (position.getRealY()>=Cube.Y_MIN) &&
				(position.getRealZ()<=Cube.Z_MAX) && (position.getRealZ()>=Cube.Z_MIN));
	}

	private Position position;

	/**
	 * Return the name of this unit.
	 */
	@Basic
	@Raw
	public String getName() {
		return this.name;
	}

	/**
	 * Check whether the given name is a valid name for any unit.
	 * 
	 * @param name
	 *            The name to check.
	 * @return Each name is at least two characters long and must start with an
	 *         uppercase letter. Names can only use letters (both upper- and
	 *         lowercase), quotes (both single and double) and spaces. 
	 *         |if (length(name) < 2) 
	 *         |	result == false 
	 *         |if (!isUpperCase(name.charAt(0))) 
	 *         |	return == false |TODO moet hier de
	 *         for-loop uit de implementatie komen?
	 */
	public static boolean isValidName(String name) {
		if (name.length() < 2)
			return false;
		if (!Character.isUpperCase(name.charAt(0)))
			return false;

		// a loop over the name which changes VALID_CHARACTERS to false if it
		// recognizes characters
		// that are not allowed.
		boolean VALID_CHARACTERS = true;
		for (int i = 0; i < name.length(); i++) {
			if ((!Character.isLetter(name.charAt(i))) // is it a letter?
					&& (!(name.charAt(i) == '"')) // is it a double quote?
					&& (!(name.charAt(i) == '\'')) // is it a single
													// quote/apostrophe?
					&& (!(name.charAt(i) == ' ')) // is it a space?
			)
				VALID_CHARACTERS = false;
		}
		return VALID_CHARACTERS;
	}

	/**
	 * Set the name of this unit to the given name.
	 * 
	 * @param name
	 *            The new name for this unit.
	 * @post The name of this unit is equal to the given name. 
	 *		| new.getName() == name
	 * @throws IllegalArgumentException
	 *             The given name is not a valid name for this unit. 
	 *		| (!isValidName(name))
	 */
	@Raw
	public void setName(String name) throws IllegalArgumentException {
		if (!isValidName(name))
			throw new IllegalArgumentException();
		this.name = name;
	}

	/**
	 * Variable registering the name of this unit.
	 */
	private String name;

	/**
	 * constants for the minimum and maximum values of primary attributes
	 * (weight, strength, agility and toughness)
	 */
	private static final int MIN_INIT_VAL_PRIMARY_ATTRIBUTE = 25;
	private static final int MAX_INIT_VAL_PRIMARY_ATTRIBUTE = 100;
	private static final int MIN_VAL_PRIMARY_ATTRIBUTE = 1;
	private static final int MAX_VAL_PRIMARY_ATTRIBUTE = 200;

	/**
	 * Return the strength of this unit.
	 */
	@Basic
	@Raw
	public int getStrength() {
		return this.strength;
	}

	/**
	 * Check whether the given strength is a valid strength for any unit.
	 * 
	 * @param strength
	 *            The strength to check.
	 * @return true if and only if strength lies between the minimum and maximum
	 *         value for primary attributes. 
	 *         |result == ((strength >= MIN_VAL_PRIMARY_ATTRIBUTE) && (strength <=
	 *         MAX_VAL_PRIMARY_ATTRIBUTE))
	 */
	public static boolean isValidStrength(int strength) {
		return ((strength >= MIN_VAL_PRIMARY_ATTRIBUTE) && (strength <= MAX_VAL_PRIMARY_ATTRIBUTE));
	}

	/**
	 * Set the strength of this unit to the given strength.
	 * 
	 * @param strength
	 *            The new strength for this unit.
	 * @post If the given strength is a valid strength for this unit, the new
	 *       strength of this unit is equal to the given strength. 
	 *       | if (isValidStrength(strength)) 
	 *       | 	then new.getStrength() == strength
	 * @post If the given strength lies beyond the limits of the specified
	 *       minimum and maximum value, the new strength will be this limit
	 *       value. 
	 *       | if (strength < MIN_VAL_PRIMARY_ATTRIBUTE) 
	 *       | then new.getStrength() == MIN_VAL_PRIMARY_ATTRIBUTE 
	 *       | if (strength > MAX_VAL_PRIMARY_ATTRIBUTE) 
	 *       | then new.getStrength() ==
	 *       MAX_VAL_PRIMARY_ATTRIBUTE TODO mogen deze 2 if-statements zo achter
	 *       elkaar? of moeten dit aparte postcondities worden?
	 */
	@Raw
	public void setStrength(int strength) {
		if (isValidStrength(strength))
			this.strength = strength;
		if (strength < MIN_VAL_PRIMARY_ATTRIBUTE)
			this.strength = MIN_VAL_PRIMARY_ATTRIBUTE;
		if (strength > MAX_VAL_PRIMARY_ATTRIBUTE)
			this.strength = MAX_VAL_PRIMARY_ATTRIBUTE;
	}

	/**
	 * Variable registering the strength of this unit.
	 */
	private int strength;

	/**
	 * Return the agility of this unit.
	 */
	@Basic
	@Raw
	public int getAgility() {
		return this.agility;
	}

	/**
	 * Check whether the given agility is a valid agility for any unit.
	 * 
	 * @param agility
	 *            The agility to check.
	 * @return true if and only if agility lies between the minimum and maximum
	 *         value for primary attributes. |result == ((agility >=
	 *         MIN_VAL_PRIMARY_ATTRIBUTE) && (agility <=
	 *         MAX_VAL_PRIMARY_ATTRIBUTE))
	 */
	public static boolean isValidAgility(int agility) {
		return ((agility >= MIN_VAL_PRIMARY_ATTRIBUTE) && (agility <= MAX_VAL_PRIMARY_ATTRIBUTE));
	}

	/**
	 * Set the agility of this unit to the given agility.
	 * 
	 * @param agility
	 *            The new agility for this unit.
	 * @post If the given agility is a valid agility for this unit, the new
	 *       agility of this unit is equal to the given agility. | if
	 *       (isValidAgility(agility)) | then new.getAgility() == agility
	 * @post If the given agility lies beyond the limits of the specified
	 *       minimum and maximum value, the new agility will be this limit
	 *       value. | if (agility < MIN_VAL_PRIMARY_ATTRIBUTE) | then
	 *       new.getAgility() == MIN_VAL_PRIMARY_ATTRIBUTE | if (agility >
	 *       MAX_VAL_PRIMARY_ATTRIBUTE) | then new.getAgility() ==
	 *       MAX_VAL_PRIMARY_ATTRIBUTE TODO mogen deze 2 if-statements zo achter
	 *       elkaar? of moeten dit aparte postcondities worden?
	 */
	@Raw
	public void setAgility(int agility) {
		if (isValidAgility(agility))
			this.agility = agility;
		if (agility < MIN_VAL_PRIMARY_ATTRIBUTE)
			this.agility = MIN_VAL_PRIMARY_ATTRIBUTE;
		if (agility > MAX_VAL_PRIMARY_ATTRIBUTE)
			this.agility = MAX_VAL_PRIMARY_ATTRIBUTE;
	}

	/**
	 * Variable registering the agility of this unit.
	 */
	private int agility;

	/**
	 * Return the weight of this unit.
	 */
	@Basic
	@Raw
	public int getWeight() {
		return this.weight;
	}

	/**
	 * Return the minimum value for the primary attribute weight.
	 * 
	 * @return TODO
	 */
	public int getMinimumWeight() {
		int minimumWeight = (int) Math.ceil((double) ((this.strength + this.agility) / 2.0));
		if (minimumWeight < MIN_VAL_PRIMARY_ATTRIBUTE)
			return MIN_VAL_PRIMARY_ATTRIBUTE;
		return minimumWeight;
	}

	/**
	 * Return the minimum initial value for the primary attribute weight.
	 * 
	 * @return TODO
	 */
	public int getMinimumInitValWeight() {
		int minimumInitValWeight = getMinimumWeight();
		if (minimumInitValWeight < MIN_INIT_VAL_PRIMARY_ATTRIBUTE)
			return MIN_INIT_VAL_PRIMARY_ATTRIBUTE;
		return minimumInitValWeight;
	}

	/**
	 * Check whether the given weight is a valid weight for any unit.
	 * 
	 * @param weight
	 *            The weight to check.
	 * @return true if and only if weight lies between the minimum and maximum
	 *         value for primary attributes. |result == ((weight >=
	 *         this.getMinimumWeight()) && (weight <=
	 *         MAX_VAL_PRIMARY_ATTRIBUTE))
	 */
	public boolean canHaveAsWeight(int weight) {
		return ((weight >= this.getMinimumWeight()) && (weight <= MAX_VAL_PRIMARY_ATTRIBUTE));
	}

	/**
	 * Set the weight of this unit to the given weight.
	 * 
	 * @param weight
	 *            The new weight for this unit.
	 * @post If the given weight is a valid weight for this unit, the new weight
	 *       of this unit is equal to the given weight. | if
	 *       (this.canHaveAsWeight(weight)) | then new.getWeight() == weight
	 * @post If the given weight lies beyond the limits of the specified minimum
	 *       and maximum value, the new weight will be this limit value. | if
	 *       (weight < this.getMinimumWeight()) | then new.getWeight() ==
	 *       this.getMinimumWeight() | if (weight > MAX_VAL_PRIMARY_ATTRIBUTE) |
	 *       then new.getWeight() == MAX_VAL_PRIMARY_ATTRIBUTE TODO mogen deze 2
	 *       if-statements zo achter elkaar? of moeten dit aparte postcondities
	 *       worden?
	 */
	@Raw
	public void setWeight(int weight) {
		if (this.canHaveAsWeight(weight))
			this.weight = weight;
		if (weight < this.getMinimumWeight())
			this.weight = this.getMinimumWeight();
		if (weight > MAX_VAL_PRIMARY_ATTRIBUTE)
			this.weight = MAX_VAL_PRIMARY_ATTRIBUTE;
	}

	/**
	 * Variable registering the strength of this unit.
	 */
	private int weight;

	/**
	 * Return the toughness of this unit.
	 */
	@Basic
	@Raw
	public int getToughness() {
		return this.toughness;
	}

	/**
	 * Check whether the given toughness is a valid toughness for any unit.
	 * 
	 * @param toughness
	 *            The toughness to check.
	 * @return true if and only if toughness lies between the minimum and
	 *         maximum value for primary attributes. |result == ((toughness >=
	 *         MIN_VAL_PRIMARY_ATTRIBUTE) && (toughness <=
	 *         MAX_VAL_PRIMARY_ATTRIBUTE))
	 */
	public static boolean isValidToughness(int toughness) {
		return ((toughness >= MIN_VAL_PRIMARY_ATTRIBUTE) && (toughness <= MAX_VAL_PRIMARY_ATTRIBUTE));
	}

	/**
	 * Set the toughness of this unit to the given toughness.
	 * 
	 * @param toughness
	 *            The new toughness for this unit.
	 * @post If the given toughness is a valid toughness for this unit, the new
	 *       toughness of this unit is equal to the given toughness. | if
	 *       (isValidToughness(toughness)) | then new.getToughness() ==
	 *       toughness
	 * @post If the given toughness lies beyond the limits of the specified
	 *       minimum and maximum value, the new toughness will be this
	 *       limitvalue. | if (toughness < MIN_VAL_PRIMARY_ATTRIBUTE) | then
	 *       new.getToughness() == MIN_VAL_PRIMARY_ATTRIBUTE | if (toughness >
	 *       MAX_VAL_PRIMARY_ATTRIBUTE) | then new.getToughness() ==
	 *       MAX_VAL_PRIMARY_ATTRIBUTE TODO mogen deze 2 if-statements zo achter
	 *       elkaar? of moeten dit aparte postcondities worden?
	 */
	@Raw
	public void setToughness(int toughness) {
		if (isValidToughness(toughness))
			this.toughness = toughness;
		if (toughness < MIN_VAL_PRIMARY_ATTRIBUTE)
			this.toughness = MIN_VAL_PRIMARY_ATTRIBUTE;
		if (toughness > MAX_VAL_PRIMARY_ATTRIBUTE)
			this.toughness = MAX_VAL_PRIMARY_ATTRIBUTE;
	}

	/**
	 * Variable registering the toughness of this unit.
	 */
	private int toughness;

	/**
	 * Return the hitpoints of this unit.
	 */
	@Basic
	@Raw
	public double getHitpoints() {
		return this.hitpoints;
	}

	/**
	 * Return the maximum number of hitpoints of this unit.
	 * 
	 * @return
	 */
	public int getMaxHitpoints() {
		return (int) Math.ceil((2 * this.getWeight() * this.getToughness()) / 100.0);
	}

	/**
	 * Check whether the given hitpoints are valid hitpoints for this unit.
	 * 
	 * @param hitpoints
	 *            The hitpoints to check.
	 * @return true if and only if hitpoints lie between zero and the maximum
	 *         value | result == ( (hitpoints >= 0) && (hitpoints <=
	 *         this.getMaxHitpoints()) )
	 */
	public boolean canHaveAsHitpoints(double hitpoints) {
		return ((hitpoints >= 0) && (hitpoints <= this.getMaxHitpoints()));
	}

	/**
	 * Set the hitpoints of this unit to the given hitpoints.
	 * 
	 * @param hitpoints
	 *            The new hitpoints for this unit.
	 * @pre The given hitpoints must be valid hitpoints for this unit. |
	 *      this.canHaveAsHitpoints(hitpoints)
	 * @post The hitpoints of this unit is equal to the given hitpoints. |
	 *       new.getHitpoints() == hitpoints
	 */
	@Raw
	public void setHitpoints(double hitpoints) {
		assert this.canHaveAsHitpoints(hitpoints);
		this.hitpoints = hitpoints;
	}

	/**
	 * Variable registering the hitpoints of this unit.
	 */
	private double hitpoints;

	/**
	 * Return the stamina points of this unit.
	 */
	@Basic
	@Raw
	public double getStaminaPoints() {
		return this.staminaPoints;
	}

	/**
	 * Return the maximum number of stamina points of this unit.
	 * 
	 * @return
	 */
	public int getMaxStaminaPoints() {
		return (int) Math.ceil((2 * this.getWeight() * this.getToughness()) / 100.0);
	}

	/**
	 * Check whether the given stamina points are a valid value for this unit.
	 * 
	 * @param staminaPoints
	 *            The staminaPoints to check.
	 * @return true if and only if stamina points lie between zero and the
	 *         maximum value | result == ( (staminaPoints >= 0) &&
	 *         (staminaPoints <= this.getMaxHitpoints()) )
	 */
	public boolean canHaveAsStaminaPoints(double staminaPoints) {
		return ((staminaPoints >= 0) && (staminaPoints <= this.getMaxStaminaPoints()));
	}

	/**
	 * Set the stamina points of this unit to the given stamina points.
	 * 
	 * @param staminaPoints
	 *            The new stamina points for this unit.
	 * @pre The given stamina points must be valid stamina points for this unit.
	 *      | this.canHaveAsStaminaPoints(staminaPoints)
	 * @post The stamina points of this unit are equal to the given stamina
	 *       points. | new.getStaminaPoints() == staminaPoints
	 */
	@Raw
	public void setStaminaPoints(double staminaPoints) {
		assert this.canHaveAsStaminaPoints(staminaPoints);
		this.staminaPoints = staminaPoints;
	}

	/**
	 * Variable registering the stamina points of this unit.
	 */
	private double staminaPoints;

	/**
	 * Return the orientation of this unit.
	 */
	@Basic
	@Raw
	public double getOrientation() {
		return this.orientation;
	}

	/**
	 * Check whether the given orientation is a valid orientation for any unit.
	 * 
	 * @param theta
	 *            The orientation to check.
	 * @return true if and only if theta lies between 0 and 2*PI | result ==
	 *         (theta>=0 && theta<(2*Math.PI))
	 */
	public static boolean isValidOrientation(double theta) {
		return (theta >= 0.0 && theta < (2.0 * Math.PI));
	}

	/**
	 * Set the orientation of this unit to the given orientation.
	 * 
	 * @param theta
	 *            The new orientation for this unit.
	 * @post If the given orientation theta is a valid orientation, the new
	 *       orientation of this unit is equal to the given orientation theta. |
	 *       if (isValidOrientation(theta)) | then new.getOrientation() == theta
	 * @post If the given orientation theta lies outside the interval [0, 2*PI[,
	 *       the new orientation of this unit is equal to the equivalent angle
	 *       in this interval with the same sinus and cosinus. |TODO moeten hier
	 *       ook de 2 while-lussen komen?
	 */
	@Raw
	public void setOrientation(double theta) {
		while ((!isValidOrientation(theta)) && theta < 0)
			theta += 2.0 * Math.PI;
		while ((!isValidOrientation(theta)) && theta > 0)
			theta -= 2.0 * Math.PI;
		this.orientation = theta;
	}

	/**
	 * Variable registering the orientation of this unit.
	 */
	private double orientation;


	/**
	 * 
	 * update the position and activity status of a Unit, based on that Unit's
	 * current position, attributes and a given duration 'seconds' in seconds of
	 * game time
	 * 
	 * @param seconds
	 * @throws IllegalArgumentException
	 *             the seconds are not in the interval [0,0.2[
	 */
	public void advanceTime(float seconds) throws IllegalArgumentException {
		if (seconds < 0 || seconds >= 0.2) {
			throw new IllegalArgumentException();
		}

		if (this.isAttacking()) {
			if (this.busyTimeMin(seconds)) {
				this.setActivity(Activity.NONE);
			}
		} else if (this.isMoving() && this.getMoveToAdjacent() != null) {
			Position moveDiff = this.getMoveToAdjacent().min(this.getPosition());
			double moveDistance = Math.sqrt(moveDiff.getXValue() * moveDiff.getXValue()
					+ moveDiff.getYValue() * moveDiff.getYValue() + moveDiff.getZValue() * moveDiff.getZValue());

			double xVelocity = this.getMovementSpeed() * moveDiff.getXValue() / moveDistance;
			double yVelocity = this.getMovementSpeed() * moveDiff.getYValue() / moveDistance;
			double zVelocity = this.getMovementSpeed() * moveDiff.getZValue() / moveDistance;

			Position next = new Position(this.getPosition().getRealX() + xVelocity * seconds,
					this.getPosition().getRealY() + yVelocity * seconds,
					this.getPosition().getRealZ() + zVelocity * seconds);

			Position diffNext = this.getMoveToAdjacent().min(next);

			this.setOrientation(Math.atan2(yVelocity, xVelocity));
			if ((Math.signum(moveDiff.getXValue()) != Math.signum(diffNext.getXValue()))
					&& (Math.signum(moveDiff.getYValue()) != Math.signum(diffNext.getYValue()))
					&& (Math.signum(moveDiff.getZValue()) != Math.signum(diffNext.getZValue()))) {
				this.setPosition(this.getMoveToAdjacent());

				// Check whether the unit is moving to a cube far away (not an adjacent cube)
				if (getMoveToCube() != null) {
					findNextCubeInPath();
				}

				// Check whether the unit is not pathfinding. If this is true, it stops moving.
				if (getMoveToCube() == null) {
					this.setMoveToAdjacent(null);
					this.setActivity(Activity.NONE);
				}

			} else {
				next.toCube();
				this.setPosition(next);
			}

			if (this.getActivity() == Activity.SPRINTING) {
				double stamina = this.getStaminaPoints() - seconds * 10;
				if (stamina > 0) {
					this.setStaminaPoints(stamina);
				} else {
					this.setStaminaPoints(0);
					this.setActivity(Activity.WALKING);
				}
			}
		} else if (this.isWorking()) {
			if (this.busyTimeMin(seconds)) {
				this.setActivity(Activity.NONE);
			}
		} else if (this.isResting()) {
			if (this.getHitpoints() != this.getMaxHitpoints()){
				double hitpoints = this.getHitpoints() + seconds * this.getToughness() / (200*0.2);
				this.setHitpoints(Math.min(hitpoints, this.getMaxHitpoints()));
			} else if (this.getStaminaPoints() != this.getMaxStaminaPoints()){
				double stamina = this.getStaminaPoints() + seconds * this.getToughness() / (100*0.2);
				this.setStaminaPoints(Math.min(stamina, this.getMaxStaminaPoints()));
			} else {
				this.setActivity(Activity.NONE);
			}
					
			if (this.busyTimeMin(seconds)) {
				this.canStopResting = true;
			}
		} else if (this.isBeingUseless() && this.canStartDefaultBehaviour()) {
			int randomGetal = randomGen.nextInt(3);
			if (randomGetal == 0) {
				moveTo(randomGen.nextInt(Cube.X_MAX - Cube.X_MIN) + Cube.X_MIN,
						randomGen.nextInt(Cube.Y_MAX - Cube.Y_MIN) + Cube.Y_MIN,
						randomGen.nextInt(Cube.Z_MAX - Cube.Z_MIN) + Cube.Z_MIN);
			} else if (randomGetal == 1) {
				this.work();
			} else {
				this.rest();
				double hitpointsTime = (this.getMaxHitpoints() - this.getHitpoints()) * 200*0.2 / this.getToughness();
				double staminaTime = (this.getMaxStaminaPoints() - this.getStaminaPoints()) * 100*0.1 / this.getToughness();
				this.setBusyTime(hitpointsTime + staminaTime);
			}
		}
	}

	public void moveToAdjacent(int x, int y, int z) throws IllegalArgumentException {

		if (this.isMoving()) {
			return;
		}

		if ((x != -1) || (x != 0) || (x != 1) || (y != -1) || (y != 0) || (y != 1) || (z != -1) || (z != 0) || (z != 1))
			throw new IllegalArgumentException();

		Position moveToAdjacent = new Position(this.getCube().getX() + x + Cube.SIDE_LENGTH / 2,
				this.getCube().getY() + y + Cube.SIDE_LENGTH / 2, this.getCube().getZ() + z + Cube.SIDE_LENGTH / 2);
		moveToAdjacent.toCube();

		this.setActivity(Activity.WALKING);
		this.moveToAdjacent = moveToAdjacent;
	}

	public Position getMoveToAdjacent() {
		return this.moveToAdjacent;
	}

	public void setMoveToAdjacent(Position position) {
		if (position.getCube() == null) {
			throw new IllegalArgumentException();
		}

		this.moveToAdjacent = position;
	}

	private Position moveToAdjacent;

	public double getMovementSpeed() {
		double speed = 1.5 * (this.getStrength() + this.getAgility()) / (2 * this.getWeight());
		if (this.isSprinting()) {
			speed *= 2;
		}
		int zDiff = this.getCube().getZValue() - this.getMoveToAdjacent().getCube().getZ();
		if (zDiff == -1) {
			speed *= 0.5;
		} else if (zDiff == 1) {
			speed *= 1.2;
		}

		return speed;
	}

	public void moveTo(int x, int y, int z) {
		this.moveToCube = new Cube(x, y, z);
		findNextCubeInPath();
	}

	private void findNextCubeInPath() {
		int adjacentX = -1;
		int adjacentY = -1;
		int adjacentZ = -1;
		if (this.getCube().getX() == this.getMoveToCube().getXValue()) {
			adjacentX = 0;
		} else if (this.getCube().getX() < this.getMoveToCube().getXValue()) {
			adjacentX = -1;
		}
		if (this.getCube().getY() == this.getMoveToCube().getYValue()) {
			adjacentY = 0;
		} else if (this.getCube().getY() < this.getMoveToCube().getYValue()) {
			adjacentY = -1;
		}
		if (this.getCube().getZ() == this.getMoveToCube().getZValue()) {
			adjacentZ = 0;
		} else if (this.getCube().getZ() < this.getMoveToCube().getZValue()) {
			adjacentZ = -1;
		}

		if (adjacentX != 0 || adjacentY != 0 || adjacentZ != 0)
			moveToAdjacent(adjacentX, adjacentY, adjacentZ);
		else
			this.moveToCube = null;
	}

	public Cube getMoveToCube() {
		return this.moveToCube;
	}

	private Cube moveToCube;

	public boolean isDefending() {
		return (this.getActivity() == Activity.DEFENDING);
	}

	public boolean isAttacking() {
		return (this.getActivity() == Activity.ATTACKING);
	}

	public boolean isMoving() {
		return (this.getActivity() == Activity.WALKING) || (this.getActivity() == Activity.SPRINTING);
	}

	public boolean isSprinting() {
		return this.getActivity() == Activity.SPRINTING;
	}

	public void toggleSprinting() {
		if (this.isSprinting()) {
			this.setActivity(Activity.WALKING);
		} else if (this.isMoving()) {
			this.setActivity(Activity.SPRINTING);
		}
	}

	public boolean isWorking() {
		return (this.getActivity() == Activity.WORKING);
	}

	public boolean isResting() {
		return (this.getActivity() == Activity.RESTING);
	}

	public boolean isBeingUseless() {
		return (this.getActivity() == Activity.NONE);
	}

	public Activity getActivity() {
		return this.activity;
	}

	public boolean setActivity(Activity activity, double busyTime) {
		if (this.setActivity(activity)) {
			this.setBusyTime(busyTime);
			return true;
		}
		else {
			return false;
		}
	}

	public boolean setActivity(Activity activity) {
		if (this.isResting() && !this.canStopResting) {
			return false;
		} else if (this.isAttacking() && this.getBusyTime() > 0) {
			return false;
		} else if (activity == Activity.SPRINTING && this.getStaminaPoints() == 0) {
			return false;
		}

		this.activity = activity;
		return true;
	}

	private Activity activity;

	public boolean busyTimeMin(double seconds) {
		this.setBusyTime(this.getBusyTime() - seconds);
		if (this.getBusyTime() == 0) {
			return true;
		}

		return false;
	}

	public void setBusyTime(double busyTime) {
		this.busyTime = Math.max(busyTime, 0);
	}

	public double getBusyTime() {
		return this.busyTime;
	}

	private double busyTime;
	
	public void startDefaultBehaviour() {
		this.defaultBehaviour = true;
	}

	public void stopDefaultBehaviour() {
		this.defaultBehaviour = false;
	}

	public boolean canStartDefaultBehaviour() {
		return (!this.defaultBehaviour);
	}

	private boolean defaultBehaviour;
	
	public void work() {
		this.setActivity(Activity.WORKING);
		this.setBusyTime(500 / this.getStrength());
	}

	public void rest() {
		busyTime = 200 * 0.2 / this.getToughness();
		if (this.setActivity(Activity.RESTING, busyTime)) {
			this.canStopResting = false;
		}
	}

	private boolean canStopResting;

	public void attack(Unit other) {
		if (this.setActivity(Activity.ATTACKING, 1)) {
			other.defend(this);
			this.setOrientation(Math.atan2(other.getPosition().getRealY()-this.getPosition().getRealY(), other.getPosition().getRealX()-this.getPosition().getRealX()));
		}
	}

	public void defend(Unit attacker) {
		if (randomGen.nextDouble() < 0.2 * this.getAgility() / attacker.getAgility()) {
			Position nextPosition = null;
			while (nextPosition == this.getPosition()) {
				Position minPosition = new Position(randomGen.nextInt(3)-1, 
						randomGen.nextInt(3)-1, 0);
				nextPosition = this.getPosition().min(minPosition);
			}
			this.setPosition(nextPosition);
		} else if (randomGen.nextDouble() > 0.25 * (this.getStrength() + this.getAgility()) / (attacker.getStrength() + attacker.getAgility())) {
			this.setHitpoints(attacker.getStrength()/10);
		}
		this.setOrientation(Math.atan2(attacker.getPosition().getRealY()-this.getPosition().getRealY(), attacker.getPosition().getRealX()-this.getPosition().getRealX()));
	}
}
