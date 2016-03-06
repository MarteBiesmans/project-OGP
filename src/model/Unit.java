package model;


import java.util.Random;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * 
 * @invar The name of each unit must be a valid name for any unit.
 * 		 | isValidName(getName())
 * @invar  The position of each unit must be a valid position for any unit.
 *       | isValidPosition(getPosition())
 * @invar  The weight of each unit must be a valid weight for any
 *         unit.
 *       | this.canHaveAsWeight(getWeight())
 * @invar The strength of each unit must be a valid strength for any unit.
 *       | isValidStrength(getStrength())
 * @invar  The agility of each unit must be a valid agility for any
 *         unit.
 *       | isValidAgility(getAgility())
 * @invar  The toughness of each unit must be a valid toughness for any
 *         unit.
 *       | isValidToughness(getToughness())
 * @invar  The number of hitpoints of each unit must be a valid number of hitpoints for any
 *         unit.
 *       | canHaveAsHitpoints(getNbHitpoints())
 * @invar  The number of stamina points of each unit must be a valid number of stamina points for any
 *         unit.
 *       | canHaveAsStaminaPoints(getNbStaminaPoints())
 * @invar  The orientation of each unit must be a valid orientation for any
 *         unit.
 *       | isValidOrientation(getOrientation())
 * 
 * @author Ellen & Marte
 * @version 1.0
 */
public class Unit {
	
	public static final double CUBE_SIDE_LENGTH = 1;
	
	/**
	 * @pre		The given hitpoints must be valid hitpoints for this unit.
	 *			| isValidHitpoints(hitpoints)
	 * @post	The hitpoints of this new unit are equal to the given hitpoints.
	 *			| new.getHitpoints() == hitpoints
	 * @pre		The given stamina points must be valid stamina points for this unit.
	 *			| isValidStaminaPoints(staminaPoints)
	 * @post	The stamina points of this new unit are equal to the given stamina points.
	 *			| new.getStaminaPoints() == staminaPoints
	 * TODO andere post-condities, iets met @param
=======

	private static final Random randomGen = new Random();

	/**
	 * @pre The given hitpoints must be valid hitpoints for this unit. |
	 *      isValidHitpoints(hitpoints)
	 * @post The hitpoints of this new unit are equal to the given hitpoints. |
	 *       new.getHitpoints() == hitpoints
	 * @pre The given stamina points must be valid stamina points for this unit.
	 *      | isValidStaminaPoints(staminaPoints)
	 * @post The stamina points of this new unit are equal to the given stamina
	 *       points. | new.getStaminaPoints() == staminaPoints TODO andere
	 *       post-condities, iets met @param
>>>>>>> origin/master
	 * 
	 */
	public Unit(double x, double y, double z, String name, 
			int strength, int agility, int toughness, int weight)
			throws IllegalArgumentException {
		
		//name
		this.setName(name);
<<<<<<< HEAD
		
		//position, orientation and activity
		this.setPosition(x,y,z);
		this.setOrientation((float) (Math.PI/2.0));
		this.activity = Activity.NONE;
		
		
		//primary attributes
=======

		// position, orientation
		Cube cube = new Cube((int) (x / Cube.SIDE_LENGTH), (int) (y / Cube.SIDE_LENGTH), (int) (z / Cube.SIDE_LENGTH));
		this.setPosition(new Position(x % Cube.SIDE_LENGTH, x % Cube.SIDE_LENGTH, x % Cube.SIDE_LENGTH, cube));

		this.setOrientation((float) (Math.PI / 2.0));

		// primary attributes
>>>>>>> origin/master
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
		//TODO moet hier niet ergens documentatie over staan? en waar dan?
		
		
		//hitpoints and stamina points
		this.setStaminaPoints(this.getMaxStaminaPoints());
		this.setHitpoints(this.getMaxHitpoints());
		
		// TODO boolean default behavior
		

		// activity and busy time
		this.setActivity(Activity.NONE);
		this.setBusyTime(0);

		// random behaviour
		this.startDefaultBehaviour();
	}

	
	/**
	 * Return the position on the x-axis of this unit.
	 */
	@Basic @Raw
	public double getPositionX() {
		return this.x;
	}
	
