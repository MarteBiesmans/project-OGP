package hillbillies.model;

import java.util.Random;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import hillbillies.model.programs.statements.ActionStatement;
//import hillbillies.model.programs.expressions.LogExpression.LogDistPair;
//import hillbillies.model.programs.statements.ActionStatement;
import ogp.framework.util.Util;

/**
 * this class exists of these parts:
 *  - constants
 *  - constructor
 *  - attributes: position, name, strength, agility, tougness, weight, 
 *  				hitpoints, stamina points, orientation, 
 *  				experience points, level
 *  - relations with other classes: world, faction, materials
 *  - activities and busy time
 *  - advance time and helper functions
 *  - moving and pathfinding: normal moving, sprinting, falling
 *  - other behaviour: default behaviour, working, resting, fighting
 *  - termination
 *  - tasks
 */

/**
 * A class of units involving a position, name, strength, agility, toughness,
 * weight and default behaviour.
 * 
 * @invar The name of each unit must be a valid name for any unit. |
 *        isValidName(getName())
 * @invar The position of each unit must be a valid position for any unit. |
 *        isValidPosition(getPosition())
 * @invar The weight of each unit must be a valid weight for any unit. |
 *        this.canHaveAsWeight(getWeight())
 * @invar The strength of each unit must be a valid strength for any unit. |
 *        isValidStrength(getStrength())
 * @invar The agility of each unit must be a valid agility for any unit. |
 *        isValidAgility(getAgility())
 * @invar The toughness of each unit must be a valid toughness for any unit. |
 *        isValidToughness(getToughness())
 * @invar The number of hitpoints of each unit must be a valid number of
 *        hitpoints for any unit. | canHaveAsHitpoints(getNbHitpoints())
 * @invar The number of stamina points of each unit must be a valid number of
 *        stamina points for each unit. |
 *        canHaveAsStaminaPoints(getNbStaminaPoints())
 * @invar The orientation of each unit must be a valid orientation for any unit.
 *        | isValidOrientation(getOrientation())
 * @invar Each unit must have proper materials. | hasProperMaterials()
 * @invar The task of each unit must be a valid task for any unit. |
 *        isValidTask(getTask()) || getTask()==null
 * @invar The world of each unit must be a valid world for any unit. |
 *        canHaveAsWorld(getWorld()) || getWorld()==null
 * @invar The faction of each unit must be a valid faction for any unit. |
 *        canHaveAsFaction(getFaction()) || getFaction()==null
 *        
 * 
 * 
 * @author Ellen & Marte
 */
public class Unit implements ITimeVariableObject {

	/**
	 * constant necessary to create random numbers
	 */
	private static final Random RANDOM_GEN = new Random();

	/**
	 * constants for the minimum and maximum values of primary attributes
	 * (weight, strength, agility and toughness)
	 */
	private static final int MIN_INIT_VAL_PRIMARY_ATTRIBUTE = 25;
	private static final int MAX_INIT_VAL_PRIMARY_ATTRIBUTE = 100;
	private static final int MIN_VAL_PRIMARY_ATTRIBUTE = 1;
	private static final int MAX_VAL_PRIMARY_ATTRIBUTE = 200;

	/**
	 * Create a new unit with a given position, name, strength, agility,
	 * toughness and boolean to enable default behaviour or not.
	 * 
	 * @effect sets the name of this new unit to the given name |setName(name)
	 * 
	 * @post	the position of this new unit equals the given position.
	 *         |new.getPosition == new Position(x, y, z)
	 * @effect sets the orientation of this new unit to the default value PI/2
	 *         |setOrientation((float) Math.PI/2)
	 * 
	 * @post The strength of this new unit is equal to the given strength if it
	 *       lies between the minimum and maximum initial value of strength,
	 *       else it will be the closest boundary value |if (strength <
	 *       MIN_INIT_VAL_PRIMARY_ATTRIBUTE) | then new.getStrength() ==
	 *       MIN_INIT_VAL_PRIMARY_ATTRIBUTE; |else if (strength >
	 *       MAX_INIT_VAL_PRIMARY_ATTRIBUTE) | then new.getStrength() ==
	 *       MAX_INIT_VAL_PRIMARY_ATTRIBUTE; |else | then new.getStrength ==
	 *       strength;
	 * @post The agility of this new unit is equal to the given agility if it
	 *       lies between the minimum and maximum initial value of agility, else
	 *       it will be the closest boundary value |if (agility <
	 *       MIN_INIT_VAL_PRIMARY_ATTRIBUTE) | then new.getAgility() ==
	 *       MIN_INIT_VAL_PRIMARY_ATTRIBUTE; |else if (agility >
	 *       MAX_INIT_VAL_PRIMARY_ATTRIBUTE) | then new.getAgility() ==
	 *       MAX_INIT_VAL_PRIMARY_ATTRIBUTE; |else | then new.getAgility ==
	 *       agility;
	 * @post The toughness of this new unit is equal to the given tougness if it
	 *       lies between the minimum and maximum initial value of tougness,
	 *       else it will be the closest boundary value |if (tougness <
	 *       MIN_INIT_VAL_PRIMARY_ATTRIBUTE) | then new.getToughness() ==
	 *       MIN_INIT_VAL_PRIMARY_ATTRIBUTE; |else if (toughness >
	 *       MAX_INIT_VAL_PRIMARY_ATTRIBUTE) | then new.getToughness() ==
	 *       MAX_INIT_VAL_PRIMARY_ATTRIBUTE; |else | then new.getToughness ==
	 *       toughness;
	 * @post The weight of this new unit is equal to the given weight if it lies
	 *       between the minimum and maximum initial value of weight, else it
	 *       will be the closest boundary value |if (weight <
	 *       this.getMinimumInitValWeight()) | then new.getWeight() ==
	 *       this.getMinimumInitValWeight(); |else if (weight >
	 *       MAX_INIT_VAL_PRIMARY_ATTRIBUTE) | then new.getWeight() ==
	 *       MAX_INIT_VAL_PRIMARY_ATTRIBUTE; |else | then new.getWeight ==
	 *       weight;
	 *
	 * @effect sets the hitpoints of this new unit to the maximum value
	 *         |setHitpoints(this.getMaxHitpoints())
	 * @effect sets the stamina points of this new unit to the maximum value
	 *         |setStaminaPoints(this.getMaxStaminaPoints());
	 * 
	 * @effect sets the activity of this new unit to NONE
	 *         |setActivity(Activity.NONE);
	 * @post this new unit has no acitivities in it's activity queue
	 *       |new.activityQueue.isEmpty()
	 * @effect sets the busy time of this new unit to zero |setBusyTime(0);
	 * 
	 * @post the flag default behaviour of this new unit equals the value of the
	 *       variable enableDefaultBehaviour |new.defaultBehaviour ==
	 *       enableDefaultBehaviour;
	 * 
	 * @effect moveToCube, moveToAdjacent en workAtCube of this new unit are all
	 *         set to null |setMoveToCube(null); |setMoveToAdjacent(null);
	 *         |setWorkAtCube(null);
	 * 
	 * @post the world and faction of this new unit equal null |new.world =
	 *       null; |new.faction = null;
	 * @effect sets the experience points and level of this new unit to zero
	 *         |setExperiencePoints(0); |setLevel(0);
	 * 
	 * @post This new unit has no materials yet. |new.getNbMaterials() == 0
	 * @post this new unit is alive |new.isDead == false
	 * 
	 * @throws IllegalArgumentException
	 *             the given name is not valid |!this.isValidName(name)
	 * @throws IllegalArgumentException
	 *             the given coordinates are not valid
	 *             |!this.canHaveAsPosition(new Position(x,y,z))
	 */
	public Unit(double x, double y, double z, String name, int strength, int agility, int toughness, int weight,
			boolean enableDefaultBehaviour) throws IllegalArgumentException {

		// name
		this.setName(name);

		// position and orientation
		if (!this.canHaveAsPosition(new Position(x,y,z)))
			throw new IllegalArgumentException();
		this.position = ((new Cube((int)x, (int)y, (int)z)).getCenter());
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

		// moveTo and moveToAdjacent, followUnit, workAtCube
		this.setMoveToCube(null);
		this.setMoveToAdjacent(null);
		this.setFollowUnit(null);
		this.setWorkAtCube(null);

		// world, faction, experience points and level
		this.world = null;
		this.faction = null;
		this.setExperiencePoints(0);
		this.setLevel(0);

		// task
		this.setTask(null);
	}

	// ATTRIBUTES: getAlpha(), isValidAlpha(), canHaveAsAlpha(), setAlpha() ...

	/**
	 * checks if the unit is a valid unit. This means that all attributes have
	 * to be valid and that the unit must belong to a faction and must exist in
	 * a world
	 * 
	 * @return true if position, name, strength, agility, toughness, weight and
	 *         orientation are valid and if this unit belongs to a faction and
	 *         to a world |result == canHaveAsPosition(this.getPosition()) &&
	 *         isValidName(this.getName()) |&&
	 *         isValidStrength(this.getStrength()) &&
	 *         isValidAgility(this.getAgility()) |&&
	 *         canHaveAsWeight(this.getWeight()) &&
	 *         isValidToughness(this.getToughness()) |&&
	 *         isValidOrientation(this.getOrientation()) |&& this.getFaction()
	 *         != null && this.getWorld() != null
	 */
	boolean isValidUnit() {
		return (canHaveAsPosition(this.getPosition()) && isValidName(this.getName())
				&& isValidStrength(this.getStrength()) && isValidAgility(this.getAgility())
				&& canHaveAsWeight(this.getWeight()) && isValidToughness(this.getToughness())
				&& isValidOrientation(this.getOrientation()) && this.getFaction() != null && this.getWorld() != null);
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
	 * check wether the given position is valid for this unit
	 * 
	 * @param position
	 *            the position to check
	 * @return false if the given position equals null |if (position==null) |
	 *         then result == false
	 * @return false if the given position is not legal in the world of this
	 *         unit |if (world != null) | then result ==
	 *         position.isValidForObjectIn(this.getWorld())
	 * @return true if this unit doesn't have a world (position cannot be
	 *         invalid) |if (world == null) | then result == true
	 */
	private boolean canHaveAsPosition(Position position) {
		if (position == null)
			return false;
		if (world != null)
			return position.isValidForObjectIn(this.getWorld());
		return true;
	}

