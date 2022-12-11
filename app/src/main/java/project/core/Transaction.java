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
	
	
	public Transaction(Buyer buyer_, SalesListing selectedListing) 
	{ 
		transactionID 		= 0;
		completionStatus 	= "";
		listing 			= selectedListing;
		buyer 				= buyer_;

		transactionID = TransactionDBAdapter.getInstance().storeTransaction(this);
	}

	public Transaction(TransactionRecord transactionRecord, SalesListing salesListing) 
	{
		try
		{
			this.buyer 				= new Buyer(transactionRecord.buyerNetID);
			this.completionStatus 	= transactionRecord.completionStatus;
			this.listing 			= salesListing;
			this.transactionID 		= transactionRecord.transactionID;
		}
		catch(Exception ex)
		{
		}
	}
	
	
	public Conversation getConversation() 
	{
		return MessageDBAdapter.getInstance().getConversationFromTransaction(this);
	}
	
	public void markBuyerComplete() 
	{
		TransactionDBAdapter.getInstance().markBuyerComplete(this);

		if(!completionStatus.contains("buyer"))
		{
			completionStatus = "buyer" + completionStatus;
		}
	}

	public void markSellerComplete() 
	{
		TransactionDBAdapter.getInstance().markSellerComplete(this);

		if(!completionStatus.contains("seller"))
		{
			completionStatus = completionStatus + "seller";
		}
	}
	
	public long getTransactionID() //Do we need chnage the data-ttype over here as we delcared ID as a string and In class-diagram we kept return data-type as long
	{
		return this.transactionID;
	}
	
}
