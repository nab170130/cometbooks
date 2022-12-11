package project.query;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.record.TransactionRecord;

public class TestCreateTransactionQuery extends TestQueryBase 
{
    @BeforeEach
    public void init()
    {
        initializeDBState();
    }


    @Test
    public void testGetQueryString()
    {
        TransactionRecord transactionRecord   = new TransactionRecord();
        transactionRecord.transactionID     = 0;
        transactionRecord.listingID         = 1;
        transactionRecord.completionStatus  = "";
        transactionRecord.buyerNetID        = "xyz654321";

        CreateTransactionQuery query = new CreateTransactionQuery(dbConnection, transactionRecord);
        String retrievedQueryString     = query.getQueryString();

        assertEquals("INSERT INTO cometbooks.transaction VALUES (0,1,'','xyz654321');", retrievedQueryString);
    }

    
    @Test
    public void testDoQuery()
    {
        TransactionRecord transactionRecord = new TransactionRecord();
        transactionRecord.transactionID     = 0;
        transactionRecord.listingID         = 1;
        transactionRecord.completionStatus  = "";
        transactionRecord.buyerNetID        = "not000000";

        CreateTransactionQuery query = new CreateTransactionQuery(dbConnection, transactionRecord);
        query.doQuery();

        // Check and see if there's a record in the DB now.
        try(Statement statement = dbConnection.createStatement())
        {
            ResultSet results = statement.executeQuery("SELECT * FROM cometbooks.transaction ORDER BY transactionID DESC LIMIT 1");
            results.next();
            assertEquals("not000000", results.getString("buyerNetID"));
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}
