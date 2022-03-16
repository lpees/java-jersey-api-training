package br.com.lab510.models;

public class TransactionEvent {
	
	private String transactionDate;
	private String transactionAmount;
	private String transactionType;
	private String transactionState;
	private String recurrencyFlag;
	
	public String getRecurrencyFlag() {
		return recurrencyFlag;
	}

	public void setRecurrencyFlag(String recurrencyFlag) {
		this.recurrencyFlag = recurrencyFlag;
	}

	private String orderID;
	private String tid;
	private String nsu;
	
	private int transactionStatus;
	private int transactionID; 
	private int merchantId;
	
	public String getTransactionType() {
		return transactionType;
	}
	
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	
	public String getTransactionState() {
		return transactionState;
	}
	
	public void setTransactionState(String transactionState) {
		this.transactionState = transactionState;
	}
	
	public String getOrderID() {
		return orderID;
	}
	
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	
	public String getTid() {
		return tid;
	}
	
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getNsu() {
		return nsu;
	}
	
	public void setNsu(String nsu) {
		this.nsu = nsu;
	}
	
	public int getTransactionStatus() {
		return transactionStatus;
	}
	
	public void setTransactionStatus(int transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	
	public int getTransactionID() {
		return transactionID;
	}
	
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}
	
	public int getMerchantId() {
		return merchantId;
	}
	
	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}
	
	public String getTransactionDate() {
		return transactionDate;
	}
	
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	public String getTransactionAmount() {
		return transactionAmount;
	}
	
	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	
	
}
