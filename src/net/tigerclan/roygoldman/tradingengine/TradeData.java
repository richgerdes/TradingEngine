package net.tigerclan.roygoldman.tradingengine;

public class TradeData {
	
	private float last;
	private float high;
	private float low;
	
	public TradeData(float last, float high, float low){
		this.last = last;
		this.high = high;
		this.low = low;
	}

	public float getLast(){
		return last;
	}
	
	public float getHigh(){
		return high;
	}
	
	public float getLow(){
		return low;
	}

}
