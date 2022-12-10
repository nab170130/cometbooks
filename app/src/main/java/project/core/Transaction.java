package project.core;

import project.record.TransactionRecord;

public class Transaction 
{
	public long				transactionID;
	public String 			completionStatus;
	public SalesListing 	listing;
	public Buyer 			buyer;
	
	
	public Transaction(Buyer buyer, SalesListing selectedListing) 
	{ // Both types are classes
		
	}

	public Transaction(TransactionRecord transactionRecord, SalesListing salesListing) 
	{

	}
	
	
	public Conversation getConversation() 
	{
		return null;
	}
	
	public void setTransactionID() 
	{
		
	}
	
	public void markBuyerComplete() 
	{
		
	}
	public void markSellerComplete() 
	{
		
	}
	
	public long getTransactionID() 
	{
		return 0;
	}
	
}
