package net.tigerclan.roygoldman.tradingengine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TradeWatcherSQL extends Thread {
	
	TradingEngine engine;
	Connection con;
	ArrayList<Float> rawTrades = new ArrayList<Float>();
	ArrayList<Float> smoothTrades = new ArrayList<Float>();
	
	
	public TradeWatcherSQL(TradingEngine engine){
		
		try {
	        Class.forName("com.mysql.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } 
		
		this.engine = engine;
		
		String server = "jdbc:mysql://localhost:3306/";
		String user = "rich";
		String password = "interrobang";
		
		try {
			con = DriverManager.getConnection(server, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void run(){
		try {
			if(con == null){
				System.out.println("No SQL database... Exiting...");
				return;
			}
			
			String query = "Select * from bitcoin.mtgox_price";
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()){
				addTrade(Float.parseFloat(rs.getString("last")));
			}

			rs.close();
			st.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void addTrade(float trade) {
		rawTrades.add(trade);
		smoothTrades.add(smoothValue(rawTrades.size() - 1));
	}

	private Float smoothValue(int i) {
		if(i < 3){
			return rawTrades.get(i);
		}else{
			//smooth
			float raw = rawTrades.get(i);
			
			
			float value = raw;
			return value;
		}
	}

}