	/**
	 * Return the position on the y-axis of this unit.
	 */
	@Basic @Raw
	public double getPositionY() {
		return this.y;
	}
	
	/**
	 * Return the position on the z-axis of this unit.
	 */
	@Basic @Raw
	public double getPositionZ() {
		return this.z;
	}
	
	/**
	 * Return the position on the x-axis of the cube that is occupied by this unit.
	 * @return	the position on the x-axis of this unit rounded down to an integer
	 * 			|result == (int) this.getPositionX()
	 */
	public int getCubePositionX() {
		return (int) this.getPositionX();
	}
	
	
	/**
	 * Return the position on the y-axis of the cube that is occupied by this unit.
	 * @return	the position on the y-axis of this unit rounded down to an integer
	 * 			|result == (int) this.getPositionY()
	 */
	public int getCubePositionY() {
		return (int) this.getPositionY();
	}

	
	/**
	 * Return the position on the z-axis of the cube that is occupied by this unit.
	 * @return	the position on the z-axis of this unit rounded down to an integer
	 * 			|result == (int) this.getPositionZ()
	 */
	public int getCubePositionZ() {
		return (int) this.getPositionZ();
	}

	
	/**
	 * Check whether the given position is a valid position for
	 * any unit.
	 * 
	 * @param	x
	 * 			position on the x-axis
	 * @param	y
	 * 			position on the y-axis
	 * @param	z
	 * 			position on the z-axis
	 * @return	false if at least one of the coordinates is outside the boundaries of the game world.
	 * 			| result == (! ((x<xMin) || (x>xMax) || (y<yMin) || (y>yMax) || (z<zMin) || (z>zMax)) )
	 */
	public static boolean isValidPosition(double x, double y, double z) {
		return (! ((x<X_MIN) || (x>=X_MAX) || (y<Y_MIN) || (y>=Y_MAX) || (z<Z_MIN) || (z>=Z_MAX)) );
	}
	
	/**
	 * Constants describing the boundaries of the game world.
	 */
	private static final int X_MIN = 0;
	private static final int Y_MIN = 0;
	private static final int Z_MIN = 0;
	private static final int X_MAX = 50;
	private static final int Y_MAX = 50;
	private static final int Z_MAX = 50;
	
	
	
