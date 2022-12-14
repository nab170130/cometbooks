package project.core;

import project.adapter.ListingDBAdapter;
import project.record.ListingRecord;

public class SalesListing 
{
	public long listingID;
	public String condition;
	public double listingPrice;
	public String description;
	public boolean onHold;
	public Seller seller;
	public Textbook textbook;


	public SalesListing(ListingRecord listingRecord, Textbook selectedTextbook) 
	{
		this.setTextbook(selectedTextbook);
		
		try
		{
			this.seller = new Seller(listingRecord.sellerNetID);
		}
		catch(Exception ex)
		{
		}

		this.listingID = listingRecord.listingID;
		this.condition = listingRecord.condition;
		this.listingPrice = listingRecord.listingPrice;
		this.description = listingRecord.description;
		this.onHold = listingRecord.onHold;
	}


	public void setTextbook(Textbook selectedTextbook) 
	{
		this.textbook = selectedTextbook;
	}


	public boolean placeOnHold() 
	{
		if(this.onHold == true)
		{
			return false;
		}
		else
		{
			setOnHold(true);
			return true;
		}

	}
	
	
	public void setOnHold(boolean holdStatus) 
	{
		this.onHold = holdStatus;
		ListingDBAdapter.getInstance().updateHoldStatus(this.listingID);
	}
}
