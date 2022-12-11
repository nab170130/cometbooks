package project.query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import project.record.BookRecord;
import project.record.ListingRecord;
import project.record.MessageRecord;
import project.record.TransactionRecord;

public class MessageConversationQuery extends Query
{
    public List<MessageRecord>  retrievedMessageRecords;
    public BookRecord           retrievedBookRecord;
    public ListingRecord        retrievedListingRecord;
    public TransactionRecord    retrievedTransactionRecord;
    public long                 transactionID;

    public MessageConversationQuery(Connection dbConnection, long transactionID_)
    {
        super(dbConnection);
        transactionID           = transactionID_;
    }

    public List<MessageRecord> getMessageRecords()
    {
        return retrievedMessageRecords;
    }

    public BookRecord getBookRecord()
    {
        return retrievedBookRecord;
    }

    public ListingRecord getListingRecord()
    {
        return retrievedListingRecord;
    }

    public TransactionRecord getTransactionRecord()
    {
        return retrievedTransactionRecord;
    }

    @Override
    public String getQueryString()
    {
        // Formulate the query string based on the transactionID.
        StringBuilder queryStringBuilder = new StringBuilder();
        queryStringBuilder.append("SELECT * FROM cometbooks.book NATURAL JOIN cometbooks.listing NATURAL JOIN cometbooks.transaction NATURAL JOIN cometbooks.message WHERE transactionID = ");
        queryStringBuilder.append(transactionID);
        queryStringBuilder.append(";");

        return queryStringBuilder.toString();
    }

    @Override
    public void processResult(ResultSet resultSet)
    {
        try
        {
            List<MessageRecord> retrievedMessageRecords = new ArrayList<>();

            BookRecord bookRecord               = null;
            ListingRecord listingRecord         = null;
            TransactionRecord transactionRecord = null;

            while(resultSet.next())
            {
                MessageRecord newMessageRecord = new MessageRecord(resultSet);
                bookRecord          = new BookRecord(resultSet);
                listingRecord       = new ListingRecord(resultSet);
                transactionRecord   = new TransactionRecord(resultSet);

                retrievedMessageRecords.add(newMessageRecord);
            }

            Collections.sort(retrievedMessageRecords);
            setMessageRecords(retrievedMessageRecords);
            setBookRecord(bookRecord);
            setListingRecord(listingRecord);
            setTransactionRecord(transactionRecord);
        }
        catch(SQLException ex)
        {
        }
    }

    public void setMessageRecords(List<MessageRecord> newMessageRecords)
    {
        retrievedMessageRecords = newMessageRecords;
    }

    public void setBookRecord(BookRecord newBookRecord)
    {
        retrievedBookRecord = newBookRecord;
    }

    public void setListingRecord(ListingRecord newListingRecord)
    {
        retrievedListingRecord = newListingRecord;
    }

    public void setTransactionRecord(TransactionRecord newTransactionRecord)
    {
        retrievedTransactionRecord = newTransactionRecord;
    }
}
