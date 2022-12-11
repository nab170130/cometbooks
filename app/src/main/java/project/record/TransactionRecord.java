package project.record;

import java.sql.ResultSet;
import java.sql.SQLException;

import project.core.Transaction;

public class TransactionRecord 
{
    public String   buyerNetID;
    public String   completionStatus;
    public long     listingID;
    public long     transactionID;


    public TransactionRecord(ResultSet resultSet)
    {
        // Pull information from the currently focused tuple.
        try
        {
            buyerNetID          = resultSet.getString("buyerNetID");
            completionStatus    = resultSet.getString("completionStatus");
            listingID           = resultSet.getLong("listingID");
            transactionID       = resultSet.getLong("transactionID");
        }
        catch(SQLException ex)
        {
        }
    }


    public TransactionRecord(Transaction transaction)
    {
        buyerNetID          = transaction.buyer.account.netID;
        completionStatus    = transaction.completionStatus;
        listingID           = transaction.listing.listingID;
        transactionID       = transaction.getTransactionID();
    }

    
    public TransactionRecord()
    {
    }
}
