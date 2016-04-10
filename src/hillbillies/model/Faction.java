package hillbillies.model;

import java.util.HashSet;
import java.util.Set;

public class Faction {

	/**
	 * a constant representing the maximum units in a faction
	 */
	final static int MAX_UNITS = 50;

	/**
	 * @post this faction doesn't have any units
	 */
	public Faction() {
		this.units = new HashSet<Unit>();
	}

	/**
	 * add the given unit to this faction
	 * 
	 * @param unit
	 *            The unit to add to this faction
	 * @post if the unit can have this faction as a faction the unit is added to
	 *       the faction
	 */
	public void addUnit(Unit unit) {
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
	 * remove the given unit to this faciton
	 * 
	 * @param unit
	 *            The unit to remove from this faction
	 * @post if the unit belongs to this faction the unit is removed from this
	 *       faction
	 */
	public void removeUnit(Unit unit) {
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
	 * check whether the given unit can be added to this faction
	 * 
	 * @param unit
	 *            The unit to check if it can be added to this faction
	 * @return true if the unit can be added to this faction, considering the
	 *         units condition and the number of units in this faction and in
	 *         the world
	 */
	public boolean canAddAsUnit(Unit unit) {
		if (unit.isDead()) {
			System.out.println("isDead");
			return false;
		}
		if (unit.getFaction() != null) {
			System.out.println("unit.getFaction != null");
			return false;
		}
		if (this.units.size() == MAX_UNITS) {
			System.out.println("faction.MAX_UNITS");
			return false;
		}
		if (this.getWorld() != null) {
			if (this.getNbUnits() == 0 && this.getWorld().getNbActiveFactions() == World.MAX_FACTIONS) {
				System.out.println("world != null, world.MAX_FACTIONS");
				return false;
			}
			if (this.getWorld().getNbUnits() == World.MAX_UNITS) {
				System.out.println("world != null, world.MAX_UNITS");
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
	 * Return all the units in this faction.
	 */
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
	 * check whether this faciton has proper units
	 * 
	 * @return true if this faction has proper units
	 */
	public boolean hasProperUnits() {
		for (Unit unit : this.units)
			if (unit == null || unit.isDead() || unit.getFaction() != this)
				return false;
		return true;
	}

	/**
	 * a set of all units belonging to this faction
	 */
	private final Set<Unit> units;

	public World getWorld() {
		return this.world;
	}

	/**
	 * check wheter this faction can be in the given world
	 */
	public boolean canHaveAsWorld(World world) {
		return (world != null && world.hasAsFaction(this) && this.getWorld() == null);
	}

	/**
	 * set the world of this faction to the given world
	 */
	public void setWorld(World world) {
		if (world != null) {
			if (!this.canHaveAsWorld(world))
				throw new IllegalArgumentException();
		} else if ((this.getWorld() != null) && (this.getWorld().hasAsFaction(this)))
			throw new IllegalArgumentException();
		this.world = world;
	}

	private World world;

}
