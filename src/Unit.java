import be.kuleuven.cs.som.annotate.*;

/**
 * 
 * @invar The name of each unit must be a valid name for any unit. |
 *        isValidName(getName())
 * @invar  The position of each unit must be a valid position for any unit.
 *       | isValidPosition(getPosition())
 * @invar  The weight of each unit must be a valid weight for any
 *         unit.
 *       | isValidWeight(getWeight())
 * @invar The strength of each unit must be a valid strength for any unit. |
 *        isValidStrength(getStrength())
 * @invar  The agility of each unit must be a valid agility for any
 *         unit.
 *       | isValidAgility(getAgility())
 * @invar  The toughness of each unit must be a valid toughness for any
 *         unit.
 *       | isValidToughness(getToughness())
 * @invar  The number of hitpoints of each unit must be a valid number of hitpoints for any
 *         unit.
 *       | isValidNbHitpoints(getNbHitpoints())
 * @invar  The number of stamina points of each unit must be a valid number of stamina points for any
 *         unit.
 *       | isValidNbStaminaPoints(getNbStaminaPoints())
 * @invar  The orientation of each unit must be a valid orientation for any
 *         unit.
 *       | isValidOrientation(getOrientation())
 * 
 * @author Ellen & Marte
 * @version 1.0
 */
public class Unit {


	/**
	 * Initialize this new unit with given position.
	 *
	 * @param  x,y,z
	 *         The position for this new unit.
	 * @effect The position of this new unit is set to
	 *         the given position.
	 *       | this.setPosition(x,y,z)
	 */
	public Unit(double x,y,z)
			throws IllegalPositionException {
		this.setPosition(x,y,z);
	}
	
	/**
	 * Return the position of this unit.
	 */
	@Basic @Raw
	public double getPosition() {
		return this.x,y,z;
	}
	
	/**
	 * Check whether the given position is a valid position for
	 * any unit.
	 *  
	 * @param  position
	 *         The position to check.
	 * @return 
	 *       | result ==
	 *       | TODO xyz
	*/
	public static boolean isValidPosition(double x,y,z) {
		return false;
	}
	
	/**
	 * Set the position of this unit to the given position.
	 * 
	 * @param  x,y,z
	 *         The new position for this unit.
	 * @post   The position of this new unit is equal to
	 *         the given position.
	 *       | new.getPosition() == x,y,z
	 * @throws IllegalPositionException
	 *         The given position is not a valid position for any
	 *         unit.
	 *       | ! isValidPosition(getPosition())
	 */
	@Raw
	public void setPosition(double x,y,z) 
			throws IllegalPositionException {
		if (! isValidPosition(x,y,z))
			throw new IllegalPositionException();
		this.x,y,z = x,y,z;
	}
	
		/**
		 * Variable registering the position of this unit.
		 */
		private double x, y, z;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Initialize this new unit with given name.
	 *
	 * @param givenName
	 *            The name for this new unit.
	 * @effect The name of this new unit is set to the given name. |
	 *         this.setName(givenName)
	 */
	public Unit(String givenName) throws IllegalNameException {
		this.setName(givenName);
	}

	/**
	 * Return the name of this unit.
	 */
	@Basic
	@Raw
	public String getName() {
		return this.givenName;
	}

	/**
	 * Check whether the given name is a valid name for any unit.
	 * 
	 * @param name
	 *            The name to check.
	 * @return Each name is at least two characters long and must start with an
	 *         uppercase letter. Names can only use letters (both upper- and
	 *         lowercase), quotes (both single and double) and spaces. |if
	 *         (length(givenName) < 2) | result == False |if (! isUpperCase(char
	 *         givenName.charAt(0))) | return == False |TODO only letters,
	 *         spaces, quotes in name
	 */
	public static boolean isValidName(String givenName) {
		return false;
	}

	/**
	 * Set the name of this unit to the given name.
	 * 
	 * @param givenName
	 *            The new name for this unit.
	 * @post The name of this new unit is equal to the given name. |
	 *       new.getName() == givenName
	 * @throws IllegalNameException
	 *             The given name is not a valid name for any unit. | !
	 *             isValidName(getName())
	 */
	@Raw
	public void setName(String givenName) throws IllegalNameException {
		if (!isValidName(givenName))
			throw new IllegalNameException();
		this.givenName = givenName;
	}

