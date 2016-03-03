/** TO BE ADDED TO CLASS HEADING
 * @invar  The name of each unit must be a valid name for any
 *         unit.
 *       | isValidName(getName())
 */


/**
 * Initialize this new unit with given name.
 *
 * @param  Name
 *         The name for this new unit.
 * @effect The name of this new unit is set to
 *         the given name.
 *       | this.setName(Name)
 */
public Unit(String Name)
		throws IllegalNameException {
	this.setName(Name);
}


/**
 * Return the name of this unit.
 */
@Basic @Raw
public String getName() {
	return this.Name;
}

/**
 * Check whether the given name is a valid name for
 * any unit.
 *  
 * @param  name
 *         The name to check.
 * @return 
 *       | result == 
*/
public static boolean isValidName(String Name) {
	return false;
}

/**
 * Set the name of this unit to the given name.
 * 
 * @param  Name
 *         The new name for this unit.
 * @post   The name of this new unit is equal to
 *         the given name.
 *       | new.getName() == Name
 * @throws IllegalNameException
 *         The given name is not a valid name for any
 *         unit.
 *       | ! isValidName(getName())
 */
@Raw
public void setName(String Name) 
		throws IllegalNameException {
	if (! isValidName(Name))
		throw new IllegalNameException();
	this.Name = Name;
}

/**
 * Variable registering the name of this unit.
 */
private String Name;