package hillbillies.model;

import java.util.HashSet;
import java.util.Set;

import be.kuleuven.cs.som.annotate.*;

/**
 * a class describing a faction, i.e. a group of units working together
 * 
 * @invar Each faction can have its scheduler as scheduler.
 * @author Ellen & Marte
 */
public class Faction {

	/**
	 * a constant representing the maximum units in a faction
	 */
	final static int MAX_UNITS = 50;

	/**
	 * create a new empty faction with a new scheduler
	 * 
	 * @post this faction doesn't have any units
	 * @post this faction has a new scheduler
	 */
	public Faction() {
		this.units = new HashSet<Unit>();
		this.scheduler = new Scheduler(this); //TODO: kan dit? -> eclipse klaagt niet..
	}

	/**
	 * add the given unit to this faction
	 * 
	 * @param unit
	 *            The unit to add to this faction
	 * @post if the unit can have this faction as a faction the unit is added to
	 *       the faction
	 * @throws IllegalArgumentException
	 *             the given unit cannot be added to this faction
	 */
	public void addUnit(Unit unit) throws IllegalArgumentException {
		if (!canAddAsUnit(unit)) {
			throw new IllegalArgumentException();
		}
		this.units.add(unit);

		try {
			unit.setFaction(this);
		} catch (IllegalArgumentException e) {
			this.units.remove(unit);
			throw e;
		}
	}

	/**
	 * remove the given unit from this faction
	 * 
	 * @param unit
	 *            The unit to remove from this faction
	 * @post if the unit belongs to this faction the unit is removed from this
	 *       faction
	 * @throws IllegalArgumentException
	 *             this faction doesn't contain the given unit
	 */
	public void removeUnit(Unit unit) throws IllegalArgumentException {
		if (!this.hasAsUnit(unit))
			throw new IllegalArgumentException();
		this.units.remove(unit);

		try {
			unit.setFaction(null);
		} catch (IllegalArgumentException e) {
			this.units.add(unit);
			throw e;
		}
	}

	/**
	 * check whether the given unit can be added to this faction, considering
	 * the units condition and the number of units in this faction and in the
	 * world
	 * 
	 * @param unit
	 *            The unit to check if it can be added to this faction
	 * @return false if the given unit is dead
	 * @return false if the given unit already belongs to a faction
	 * @return false if this faction has reached it's maximum number of units
	 * @return false if the faction belongs to a world that has reached it's
	 *         maximum number of factions
	 * @return false if the faction belongs to a world that has reached it's
	 *         maximum number of units
	 */
	private boolean canAddAsUnit(Unit unit) {
		if (unit.isDead()) {
			return false;
		}
		if (unit.getFaction() != null) {
			return false;
		}
		if (this.units.size() == MAX_UNITS) {
			return false;
		}
		if (this.getWorld() != null) {
			if (this.getNbUnits() == 0 && this.getWorld().getNbActiveFactions() == World.MAX_FACTIONS) {
				return false;
			}
			if (this.getWorld().getNbUnits() == World.MAX_UNITS) {
				return false;
			}
		}
		return true;
	}

	/**
	 * check whether the given unit belongs to this faction
	 * 
	 * @param unit
	 *            The unit to check
	 * @return true if the given unit belongs to this faction
	 */
	public boolean hasAsUnit(Unit unit) {
		if (unit == null)
			return false;
		return this.units.contains(unit);
	}

	/**
	 * Return a set of all the units in this faction.
	 */
	@Basic
	public Set<Unit> getAllUnits() {
		return units;
	}

	/**
	 * return the number of units in this faction
	 */
	public int getNbUnits() {
		return this.units.size();
	}

	/**
	 * check whether this faction has proper units
	 * 
	 * @return true if this faction has proper units, i.e. not null, alive and
	 *         belonging to this faction
	 */
	@SuppressWarnings("unused")
	private boolean hasProperUnits() {
		for (Unit unit : this.units)
			if (unit == null || unit.isDead() || unit.getFaction() != this)
				return false;
		return true;
	}

	/**
	 * a set of all units belonging to this faction
	 */
	private final Set<Unit> units;

	/**
	 * return the world where this faction belongs to
	 */
	@Basic
	public World getWorld() {
		return this.world;
	}

	/**
	 * check whether this faction can be in the given world
	 * 
	 * @param world
	 *            the world to check
	 * @return true if the world is not null and the world contains this faction
	 *         and the world of this faction is null
	 */
	private boolean canHaveAsWorld(World world) {
		return (world != null && world.hasAsFaction(this) && this.getWorld() == null);
	}

	/**
	 * set the world of this faction to the given world
	 * 
	 * @param world
	 *            the world to set to
	 * @throws IllegalArgumentException
	 *             the given world is not null and this faction can not have the
	 *             given world as world
	 * 
	 */
	public void setWorld(World world) throws IllegalArgumentException {
		if (world != null) {
			if (!this.canHaveAsWorld(world))
				throw new IllegalArgumentException();
		} else if ((this.getWorld() != null) && (this.getWorld().hasAsFaction(this)))
			throw new IllegalArgumentException();
		this.world = world;
	}

	/**
	 * a variable registering the world this faction belongs to
	 */
	private World world;

	/**
	 * Return the scheduler of this faction.
	 */
	@Basic
	@Raw
	@Immutable
	public Scheduler getScheduler() {
		return this.scheduler;
	}

	/**
	 * Check whether this faction can have the given scheduler as its scheduler.
	 * 
	 * @param scheduler
	 *            The scheduler to check.
	 * @return true if the given scheduler is not null.
	 */
	@Raw
	public boolean canHaveAsScheduler(Scheduler scheduler) {
		return (scheduler != null);
	}

	/**
	 * Variable registering the scheduler of this faction.
	 */
	private final Scheduler scheduler;

}