	/**
	 * Variable registering the name of this unit.
	 */
	private String givenName;
	
	
	
	
	
	
	
	
	/**
	 * Initialize this new unit with given strength.
	 * 
	 * @param  givenStrength
	 *         The strength for this new unit.
	 * @post   If the given strength is a valid strength for any unit,
	 *         the strength of this new unit is equal to the given
	 *         strength. Otherwise, the strength of this new unit is equal
	 *         to 25 or 100.
	 *       | if (isValidStrength(givenStrength))
	 *       |   then new.getStrength() == givenStrength
	 *       |   else new.getStrength() == 25 or 100
	 */
	public Unit(int givenStrength) {
		if (! isValidStrength(givenStrength))
			givenStrength = 25 or 100;
		setStrength(givenStrength);
	}
	
		/**
		 * Return the strength of this unit.
		 */
		@Basic
		@Raw
		public int getStrength() {
			return this.givenStrength;
		}
	
		/**
		 * Check whether the given strength is a valid strength for any unit.
		 * 
		 * @param givenStrength
		 *            The strength to check.
		 * @return | result == TODO
		 */
		public static boolean isValidStrength(int givenStrength) {
			return false;
		}
	
		/**
		 * Set the strength of this unit to the given strength.
		 * 
		 * @param givenStrength
		 *            The new strength for this unit.
		 * @post If the given strength is a valid strength for any unit, the
		 *       strength of this new unit is equal to the given strength. | if
		 *       (isValidStrength(givenStrength)) | then new.getStrength() ==
		 *       givenStrength
		 */
		@Raw
		public void setStrength(int givenStrength) {
			if (isValidStrength(givenStrength))
				this.givenStrength = givenStrength;
		}
	
		/**
		 * Variable registering the strength of this unit.
		 */
		private int givenStrength;

	
	
	
	
	


	/**
	 * Initialize this new unit with given agility.
	 * 
	 * @param  givenAgility
	 *         The agility for this new unit.
	 * @post   If the given agility is a valid agility for any unit,
	 *         the agility of this new unit is equal to the given
	 *         agility. Otherwise, the agility of this new unit is equal
	 *         to 25 or 100.
	 *       | if (isValidAgility(givenAgility))
	 *       |   then new.getAgility() == givenAgility
	 *       |   else new.getAgility() == 25 or 100
	 */
	public Unit(int givenAgility) {
		if (! isValidAgility(givenAgility))
			givenAgility = 25 or 100;
		setAgility(givenAgility);
	}
	
	/**
	 * Return the agility of this unit.
	 */
	@Basic @Raw
	public int getAgility() {
		return this.givenAgility;
	}
	
	/**
	 * Check whether the given agility is a valid agility for
	 * any unit.
	 *  
	 * @param  givenAgility
	 *         The agility to check.
	 * @return 
	 *       | result == TODO
	*/
	public static boolean isValidAgility(int givenAgility) {
		return false;
	}
	
	/**
	 * Set the agility of this unit to the given agility.
	 * 
	 * @param  givenAgility
	 *         The new agility for this unit.
	 * @post   If the given agility is a valid agility for any unit,
	 *         the agility of this new unit is equal to the given
	 *         agility.
	 *       | if (isValidAgility(givenAgility))
	 *       |   then new.getAgility() == givenAgility
	 */
	@Raw
	public void setAgility(int givenAgility) {
		if (isValidAgility(givenAgility))
			this.givenAgility = givenAgility;
	}
	
	/**
	 * Variable registering the agility of this unit.
	 */
	private int givenAgility;



		

	
	
	
	


	/**
	 * Initialize this new unit with given weight.
	 * 
	 * @param  givenWeight
	 *         The weight for this new unit.
	 * @post   If the given weight is a valid weight for any unit,
	 *         the weight of this new unit is equal to the given
	 *         weight. Otherwise, the weight of this new unit is equal
	 *         to (strenght+agility)/2 or 100.
	 *       | if (isValidWeight(givenWeight))
	 *       |   then new.getWeight() == givenWeight
	 *       |   else new.getWeight() == (strenght+agility)/2
	 */
	public Unit(int givenWeight) {
		if (! isValidWeight(givenWeight))
			givenWeight = (strenght+agility)/2;
		setWeight(givenWeight);
	}
	
	/**
	 * Return the weight of this unit.
	 */
	@Basic @Raw
	public int getWeight() {
		return this.givenWeight;
	}
	
	/**
	 * Check whether the given weight is a valid weight for
	 * any unit.
	 *  
	 * @param  givenWeight
	 *         The weight to check.
	 * @return 
	 *       | result == TODO
	*/
	public static boolean isValidWeight(int givenWeight) {
		return false;
	}
	
