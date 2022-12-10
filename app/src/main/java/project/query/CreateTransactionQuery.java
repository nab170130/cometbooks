package project.query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import project.record.TransactionRecord;

public class CreateTransactionQuery extends Query
{
    public TransactionRecord transactionRecordToStore;
    public long newTransactionID;

    public CreateTransactionQuery(Connection connection, TransactionRecord transactionRecord)
    {
        super(connection);
        transactionRecordToStore = transactionRecord;
    }

    @Override
    public String getQueryString()
    {
        // Formulate the query string based on the transactionID.
        StringBuilder queryStringBuilder = new StringBuilder();
        queryStringBuilder.append("INSERT INTO cometbooks.transaction VALUES (");
        queryStringBuilder.append(transactionRecordToStore.transactionID);
        queryStringBuilder.append(",");
        queryStringBuilder.append(transactionRecordToStore.listingID);
        queryStringBuilder.append(",'");
        queryStringBuilder.append(transactionRecordToStore.completionStatus);
        queryStringBuilder.append("','");
        queryStringBuilder.append(transactionRecordToStore.buyerNetID);
        queryStringBuilder.append("');");

        return queryStringBuilder.toString();
    }

    @Override
    public void processResult(ResultSet resultSet)
    {
        // We need to additionally fetch the newly-generated transactionID created earlier.
        StringBuilder queryStringBuilder = new StringBuilder();
        queryStringBuilder.append("SELECT transactionID FROM cometbooks.transaction WHERE listingID = ");
        queryStringBuilder.append(transactionRecordToStore.listingID);
        queryStringBuilder.append(";");

        ResultSet newResultSet = executeSQL(queryStringBuilder.toString());
        try
        {
            newResultSet.next();
            newTransactionID = newResultSet.getLong("transactionID");
        }
        catch(SQLException ex)
        {
        }
    }
}
