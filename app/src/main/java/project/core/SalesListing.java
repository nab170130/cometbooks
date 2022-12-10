package project.core;

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
		
	}
	
	public SalesListing()
	{
		
	}

	public void setTextbook(Textbook selectedTextbook) {
		
	}
	public void placeOnHold() {
		
	}
	public void setOnHold(boolean holdStatus) {
		
	}
	
}
