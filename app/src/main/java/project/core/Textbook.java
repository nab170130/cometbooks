package project.core;

import project.record.BookRecord;
import java.util.List;

import project.actor.ThirdPartyVendor;
import project.adapter.ListingDBAdapter;

public class Textbook 
{
	public long isbn;
	public int year;
	public String[] authors;
	public int edition;
	public double suggestedPrice;
	public String title;
	
	public Textbook()
	{
	}

	public Textbook(BookRecord bookRecord)
	{ 
		copyRecordAttributes(bookRecord);
	}

	public void copyRecordAttributes(BookRecord bookRecord)
	{
		this.isbn 		= bookRecord.isbn;
		this.year 		= bookRecord.year;
		this.authors 	= bookRecord.authors;
		this.edition 	= bookRecord.edition;
		this.title 		= bookRecord.title;
	}

	public List<SalesListing> getMatchingListings()
	{
		return ListingDBAdapter.getInstance().getMatchingListings(this);
	}
	
	public double setSuggestedPrice() 
	{
		return ThirdPartyVendor.getSuggestedPrice(this);
	}
}