	/**
	 * Set the weight of this unit to the given weight.
	 * 
	 * @param  givenWeight
	 *         The new weight for this unit.
	 * @post   If the given weight is a valid weight for any unit,
	 *         the weight of this new unit is equal to the given
	 *         weight.
	 *       | if (isValidWeight(givenWeight))
	 *       |   then new.getWeight() == givenWeight
	 */
	@Raw
	public void setWeight(int givenWeight) {
		if (isValidWeight(givenWeight))
			this.givenWeight = givenWeight;
	}
	
	/**
	 * Variable registering the weight of this unit.
	 */
	private int givenWeight;

	
	
	
	
	
	
	
	
	
	
	
	
	
	


	/**
	 * Initialize this new unit with given toughness.
	 * 
	 * @param  givenToughness
	 *         The toughness for this new unit.
	 * @post   If the given toughness is a valid toughness for any unit,
	 *         the toughness of this new unit is equal to the given
	 *         toughness. Otherwise, the toughness of this new unit is equal
	 *         to 25 or 100.
	 *       | if (isValidToughness(givenToughness))
	 *       |   then new.getToughness() == givenToughness
	 *       |   else new.getToughness() == 25 or 100
	 */
	public Unit(int givenToughness) {
		if (! isValidToughness(givenToughness))
			givenToughness = 25 or 100;
		setToughness(givenToughness);
	}
	
	/**
	 * Return the toughness of this unit.
	 */
	@Basic @Raw
	public int getToughness() {
		return this.givenToughness;
	}
	
	/**
	 * Check whether the given toughness is a valid toughness for
	 * any unit.
	 *  
	 * @param  givenToughness
	 *         The toughness to check.
	 * @return 
	 *       | result == TODO
	*/
	public static boolean isValidToughness(int givenToughness) {
		return false;
	}
	
	/**
	 * Set the toughness of this unit to the given toughness.
	 * 
	 * @param  givenToughness
	 *         The new toughness for this unit.
	 * @post   If the given toughness is a valid toughness for any unit,
	 *         the toughness of this new unit is equal to the given
	 *         toughness.
	 *       | if (isValidToughness(givenToughness))
	 *       |   then new.getToughness() == givenToughness
	 */
	@Raw
	public void setToughness(int givenToughness) {
		if (isValidToughness(givenToughness))
			this.givenToughness = givenToughness;
	}
	
	/**
	 * Variable registering the toughness of this unit.
	 */
	private int givenToughness;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private int getMaxHitpoints() {
		return 2*this.getWeight()*this.getToughness()/100; // TODO round up
	}
	
	private int getMaxStaminaPoints() {
		return 2*this.getWeight()*this.getToughness()/100; // TODO round up
	}
	

	/**
	 * Initialize this new unit with given number of hitpoints.
	 * 
	 * @param  givenNbHitpoints
	 *         The number of hitpoints for this new unit.
	 * @pre    The given number of hitpoints must be a valid number of hitpoints for any unit.
	 *       | isValidNbHitpoints(number of hitpoints)
	 * @post   The number of hitpoints of this new unit is equal to the given
	 *         number of hitpoints.
	 *       | new.getNbHitpoints() == givenNbHitpoints
	 */
	public Unit(int givenNbHitpoints) {
		this.setNbHitpoints(givenNbHitpoints);
	}
	
	/**
	 * Return the number of hitpoints of this unit.
	 */
	@Basic @Raw
	public int getNbHitpoints() {
		return this.givenNbHitpoints;
	}
	
	/**
	 * Check whether the given number of hitpoints is a valid number of hitpoints for
	 * any unit.
	 *  
	 * @param  number of hitpoints
	 *         The number of hitpoints to check.
	 * @return 
	 *       | result == TODO
	*/
	public static boolean isValidNbHitpoints(int givenNbHitpoints) {
		return false;
	}
	
	/**
	 * Set the number of hitpoints of this unit to the given number of hitpoints.
	 * 
	 * @param  givenNbHitpoints
	 *         The new number of hitpoints for this unit.
	 * @pre    The given number of hitpoints must be a valid number of hitpoints for any
	 *         unit.
	 *       | isValidNbHitpoints(givenNbHitpoints)
	 * @post   The number of hitpoints of this unit is equal to the given
	 *         number of hitpoints.
	 *       | new.getNbHitpoints() == givenNbHitpoints
	 */
	@Raw
	public void setNbHitpoints(int givenNbHitpoints) {
		assert isValidNbHitpoints(givenNbHitpoints);
		this.givenNbHitpoints = givenNbHitpoints;
	}
	