	/**
	 * Set the position of this unit to the given coordinates.
	 * 
	 * @param	x
	 * 			the new position on the x-axis for this unit
	 * @param	y
	 * 			the new position on the y-axis for this unit
	 * @param	z
	 * 			the new position on the z-axis for this unit
	 * @post	the new position on the x-axis of this unit is equal to the given x-coordinate.
	 * 			|new.getPositionX() = x
	 * @post	the new position on the y-axis of this unit is equal to the given y-coordinate.
	 * 			|new.getPositionY() = y
	 * @post	the new position on the z-axis of this unit is equal to the given z-coordinate.
	 * 			|new.getPositionZ() = z
	 * @throws	IllegalArgumentException
	 * 			At least one of the given coordinates is not within the boundaries of the game world.
	 * 			| (! isValidPosition(x,y,z))
	 */
	@Raw
	public void setPosition(double x,double y, double z) 
			throws IllegalArgumentException {
		if (! isValidPosition(x,y,z))
			throw new IllegalArgumentException();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	
	/**
	 * Variables registering the position of this unit.
	 */
	private double x;
	private double y;
	private double z;
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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
	 * @param	name
	 * 			The name to check.
	 * @return Each name is at least two characters long and must start with an
	 *         uppercase letter. Names can only use letters (both upper- and
	 *         lowercase), quotes (both single and double) and spaces.
	 *         |if (length(name) < 2)
	 *         |	result == false
	 *         |if (! isUpperCase(name.charAt(0)))
	 *         |	return == false
	 *         |TODO moet hier de for-loop uit de implementatie komen?
	 */
	public static boolean isValidName(String name) {
		if (name.length() < 2)
			return false;
		if (! Character.isUpperCase(name.charAt(0)))
			return false;
		
		//a loop over the name which changes VALID_CHARACTERS to false if it recognizes characters
		// that are not allowed.
		boolean VALID_CHARACTERS = true;
		for(int i = 0; i < name.length(); i++) {
	        if (   (! Character.isLetter(name.charAt(i)))      //is it a letter?
	        	&& (! (name.charAt(i) == '"'))                 //is it a double quote?
	        	&& (! (name.charAt(i) == '\''))                //is it a single quote/apostrophe?
	        	&& (! (name.charAt(i) == ' '))                 //is it a space?
	        	)
	            VALID_CHARACTERS = false;
		}
		return VALID_CHARACTERS;
	}

	
	/**
	 * Set the name of this unit to the given name.
	 * 
	 * @param	name
	 * 			The new name for this unit.
	 * @post	The name of this unit is equal to the given name.
	 * 			| new.getName() == name
	 * @throws	IllegalArgumentException
	 * 			The given name is not a valid name for this unit.
	 * 			| (! isValidName(name))
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
	 * constants for the minimum and maximum values
	 * of primary attributes (weight, strength, agility and toughness)
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
	 * @param	strength
	 * 			The strength to check.
	 * @return	true if and only if strength lies between the minimum and maximum value
	 * 			for primary attributes.
	 * 			|result == ((strength >= MIN_VAL_PRIMARY_ATTRIBUTE)
	 * 							&& (strength <= MAX_VAL_PRIMARY_ATTRIBUTE))
	 */
	public static boolean isValidStrength(int strength) {
		return ((strength >= MIN_VAL_PRIMARY_ATTRIBUTE) && (strength <= MAX_VAL_PRIMARY_ATTRIBUTE));
	}

	/**
	 * Set the strength of this unit to the given strength.
	 * 
	 * @param	strength
	 * 			The new strength for this unit.
	 * @post	If the given strength is a valid strength for this unit, the new
	 * 			strength of this unit is equal to the given strength.
	 * 			| if (isValidStrength(strength))
	 * 			| then new.getStrength() == strength
	 * @post	If the given strength lies beyond the limits of the specified minimum and maximum
	 * 			value, the new strength will be this limit value.
	 * 			| if (strength < MIN_VAL_PRIMARY_ATTRIBUTE)
	 * 			| then new.getStrength() == MIN_VAL_PRIMARY_ATTRIBUTE
	 * 			| if (strength > MAX_VAL_PRIMARY_ATTRIBUTE)
	 * 			| then new.getStrength() == MAX_VAL_PRIMARY_ATTRIBUTE
	 *     TODO mogen deze 2 if-statements zo achter elkaar? of moeten dit aparte postcondities worden?
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
	 * @param	agility
	 * 			The agility to check.
	 * @return	true if and only if agility lies between the minimum and maximum value
	 * 			for primary attributes.
	 * 			|result == ((agility >= MIN_VAL_PRIMARY_ATTRIBUTE)
	 * 							&& (agility <= MAX_VAL_PRIMARY_ATTRIBUTE))
	 */
	public static boolean isValidAgility(int agility) {
		return ((agility >= MIN_VAL_PRIMARY_ATTRIBUTE) && (agility <= MAX_VAL_PRIMARY_ATTRIBUTE));
	}

	/**
	 * Set the agility of this unit to the given agility.
	 * 
	 * @param	agility
	 * 			The new agility for this unit.
	 * @post	If the given agility is a valid agility for this unit, the new
	 * 			agility of this unit is equal to the given agility.
	 * 			| if (isValidAgility(agility))
	 * 			| then new.getAgility() == agility
	 * @post	If the given agility lies beyond the limits of the specified minimum and maximum
	 * 			value, the new agility will be this limit value.
	 * 			| if (agility < MIN_VAL_PRIMARY_ATTRIBUTE)
	 * 			| then new.getAgility() == MIN_VAL_PRIMARY_ATTRIBUTE
	 * 			| if (agility > MAX_VAL_PRIMARY_ATTRIBUTE)
	 * 			| then new.getAgility() == MAX_VAL_PRIMARY_ATTRIBUTE
	 *     TODO mogen deze 2 if-statements zo achter elkaar? of moeten dit aparte postcondities worden?
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
	 * @return	TODO
	 */
	public int getMinimumWeight() {
		int minimumWeight = (int) Math.ceil( (double) ((this.strength + this.agility)/2.0) );
		if (minimumWeight < MIN_VAL_PRIMARY_ATTRIBUTE)
			return MIN_VAL_PRIMARY_ATTRIBUTE;
		return minimumWeight;
	}
	
	
	/**
	 * Return the minimum initial value for the primary attribute weight.
	 * 
	 * @return	TODO
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
	 * @param	weight
	 * 			The weight to check.
	 * @return	true if and only if weight lies between the minimum and maximum value
	 * 			for primary attributes.
	 * 			|result == ((weight >= this.getMinimumWeight())
	 * 						&& (weight <= MAX_VAL_PRIMARY_ATTRIBUTE))
	 */
	public boolean canHaveAsWeight(int weight) {
		return ((weight >= this.getMinimumWeight())
				&& (weight <= MAX_VAL_PRIMARY_ATTRIBUTE));
	}

	/**
	 * Set the weight of this unit to the given weight.
	 * 
	 * @param	weight
	 * 			The new weight for this unit.
	 * @post	If the given weight is a valid weight for this unit, the new
	 * 			weight of this unit is equal to the given weight.
	 * 			| if (this.canHaveAsWeight(weight))
	 * 			| then new.getWeight() == weight
	 * @post	If the given weight lies beyond the limits of the specified minimum and maximum
	 * 			value, the new weight will be this limit value.
	 * 			| if (weight < this.getMinimumWeight())
	 * 			| then new.getWeight() == this.getMinimumWeight()
	 * 			| if (weight > MAX_VAL_PRIMARY_ATTRIBUTE)
	 * 			| then new.getWeight() == MAX_VAL_PRIMARY_ATTRIBUTE
	 *     TODO mogen deze 2 if-statements zo achter elkaar? of moeten dit aparte postcondities worden?
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
	 * @param	toughness
	 * 			The toughness to check.
	 * @return	true if and only if toughness lies between the minimum and maximum value
	 * 			for primary attributes.
	 * 			|result == ((toughness >= MIN_VAL_PRIMARY_ATTRIBUTE)
	 * 							&& (toughness <= MAX_VAL_PRIMARY_ATTRIBUTE))
	 */
	public static boolean isValidToughness(int toughness) {
		return ((toughness >= MIN_VAL_PRIMARY_ATTRIBUTE) && (toughness <= MAX_VAL_PRIMARY_ATTRIBUTE));
	}

	/**
	 * Set the toughness of this unit to the given toughness.
	 * 
	 * @param	toughness
	 * 			The new toughness for this unit.
	 * @post	If the given toughness is a valid toughness for this unit, the new
	 * 			toughness of this unit is equal to the given toughness.
	 * 			| if (isValidToughness(toughness))
	 * 			| then new.getToughness() == toughness
	 * @post	If the given toughness lies beyond the limits of the specified minimum and maximum
	 * 			value, the new toughness will be this limitvalue.
	 * 			| if (toughness < MIN_VAL_PRIMARY_ATTRIBUTE)
	 * 			| then new.getToughness() == MIN_VAL_PRIMARY_ATTRIBUTE
	 * 			| if (toughness > MAX_VAL_PRIMARY_ATTRIBUTE)
	 * 			| then new.getToughness() == MAX_VAL_PRIMARY_ATTRIBUTE
	 *     TODO mogen deze 2 if-statements zo achter elkaar? of moeten dit aparte postcondities worden?
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
	public int getHitpoints() {
		return this.hitpoints;
	}

	
	/**
	 * Return the maximum number of hitpoints of this unit.
	 * @return
	 */
	private int getMaxHitpoints() {
		return (int) Math.ceil( (2*this.getWeight()*this.getToughness())/100.0);
	}
	
	
	
	/**
	 * Check whether the given hitpoints are valid hitpoints for this unit.
	 * 
	 * @param	hitpoints
	 * 			The hitpoints to check.
	 * @return	true if and only if hitpoints lie between zero and the maximum value
	 * 			| result == ( (hitpoints >= 0) && (hitpoints <= this.getMaxHitpoints()) )
	 */
	public boolean canHaveAsHitpoints(int hitpoints) {
				return ( (hitpoints >= 0) && (hitpoints <= this.getMaxHitpoints()) );
	}

	/**
	 * Set the hitpoints of this unit to the given hitpoints.
	 * 
	 * @param	hitpoints
	 * 			The new hitpoints for this unit.
	 * @pre		The given hitpoints must be valid hitpoints for this unit.
	 * 			| this.canHaveAsHitpoints(hitpoints)
	 * @post	The hitpoints of this unit is equal to the given hitpoints.
	 * 			| new.getHitpoints() == hitpoints
	 */
	@Raw
	public void setHitpoints(int hitpoints) {
		assert this.canHaveAsHitpoints(hitpoints);
		this.hitpoints = hitpoints;
	}

	/**
	 * Variable registering the hitpoints of this unit.
	 */
	private int hitpoints;
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Return the stamina points of this unit.
	 */
	@Basic
	@Raw
	public int getStaminaPoints() {
		return this.staminaPoints;
	}

	
	/**
	 * Return the maximum number of stamina points of this unit.
	 * @return
	 */
	private int getMaxStaminaPoints() {
		return (int) Math.ceil( (2*this.getWeight()*this.getToughness())/100.0);
	}
	
	
	
	/**
	 * Check whether the given stamina points are a valid value for this unit.
	 * 
	 * @param	staminaPoints
	 * 			The staminaPoints to check.
	 * @return	true if and only if stamina points lie between zero and the maximum value
	 * 			| result == ( (staminaPoints >= 0) && (staminaPoints <= this.getMaxHitpoints()) )
	 */
	public boolean canHaveAsStaminaPoints(int staminaPoints) {
				return ( (staminaPoints >= 0) && (staminaPoints <= this.getMaxStaminaPoints()) );
	}

	/**
	 * Set the stamina points of this unit to the given stamina points.
	 * 
	 * @param	staminaPoints
	 * 			The new stamina points for this unit.
	 * @pre		The given stamina points must be valid stamina points for this unit.
	 * 			| this.canHaveAsStaminaPoints(staminaPoints)
	 * @post	The stamina points of this unit are equal to the given stamina points.
	 * 			| new.getStaminaPoints() == staminaPoints
	 */
	@Raw
	public void setStaminaPoints(int staminaPoints) {
		assert this.canHaveAsStaminaPoints(staminaPoints);
		this.staminaPoints = staminaPoints;
	}

	/**
	 * Variable registering the stamina points of this unit.
	 */
	private int staminaPoints;
	
	
	
	
	
	
	


	
	/**
	 * Return the orientation of this unit.
	 */
	@Basic @Raw
	public double getOrientation() {
		return this.orientation;
	}
	
	
	/**
	 * Check whether the given orientation is a valid orientation for
	 * any unit.
	 *  
	 * @param	theta
	 * 			The orientation to check.
	 * @return	true if and only if theta lies between 0 and 2*PI
	 * 			| result == (theta>=0 && theta<(2*Math.PI))
	*/
	public static boolean isValidOrientation(double theta) {
		return (theta>=0.0 && theta<(2.0*Math.PI));
	}
	
	
	/**
	 * Set the orientation of this unit to the given orientation.
	 * 
	 * @param	theta
	 *			The new orientation for this unit.
	 * @post	If the given orientation theta is a valid orientation,
	 *			the new orientation of this unit is equal to the given orientation theta.
	 *			| if (isValidOrientation(theta))
	 *			|   then new.getOrientation() == theta
	 * @post	If the given orientation theta lies outside the interval [0, 2*PI[,
	 * 			the new orientation of this unit is equal to the equivalent
	 * 			angle in this interval with the same sinus and cosinus.
	 * 			|TODO moeten hier ook de 2 while-lussen komen?
	 */
	@Raw
	public void setOrientation(double theta) {
		while ((! isValidOrientation(theta)) && theta<0)
			theta += 2.0*Math.PI;
		while ((! isValidOrientation(theta)) && theta>0)
			theta -= 2.0*Math.PI;
		this.orientation = theta;
	}
	
	
	/**
	 * Variable registering the orientation of this unit.
	 */
	private double orientation;

	
	
	
	
	
	
	
	
	
	
	
	
	
	//************************************************************************************************//
	
	
	/**
	 * 
	 * update the position and activity status of a Unit, based on that Unit's current position,
	 * attributes and a given duration 'seconds' in seconds of game time
	 * 
	 * @param	seconds
	 * @throws	IllegalArgumentException
	 * 			the seconds are not in the interval [0,0.2[
	 */
	public void advanceTime(float seconds) throws IllegalArgumentException{
		if (seconds < 0 || seconds >= 0.2) {
			throw new IllegalArgumentException();
		}
<<<<<<< HEAD
		
		if (this.isDefending()) {
			
		}
		if (this.isMoving()) {
			double xDiff = this.getMoveToX() - this.getPositionX();
			double yDiff = this.getMoveToY() - this.getPositionY();
			double zDiff = this.getMoveToZ() - this.getPositionZ();
			double moveDistance = Math.sqrt(xDiff * xDiff + yDiff * yDiff + zDiff * zDiff);
			
			double xVelocity = this.getMovementSpeed() * xDiff / moveDistance;
			double yVelocity = this.getMovementSpeed() * yDiff / moveDistance;
			double zVelocity = this.getMovementSpeed() * zDiff / moveDistance;
						
			// TODO: als variabele opslaan en kijken of het verder is dan targetposition, rekening houden met teken van x,y,zdiff (richting)!!
			double xNext = this.getPositionX() + xVelocity * seconds;
			double yNext = this.getPositionY() + yVelocity * seconds;
			double zNext = this.getPositionZ() + zVelocity * seconds;
			
			double xDiffNext = this.getMoveToX() - xNext;
			double yDiffNext = this.getMoveToY() - yNext;
			double zDiffNext = this.getMoveToZ() - zNext;
			
			if (((xDiff > 0 && xDiffNext < 0) || (xDiff < 0 && xDiffNext > 0)) && 
					((yDiff > 0 && yDiffNext < 0) || (yDiff < 0 && yDiffNext > 0)) &&
					((zDiff > 0 && zDiffNext < 0) || (zDiff < 0 && zDiffNext > 0))) {
				this.setPosition(this.moveToX, this.moveToY, this.moveToZ);
			}
				
			else {
				this.setPosition(xNext, yNext, zNext);
				this.setOrientation(Math.atan2(yVelocity, xVelocity));
				this.activity = Activity.NONE;
			}
			
			if (this.activity == Activity.SPRINTING)
				this.setStaminaPoints(this.getStaminaPoints() - (int) seconds*10); 
				
		}
		//TODO: else if work dan work duration -= seconds, check rusten
=======

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

				// Check voor verre move to (maw pathfinding is actief)
				if (getMoveToCube() != null) {
					findNextCubeInPath();
				}

				// Check if klaar met pathfinding/pathfinding niet actief)
				// -> stop met bewegen want aangekomen
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
			// TODO: increase hitpoints/stamina
			if (this.busyTimeMin(seconds)) {
				this.canStopResting = true;
			}
		} else if (this.isBeingUseless() && this.canStartDefaultBehaviour()) {
			int randomGetal = randomGen.nextInt(10);
			if (randomGetal == 0) {
				moveTo(randomGen.nextInt(Cube.X_MAX - Cube.X_MIN) + Cube.X_MIN,
						randomGen.nextInt(Cube.Y_MAX - Cube.Y_MIN) + Cube.Y_MIN,
						randomGen.nextInt(Cube.Z_MAX - Cube.Z_MIN) + Cube.Z_MIN);
			} else if (randomGetal == 1) {
				this.work();
			} else {
				this.rest();
				// TODO: calc full rest time
				this.setBusyTime(10);
			}
		}
>>>>>>> origin/master
	}
	
