package net.tigerclan.roygoldman.tradingengine;

public abstract class TradingProfile {
	
	protected final float startingBTC;
	protected final float startingUSD;
	protected float btc = 0;
	protected float usd = 0;
	protected float lastBuyPrice = 0;
	protected int lastBuyIndex = 0;
	protected float lastSellPrice = 0;
	protected int lastSellIndex = 0;

	public TradingProfile(float btc, float usd) {
		this.btc = btc;
		this.startingBTC = btc;
		this.usd = usd;
		this.startingUSD = usd;
	}

	public abstract void onTrade(Market m);

	public float getBTC() {
		return btc;
	}

	public float getUSD() {
		return usd;
	}

	public void buyBTC(float num, float price) throws FundsUnavaliableException {
		if (usd >= num * price) {

		} else {
			throw new FundsUnavaliableException("USD", usd, num * price);
		}
	}

	public void sellBTC(float num, float price) throws FundsUnavaliableException {

		if (btc >= num) {
			btc -= num;
			usd += num * price;
		} else {
			throw new FundsUnavaliableException("BTC", btc, num);
		}
	}

	public void printProfit() {
		System.out.println("BTC: " + btc + " USD: " + usd );
	}

}
