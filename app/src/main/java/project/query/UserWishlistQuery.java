package project.query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project.record.BookRecord;

public class UserWishlistQuery extends Query
{
    public List<BookRecord> storedBookRecords;
    public String           userNetID;


    public UserWishlistQuery(Connection dbConnection, String netID)
    {
        super(dbConnection);
        userNetID = netID;
    }


    @Override
    public String getQueryString()
    {
        // Formulate the query string based on the transactionID.
        StringBuilder queryStringBuilder = new StringBuilder();
        queryStringBuilder.append("SELECT * FROM cometbooks.wishlist NATURAL JOIN cometbooks.book WHERE netID = '");
        queryStringBuilder.append(userNetID);
        queryStringBuilder.append("';");

        return queryStringBuilder.toString();
    }


    public List<BookRecord> getWishlistBookRecords()
    {
        return storedBookRecords;
    }


    @Override
    public void processResult(ResultSet resultSet)
    {
        try
        {
            List<BookRecord> retrievedBookRecords = new ArrayList<>();
            while(resultSet.next())
            {
                BookRecord bookRecord = new BookRecord(resultSet);
                retrievedBookRecords.add(bookRecord);
            }
            setBookRecords(retrievedBookRecords);
        }
        catch(SQLException ex)
        {
        }
    }

    
    public void setBookRecords(List<BookRecord> bookRecords)
    {
        storedBookRecords = bookRecords;
    }
}
