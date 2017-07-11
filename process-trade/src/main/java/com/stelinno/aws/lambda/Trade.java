package com.stelinno.aws.lambda;

public class Trade {
	private int tradeId;
	private double tradeAmount;
	private String tradeType;
	private String tradeDate;
	private String trader;
	private String tradeBook;
	
	public int getTradeId() {
		return tradeId;
	}
	public void setTradeId(int tradeId) {
		this.tradeId = tradeId;
	}
	public double getTradeAmount() {
		return tradeAmount;
	}
	public void setTradeAmount(double tradeAmount) {
		this.tradeAmount = tradeAmount;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
	public String getTrader() {
		return trader;
	}
	public void setTrader(String trader) {
		this.trader = trader;
	}
	public String getTradeBook() {
		return tradeBook;
	}
	public void setTradeBook(String tradeBook) {
		this.tradeBook = tradeBook;
	}
}
