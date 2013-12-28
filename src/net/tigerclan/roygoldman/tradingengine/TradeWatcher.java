package net.tigerclan.roygoldman.tradingengine;

import java.util.ArrayList;

public class TradeWatcher extends Thread{
	
	private TradingEngine engine;
	private ArrayList<Float> trades = new ArrayList<Float>();
	
	public TradeWatcher(TradingEngine engine){
		this.engine = engine;
		importTrades();
	}
	
	private void importTrades() {
		//Import Trades
		
		
	}

	public void run(){
		
		for(float value : trades){
			engine.onTrade(value);
		}
		
		engine.onClose();
	}

}
