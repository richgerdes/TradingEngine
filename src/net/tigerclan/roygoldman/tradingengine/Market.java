package net.tigerclan.roygoldman.tradingengine;

import java.util.ArrayList;

public class Market {

	ArrayList<Float> trades = new ArrayList<Float>();

	public Market(String name) {

	}

	public void addTrade(Float t) {
		trades.add(t);
	}
	
	public float lastPrice(){
		return trades.get(lastIndex());
	}

	public int lastIndex() {
		return trades.size() - 1;
	}

}
