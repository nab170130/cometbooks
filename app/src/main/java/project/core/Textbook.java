package project.core;

import project.record.BookRecord;
import project.rest.PriceResponseJSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.lang.model.util.ElementScanner14;

import com.google.gson.Gson;

import project.actor.ThirdPartyVendor;
import project.adapter.ListingDBAdapter;

public class Textbook implements Comparable<Textbook>
{
	public long isbn;
	public int year;
	public String[] authors;
	public int edition;
	public double suggestedPrice;
	public String title;
	
	private static final String INITIAL_API_ENDPOINT 	= "https://booksrun.com/api/v3/price/buy/";
    private static final String API_KEY               	= "id1tlyu4fvg8i2ua5t68";

	public Textbook()
	{
	}

	public int compareTo(Textbook other)
	{
		if(isbn < other.isbn)
		{
			return -1;
		}
		else if(isbn == other.isbn)
		{
			return 0;
		}
		else
		{
			return 1;
		}
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
		setSuggestedPrice();
		return ListingDBAdapter.getInstance().getMatchingListings(this);
	}
	
	public double setSuggestedPrice() 
	{
		String endpoint = INITIAL_API_ENDPOINT + String.format("%010d", isbn) + "?key=" + API_KEY;

        try
        {
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            System.out.println(connection.getResponseCode());
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String res = reader.readLine().replace("\"new\"", "\"new_\"");
            System.out.println(res);
            reader.close();

            Gson gson = new Gson();
            PriceResponseJSONObject j = gson.fromJson(res, PriceResponseJSONObject.class);
			suggestedPrice = j.getPrice();
        }
        catch(Exception ex)
        {
			ex.printStackTrace();
        }

		return suggestedPrice;
	}
}
