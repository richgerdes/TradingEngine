package net.tigerclan.roygoldman.tradingengine;

public class TenPercentRule extends TradingProfile {
	
	float peak = 0;
	int peakIndex = 0;
	float trough = 0;
	int troughIndex = 0;

	public TenPercentRule(float btc, float usb) {
		super(btc, usb);
	}

	@Override
	public void onTrade(Market m) {
		
		buyRoute(m);
		sellRoute(m);
		
		if(m.lastPrice() > peak){
			peak = m.lastPrice();
			peakIndex = m.lastIndex();
			System.out.println("Changing peak...");
		}
		if(m.lastPrice() < trough){
			trough = m.lastPrice();
			troughIndex = m.lastIndex();
			System.out.println("Changing trough...");
		}

	}

	private void buyRoute(Market m) {
		if((m.lastIndex() - trough) / trough > .1f){
			System.out.println("Market is Buyable!");
		}
	}

	private void sellRoute(Market m) {
		
		if(peakIndex < lastBuyIndex){
			System.out.println("It has not peaked yet...");
			return;
		}

		if(m.lastPrice() < peak && lastBuyIndex < peakIndex && (peak - m.lastPrice()) / (peak - lastBuyPrice) > .1f){
			System.out.println("We should really sell...");
		}else{
			System.out.println("Not Selling");
		}
	}

}
