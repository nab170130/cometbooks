package project.query;

import java.sql.Connection;
import java.sql.ResultSet;

public class UpdateListingHoldStatusQuery extends Query
{
    public long listingID;
    

    public UpdateListingHoldStatusQuery(Connection dbConnection, long listingID_)
    {
        super(dbConnection);
        listingID = listingID_;
    }


    @Override
    public String getQueryString()
    {
        // Formulate the query string based on the transactionID.
        StringBuilder queryStringBuilder = new StringBuilder();
        queryStringBuilder.append("UPDATE cometbooks.listing SET onHold = 1 WHERE listingID = ");
        queryStringBuilder.append(listingID);
        queryStringBuilder.append(";");

        return queryStringBuilder.toString();
    }

    
    @Override
    public void processResult(ResultSet resultSet)
    {
    }
}
