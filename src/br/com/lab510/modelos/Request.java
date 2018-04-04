package br.com.lab510.modelos;

public class Request {

	private TransactionEvent transactionEvent;

	public TransactionEvent getTransactionEvent() {
		return transactionEvent;
	}

	public void setTransactionEvent(TransactionEvent transactionEvent) {
		this.transactionEvent = transactionEvent;
	}
	
	@Override
	public String toString() {
		
		return "transactionStatus: " + this.transactionEvent.getTransactionStatus() +
				"\ntransactionType: " + this.transactionEvent.getTransactionType() +
				"\ntransactionID: " + this.transactionEvent.getTransactionID() +
				"\ntid: " + this.transactionEvent.getTid() +
				"\norderID: " + this.transactionEvent.getOrderID() +
				"\ntransactionState: " + this.transactionEvent.getTransactionState() +
				"\ntransactionDate: " + this.transactionEvent.getTransactionDate() +
				"\nnsu: " + this.transactionEvent.getNsu() +
				"\nmerchantId: " + this.transactionEvent.getMerchantId() +
				"\ntransactionAmount: " + this.transactionEvent.getTransactionAmount();
	}

}
