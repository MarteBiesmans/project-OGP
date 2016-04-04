package hillbillies.model;

import java.util.HashSet;
import java.util.Set;

public class Faction {

	private final int MAX_UNITS = 50;

	public Faction() {
		this.units = new HashSet<Unit>();
	}

	public void addUnit(Unit unit) {
		if (! canHaveAsUnit(unit))
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

	public boolean canHaveAsUnit(Unit unit) {
		if (unit.isDead())
			return false;
		if (unit.getFaction() != null)
			return false;
		if (this.units.size() == MAX_UNITS)
			return false;
		return true;
	}

	public boolean hasAsUnit(Unit unit) {
		if (unit == null)
			return false;
		return this.units.contains(unit);
	}

	private final Set<Unit> units;
	
	public World getWorld() {
		return this.world;
	}

	public boolean canHaveAsWorld(World world) {
		return (world != null && world.hasAsFaction(this) && this.getWorld() ==  null);
	}

	public void setWorld(World world) {
		if (world != null) {
			if (this.canHaveAsWorld(world))
				throw new IllegalArgumentException();
		} else if ((this.getWorld() != null) && (this.getWorld().hasAsFaction(this)))
			throw new IllegalArgumentException();
		this.world = world;
	}

	private World world;

}
