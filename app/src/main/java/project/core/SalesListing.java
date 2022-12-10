package project.core;

public class SalesListing 
{
	public long listingID;
	public String condition;
	public double listingPrice;
	public String description;
	public boolean onHold;
	public Seller seller;
	
//	public SalesListing(ListingRecord listingRecord, Textbook selectedTextbook) 
//	{
//		
//	}
	public void setTextbook(Textbook selectedTextbook) 
	{
		return null;
	}
	public boolean placeOnHold() 
	{
		if(this.onHold == true)
		{
			return false;
		}
		else
		{
			setOnHold();
			return true;
		}

	}
	public void setOnHold(boolean holdStatus) 
	{
		this.onHold = !this.onHold;
		UpdateListingHoldStatusQuery status = UpdateListingHoldStatusQuery(this.listingID);
	}
	
}