	public void moveToAdjacent(int x, int y, int z) throws IllegalArgumentException {
		
		if ((x != -1) || (x != 0) || (x != 1) ||
				(y != -1) || (y != 0) || (y != 1) ||
				(z != -1) || (z != 0) || (z != 1)) 
			throw new IllegalArgumentException();
<<<<<<< HEAD
			
		
		if (! isValidPosition(this.getCubePositionX() + x + CUBE_SIDE_LENGTH/2,
				this.getCubePositionY() + y + CUBE_SIDE_LENGTH/2,
				this.getCubePositionZ() + z + CUBE_SIDE_LENGTH/2))
			throw new IllegalArgumentException();
		
		if  {// moving to adjacent
=======

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
>>>>>>> origin/master
			throw new IllegalArgumentException();
		}
		
		this.activity = Activity.WALKING;
		this.moveToX = x + CUBE_SIDE_LENGTH / 2;
		this.moveToY = y + CUBE_SIDE_LENGTH / 2;
		this.moveToZ = z + CUBE_SIDE_LENGTH / 2;
	}
	
	public double getMoveToX() {
		return this.moveToX;
	}
	
	public double getMoveToY() {
		return this.moveToY;
	}
	
	public double getMoveToZ() {
		return this.moveToZ;
	}
	
