package net.tigerclan.roygoldman.tradingengine;

public class FundsUnavaliableException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public FundsUnavaliableException() {
		super();
	}
	
	public FundsUnavaliableException(String type, float limit, float need) {
		super(bulidMessage(type, limit, need));
	}
	
	public FundsUnavaliableException(String message) {
		super(message);
	}

	public FundsUnavaliableException(String message, Throwable cause) {
		super(message, cause);
	}

	public FundsUnavaliableException(Throwable cause) {
		super(cause);
	}
	private static String bulidMessage(String type, float limit, float need){
		return "Not Enuough " + type + " you only have " + limit + type + " and you need " + need + type + "!";
	}

}
