package project.core;

import java.util.ArrayList;
import java.util.List;

import project.record.BookRecord;

public class Wishlist 
{
	public List<Textbook> textbooks;
	
	public Wishlist(List<BookRecord> wishlistBookRecords)
	{
		textbooks = new ArrayList<>();

		for(BookRecord record : wishlistBookRecords)
		{
			Textbook textbook = new Textbook(record);
			textbooks.add(textbook);
		}
	}

	public Wishlist()
	{
	}

	public void add(Textbook selectedBook) 
	{
		textbooks.add(selectedBook);
	}
}