	/**
	 * Set the position of this unit to the given coordinates.
	 * 
	 * @param position
	 *            the new position for this unit
	 * @post the new position of this unit is equal to the given position.
	 *       |new.getPosition() = position
	 * 
	 * @effect if this unit is not falling yet and it should fall, an activity
	 *         falling will be added |if (this.getCurrentActivity() !=
	 *         Activity.FALLING &&
	 *         this.shouldStartFallingAt(position.getCube())) | then
	 *         insertActivity(Activity.FALLING)
	 * @effect if this unit was falling and reached a stable position, go to the
	 *         next activity and set the position the the centre of its current
	 *         cube |if (this.getCurrentActivity() == Activity.FALLING
	 *         && position.isStableForUnitIn(world)) | then nextActivity(), 
	 *         this.setPosition(this.getPosition().getCube().getCenter())
	 * 
	 * @throws IllegalArgumentException
	 *             the given coordinates are not valid for this unit
	 *             |(!this.canHaveAsPosition(new Position(x,y,z)))
	 */
	@Raw
	private void setPosition(Position position) throws IllegalArgumentException {
		if (!this.canHaveAsPosition(position))
			throw new IllegalArgumentException();
		if (this.getCurrentActivity() != Activity.FALLING && this.shouldStartFallingAt(position.getCube())) {
			this.insertActivity(Activity.FALLING);
		}
		if (this.getCurrentActivity() == Activity.FALLING && this.canStopFallingAt(position)) {
			position = position.getCube().getCenter();
			this.nextActivity();
		}
		this.position = position;
	}

	/**
	 * a variable registering the position of a unit
	 */
	private Position position;

