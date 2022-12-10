package project.core;

public class SalesListing 
{
	public long listingID;
	public String condition;
	public double listingPrice;
	public String description;
	public boolean onHold;
	public Seller seller;
	
	public SalesListing(ListingRecord listingRecord, Textbook selectedTextbook) 
	{
		this.setTextbook(selectedTextbook);
		this.seller = new Seller(listingRecord.sellerNetID);
		this.listingID = listingRecord.listingID;
		this.condition = listingRecord.condition;
		this.listingPrice = listingRecord.listingPrice;
		this.description = lisitingRecord.description;
		this.onHold = listingRecord.onHold;
	}
	// Do we need to use the textbook object parameter?
	// What is the purpose of setTextbook method?
	public void setTextbook(Textbook selectedTextbook) 
	{

	}
	public boolean placeOnHold() 
	{
		// if book is on hold return availability of false
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
		ListingDBAdapter.getInstance().uploadHoldStatus(this.listingID);
		// UpdateListingHoldStatusQuery status = UpdateListingHoldStatusQuery(this.listingID);
	}
	
}
