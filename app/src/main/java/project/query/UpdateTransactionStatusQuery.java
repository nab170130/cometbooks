package project.query;

import java.sql.Connection;
import java.sql.ResultSet;

public class UpdateTransactionStatusQuery extends Query
{
    public boolean  buyerComplete;
    public long     transactionID;


    public UpdateTransactionStatusQuery(Connection connection, boolean buyerComplete_, long transactionID_)
    {
        super(connection);
        buyerComplete = buyerComplete_;
        transactionID = transactionID_;
    }


    @Override
    public String getQueryString()
    {
        // Formulate the query string based on the transactionID.
        StringBuilder queryStringBuilder = new StringBuilder();
        if(buyerComplete)
        {
            queryStringBuilder.append("UPDATE cometbooks.transaction SET completionStatus = CONCAT('buyer', completionStatus) WHERE transactionID = ");
        }
        else
        {
            queryStringBuilder.append("UPDATE cometbooks.transaction SET completionStatus = CONCAT(completionStatus, 'seller') WHERE transactionID = ");
        }
        queryStringBuilder.append(transactionID);
        queryStringBuilder.append(";");

        return queryStringBuilder.toString();
    }

    
    @Override
    public void processResult(ResultSet resultSet)
    {
    }
}
