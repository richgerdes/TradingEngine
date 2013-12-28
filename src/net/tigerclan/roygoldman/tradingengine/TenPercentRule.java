package net.tigerclan.roygoldman.tradingengine;

public class TenPercentRule extends TradingProfile {
	
	float peak = 0;
	int peakIndex = 0;
	float trough = 0;
	int troughIndex = 0;
	
	boolean firstTrade = true;

	public TenPercentRule(float btc, float usd) {
		super(btc, usd);
	}

	@Override
	public void onTrade(Market m) {
		
		if(firstTrade){
			peak = m.lastPrice();
			peakIndex = m.lastIndex();
			trough = m.lastPrice();
			troughIndex = m.lastIndex();
			firstTrade = false;
		}
		
		buyRoute(m);
		sellRoute(m);
		
		if(m.lastPrice() > peak){
			peak = m.lastPrice();
			peakIndex = m.lastIndex();
			System.out.println("Changing peak..." + " Peak = " + peak);
		}else if(m.lastPrice() < trough){
			trough = m.lastPrice();
			troughIndex = m.lastIndex();
			System.out.println("Changing trough..." + " Trough = " + trough);
		}else{
			//System.out.println(trough);
		}

	}

	private void buyRoute(Market m) {
		if(usd <= 0.0f || m.lastPrice() == 0)
			return;
		
		if((m.lastPrice() - trough) / trough > .01f){
			//System.out.println("Market is Buyable!");
			try {
				buyBTC(((float)(Math.floor(usd * 100)) / 100) / m.lastPrice(), m.lastPrice());

				peak = m.lastPrice();
				peakIndex = m.lastIndex();
				lastBuyPrice = m.lastPrice();
				lastBuyIndex = m.lastIndex();
			} catch (FundsUnavaliableException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private void sellRoute(Market m) {
		if(btc <= 0.0f || m.lastPrice() == 0)
			return;

		if(peak > lastBuyPrice && m.lastPrice() < peak && m.lastPrice() > lastBuyPrice && (peak - m.lastPrice()) / (peak - lastBuyPrice) > .05f){
			//System.out.println("We should really sell...");
			try {
				sellBTC(btc, m.lastPrice());

				trough = m.lastPrice();
				troughIndex = m.lastIndex();
				lastSellPrice = m.lastPrice();
				lastSellIndex = m.lastIndex();
			} catch (FundsUnavaliableException e) {
				System.out.println(e.getMessage());
			}
		}else{
			//System.out.println("Not Selling");
		}
	}

}
