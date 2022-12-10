package project.adapter;

import java.util.ArrayList;
import java.util.List;

import project.core.SalesListing;
import project.core.Textbook;
import project.core.Wishlist;
import project.query.ISBNListingQuery;
import project.query.UpdateListingHoldStatusQuery;
import project.record.ListingRecord;

public class ListingDBAdapter extends DBAdapter 
{
    private static final ListingDBAdapter instance = new ListingDBAdapter(HOST, PORT);

    public ListingDBAdapter(String host, int portNumber)
    {
        super(host, portNumber);
    }

    public static ListingDBAdapter getInstance()
    {
        return instance;
    }

    public List<SalesListing> getMatchingListings(Textbook textbook)
    {
        // Get the listing records matching this textbook.
        ISBNListingQuery isbnListingQuery = new ISBNListingQuery(connection, textbook.isbn);
        isbnListingQuery.doQuery();
        List<ListingRecord> listingRecords = isbnListingQuery.getListingRecords();

        // Create a list of listings.
        List<SalesListing> listings = new ArrayList<>();

        for(ListingRecord listingRecord : listingRecords)
        {
            SalesListing newListing = new SalesListing(listingRecord, textbook);
            listings.add(newListing);
        }

        return listings;
    }

    public void updateHoldStatus(long listingID)
    {
        // Create the query and execute it.
        UpdateListingHoldStatusQuery updateListingHoldStatusQuery = new UpdateListingHoldStatusQuery(connection, listingID);
        updateListingHoldStatusQuery.doQuery();
    }
}
