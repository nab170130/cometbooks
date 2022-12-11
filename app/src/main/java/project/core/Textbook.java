package project.core;

import project.record.BookRecord;
import java.util.ArrayList;
import java.util.List;

import project.adapter.ListingDBAdapter;

public class Textbook 
{
	public long isbn;
	public int year;
	public String[] authors;
	public int edition;
	public double suggestedPrice;
	public String title;
	public List<Textbook> textbook;
	
	public Textbook(BookRecord bookRecord)
	{ 
	  copyRecordAttributes(bookRecord);
	  textbooks =new ArrayList<>();
	  ListingDBAdapter.getInstance();	
	}

	public getMatchingListings()
	{

	}
	
	public Textbook()
	{
	}

	public double setSuggestedPrice() 
	{
		return 0;
	}
}
