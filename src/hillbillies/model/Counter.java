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
	
	@Override
	public Counter clone() {
		return new Counter(getCount());
	}
}
