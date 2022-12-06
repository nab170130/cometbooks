package project.query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project.record.BookRecord;
import project.record.ListingRecord;
import project.record.TransactionRecord;

public class UserTransactionQuery extends Query
{
    public List<BookRecord>         bookRecords;    
    public List<ListingRecord>      listingRecords;
    public List<TransactionRecord>  transactionRecords;
    public String                   userNetID;

    public UserTransactionQuery(Connection connection, String netID)
    {
        super(connection);
        userNetID = netID;
    }

    public List<BookRecord> getBookRecords()
    {
        return bookRecords;
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
        queryStringBuilder.append("SELECT * FROM cometbooks.book NATURAL JOIN cometbooks.listing NATURAL JOIN cometbooks.transaction WHERE (buyerNetID = '");
        queryStringBuilder.append(userNetID);
        queryStringBuilder.append("' OR sellerNetID = '");
        queryStringBuilder.append(userNetID);
        queryStringBuilder.append("');");

        return queryStringBuilder.toString();
    }

    public List<TransactionRecord> getTransactionRecords()
    {
        return transactionRecords;
    }

    @Override
    public void processResult(ResultSet resultSet)
    {
        try
        {
            List<BookRecord> retrievedBookRecords               = new ArrayList<>();
            List<ListingRecord> retrievedListingRecords         = new ArrayList<>();
            List<TransactionRecord> retrievedTransactionRecords = new ArrayList<>();

            while(resultSet.next())
            {
                BookRecord bookRecord               = new BookRecord(resultSet);
                ListingRecord listingRecord         = new ListingRecord(resultSet);
                TransactionRecord transactionRecord = new TransactionRecord(resultSet);

                retrievedBookRecords.add(bookRecord);
                retrievedListingRecords.add(listingRecord);
                retrievedTransactionRecords.add(transactionRecord);
            }

            storeBookRecords(retrievedBookRecords);
            storeListingRecords(retrievedListingRecords);
            storeTransactionRecords(retrievedTransactionRecords);
        }
        catch(SQLException ex)
        {
        }
    }

    public void storeBookRecords(List<BookRecord> bookRecords_)
    {
        bookRecords = bookRecords_;
    }

    public void storeListingRecords(List<ListingRecord> listingRecords_)
    {
        listingRecords = listingRecords_;
    }

    public void storeTransactionRecords(List<TransactionRecord> transactionRecords_)
    {
        transactionRecords = transactionRecords_;
    }
}
