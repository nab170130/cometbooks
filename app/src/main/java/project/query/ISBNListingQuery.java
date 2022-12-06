package project.query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project.record.ListingRecord;

public class ISBNListingQuery extends Query
{
    public long                 isbn;
    public List<ListingRecord>  listingRecords;

    public ISBNListingQuery(Connection dbConnection, long isbn_)
    {
        super(dbConnection);
        isbn = isbn_;
    }

    public List<ListingRecord> getListingRecords()
    {
        return listingRecords;
    }

    @Override
    public String getQueryString()
    {
        // Formulate the query string based on the transactionID.
        StringBuilder queryStringBuilder = new StringBuilder();
        queryStringBuilder.append("SELECT * FROM cometbooks.listing WHERE isbn = ");
        queryStringBuilder.append(isbn);
        queryStringBuilder.append(";");

        return queryStringBuilder.toString();
    }

    @Override
    public void processResult(ResultSet resultSet)
    {
        try
        {
            List<ListingRecord> retrievedListingRecords = new ArrayList<>();
            while(resultSet.next())
            {
                ListingRecord listingRecord = new ListingRecord(resultSet);
                retrievedListingRecords.add(listingRecord);
            }
            storeListingRecords(retrievedListingRecords);
        }
        catch(SQLException ex)
        {
        }
    }

    public void storeListingRecords(List<ListingRecord> newListingRecords)
    {
        listingRecords = newListingRecords;
    }
}
