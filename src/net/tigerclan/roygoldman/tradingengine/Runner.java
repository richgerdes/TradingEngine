package net.tigerclan.roygoldman.tradingengine;

public class Runner {

	public static void main(String[] args) {

		TradingEngine e = new TradingEngine();

		e.onTrade(1f);
		e.onTrade(3f);
		e.onTrade(2f);

		System.out.println("Done");
	}

}