	/**
	 * return the cube where this unit is located
	 * 
	 * @return the cube of the position of this unit |result ==
	 *         this.getPosition().getCube()
	 */
	public Cube getCube() {
		return this.getPosition().getCube();
	}

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
	 * @return false if given name equals null |if (name==null) | then result ==
	 *         false
	 * @return false if the given name is not at least two characters long and
	 *         doesn't start with an uppercase letter |if (name.length() < 2) |
	 *         then result == false |if (!Character.isUpperCase(name.charAt(0)))
	 *         | then result == false
	 * @return false if at least one character is not valid (valid characters
	 *         are letters, double quotes, single quotes and spaces) |for each i
	 *         in [0, name.length()] |if ((!Character.isLetter(name.charAt(i)))
	 *         && (!(name.charAt(i) == '"')) | && (!(name.charAt(i) == '\'')) &&
	 *         (!(name.charAt(i) == ' ')) | then result == false
	 */
	private static boolean isValidName(String name) {
		if (name == null)
			return false;
		if (name.length() < 2)
			return false;
		if (!Character.isUpperCase(name.charAt(0)))
			return false;

		// a loop over the name which returns false if it
		// recognizes a character that is not allowed.
		for (int i = 0; i < name.length(); i++) {
			if ((!Character.isLetter(name.charAt(i))) // is it a letter?
					&& (!(name.charAt(i) == '"')) // is it a double quote?
					&& (!(name.charAt(i) == '\'')) // is it a single
													// quote/apostrophe?
					&& (!(name.charAt(i) == ' ')) // is it a space?
			)
				return false;
		}
		return true;
	}

	/**
	 * Set the name of this unit to the given name.
	 * 
	 * @param name
	 *            The new name for this unit.
	 * @post The name of this unit is equal to the given name if it is valid.
	 *       |if (isValidName(name)) | then new.getName() == name
	 * @throws IllegalArgumentException
	 *             The given name is not a valid name for this unit.
	 *             |(!isValidName(name))
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
	 *         value for primary attributes. |result == ((strength >=
	 *         MIN_VAL_PRIMARY_ATTRIBUTE) && (strength <=
	 *         MAX_VAL_PRIMARY_ATTRIBUTE))
	 */
	private static boolean isValidStrength(int strength) {
		return ((strength >= MIN_VAL_PRIMARY_ATTRIBUTE) && (strength <= MAX_VAL_PRIMARY_ATTRIBUTE));
	}

	/**
	 * Set the strength of this unit to the given strength.
	 * 
	 * @param strength
	 *            The new strength for this unit.
	 * @post If the given strength is a valid strength for this unit, the new
	 *       strength of this unit is equal to the given strength. |if
	 *       (isValidStrength(strength)) | then new.getStrength() == strength
	 * @post If the given strength lies beyond the limits of the specified
	 *       minimum and maximum value, the new strength will be this limit
	 *       value. |if (strength < MIN_VAL_PRIMARY_ATTRIBUTE) | then
	 *       new.getStrength() == MIN_VAL_PRIMARY_ATTRIBUTE |if (strength >
	 *       MAX_VAL_PRIMARY_ATTRIBUTE) | then new.getStrength() ==
	 *       MAX_VAL_PRIMARY_ATTRIBUTE
	 * @effect the weight of this unit is set to it's current weight to meet the
	 *         possible new limits |setWeight(this.getWeight())
	 */
	@Raw
	public void setStrength(int strength) {
		if (isValidStrength(strength))
			this.strength = strength;
		if (strength < MIN_VAL_PRIMARY_ATTRIBUTE)
			this.strength = MIN_VAL_PRIMARY_ATTRIBUTE;
		if (strength > MAX_VAL_PRIMARY_ATTRIBUTE)
			this.strength = MAX_VAL_PRIMARY_ATTRIBUTE;
		this.setWeight(this.getWeight());
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
	private static boolean isValidAgility(int agility) {
		return ((agility >= MIN_VAL_PRIMARY_ATTRIBUTE) && (agility <= MAX_VAL_PRIMARY_ATTRIBUTE));
	}

	/**
	 * Set the agility of this unit to the given agility.
	 * 
	 * @param agility
	 *            The new agility for this unit.
	 * @post If the given agility is a valid agility for this unit, the new
	 *       agility of this unit is equal to the given agility. |if
	 *       (isValidAgility(agility)) | then new.getAgility() == agility
	 * @post If the given agility lies beyond the limits of the specified
	 *       minimum and maximum value, the new agility will be this limit
	 *       value. |if (agility < MIN_VAL_PRIMARY_ATTRIBUTE) | then
	 *       new.getAgility() == MIN_VAL_PRIMARY_ATTRIBUTE |if (agility >
	 *       MAX_VAL_PRIMARY_ATTRIBUTE) | then new.getAgility() ==
	 *       MAX_VAL_PRIMARY_ATTRIBUTE
	 * @effect the weight of this unit is set to it's current weight to meet the
	 *         possible new limits |setWeight(this.getWeight())
	 */
	@Raw
	public void setAgility(int agility) {
		if (isValidAgility(agility))
			this.agility = agility;
		if (agility < MIN_VAL_PRIMARY_ATTRIBUTE)
			this.agility = MIN_VAL_PRIMARY_ATTRIBUTE;
		if (agility > MAX_VAL_PRIMARY_ATTRIBUTE)
			this.agility = MAX_VAL_PRIMARY_ATTRIBUTE;
		this.setWeight(this.getWeight());
	}

	/**
	 * Variable registering the agility of this unit.
	 */
	private int agility;

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
	private static boolean isValidToughness(int toughness) {
		return ((toughness >= MIN_VAL_PRIMARY_ATTRIBUTE) && (toughness <= MAX_VAL_PRIMARY_ATTRIBUTE));
	}

	/**
	 * Set the toughness of this unit to the given toughness.
	 * 
	 * @param toughness
	 *            The new toughness for this unit.
	 * @post If the given toughness is a valid toughness for this unit, the new
	 *       toughness of this unit is equal to the given toughness. |if
	 *       (isValidToughness(toughness)) | then new.getToughness() ==
	 *       toughness
	 * @post If the given toughness lies beyond the limits of the specified
	 *       minimum and maximum value, the new toughness will be this
	 *       limitvalue. |if (toughness < MIN_VAL_PRIMARY_ATTRIBUTE) | then
	 *       new.getToughness() == MIN_VAL_PRIMARY_ATTRIBUTE |if (toughness >
	 *       MAX_VAL_PRIMARY_ATTRIBUTE) | then new.getToughness() ==
	 *       MAX_VAL_PRIMARY_ATTRIBUTE
	 * @post If after the change of toughness, hitpoints and stamina points
	 *       aren't legal anymore, they will be changed to a legal value. |if
	 *       (!new.canHaveAsHitpoints(this.getHitpoints()) ) | then
	 *       new.getHitpoints() == new.getMaxHitpoints() |if
	 *       (!new.canHaveAsStaminaPoints(this.getStaminaPoints()) ) | then
	 *       new.getStaminaPoints() == new.getMaxStaminaPoints()
	 */
	@Raw
	public void setToughness(int toughness) {
		if (isValidToughness(toughness))
			this.toughness = toughness;
		if (toughness < MIN_VAL_PRIMARY_ATTRIBUTE)
			this.toughness = MIN_VAL_PRIMARY_ATTRIBUTE;
		if (toughness > MAX_VAL_PRIMARY_ATTRIBUTE)
			this.toughness = MAX_VAL_PRIMARY_ATTRIBUTE;

		if (!this.canHaveAsHitpoints(this.getHitpoints()))
			this.setHitpoints(this.getMaxHitpoints());
		if (!this.canHaveAsStaminaPoints(this.getStaminaPoints()))
			this.setStaminaPoints(this.getMaxStaminaPoints());
	}

	/**
	 * Variable registering the toughness of this unit.
	 */
	private int toughness;

	/**
	 * Return the weight of this unit.
	 */
	@Basic
	@Raw
	public int getWeight() {
		return this.weight;
	}

	/**
	 * return the total weight of this unit (including weight of carried
	 * materials)
	 * 
	 * @return the weight of this unit plus the weight of carried materials
	 *         |weightSoFar == this.getWeight() |for each material in
	 *         this.materials | weightSoFar += material.getWeight() |result ==
	 *         weightSoFar
	 */
	private int getTotalWeight() {
		int weightSoFar = this.getWeight();
		for (Material material : this.materials)
			weightSoFar += material.getWeight();
		return weightSoFar;
	}

	/**
	 * Return the minimum value for the primary attribute weight.
	 * 
	 * @return the maximum of (strength + agility) / 2 and the minimum value for
	 *         any primary attribute |result == Math.max( | (int)
	 *         Math.ceil((double) ((this.strength + this.agility) / 2.0)), |
	 *         MIN_VAL_PRIMARY_ATTRIBUTE )
	 */
	private int getMinimumWeight() {
		int minimumWeight = (int) Math.ceil((double) ((this.strength + this.agility) / 2.0));
		return Math.max(minimumWeight, MIN_VAL_PRIMARY_ATTRIBUTE);
	}

	/**
	 * Return the minimum initial value for the primary attribute weight.
	 * 
	 * @return the maximum of (strength + agility) / 2 and the minimum initial
	 *         value for any primary attribute |Math.max( |(int)
	 *         Math.ceil((double) ((this.strength + this.agility) / 2.0)), |
	 *         MIN_INIT_VAL_PRIMARY_ATTRIBUTE )
	 */
	private int getMinimumInitValWeight() {
		int minimumInitValWeight = (int) Math.ceil((double) ((this.strength + this.agility) / 2.0));
		return Math.max(minimumInitValWeight, MIN_INIT_VAL_PRIMARY_ATTRIBUTE);
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
	private boolean canHaveAsWeight(int weight) {
		return ((weight >= this.getMinimumWeight()) && (weight <= MAX_VAL_PRIMARY_ATTRIBUTE));
	}

	/**
	 * Set the weight of this unit to the given weight.
	 * 
	 * @param weight
	 *            The new weight for this unit.
	 * @post If the given weight is a valid weight for this unit, the new weight
	 *       of this unit is equal to the given weight. |if
	 *       (this.canHaveAsWeight(weight)) | then new.getWeight() == weight
	 * @post If the given weight lies beyond the limits of the specified minimum
	 *       and maximum value, the new weight will be this limit value. |if
	 *       (weight < this.getMinimumWeight()) | then new.getWeight() ==
	 *       this.getMinimumWeight() |if (weight > MAX_VAL_PRIMARY_ATTRIBUTE) |
	 *       then new.getWeight() == MAX_VAL_PRIMARY_ATTRIBUTE
	 * @post If after the change of weight, hitpoints and stamina points aren't
	 *       legal anymore, they will be changed to a legal value. |if
	 *       (!new.canHaveAsHitpoints(this.getHitpoints()) ) | then
	 *       new.getHitpoints() == new.getMaxHitpoints() |if
	 *       (!new.canHaveAsStaminaPoints(this.getStaminaPoints()) ) | then
	 *       new.getStaminaPoints() == new.getMaxStaminaPoints()
	 */
	@Raw
	public void setWeight(int weight) {
		if (this.canHaveAsWeight(weight)) {
			this.weight = weight;
		}
		if (weight < this.getMinimumWeight()) {
			this.weight = this.getMinimumWeight();
		}
		if (weight > MAX_VAL_PRIMARY_ATTRIBUTE) {
			this.weight = MAX_VAL_PRIMARY_ATTRIBUTE;
		}

		if (!this.canHaveAsHitpoints(this.getHitpoints()))
			this.setHitpoints(this.getMaxHitpoints());
		if (!this.canHaveAsStaminaPoints(this.getStaminaPoints()))
			this.setStaminaPoints(this.getMaxStaminaPoints());
	}

	/**
	 * Variable registering the weight of this unit.
	 */
	private int weight;

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
	 * @return the maximum hitpoints based on the weight and thoughness of this
	 *         unit |result == (int) Math.ceil((2 * this.getWeight() *
	 *         this.getToughness()) / 100.0)
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
	private boolean canHaveAsHitpoints(double hitpoints) {
		return ((hitpoints >= 0) && (hitpoints <= this.getMaxHitpoints()));
	}

	/**
	 * Set the hitpoints of this unit to the given hitpoints.
	 * 
	 * @param hitpoints
	 *            The new hitpoints for this unit.
	 * @pre The given hitpoints must be valid hitpoints for this unit.
	 *      |this.canHaveAsHitpoints(hitpoints)
	 * @post The hitpoints of this unit is equal to the given hitpoints.
	 *       |new.getHitpoints() == hitpoints
	 */
	@Raw
	private void setHitpoints(double hitpoints) {
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
	 * @return the maximum stamina points based on the weight and thoughness of
	 *         this unit |result == (int) Math.ceil((2 * this.getWeight() *
	 *         this.getToughness()) / 100.0)
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
	 *         maximum value |result == ( (staminaPoints >= 0) && (staminaPoints
	 *         <= this.getMaxHitpoints()) )
	 */
	private boolean canHaveAsStaminaPoints(double staminaPoints) {
		return ((staminaPoints >= 0) && (staminaPoints <= this.getMaxStaminaPoints()));
	}

	/**
	 * Set the stamina points of this unit to the given stamina points.
	 * 
	 * @param staminaPoints
	 *            The new stamina points for this unit.
	 * @pre The given stamina points must be valid stamina points for this unit.
	 *      |this.canHaveAsStaminaPoints(staminaPoints)
	 * @post The stamina points of this unit are equal to the given stamina
	 *       points. |new.getStaminaPoints() == staminaPoints
	 */
	@Raw
	private void setStaminaPoints(double staminaPoints) {
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
	 * @return true if and only if theta lies in the interval [0, 2*PI[ |result
	 *         == (theta>=0 && theta<(2*Math.PI))
	 */
	private static boolean isValidOrientation(double theta) {
		return (theta >= 0.0 && theta < (2.0 * Math.PI));
	}

	/**
	 * Set the orientation of this unit to the given orientation.
	 * 
	 * @param theta
	 *            The new orientation for this unit.
	 * @post If the given orientation theta is valid orientation, the new
	 *       orientation equals this value. If the given orientation theta lies
	 *       outside the interval [0, 2*PI[, the new orientation of this unit is
	 *       equal to the equivalent angle in this interval with the same sinus
	 *       and cosinus. |result == Math.abs(theta % (2.0 * Math.PI))
	 */
	@Raw
	private void setOrientation(double theta) {
		this.orientation = Math.abs(theta % (2.0 * Math.PI));
	}

	/**
	 * Variable registering the orientation of this unit.
	 */
	private double orientation;

	/**
	 * return the experience points of this unit
	 */
	@Basic
	public int getExperiencePoints() {
		return this.experiencePoints;
	}

	/**
	 * check whether the given experience points are valid for this unit
	 * 
	 * @param points
	 *            The points to check
	 * @return true if the given points are greater or equal than zero |result
	 *         == (points >= 0)
	 */
	private boolean isValidExperiencePoints(int points) {
		return (points >= 0);
	}

	/**
	 * set the experience points of this unit to the given points
	 * 
	 * @param points
	 *            the number of experience points to set
	 * @post if points is valid, the experience points if this unit equal points
	 *       |if (isValidExperiencePoints(points)) | then
	 *       new.getExperiencePoints() == points
	 */
	private void setExperiencePoints(int points) {
		if (isValidExperiencePoints(points))
			this.experiencePoints = points;
	}

	/**
	 * a variable registering the experience points of a unit
	 */
	private int experiencePoints;

	/**
	 * return the level of this unit
	 */
	@Basic
	private int getLevel() {
		return this.level;
	}

	/**
	 * check wheter the given level is a valid level for this unit
	 * 
	 * @param level
	 *            the level to check
	 * @return true if the level is greater or equal than zero |result == level
	 *         >= 0
	 */
	private boolean isValidLevel(int level) {
		return level >= 0;
	}

	/**
	 * set the level of this unit to the given level
	 * 
	 * @param level
	 *            the level to set to
	 * @post if the given level is valid, the level of this unit equals this
	 *       value |if (isValidLevel(level)) | then new.getLevel() == level
	 */
	private void setLevel(int level) {
		if (isValidLevel(level))
			this.level = level;
	}

	/**
	 * a variable to register the level of this unit
	 */
	private int level;

	// RELATIONS with world, faction and material

	/**
	 * return the world this unit belongs to
	 */
	@Basic
	public World getWorld() {
		return this.world;
	}

	/**
	 * check whether the world of this unit can be set to the given world
	 * 
	 * @param world
	 *            the world to check
	 * @return true if all these are true: the given world is not null, this
	 *         unit already belongs to the given world, the current world of
	 *         this unit equals null | result == (world != null &&
	 *         world.hasAsUnit(this) | && this.getWorld() == null)
	 */
	private boolean canHaveAsWorld(World world) {
		return (world != null && world.hasAsUnit(this) && this.getWorld() == null);
	}

	/**
	 * set the world of this unit to the given world
	 * 
	 * @param world
	 *            the world to set to (can be null)
	 * @post world of this unit equals the given world |new.getWorld() == world
	 * @throws IllegalArgumentException
	 *             the world of this unit cannot be set to the given world
	 *             except if it equals null | (world != null &&
	 *             !canHaveAsWorld(world))
	 * @throws IllegalArgumentException
	 *             this unit already has a recorded world and already belongs to
	 *             it | ((this.getWorld() != null) &&
	 *             (this.getWorld().hasAsUnit(this)))
	 */
	void setWorld(World world) throws IllegalArgumentException {
		if (world != null) {
			if (!canHaveAsWorld(world))
				throw new IllegalArgumentException();
		} else if ((this.getWorld() != null) && (this.getWorld().hasAsUnit(this)))
			throw new IllegalArgumentException();
		this.world = world;
	}

	/**
	 * variable registering the world where a unit belongs to
	 */
	private World world;

	/**
	 * return the faction this unit belongs to
	 */
	@Basic
	public Faction getFaction() {
		return this.faction;
	}

	/**
	 * check whether this unit can belong to the given faction
	 * 
	 * @param faction
	 *            the faction to check
	 * @return true if al these are true: the given faction is not null, the
	 *         given faction contains this unit, the current faction of this
	 *         unit equals null | (faction != null && faction.hasAsUnit(this) &&
	 *         this.getFaction() == null)
	 */
	private boolean canHaveAsFaction(Faction faction) {
		return (faction != null && faction.hasAsUnit(this) && this.getFaction() == null);
	}

	/**
	 * set the faction of this unit to the given faction
	 * 
	 * @param faction
	 *            the faction to set to (can be null)
	 * @post the faction of this unit equals the given faction |new.getFaction()
	 *       == faction
	 * @throws IllegalArgumentException
	 *             the faction of this unit cannot be set to the given faction
	 *             except if it equals null | ((faction != null) &&
	 *             (!canHaveAsFaction(faction)))
	 */
	void setFaction(Faction faction) throws IllegalArgumentException {
		if (faction != null)
			if (!canHaveAsFaction(faction))
				throw new IllegalArgumentException();
		this.faction = faction;
	}

	/**
	 * a variable registering the faction this unit belongs to
	 */
	private Faction faction;

	/**
	 * Check whether this unit has the given material as one of its materials.
	 * 
	 * @param material
	 *            The material to check.
	 * @return true if the variable materials contains the given material
	 *         |result == materials.contains(material)
	 */
	@Raw
	boolean hasAsMaterial(@Raw Material material) {
		return materials.contains(material);
	}

	/**
	 * check whether this unit carries at least one log
	 * 
	 * @return true if at least one material of this unit is a log |if (for some
	 *         material in this.materials (material instanceof Log)) | result ==
	 *         true
	 */
	public boolean hasLog() {
		for (Material material : this.materials) {
			if (material instanceof Log)
				return true;
		}
		return false;
	}

	/**
	 * check whether this unit carries at least one boulder
	 * 
	 * @return true if at least one material of this unit is a boulder |if (for
	 *         some material in this.materials (material instanceof Boulder)) |
	 *         result == true
	 */
	public boolean hasBoulder() {
		for (Material material : this.materials) {
			if (material instanceof Boulder)
				return true;
		}
		return false;
	}

	/**
	 * Check whether this unit can have the given material as one of its
	 * materials.
	 * 
	 * @param material
	 *            The material to check.
	 * @return true if the given material does not equal null |result ==
	 *         (material != null)
	 */
	@Raw
	private boolean canHaveAsMaterial(Material material) {
		return (material != null);
	}

	/**
	 * Check whether this unit has proper materials attached to it.
	 * 
	 * @return false if at least one material is not valid or has not this unit
	 *         as owner | if (for some material in materials |
	 *         (!this.canHaveAsMaterial(material) || material.getOwner() !=
	 *         this)) | then result == false
	 */
	@SuppressWarnings("unused")
	private boolean hasProperMaterials() {
		for (Material material : materials) {
			if (!this.canHaveAsMaterial(material))
				return false;
			if (material.getOwner() != this)
				return false;
		}
		return true;
	}

	/**
	 * Return the number of materials associated with this unit.
	 *
	 * @return the number of materials carried by this unit |result ==
	 *         materials.size()
	 */
	public int getNbMaterials() {
		return materials.size();
	}

	/**
	 * Add the given material to the set of materials of this unit.
	 * 
	 * @param material
	 *            The material to be added.
	 * @post This unit has the given material as one of its materials if
	 *       possible |new.hasAsMaterial(material)
	 * @throws IllegalArgumentException
	 *             the material cannot be carried by this unit |
	 *             (!canHaveAsMaterial(material))
	 * @throws IllegalArgumentException
	 *             the unit cannot be the owner of this material |
	 *             (!material.canHaveAsOwner(owner))
	 */
	void addMaterial(@Raw Material material) throws IllegalArgumentException {
		if (!canHaveAsMaterial(material)) {
			throw new IllegalArgumentException();
		}
		this.materials.add(material);
		try {
			material.setOwner(this);
			material.getWorld().removeMaterial(material);
		} catch (IllegalArgumentException e) {
			this.materials.remove(material);
			throw e;
		}
	}

	/**
	 * Remove the given material from the set of materials of this unit.
	 * 
	 * @param material
	 *            The material to be removed.
	 * @post This unit no longer has the given material as one of its materials.
	 *       | ! new.hasAsMaterial(material)
	 * @throws IllegalArgumentException
	 *             the given material is not carried by this unit |
	 *             (!this.hasAsMaterial(material))
	 */
	@Raw
	void removeMaterial(Material material) throws IllegalArgumentException {
		if (!this.hasAsMaterial(material))
			throw new IllegalArgumentException();
		this.materials.remove(material);

		try {
			material.setOwner(null);
		} catch (IllegalArgumentException e) {
			this.materials.add(material);
			throw e;
		}
	}

	/**
	 * Variable registering all the materials carried by this unit.
	 */
	private final Set<Material> materials = new HashSet<Material>();

	// ACTIVITY & BUSY TIME//

	/**
	 * return the activity queue of this unit.
	 */
	@Basic
	private List<Activity> getActivityQueue() {
		return this.activityQueue;
	}

	/**
	 * return the current activity of this unit
	 * 
	 * @return the current activity of this unit | if
	 *         (!this.getActivityQueue().isEmpty()) | then result ==
	 *         this.activityQueue.get(0) | else result == Activity.NONE
	 */
	private Activity getCurrentActivity() {
		if (!this.getActivityQueue().isEmpty())
			return this.activityQueue.get(0);
		else
			return Activity.NONE;
	}

	/**
	 * replace the first activity in the activity queue of this unit by the
	 * given activity, if possible
	 * 
	 * @param activity
	 *            the activity to set
	 * @post do nothing in these cases: unit is resting and cannot stop resting,
	 *       unit is attacking and busyTime>0, unit is falling and cannot stop
	 *       falling |new.getActivityQueue() == this.getActivityQueue()
	 * @post if the activityQueue is not empty, remove the activity on position
	 *       0 and add activity on position 0 and set the busyTime right |if
	 *       (!this.getActivityQueue().isEmpty()) | then new.getActivityQueue()
	 *       == [activity] + this.getActivityQueue().subList(1,end) | &&
	 *       new.getBusyTime == getBusyTimeFor(new.getCurrentActivity)
	 * @post if the activityQueue is empty, add activity on position 0 and set
	 *       the busyTime right |if (this.getActivityQueue().isEmpty()) | then
	 *       new.getActivityQueue == [activity] | && new.getBusyTime ==
	 *       getBusyTimeFor(new.getCurrentActivity)
	 */
	private void setActivity(Activity activity) {
		if (this.getWorld() == null && activity == Activity.NONE)
			this.activityQueue.add(0, activity);
		if (this.isResting() && !this.canStopResting)
			return;
		if (this.isAttacking() && this.getBusyTime() > 0)
			return;
		if (this.isFalling() && !this.canStopFalling())
			return;
		if (!this.getActivityQueue().isEmpty())
			this.activityQueue.remove(0);
		this.activityQueue.add(0, activity);
		this.setBusyTime(this.getBusyTimeFor(this.getCurrentActivity()));
	}

	/**
	 * add a new activity in the first place of the activity queue of this unit,
	 * if possible
	 * 
	 * @param activity
	 *            the activity to insert
	 * @post do nothing in these cases: unit is resting and cannot stop resting,
	 *       unit is attacking and busyTime>0, unit is falling and cannot stop
	 *       falling |if ((this.isResting() && !this.canStopResting) || |
	 *       (this.isAttacking() && this.getBusyTime() > 0) || |
	 *       (this.isFalling() && !this.canStopFalling()) ) |then
	 *       new.getActivityQueue() == this.getActivityQueue()
	 * @post else add activity on position 0 and set the busyTime right |else
	 *       |new.getActivityQueue() == [activity] + this.getActivityQueue() |
	 *       && new.getBusyTime == getBusyTimeFor(new.getCurrentActivity)
	 */
	private void insertActivity(Activity activity) {
		if (this.isResting() && !this.canStopResting)
			return;
		else if (this.isAttacking() && this.getBusyTime() > 0)
			return;
		if (this.isFalling() && !this.canStopFalling())
			return;
		this.activityQueue.add(0, activity);
		this.setBusyTime(this.getBusyTimeFor(this.getCurrentActivity()));
	}

	/**
	 * start a new activity, resetting all parameters if necessary
	 * 
	 * @effect	if the unit has a task, the task will be continued
	 * @effect if the activity queue is empty, NONE is included |if
	 *         (this.getActivityQueue().isEmpty()) | then
	 *         this.setActivity(Activity.NONE)
	 * @effect if the unit is working, set workAtCube to null if the unit is
	 *         moving, set moveToCube en moveToAdjacent to null |if
	 *         (this.isWorking()) | then this.setWorkAtCube(null) |if
	 *         (this.isMoving()) | then this.setMoveToCube(null),
	 *         this.setMoveToAdjacent(null)
	 * @effect if the activity queue consists of one activity, replace it by
	 *         NONE (also resets busyTime) |if (this.getActivityQueue().length()
	 *         == 1) | then this.activityQueue.remove(0), |
	 *         this.setActivity(Activity.NONE)
	 * @effect if the activity queue consists of more than one activity, remove
	 *         the first activity and set the busyTime for the next not-NONE
	 *         activity |if (this.getActivityQueue().length() > 1) | then
	 *         this.activityQueue.remove(0), |
	 *         this.setBusyTime(this.getBusyTimeFor(this.activityQueue.get(0)))
	 */
	public void nextActivity() {
		if (this.getWorld() != null && this.getTask() != null) {
			if (getTask().getActivities().getExecutingStatement() instanceof ActionStatement)
				getTask().getActivities().getExecutingStatement().SetHasFullyExecutedToTrue();
			if (!getTask().hasBeenFullyExecuted()) {
				getTask().execute(this.getWorld().getCounter());
			} else
				nextTask();
		}
		while (true) {
			if (this.getActivityQueue().isEmpty()) {
				this.setActivity(Activity.NONE);
				return;
			} else {
				if (this.isWorking())
					this.setWorkAtCube(null);
				if (this.isMoving()) {
					this.setMoveToCube(null);
					this.setMoveToAdjacent(null);
				}
				if (this.isFollowing())
					this.setFollowUnit(null);
				this.activityQueue.remove(0);
				if (!this.getActivityQueue().isEmpty()) {
					if (this.getActivityQueue().get(0) == Activity.NONE || this.getActivityQueue().get(0) == Activity.FALLING)
						this.activityQueue.remove(0);
					else {
						this.setBusyTime(this.getBusyTimeFor(this.activityQueue.get(0)));
						return;
					}
				}
			}
		}

	}

	/**
	 * return the busyTime for a given activity
	 * 
	 * @param activity
	 *            the activity to calculate the busyTime
	 * @return if the given activity equals working, return 500/strength |if
	 *         (activity == Activity.WORKING) | then result == (500 /
	 *         this.getStrength())
	 * @return if the given activity equals resting, return time to recover one
	 *         hitpoint |if (activity == Activity.RESTING) | then result ==
	 *         Math.max(this.getBusyTime(), 200 * 0.2 / this.getToughness())
	 * @return if the given activity equals attacking, return 1.0 |if (activity
	 *         == Activity.ATTACKING) | then result == 1.0
	 * @return if the given activity equals none of the above, return 0 |if
	 *         (activity != Activity.WORKING) && | (activity !=
	 *         Activity.RESTING) && | (activity != Activity.ATTACKING) | then
	 *         result == 0.0
	 */
	private double getBusyTimeFor(Activity activity) {
		if (activity == Activity.WORKING)
			return (500 / this.getStrength());
		else if (activity == Activity.RESTING)
			return Math.max(this.getBusyTime(), 200 * 0.2 / this.getToughness());
		else if (activity == Activity.ATTACKING)
			return 1.0;
		else
			return 0;
	}

	/**
	 * a variable to store the activity queue of a unit
	 */
	private final List<Activity> activityQueue = new ArrayList<Activity>();

	/**
	 * sets the busytime of this time variable object to the given seconds
	 * 
	 * @post busyTime of this time variable object will equal the given seconds
	 *       if it is a positive value, else busyTime will equal 0
	 * @param busyTime
	 */
	private void setBusyTime(double busyTime) {
		this.busyTime = Math.max(busyTime, 0);
	}

	/**
	 * returns the busytime of this time variable object
	 */
	private double getBusyTime() {
		return this.busyTime;
	}

	/**
	 * substracts the given seconds off of the busytime of this time variable
	 * object.
	 * 
	 * @param seconds
	 */
	private void busyTimeMin(double seconds) {
		this.setBusyTime(this.getBusyTime() - seconds);
	}

	private double busyTime;

	/**
	 * returns whether this unit is defending or not.
	 * 
	 * @result whether this unit is defending or not | result ==
	 *         (this.getCurrentActivity() == Activity.DEFENDING)
	 */
	@SuppressWarnings("unused")
	private boolean isDefending() {
		return (this.getCurrentActivity() == Activity.DEFENDING);
	}

	/**
	 * returns whether this unit is attacking or not.
	 * 
	 * @result whether this unit is attacking or not | result ==
	 *         (this.getCurrentActivity() == Activity.ATTACKING)
	 */
	public boolean isAttacking() {
		return (this.getCurrentActivity() == Activity.ATTACKING);
	}

	/**
	 * returns whether this unit is moving or not.
	 * 
	 * @result whether this unit is moving or not | result ==
	 *         (this.getCurrentActivity() == Activity.WALKING) || |
	 *         (this.getCurrentActivity() == Activity.SPRINTING) || |
	 *         (this.getCurrentActivity() == Activity.FOLLOWING)
	 */
	public boolean isMoving() {
		return (this.getCurrentActivity() == Activity.WALKING) || (this.getCurrentActivity() == Activity.SPRINTING)
				|| (this.getCurrentActivity() == Activity.FOLLOWING);
	}

	/**
	 * returns whether this unit is walking or not.
	 * 
	 * @result whether this unit is walking or not | result ==
	 *         (this.getCurrentActivity() == Activity.WALKING)
	 */
	public boolean isWalking() {
		return (this.getCurrentActivity() == Activity.WALKING);
	}

	/**
	 * returns whether this unit is sprinting or not.
	 * 
	 * @result whether this unit is sprinting or not | result ==
	 *         (this.getCurrentActivity() == Activity.SPRINTING)
	 */
	public boolean isSprinting() {
		return (this.getCurrentActivity() == Activity.SPRINTING);
	}

	public boolean isFollowing() {
		return (this.getCurrentActivity() == Activity.FOLLOWING);
	}

	/**
	 * returns whether this unit is falling or not.
	 * 
	 * @result whether this unit is falling or not | result ==
	 *         (this.getCurrentActivity() == Activity.FALLING)
	 */
	boolean isFalling() {
		return (this.getCurrentActivity() == Activity.FALLING);
	}

	/**
	 * returns whether this unit is working or not.
	 * 
	 * @result whether this unit is working or not | result ==
	 *         (this.getCurrentActivity() == Activity.WORKING)
	 */
	public boolean isWorking() {
		return (this.getCurrentActivity() == Activity.WORKING);
	}

	/**
	 * returns whether this unit is resting or not.
	 * 
	 * @result whether this unit is resting or not | result ==
	 *         (this.getCurrentActivity() == Activity.RESTING)
	 */
	public boolean isResting() {
		return (this.getCurrentActivity() == Activity.RESTING);
	}

	/**
	 * returns whether this unit is doing nothing
	 * 
	 * @result whether this unit is doing nothing | result ==
	 *         (this.getCurrentActivity() == Activity.NONE)
	 */
	boolean isBeingUseless() {
		return (this.getCurrentActivity() == Activity.NONE);
	}

	// ADVANCE TIME en helper methods

	/**
	 * update the attributes of a Unit, based on that Unit's current attributes
	 * and a given duration 'seconds' in seconds of game time
	 * 
	 * @param seconds
	 *            the amount of seconds to advance time
	 * @effect reduce the busyTime of the unit with the given seconds if it is
	 *         not moving, doing nothing or falling
	 * @effect execute the helper method attacking for the given seconds if this
	 *         unit is attacking
	 * @effect execute the helper method falling for the given seconds if this
	 *         unit is falling
	 * @effect execute the helper method moving for the given seconds if this
	 *         unit is moving and getMoveToAdjacent does not equal null
	 * @effect execute the helper method working for the given seconds if this
	 *         unit is working
	 * @effect execute the helper method resting for the given seconds if this
	 *         unit is resting
	 * @effect execute the helper method actingDefault if current activity is
	 *         NONE and default behaviour is enabled
	 * @effect if the unit should fall, insert the activity falling when already
	 *         moving (must be resumed afterwards) or replace the current
	 *         activity by falling when not moving
	 * @effect level up untill experiencePoints - 10 * level <= 10
	 * @effect die if hitpoints equal zero
	 * @throws IllegalArgumentException
	 *             the seconds are not in the interval [0, 0.2]
	 */
	public void advanceTime(float seconds) throws IllegalArgumentException {
		if (!(Util.fuzzyGreaterThanOrEqualTo(seconds, 0) && Util.fuzzyLessThanOrEqualTo(seconds, 0.2)))
			throw new IllegalArgumentException();

		if (!(this.isMoving() || this.isBeingUseless() || this.isFalling())) {
			this.busyTimeMin(seconds);
		}
		
		if (!this.isResting())
			this.setRestTimer(this.getRestTimer() + seconds);
		if (this.getRestTimer() > 180)
			this.restInterrupt();

		if (this.isFalling())
			falling(seconds);

		if (this.isAttacking())
			attacking(seconds);

		if (this.isMoving() && this.getMoveToAdjacent() != null) {
			moving(seconds);
		}

		else if (this.isWorking()) {
			working(seconds);
		}

		else if (this.isResting())
			resting(seconds);

		if (this.isBeingUseless() && this.defaultBehaviour)
			actingDefault(seconds);

		if (this.shouldFall()) {
			if (this.isMoving())
				this.insertActivity(Activity.FALLING);
			else
				this.setActivity(Activity.FALLING);
		}

		while (this.getExperiencePoints() - (10 * this.getLevel()) > 10)
			this.levelUp();

		if (this.getHitpoints() == 0)
			this.die();
	}

	/**
	 * helper method for advance time
	 * 
	 * @note this helper method is only used in advance time and seperately
	 *       defined because of readability therefore it does not contain formal
	 *       documentation
	 * @param seconds
	 *            the seconds to advance time
	 * @effect if busyTime equals zero, initiate the next activity
	 */
	private void attacking(float seconds) {
		if (this.getBusyTime() == 0) {
			this.nextActivity();
		}
	}

	/**
	 * helper method for advance time
	 * 
	 * @note this helper method is only used in advance time and seperately
	 *       defined because of readability therefore it does not contain formal
	 *       documentation
	 * @note next is a position defined as new
	 *       Position(this.getPosition().getRealX(),
	 *       this.getPosition().getRealY(),
	 *       Math.max(this.getPosition().getRealZ() + World.FALLING_VELOCITY *
	 *       seconds, 0.))
	 * @param seconds
	 *            the seconds to advance time
	 * @effect lose 10 hitpoints when falling into a new cube (cannot become
	 *         negative)
	 * @effect set the position to next if possible, otherwise a fraction of
	 *         this change
	 */
	private void falling(float seconds) {
		Position next = new Position(this.getPosition().getRealX(), this.getPosition().getRealY(),
				Math.max(this.getPosition().getRealZ() + ITimeVariableObject.FALLING_VELOCITY * seconds, 0.));
		if (!this.getCube().equals(next.getCube()))
			this.setHitpoints(Math.max(0, this.getHitpoints() - 10));
		try {
			this.setPosition(next);
		} catch (IllegalArgumentException e) {
			if (!next.getCube().equals(this.getCube()) && !next.getCube().equals(this.getMoveToAdjacent())) {
				while (!next.getCube().equals(this.getMoveToAdjacent())) {
					next = new Position(next.getRealX(), next.getRealY(),
							next.getRealZ() - (ITimeVariableObject.FALLING_VELOCITY * seconds / 10));
					try {
						this.setPosition(next);
						break;
					} catch (IllegalArgumentException r) {
						continue;
					}
				}
			}
		}
		if (this.canStopFalling()) {
			this.setPosition(this.getPosition().getCube().getCenter());
			this.nextActivity();
		}
	}

	/**
	 * helper method for advance time
	 * 
	 * @note this helper method is only used in advance time and seperately
	 *       defined because of readability therefore it does not contain formal
	 *       documentation
	 * @param seconds
	 *            the seconds to advance time
	 * @effect reduce stamina points with 10*seconds while sprinting if this
	 *         results in a positive number
	 * @effect while sprinting, set stamina points to zero and stop sprinting if
	 *         current value reduced with 10*seconds results in a negative
	 *         number
	 * @effect set the orientation of the unit in the movement direction
	 * @effect if the centre of the next cube is reached, do these things: set
	 *         the position to the centre of this target cube, set the target
	 *         cube to null, increment the experience points with 1, find next
	 *         cube in path if moving to a cube further away, stop moving when
	 *         not pathfinding
	 * @effect if the centre of the next cube is not reached yet, set the
	 *         position to next if possible, otherwise a fraction of this change
	 * @note next is a position defined as new
	 *       Position(this.getPosition().getRealX() + xVelocity * seconds,
	 *       this.getPosition().getRealY() + yVelocity * seconds,
	 *       this.getPosition().getRealZ() + zVelocity * seconds)
	 */
	private void moving(float seconds) {

		if (this.isSprinting()) {
			double stamina = this.getStaminaPoints() - seconds * 10;
			if (stamina > 0)
				this.setStaminaPoints(stamina);
			else {
				this.setStaminaPoints(0);
				this.stopSprinting();
			}
		}

		double moveDiffX = this.getMoveToAdjacent().getCenter().getRealX() - this.getPosition().getRealX();
		double moveDiffY = this.getMoveToAdjacent().getCenter().getRealY() - this.getPosition().getRealY();
		double moveDiffZ = this.getMoveToAdjacent().getCenter().getRealZ() - this.getPosition().getRealZ();

		double moveDistance = Math.sqrt(moveDiffX * moveDiffX + moveDiffY * moveDiffY + moveDiffZ * moveDiffZ);

		double xVelocity = this.getMovementSpeed() * moveDiffX / moveDistance;
		double yVelocity = this.getMovementSpeed() * moveDiffY / moveDistance;
		double zVelocity = this.getMovementSpeed() * moveDiffZ / moveDistance;

		Position next = new Position(this.getPosition().getRealX() + xVelocity * seconds,
				this.getPosition().getRealY() + yVelocity * seconds,
				this.getPosition().getRealZ() + zVelocity * seconds);

		this.setOrientation(Math.atan2(yVelocity, xVelocity));

		double moveDiffNextX = this.getMoveToAdjacent().getCenter().getRealX() - next.getRealX();
		double moveDiffNextY = this.getMoveToAdjacent().getCenter().getRealY() - next.getRealY();
		double moveDiffNextZ = this.getMoveToAdjacent().getCenter().getRealZ() - next.getRealZ();

		if ((moveDiffX == 0 || Math.signum(moveDiffX) != Math.signum(moveDiffNextX))
				&& (moveDiffY == 0 || Math.signum(moveDiffY) != Math.signum(moveDiffNextY))
				&& (moveDiffZ == 0 || Math.signum(moveDiffZ) != Math.signum(moveDiffNextZ))) {
			this.setPosition(this.getMoveToAdjacent().getCenter());
			this.setMoveToAdjacent(null);
			this.setExperiencePoints(this.getExperiencePoints() + 1);

			// Check whether the unit is moving to a cube far away (not
			// an adjacent cube)
			if (this.getMoveToCube() != null) {
				findNextCubeInPath();
			}

			// Check whether the unit is not pathfinding. If this is
			// true, it stops moving.
			if (this.getMoveToCube() == null) {
				this.nextActivity();
			}
		} else {
			try {
				this.setPosition(next);
			} catch (IllegalArgumentException e) {
				if (!next.getCube().equals(this.getCube()) && !next.getCube().equals(this.getMoveToAdjacent())) {
					while (!next.getCube().equals(this.getMoveToAdjacent())) {
						next = new Position(next.getRealX() + (xVelocity * seconds / 10),
								next.getRealY() + (yVelocity * seconds / 10),
								next.getRealZ() + (zVelocity * seconds / 10));
						try {
							this.setPosition(next);
							break;
						} catch (IllegalArgumentException r) {
							continue;
						}
					}
				}
			}
		}

		if (this.isFollowing()) {
			if (this.getFollowUnit() == null || this.getFollowUnit().isDead()) {
				nextActivity();
			} else
				this.setMoveToCube(this.getFollowUnit().getCube());
		}

	}

	/**
	 * helper method for advance time
	 * 
	 * @note this helper method is only used in advance time and seperately
	 *       defined because of readability therefore it does not contain formal
	 *       documentation
	 * @param seconds
	 *            the seconds to advance time
	 * @effect do nothing if busyTime is not yet zero
	 * @effect if busyTime equals zero do one of the following effects, initiate
	 *         the next activity and increase the experience points by ten
	 *         points
	 * @note in the following 6 effects only the first case for which all
	 *       conditions are met shall be executed (i.e., switch-case semantics)
	 * @effect if busyTime equals zero and the unit carries a material that can
	 *         be dropped on the workAtCube, add it to the world at the centre
	 *         of that cube
	 * @effect if busyTime equals zero and the workAtCube is a workshop and at
	 *         least one boulder and one log are available on that cube, weight
	 *         and toughness increase while consuming these two materials
	 * @effect if busyTime equals zero and a boulder is present on the
	 *         workAtCube, pick up the boulder
	 * @effect if busyTime equals zero and a log is present on the workAtCube,
	 *         pick up the log
	 * @effect if busyTime equals zero and the workAtCube is wood it collapses
	 * @effect if busyTime equals zero and the workAtCube is a rock it collapses
	 */
	private void working(float seconds) {
		if (this.getBusyTime() == 0) {

			if (this.getNbMaterials() > 0 && this.getWorkAtCube().getCenter().isValidForObjectIn(this.getWorld())) {
				Iterator<Material> iter = this.materials.iterator();
				Material material = (Material) iter.next();
				this.getWorld().addMaterial(material, this.getWorkAtCube().getCenter());

			} else if (this.getWorld().getTerrainType(this.getWorkAtCube()) == TerrainType.WORKSHOP
					&& this.getWorld().getBouldersIn(this.getWorkAtCube()).size() > 0
					&& this.getWorld().getLogsIn(this.getWorkAtCube()).size() > 0) {
				Iterator<Boulder> iterBoulder = this.getWorld().getBouldersIn(this.getWorkAtCube()).iterator();
				Boulder boulder = (Boulder) iterBoulder.next();
				Iterator<Log> iterLog = this.getWorld().getLogsIn(this.getWorkAtCube()).iterator();
				Log log = (Log) iterLog.next();
				int extraWeight = (log.getWeight() + boulder.getWeight()) / 2;
				int extraToughness = extraWeight / 10;
				this.setToughness(this.getToughness() + extraToughness);
				this.setWeight(this.getWeight() + extraWeight);
				this.getWorld().removeMaterial(boulder);
				this.getWorld().removeMaterial(log);

			} else if (this.getWorld().getBouldersIn(this.getWorkAtCube()).size() > 0) {
				Iterator<Boulder> iterBoulder = this.getWorld().getBouldersIn(this.getWorkAtCube()).iterator();
				Boulder boulder = (Boulder) iterBoulder.next();
				this.addMaterial(boulder);

			} else if (this.getWorld().getLogsIn(this.getWorkAtCube()).size() > 0) {
				Iterator<Log> iterLog = this.getWorld().getLogsIn(this.getWorkAtCube()).iterator();
				Log log = (Log) iterLog.next();
				this.addMaterial(log);

			} else if (this.getWorld().getTerrainType(this.getWorkAtCube()) == TerrainType.WOOD
					|| this.getWorld().getTerrainType(this.getWorkAtCube()) == TerrainType.ROCK) {
				this.getWorld().collapse(this.getWorkAtCube());

			}
			this.nextActivity();
			this.setExperiencePoints(this.getExperiencePoints() + 10);
		}
	}

	/**
	 * helper method for advance time
	 * 
	 * @note this helper method is only used in advance time and seperately
	 *       defined because of readability therefore it does not contain formal
	 *       documentation
	 * @param seconds
	 *            the seconds to advance time
	 * @effect if the maximum number of hitpoints is not yet reached, increase
	 *         it by seconds * this.getToughness() / (200 * 0.2)
	 * @effect if the maximum number of hitpoints is reached and the maximum
	 *         number of stamina points is not yet reached, increase it by
	 *         seconds * this.getToughness() / (100 * 0.2)
	 * @effect if the maximum numbers of both hitpoints and stamina points are
	 *         reached, initiate the next activity
	 * @post if busyTime equals zero, new.canStopResting equals true
	 */
	private void resting(float seconds) {
		this.setRestTimer(0);
		if (this.getHitpoints() != this.getMaxHitpoints()) {
			double hitpoints = this.getHitpoints() + seconds * this.getToughness() / (40);
			this.setHitpoints(Math.min(hitpoints, this.getMaxHitpoints()));
		} else if (this.getStaminaPoints() != this.getMaxStaminaPoints()) {
			double stamina = this.getStaminaPoints() + seconds * this.getToughness() / (20);
			this.setStaminaPoints(Math.min(stamina, this.getMaxStaminaPoints()));
		} else {
			this.nextActivity();
		}

		if (this.getBusyTime() == 0) {
			this.canStopResting = true;
		}
	}

	/**
	 * helper method for advance time
	 * 
	 * @note this helper method is only used in advance time and seperately
	 *       defined because of readability therefore it does not contain formal
	 *       documentation
	 * @param seconds
	 *            the seconds to advance time
	 */
	private void actingDefault(float seconds) {

		if (getFaction().getScheduler().getHighestPriorityTaskNotExecuted() != null) {
			nextTask();
		}

		else
			doRandomBehaviour();
	}

	/**
	 * unit will start the next task from the scheduler
	 */
	private void nextTask() {
		if (getTask() != null && getTask().hasBeenFullyExecuted()) {
			getTask().reset();
			getFaction().getScheduler().removeTask(getTask());
		} else {
			getTask().reset();
			getTask().setPriority(getTask().getPriority() - 1);
		}
		setTask(getFaction().getScheduler().getHighestPriorityTaskNotExecuted());
		getTask().setUnit(this);
		if (this.getWorld() != null)
			getTask().execute(this.getWorld().getCounter());
	}

	/**
	 * choose a random activity for this unit
	 */
	private void doRandomBehaviour() {
		int randomFight = RANDOM_GEN.nextInt(4);
		if (randomFight == 0 && this.getPotentialEnemies().size() != 0) {
			Set<Unit> potentialEnemies = this.getPotentialEnemies();
			ArrayList<Unit> potentialEnemiesArray = new ArrayList<Unit>(potentialEnemies);
			this.attack(potentialEnemiesArray.get(RANDOM_GEN.nextInt(potentialEnemiesArray.size())));
		} else {

			int randomWork = RANDOM_GEN.nextInt(3);
			Set<Cube> allNeighbouringCubes = this.getCube().getAllNeighbouringCubes(this.getWorld());
			allNeighbouringCubes.add(this.getCube());
			for (Cube cube : new HashSet<Cube>(allNeighbouringCubes)) {
				if (!cube.isWorkableCubeInBy(this.getWorld(), this))
					allNeighbouringCubes.remove(cube);
			}
			if (randomWork == 0 && allNeighbouringCubes.size() != 0) {
				ArrayList<Cube> allNeighbouringCubesArray = new ArrayList<Cube>(allNeighbouringCubes);
				this.workAt(allNeighbouringCubesArray.get(RANDOM_GEN.nextInt(allNeighbouringCubesArray.size())));
			} else {

				int randomMoveOrRest = RANDOM_GEN.nextInt(2);
				if (randomMoveOrRest == 0 && (this.getStaminaPoints() != this.getMaxStaminaPoints()
						|| this.getHitpoints() != this.getMaxHitpoints())) {
					this.rest();
					double hitpointsTime = (this.getMaxHitpoints() - this.getHitpoints()) * 200 * 0.2
							/ this.getToughness();
					double staminaTime = (this.getMaxStaminaPoints() - this.getStaminaPoints()) * 100 * 0.1
							/ this.getToughness();
					this.setBusyTime(hitpointsTime + staminaTime);

				} else {
					Set<Cube> allCubes = this.getWorld().getAllCubes();
					for (Cube cube : new HashSet<Cube>(allCubes)) {
						if (!cube.getCenter().isStableForUnitIn(this.getWorld()))
							allCubes.remove(cube);
					}
					ArrayList<Cube> allCubesArray = new ArrayList<Cube>(allCubes);
					moveTo(allCubesArray.get(RANDOM_GEN.nextInt(allCubesArray.size())));
				}
			}
		}
	}

	/**
	 * collect a set of all potential enemies for this unit
	 * 
	 * @return a set of all non-falling units from another faction in same or
	 *         adjacent cubes
	 */
	private Set<Unit> getPotentialEnemies() {
		Set<Cube> sameOrAdjacentCubes = this.getCube().getAllAdjacentCubes(this.getWorld());
		sameOrAdjacentCubes.add(this.getCube());
		Set<Unit> enemiesSoFar = new HashSet<Unit>();
		for (Cube cube : sameOrAdjacentCubes) {
			Set<Unit> unitsInCube = this.getWorld().getUnitsInCube(cube);
			for (Unit unit : unitsInCube) {
				if (this.getFaction() != unit.getFaction() && !unit.isFalling())
					enemiesSoFar.add(unit);
			}
		}
		return enemiesSoFar;
	}

	/**
	 * the unit will increase its level when necessary and randomly agility, strength or toughness
	 */
	private void levelUp() {
		if (this.getAgility() != MAX_VAL_PRIMARY_ATTRIBUTE || this.getStrength() != MAX_VAL_PRIMARY_ATTRIBUTE
				|| this.getToughness() != MAX_VAL_PRIMARY_ATTRIBUTE) {
			boolean leveledUp = false;
			while (!leveledUp) {
				int randomGetal = RANDOM_GEN.nextInt(3);
				if (randomGetal == 0) {
					if (this.getAgility() != MAX_VAL_PRIMARY_ATTRIBUTE) {
						this.setAgility(this.getAgility() + 1);
						leveledUp = true;
					}
				} else if (randomGetal == 1) {
					if (this.getStrength() != MAX_VAL_PRIMARY_ATTRIBUTE) {
						this.setStrength(this.getStrength() + 1);
						leveledUp = true;
					}
				} else {
					if (this.getToughness() != MAX_VAL_PRIMARY_ATTRIBUTE) {
						this.setToughness(this.getToughness() + 1);
						leveledUp = true;
					}
				}
			}
		}
		this.setLevel(this.getLevel() + 1);
	}

	// MOVING AND PATH FINDING//

	/**
	 * Moves this unit to an adjacent cube.
	 * 
	 * @param x
	 *            the relative position on de x-axis
	 * @param y
	 *            the relative position on de y-axis
	 * @param z
	 *            the relative position on de z-axis
	 * @throws IllegalArgumentException
	 *             when the x, y and z are not referring to an adjacent cube.
	 */
	public void moveToAdjacent(int x, int y, int z) throws IllegalArgumentException {
		if (this.getMoveToAdjacent() != null) {
			return;
		}

		if (!(((x == -1) || (x == 0) || (x == 1)) && ((y == -1) || (y == 0) || (y == 1))
				&& ((z == -1) || (z == 0) || (z == 1))))
			throw new IllegalArgumentException();

		Cube moveToAdjacent = new Cube(this.getPosition().getCube().getX() + x, this.getPosition().getCube().getY() + y,
				this.getPosition().getCube().getZ() + z);

		if (moveToAdjacent.getCenter().isStableForUnitIn(this.getWorld())) {
			if (!(this.isMoving())) {
				this.setActivity(Activity.WALKING);
			}

			this.setMoveToAdjacent(moveToAdjacent);
		}
	}

	/**
	 * return the adjacent cube to move to
	 */
	@Basic
	private Cube getMoveToAdjacent() {
		return this.moveToAdjacent;
	}

	/**
	 * set moveToAdjacent to the given cube if possible
	 * 
	 * @param cube
	 *            the cube to set to
	 * @post if the given cube equals null or is valid in the world this unit
	 *       belongs to, new.moveToAdjacent will equal the given cube |if (cube
	 *       == null || cube.isValidIn(this.getWorld())) | then
	 *       new.getMoveToAdjacent == cube
	 */
	private void setMoveToAdjacent(Cube cube) {
		if (cube == null || cube.isValidIn(this.getWorld()))
			this.moveToAdjacent = cube;
	}

	/**
	 * a variable that contains the next adjacent cube in the path to reach the
	 * moveToCube
	 */
	private Cube moveToAdjacent;

	/**
	 * returns the movement speed in accordance with the units activity status
	 * and primary attributes.
	 * 
	 * @return if falling, return 3.0 |if (this.isFalling()) | then result ==
	 *         3.0
	 * @return if not falling, climbing or sprinting (i.e. ordinary walking),
	 *         return 1.5 * (strength+agility)/(2*totalWeight)
	 * 
	 *         |result == 1.5 * (this.getStrength() + this.getAgility()) / (2 *
	 *         this.getTotalWeight())
	 * @return if sprinting, return 2*walking speed |if (this.isSprinting()) |
	 *         then result == 1.5 * (this.getStrength() + this.getAgility()) /
	 *         (this.getTotalWeight())
	 * @return if climbing up, return 0.5*walking speed |if
	 *         (this.getCube().getZ() - this.getMoveToAdjacent().getZ() == -1) |
	 *         then result == 0.75 * (this.getStrength() + this.getAgility()) /
	 *         (2 * this.getTotalWeight())
	 * @return if climbing down, return 1.2*walking speed |if
	 *         (this.getCube().getZ() - this.getMoveToAdjacent().getZ() == 1) |
	 *         then result == 1.2*1.5 * (this.getStrength() + this.getAgility())
	 *         / (2 * this.getTotalWeight())
	 * @return if climbing up while sprinting, return 2*0.5*walking speed |if
	 *         (this.getCube().getZ() - this.getMoveToAdjacent().getZ() == -1) |
	 *         then result == 1.5 * (this.getStrength() + this.getAgility()) /
	 *         (2 * this.getTotalWeight())
	 * @return if climbing down while sprinting, return 2*1.2*walking speed |if
	 *         (this.getCube().getZ() - this.getMoveToAdjacent().getZ() == 1) |
	 *         then result == 2.4*1.5 * (this.getStrength() + this.getAgility())
	 *         / (2 * this.getTotalWeight())
	 */
	public double getMovementSpeed() {
		if (this.isFalling())
			return ITimeVariableObject.FALLING_VELOCITY * (-1);
		double speed = 1.5 * (this.getStrength() + this.getAgility()) / (2 * this.getTotalWeight());
		if (this.isSprinting())
			speed *= 2;
		if (this.getMoveToAdjacent() != null) {
			int zDiff = this.getCube().getZ() - this.getMoveToAdjacent().getZ();
			if (zDiff == -1)
				speed *= 0.5;
			else if (zDiff == 1)
				speed *= 1.2;
		}
		return speed;
	}

	/**
	 * start moving to a cube further away.
	 * 
	 * @param cube
	 *            the cube to move to
	 * @effect set moveToCube to the given cube |this.setMoveToCube(cube);
	 * @effect find next cube in path |findNextCubeInPath();
	 */
	public void moveTo(Cube cube) {
		this.setMoveToCube(cube);
		findNextCubeInPath();
	}

	/**
	 * Moves this unit to the next cube when it's pathfinding to a cube far
	 * away.
	 * 
	 * @effect initiate the next activity when the moveToCube is reached |if
	 *         (this.getCube().equals(this.getMoveToCube())) | then
	 *         this.nextActivity();
	 * @effect if the moveToCube is not yet reached, move to the calculated next
	 *         cube(x,y,z) in path and initiate the next activity when the
	 *         moveToCube is reached after this |if
	 *         !(this.getCube().equals(this.getMoveToCube())) | then
	 *         (this.moveToAdjacent(x, y, z), if (current ==
	 *         this.getMoveToCube()) | then this.nextActivity())
	 */
	private void findNextCubeInPath() {
		if (this.getCube().equals(this.getMoveToCube())) {
			this.nextActivity();
			return;
		}
		Set<Cube> allCubes = this.getWorld().getAllCubes();
		for (Cube cube : new HashSet<Cube>(allCubes)) {
			if (!this.canHaveAsPosition(cube.getCenter()) || !cube.getCenter().isStableForUnitIn(this.getWorld()))
				allCubes.remove(cube);
		}

		Cube current = this.getCube();
		double currentDistance = 0;
		Map<Cube, Double> unvisited = new HashMap<Cube, Double>();
		for (Cube cube : allCubes) {
			if (cube.equals(this.getCube())) {
				unvisited.put(cube, currentDistance);
				current = cube;
			} else
				unvisited.put(cube, Double.MAX_VALUE);
		}
		Map<Cube, Double> visited = new HashMap<Cube, Double>();
		Map<Cube, Cube> shortestPath = new HashMap<Cube, Cube>();
		Queue<DistPair<Cube>> queue = new PriorityQueue<DistPair<Cube>>();

		while (currentDistance != Double.MAX_VALUE) {
			if (current.equals(this.getMoveToCube())) {
				while (!shortestPath.get(current).equals(this.getCube()))
					current = shortestPath.get(current);
				int x = current.getX() - this.getCube().getX();
				int y = current.getY() - this.getCube().getY();
				int z = current.getZ() - this.getCube().getZ();
				this.moveToAdjacent(x, y, z);
				if (current == this.getMoveToCube()) {
					this.nextActivity();
				}
				return;
			}

			for (Cube cube : unvisited.keySet()) {
				if (cube.isSameOrAdjacentCube(current) && !cube.equals(current)) {
					double weight = current.getDistanceWeightTo(cube);
					double newDistance = currentDistance + weight;
					if (newDistance < unvisited.get(cube)) {
						unvisited.put(cube, newDistance);
						shortestPath.put(cube, current);
						queue.offer(new DistPair<Cube>(cube, newDistance));
					}
				}
			}

			visited.put(current, currentDistance);
			unvisited.remove(current);

			while (!queue.isEmpty() && visited.containsKey(queue.peek().getThing()))
				queue.poll();

			// No next node -> no path
			if (queue.isEmpty())
				return;
			DistPair<Cube> next = queue.poll();
			current = next.getThing();
			currentDistance = next.getDistance();
		}

		return;
	}

	/**
	 * returns the cube far away where this unit is going.
	 */
	@Basic
	private Cube getMoveToCube() {
		return this.moveToCube;
	}

	/**
	 * set moveToCube to the given cube, if possible
	 * 
	 * @param cube
	 *            the cube to set to
	 * @post if the given cube equals null or is stable for a unit in the world
	 *       this unit belongs to, new.moveToCube will equal the given cube |if
	 *       (cube == null ||
	 *       cube.getCenter().isStableForUnitIn(this.getWorld())) | then
	 *       new.getMoveToCube == cube
	 */
	private void setMoveToCube(Cube cube) {
		if (cube == null || cube.getCenter().isStableForUnitIn(this.getWorld())) {
			this.moveToCube = cube;
		}
	}

	/**
	 * a variable storing the cube far away this unit is moving to
	 */
	private Cube moveToCube;

	// SPECIAL FORMS OF MOVING: sprinting, falling and following//

	/**
	 * start sprinting if this unit is already walking and has stamina points
	 * 
	 * @effect if this unit is walking and a positive amount of stamina points,
	 *         the activity of this unit is set to sprinting |if
	 *         (this.isWalking() && this.getStaminaPoints() > 0) | then
	 *         this.setActivity(Activity.SPRINTING)
	 */
	public void startSprinting() {
		if (this.isWalking() && this.getStaminaPoints() > 0)
			this.setActivity(Activity.SPRINTING);
	}

	/**
	 * stop sprinting if this unit is already sprinting
	 * 
	 * @effect if this unit is sprinting, the activity of this unit is set to
	 *         sprinting |if (this.isSprinting()) | then
	 *         this.setActivity(Activity.WALKING)
	 */
	public void stopSprinting() {
		if (this.isSprinting())
			this.setActivity(Activity.WALKING);
	}

	/**
	 * check whether this unit should fall in its world
	 * 
	 * @return false if the position of this unit is stable |result ==
	 *         !this.getPosition().isStableForUnitIn(this.getWorld())
	 */
	private boolean shouldFall() {
		return !this.getPosition().isStableForUnitIn(this.getWorld());
	}

	/**
	 * check whether this unit should fall if it is located in the given cube
	 * 
	 * @param cube
	 *            the cube to check whether a unit located in it should fall
	 * @return false if the centre-position of the given cube is stable |result
	 *         == !cube.getCenter().isStableForUnitIn(this.getWorld())
	 */
	private boolean shouldStartFallingAt(Cube cube) {
		return !cube.getCenter().isStableForUnitIn(this.getWorld());
	}

	/**
	 * check whether this unit can stop falling
	 */
	private boolean canStopFalling() {
		if (this.isFalling()) {
			if (this.getCube().getZ() == 0) {
				return true;
			}
			Cube cubeBelow = new Cube(this.getCube().getX(), this.getCube().getY(), this.getCube().getZ() - 1);
			if (cubeBelow.isPassableIn(this.getWorld()))
				return true;
		}
		return false;
	}
	
	/**
	 * check whether this unit can stop falling on the given position
	 */
	private boolean canStopFallingAt(Position position) {
		if (this.isFalling()) {
			if (position.getCube().getZ() == 0) {
				return true;
			}
			Cube cubeBelow = new Cube(position.getCube().getX(), position.getCube().getY(), position.getCube().getZ() - 1);
			if (cubeBelow.isPassableIn(this.getWorld()))
				return true;
		}
		return false;
	}
	

	/**
	 * this unit will start following the other unit.
	 * 
	 * @param other
	 *            the other unit that will be followed by this unit.
	 */
	public void follow(Unit other) {
		this.setFollowUnit(other);
		this.setActivity(Activity.FOLLOWING);
		moveTo(getFollowUnit().getCube());
	}

	/**
	 * return the unit that is followed
	 */
	@Basic
	private Unit getFollowUnit() {
		return this.followUnit;
	}

	/**
	 * set the followUnit to the given unit
	 * 
	 * @param other
	 *            the unit to follow
	 * @post the unit to be followed equals the given unit |new.getFollowUnit()
	 *       == other
	 */
	private void setFollowUnit(Unit other) {
		this.followUnit = other;
	}

	/**
	 * a variable registering the unit that must be followed
	 */
	private Unit followUnit;

	// DEFAULT BEHAVIOUR//

	/**
	 * starts the default behaviour of this unit
	 * 
	 * @post the flag defaultBehaviour equals true |new.defaultBehaviour == true
	 */
	public void startDefaultBehaviour() {
		this.defaultBehaviour = true;
	}

	/**
	 * stops the default behaviour of this unit
	 * 
	 * @post the flag defaultBehaviour equals false |new.defaultBehaviour ==
	 *       false
	 */
	public void stopDefaultBehaviour() {
		this.defaultBehaviour = false;
	}

	/**
	 * returns whether the default behaviour can be started
	 * 
	 * @return true if the flag defaultBehaviour equals false |result ==
	 *         (!this.defaultBehaviour)
	 */
	public boolean canStartDefaultBehaviour() {
		return (!this.defaultBehaviour);
	}

	/**
	 * a flag registering if default behaviour is enabled
	 */
	private boolean defaultBehaviour;

	// WORKING//

	/**
	 * this unit starts working at the given cube
	 * 
	 * @param cube
	 *            the cube to work at
	 * @effect if this unit can work on the given cube, then set the workAtCube
	 *         to the given cube, set the activity of this unit to working and
	 *         set the orientation to a visually logic value |if
	 *         (this.canHaveAsWorkAtCube(cube)) | then this.setWorkAtCube(cube),
	 *         this.setActivity(Activity.WORKING), |
	 *         this.setOrientation(Math.atan2(cube.getCenter().getRealY() -
	 *         this.getPosition().getRealY(), | cube.getCenter().getRealX() -
	 *         this.getPosition().getRealX()));
	 */
	public void workAt(Cube cube) {
		if (this.canHaveAsWorkAtCube(cube)) {
			this.setWorkAtCube(cube);
			this.setActivity(Activity.WORKING);
			this.setOrientation(Math.atan2(cube.getCenter().getRealY() - this.getPosition().getRealY(),
					cube.getCenter().getRealX() - this.getPosition().getRealX()));
		}
	}

	/**
	 * return the cube that is currently worked at
	 */
	@Basic
	private Cube getWorkAtCube() {
		return this.workAtCube;
	}

	/**
	 * check whether this unit can work on the given cube
	 * 
	 * @param cube
	 *            the cube to check if there can be worked on by this unit
	 * @return true if the given cube does not equal null, the given cube is the
	 *         same or a neighbouring cube to the cube this unit is located and
	 *         the cube is workable by this unit |result == (cube != null &&
	 *         this.getCube().isSameOrNeighbouringCube(cube) | &&
	 *         cube.isWorkableCubeInBy(this.getWorld(), this))
	 */
	private boolean canHaveAsWorkAtCube(Cube cube) {
		return (cube != null && this.getCube().isSameOrNeighbouringCube(cube)
				&& cube.isWorkableCubeInBy(this.getWorld(), this));
	}

	/**
	 * set the workAtCube to the given cube if possible
	 * 
	 * @param cube
	 *            the cube to set to (may be null)
	 * @post if the given cube equals null, the workAtCube will be the given
	 *       cube |if (cube != null) | then new.getWorkAtCube == cube
	 * @post if the given cube does not equal null and this unit cannot have the
	 *       given cube as workAtCube, then do nothing |this.workAtCube ==
	 *       new.workAtCube
	 * @post if the given cube does not equal null and this unit can have the
	 *       given cube as workAtCube, then workAtCube will equal the given cube
	 *       |if (cube != null) && (!this.canHaveAsWorkAtCube(cube))
	 */
	private void setWorkAtCube(Cube cube) {
		if (cube != null) {
			if (!this.canHaveAsWorkAtCube(cube))
				return;
		}
		this.workAtCube = cube;
	}

	/**
	 * a variable registering the cube this unit is working on
	 */
	private Cube workAtCube;

	/**
	 * get this unit working
	 * 
	 * @effect set activity of this unit to working
	 *         |this.setActivity(Activity.WORKING)
	 */
	@Deprecated
	public void work() {
		this.setActivity(Activity.WORKING);
	}

	// RESTING//

	/**
	 * get this unit resting
	 * 
	 * @effect set activity of this unit to resting
	 *         |this.setActivity(Activity.RESTING)
	 * @post if this unit is already resting, the flag canStopResting will equal
	 *       false |if (this.isResting()) | then new.canStopResting == false
	 */
	public void rest() {
		this.setActivity(Activity.RESTING);
		if (this.isResting())
			this.canStopResting = false;
	}
	
	public void restInterrupt() {
		this.insertActivity(Activity.RESTING);
		if (this.isResting())
			this.canStopResting = false;
	}
	
	public double getRestTimer() {
		return this.restTimer;
	}
	
	public void setRestTimer(double time) {
		if (time < 0)
			this.restTimer = 0;
		else
			this.restTimer = time;
	}
	
	private double restTimer = 0;

	/**
	 * a flag registering if this unit can stop resting
	 */
	private boolean canStopResting;

	// ATTACK AND DEFEND//

	/**
	 * attack the given unit
	 * 
	 * @param defender
	 *            the unit to be attacked by this unit
	 * @effect if it is close enough to the defender and the defender is not
	 *         falling and the defender belongs to another faction, then set the
	 *         activity of this unit to attacking, set the orientation to a
	 *         visually logic value and make the other unit defend if setting
	 *         the activity of this unit was successfull |if
	 *         (this.getCube().isSameOrAdjacentCube(defender.getCube()) | &&
	 *         !defender.isFalling() && this.getFaction() !=
	 *         defender.getFaction()) | then
	 *         this.setActivity(Activity.ATTACKING), |
	 *         this.setOrientation(Math.atan2(defender.getPosition().getRealY()
	 *         - this.getPosition().getRealY(), |
	 *         defender.getPosition().getRealX() -
	 *         this.getPosition().getRealX())), | (if (this.isAttacking()) then
	 *         defender.defend(this))
	 */
	public void attack(Unit defender) {
		if (this.getCube().isSameOrAdjacentCube(defender.getCube()) && !defender.isFalling()
				&& this.getFaction() != defender.getFaction()) {
			this.setActivity(Activity.ATTACKING);
			if (this.isAttacking())
				defender.defend(this);
			this.setOrientation(Math.atan2(defender.getPosition().getRealY() - this.getPosition().getRealY(),
					defender.getPosition().getRealX() - this.getPosition().getRealX()));
		}
	}

	/**
	 * this unit starts defending by dodging, blocking or taking dammage
	 * 
	 * @param attacker
	 *            the unit to defend from
	 */
	public void defend(Unit attacker) {
		boolean succeeded = true;
		// dodging
		if (RANDOM_GEN.nextDouble() < 0.2 * this.getAgility() / attacker.getAgility()) {
			Set<Cube> allAdjacentCubes = this.getCube().getAllAdjacentCubes(this.getWorld());
			for (Cube cube : new HashSet<Cube>(allAdjacentCubes)) {
				if (!cube.getCenter().isValidForObjectIn(this.getWorld()))
					allAdjacentCubes.remove(cube);
			}
			ArrayList<Cube> allAdjacentCubesArray = new ArrayList<Cube>(allAdjacentCubes);
			this.setPosition((allAdjacentCubesArray.get(RANDOM_GEN.nextInt(allAdjacentCubesArray.size())).getCenter()));
			// blocking
		} else if (RANDOM_GEN.nextDouble() < 0.25 * (this.getStrength() + this.getAgility())
				/ (attacker.getStrength() + attacker.getAgility())) {
			// taking damage
		} else {
			this.setHitpoints(attacker.getStrength() / 10);
			succeeded = false;
		}
		this.setOrientation(Math.atan2(attacker.getPosition().getRealY() - this.getPosition().getRealY(),
				attacker.getPosition().getRealX() - this.getPosition().getRealX()));
		if (succeeded)
			this.setExperiencePoints(this.getExperiencePoints() + 20);
		else
			attacker.setExperiencePoints(attacker.getExperiencePoints() + 20);
	}

	// TERMINATION//

	/**
	 * Terminate this unit.
	 *
	 * @post This unit is terminated. |new.isDead()
	 * @effect The unit is removed from its world and faction
	 *         |this.getWorld().removeUnit(this),
	 *         this.getFaction().removeUnit(this)
	 * @effect The materials the unit was carrying are left behind at the exact
	 *         same position the unit has died. |for each material in
	 *         this.materials | this.getWorld().addMaterial(material,
	 *         this.getPosition())
	 */
	void die() {
		for (Material material : this.materials) {
			this.getWorld().addMaterial(material, this.getPosition());
		}
		this.getWorld().removeUnit(this);
		this.getFaction().removeUnit(this);
		this.isDead = true;
	}

	/**
	 * Return a boolean indicating whether or not this unit is terminated.
	 */
	@Basic
	@Raw
	public boolean isDead() {
		return this.isDead;
	}

	/**
	 * Variable registering whether this unit is terminated, initially false
	 */
	private boolean isDead = false;

	/**
	 * Return the task of this unit.
	 */
	@Basic
	@Raw
	public Task getTask() {
		return this.task;
	}

	/**
	 * Check whether the given task is a valid task for any unit.
	 * 
	 * @param task
	 *            The task to check.
	 * @return | result == (task != null)
	 */
	private static boolean isValidTask(Task task) {
		return (task != null);
	}

	/**
	 * Set the task of this unit to the given task.
	 * 
	 * @param task
	 *            The new task for this unit.
	 * @post The task of this new unit is equal to the given task. |
	 *       new.getTask() == task
	 * @throws IllegalArgumentException
	 *             The given task is not a valid task for any unit. | !
	 *             isValidTask(getTask())
	 */
	@Raw
	public void setTask(Task task) throws IllegalArgumentException {
		if (task != null) {
			if (!isValidTask(task))
				throw new IllegalArgumentException();
		}
		this.task = task;
	}

	/**
	 * Variable registering the task of this unit.
	 */
	private Task task;

}
