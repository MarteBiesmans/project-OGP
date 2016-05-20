package hillbillies.model;

public class Counter {
	
	private int count;

	public Counter(int start){
		this.count=start;
	}
	
	public Counter() {
		this.count = 0;
	}
	
	public void increment(){
		count++;
	}

	public void reset(){
		count=0;
	}
	public int getCount(){
		return count;
	}
	
	public int getMaxValue() {
		return this.max_value;
	}
	
	public void setMaxValue(int max) {
		this.max_value = max;
	}
	
	private int max_value;
	
	@Override
	public Counter clone() {
		return new Counter(getCount());
	}
}