	/**
	 * Variable registering the number of hitpoints of this unit.
	 */
	private int givenNbHitpoints;
		

	
	
	
	
	
	
	


	/**
	 * Initialize this new unit with given number of stamina points.
	 * 
	 * @param  givenNbStaminaPoints
	 *         The number of stamina points for this new unit.
	 * @pre    The given number of stamina points must be a valid number of stamina points for any unit.
	 *       | isValidNbStaminaPoints(number of stamina points)
	 * @post   The number of stamina points of this new unit is equal to the given
	 *         number of stamina points.
	 *       | new.getNbStaminaPoints() == givenNbStaminaPoints
	 */
	public Unit(int givenNbStaminaPoints) {
		this.setNbStaminaPoints(givenNbStaminaPoints);
	}
	
	/**
	 * Return the number of stamina points of this unit.
	 */
	@Basic @Raw
	public int getNbStaminaPoints() {
		return this.givenNbStaminaPoints;
	}
	
	/**
	 * Check whether the given number of stamina points is a valid number of stamina points for
	 * any unit.
	 *  
	 * @param  number of stamina points
	 *         The number of stamina points to check.
	 * @return 
	 *       | result == TODO
	*/
	public static boolean isValidNbStaminaPoints(int givenNbStaminaPoints) {
		return false;
	}
	
	/**
	 * Set the number of stamina points of this unit to the given number of stamina points.
	 * 
	 * @param  givenNbStaminaPoints
	 *         The new number of stamina points for this unit.
	 * @pre    The given number of stamina points must be a valid number of stamina points for any
	 *         unit.
	 *       | isValidNbStaminaPoints(givenNbStaminaPoints)
	 * @post   The number of stamina points of this unit is equal to the given
	 *         number of stamina points.
	 *       | new.getNbStaminaPoints() == givenNbStaminaPoints
	 */
	@Raw
	public void setNbStaminaPoints(int givenNbStaminaPoints) {
		assert isValidNbStaminaPoints(givenNbStaminaPoints);
		this.givenNbStaminaPoints = givenNbStaminaPoints;
	}
	
	/**
	 * Variable registering the number of stamina points of this unit.
	 */
	private int givenNbStaminaPoints;
	
	
	
	
	
	
	
	
	
	
	


	/**
	 * Initialize this new unit with given orientation.
	 * 
	 * @param  theta
	 *         The orientation for this new unit.
	 * @post   If the given orientation is a valid orientation for any unit,
	 *         the orientation of this new unit is equal to the given
	 *         orientation. Otherwise, the orientation of this new unit is equal
	 *         to PI/2.
	 *       | if (isValidOrientation(theta))
	 *       |   then new.getOrientation() == theta
	 *       |   else new.getOrientation() == PI/2
	 */
	public Unit(float theta) {
		if (! isValidOrientation(theta))
			theta = PI/2;
		setOrientation(theta);
	}
	
	/**
	 * Return the orientation of this unit.
	 */
	@Basic @Raw
	public float getOrientation() {
		return this.theta;
	}
	
	/**
	 * Check whether the given orientation is a valid orientation for
	 * any unit.
	 *  
	 * @param  theta
	 *         The orientation to check.
	 * @return 
	 *       | result == TODO
	*/
	public static boolean isValidOrientation(float theta) {
		return false;
	}
	
	/**
	 * Set the orientation of this unit to the given orientation.
	 * 
	 * @param  theta
	 *         The new orientation for this unit.
	 * @post   If the given orientation is a valid orientation for any unit,
	 *         the orientation of this new unit is equal to the given
	 *         orientation.
	 *       | if (isValidOrientation(theta))
	 *       |   then new.getOrientation() == theta
	 */
	@Raw
	public void setOrientation(float theta) {
		if (isValidOrientation(theta))
			this.theta = theta;
	}
	
	/**
	 * Variable registering the orientation of this unit.
	 */
	private float theta;

	
	
	
	
	
	
	
	
	
	
	
	
	
	//************************************************************************************************//
	
	
	/**
	 * 
	 * update the position and activity status of a Unit, based on that Unit's current position,
	 * attributes and a given duration 'seconds' in seconds of game time
	 * 
	 * @param seconds
	 * @throws IllegalSecondsException
	 */
	public void advanceTime(float seconds) throws IllegalSecondsException{
		
	}
	
	
	
	
}
