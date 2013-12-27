package net.tigerclan.roygoldman.tradingengine;

import java.util.ArrayList;

public class TradingEngine {
	
	ArrayList<TradingProfile> profiles = new ArrayList<TradingProfile>();
	Market market;
	
	public TradingEngine(){
		
		profiles.add(new TenPercentRule(0, 0));
		market = new Market("MtGox");
	
	}

	public void onTrade(Float trade){
		market.addTrade(trade);


		//all Profiles trade
		
		for(TradingProfile p : profiles){
			p.onTrade(market);
		}
		
	}
}
