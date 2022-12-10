package project.core;

public class Transaction 
{
	String 			transactionID;
	String 			completionSatus;
	SalesListing 	listing;
	Buyer 			buyer;
	
	
	public Transaction(Buyer buyer, SalesListing selectedListing) 
	{ 
		this.setTransactionID();
		TransactionDBAdapter td = new TransactionDBAdapter();
		td.storeTransaction();//Are we sending this object/Transaction object as an argument of this method???????????????
	}

	public Transaction(TransactionRecord transactionRecord, SalesListing salesListing) 
	{//TransactionRecord IS A CLASS
		this.buyer = new Buyer(transactionRecord);
	}
	
	
	public Conversation getConversation() 
	{//Conversation is a class
		MessageDBAdapter md = new MessageDBAdapter(); // Class to be cretaed
		return md.getCoversationFromTransaction(this.transactionID);
	}
	
	// public void setTransactionID() 
	// {
		//--------Need to discuus
	// }
	
	public void markBuyerComplete() 
	{
		TransactionDBAdapter td = new TransactionDBAdapter();
		td.markBuyerComplete(this);
	}
	public void markSellerComplete() 
	{
		TransactionDBAdapter td = new TransactionDBAdapter();
		td.markSellerComplete(this);
	}
	
	public String getTransactionID() //Do we need chnage the data-ttype over here as we delcared ID as a string and In class-diagram we kept return data-type as long
	{
		return this.transactionID;
	}
	
}