	private double moveToX;
	private double moveToY;
	private double moveToZ;
	
	public double getMovementSpeed() {
		double speed = 1.5 * (this.getStrength() + this.getAgility()) / (2 * this.getWeight());
		if (this.isSprinting()) {
			speed *= 2;
		}
		int zDiff = this.getCubePositionZ() - (int) this.getMoveToZ();
		if (zDiff == -1) {
			speed *= 0.5;
		} else if (zDiff == 1) {
			speed *= 1.2;
		}
		
		return speed;
	}
	
	public void moveTo(int x, int y, int z) {
		int adjacentX = -1;
		int adjacentY = -1;
		int adjacentZ = -1;
		while ((this.getCubePositionX() != x) && (this.getCubePositionY() != y) &&(this.getCubePositionZ() != z)) {
			if (this.getCubePositionX() == x) {
				adjacentX = 0;
			} else if (this.getCubePositionX() < x) {
				adjacentX = -1;
			}
			if (this.getCubePositionY() == y) {
				adjacentY = 0;
			} else if (this.getCubePositionY() < y) {
				adjacentY = -1;
			}
			if (this.getCubePositionZ() == z) {
				adjacentZ = 0;
			} else if (this.getCubePositionZ() < z) {
				adjacentZ = -1;
			}
			moveToAdjacent(adjacentX, adjacentY, adjacentZ);
		}
	}
	
