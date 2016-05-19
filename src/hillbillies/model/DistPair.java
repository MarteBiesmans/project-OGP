package hillbillies.model;

@SuppressWarnings("rawtypes")
public class DistPair<T> implements Comparable<DistPair> {
	
	private final Double distance;
	private final T thing;

	public DistPair(T thing, Double distance) {
		this.thing = thing;
		this.distance = distance;
	}

	public Double getDistance() {
		return distance;
	}

	public T getThing() {
		return thing;
	}

	@Override
	public int compareTo(DistPair other) {
		return this.getDistance().compareTo(other.getDistance());
	}
	
	public DistPair getMinimum(DistPair other) {
		if (this.getDistance() <= other.getDistance())
			return this;
		else
			return other;
	}
	
}
