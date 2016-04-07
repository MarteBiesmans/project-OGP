package hillbillies.model;

import java.util.HashSet;
import java.util.Set;

public class Faction {

	final static int MAX_UNITS = 50;

	public Faction() {
		this.units = new HashSet<Unit>();
	}

	public void addUnit(Unit unit) {
		if (!canAddAsUnit(unit))
			throw new IllegalArgumentException();
		this.units.add(unit);

		try {
			unit.setFaction(this);
		} catch (IllegalArgumentException e) {
			this.units.remove(unit);
			throw e;
		}
	}

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

	public boolean canAddAsUnit(Unit unit) {
		if (unit.isDead())
			return false;
		if (unit.getFaction() != null)
			return false;
		if (this.units.size() == MAX_UNITS)
			return false;
		if (this.getWorld() != null) {
			if (this.getWorld().getNbFactions() == World.MAX_FACTIONS)
				return false;
			if (this.getWorld().getNbUnits() == World.MAX_UNITS)
				return false;
			if (this.units.size() == 0 && this.getWorld().getNbActiveFactions() == World.MAX_FACTIONS)
				return false;
		}
		return true;
	}

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
	
	public int getNbUnits() {
		return this.units.size();
	}

	public boolean hasProperUnits() {
		for (Unit unit : this.units)
			if (unit == null || unit.isDead() || unit.getFaction() != this)
				return false;
		return true;
	}

	private final Set<Unit> units;

	public World getWorld() {
		return this.world;
	}

	public boolean canHaveAsWorld(World world) {
		return (world != null && world.hasAsFaction(this) && this.getWorld() == null);
	}

	public void setWorld(World world) {
		if (world != null) {
			if (! this.canHaveAsWorld(world))
				throw new IllegalArgumentException();
		} else if ((this.getWorld() != null) && (this.getWorld().hasAsFaction(this)))
			throw new IllegalArgumentException();
		this.world = world;
	}

	private World world;

}