	public boolean isDefending() {
		return (this.activity == Activity.DEFENDING);
	}
	
	public boolean isAttacking() {
		return (this.activity == Activity.ATTACKING);
	}
	
	public boolean isMoving(){
		return (this.activity == Activity.WALKING) || (this.activity == Activity.SPRINTING);
	}
	
	public boolean isSprinting() {
		return this.activity == Activity.SPRINTING;
	}
	
	public void toggleSprinting() {
		if (this.isSprinting()) {
			this.setActivity(Activity.WALKING);
		} else if (this.isMoving()) {
<<<<<<< HEAD
			this.activity = Activity.SPRINTING;
		} 
=======
			this.setActivity(Activity.SPRINTING);
		}
>>>>>>> origin/master
	}
	
	public boolean isWorking(){
		return (this.activity == Activity.WORKING);
	}
	
	public boolean isResting(){
		return (this.activity == Activity.RESTING);
	}
	

<<<<<<< HEAD
		
	//TODO: maak method CanMove: check isMoving == 0, check niet aangevallen enz
	
	public Activity getIsMoving() {
		return this.activity;
	}
	
	
	private Activity activity;
	
	
	
	
	
=======
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
		return this.defaultBehaviour;
	}

	private boolean defaultBehaviour;
	
	public void work() {
		this.setActivity(Activity.WORKING);
		this.setBusyTime(500 / this.getStrength());
	}

	public void rest() {
		if (this.setActivity(Activity.RESTING, 0.2)) {
			// TODO: calc busy time
			this.canStopResting = false;
		}
	}

	private boolean canStopResting;

	public void attack(Unit other) {
		if (this.setActivity(Activity.ATTACKING, 1)) {
			//TODO: handle attack (update orientation)
			other.defend(this);
		}
	}

	public void defend(Unit attacker) {
		//TODO: handle defense, set orientation
		/*
		 * if (randomGen.getDouble() < 0.2*...) {dodge}
		 * else if (randomGen.getDouble() < 0.25*...) {block}
		 * else {take damage}
		 */
	}
>>>>>>> origin/master
}
