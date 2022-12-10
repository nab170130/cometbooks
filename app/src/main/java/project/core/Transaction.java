package project.core;

public class Transaction 
{
	public String 			transactionID;
	public String 			completionSatus;
	public SalesListing 	listing;
	public Buyer 			buyer;
	
	
	public Transaction(Buyer buyer, SalesListing selectedListing) 
	{ // Both types are classes
		
	}

//	public Transaction(TransactionRecord transactionRecord, SalesListing salesListing) 
//	{//TransactionRecord IS A CLASS
//		
//	}
	
	
//	public Conversation getConversation() 
//	{//Conversation is a class
//		
//	}
	
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
