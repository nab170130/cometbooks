package project.core;

import project.adapter.MessageDBAdapter;
import project.adapter.TransactionDBAdapter;
import project.record.TransactionRecord;

public class Transaction 
{
	public long				transactionID;
	public String 			completionStatus;
	public SalesListing 	listing;
	public Buyer 			buyer;
	
	
	public Transaction(Buyer buyer, SalesListing selectedListing) 
	{ 
		transactionID = 0;
		transactionID = TransactionDBAdapter.getInstance().storeTransaction(this);//Are we sending this object/Transaction object as an argument of this method???????????????
	}

	public Transaction(TransactionRecord transactionRecord, SalesListing salesListing) 
	{//TransactionRecord IS A CLASS
		this.buyer = new Buyer(transactionRecord.buyerNetID);
	}
	
	
	public Conversation getConversation() 
	{//Conversation is a class
//		MessageDBAdapter md = new MessageDBAdapter(); // Class to be cretaed
		return MessageDBAdapter.getInstance().getConversationFromTransaction(this.transactionID);
	}
	
	public void markBuyerComplete() 
	{
		//TransactionDBAdapter td = new TransactionDBAdapter();
		TransactionDBAdapter.getInstance().markBuyerComplete(this);
	}
	public void markSellerComplete() 
	{
		//TransactionDBAdapter td = new TransactionDBAdapter();
		TransactionDBAdapter.getInstance().markSellerComplete(this);
	}
	
	public long getTransactionID() //Do we need chnage the data-ttype over here as we delcared ID as a string and In class-diagram we kept return data-type as long
	{
		return this.transactionID;
	}
	
}
