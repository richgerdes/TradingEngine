package net.tigerclan.roygoldman.tradingengine;

public class Runner {

	public static void main(String[] args) {

		TradingEngine e = new TradingEngine();
		
		TradeWatcher t = new TradeWatcher(e);

		//t.start();
		
		TradeWatcherSQL w = new TradeWatcherSQL(e);
		
		w.start();
		
	}

}
