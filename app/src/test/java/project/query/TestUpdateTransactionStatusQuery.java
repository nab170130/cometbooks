package project.query;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestUpdateTransactionStatusQuery extends TestQueryBase
{
    @BeforeEach
    public void init()
    {
        initializeDBState();
    }

    @Test
    public void testGetQueryString()
    {
        UpdateTransactionStatusQuery query  = new UpdateTransactionStatusQuery(dbConnection, true, 1);
        String retrievedQueryString         = query.getQueryString();
        assertEquals("UPDATE cometbooks.transaction SET completionStatus = CONCAT('buyer', completionStatus) WHERE transactionID = 1;", retrievedQueryString);

        // Try the buyerComplete = false case.
        query                   = new UpdateTransactionStatusQuery(dbConnection, false, 1);
        retrievedQueryString    = query.getQueryString();
        assertEquals("UPDATE cometbooks.transaction SET completionStatus = CONCAT(completionStatus, 'seller') WHERE transactionID = 1;", retrievedQueryString);
    }

    @Test
    public void testDoQuery()
    {
        UpdateTransactionStatusQuery query = new UpdateTransactionStatusQuery(dbConnection, true, 1);
        query.doQuery();

        // Check and see if there's a record in the DB now.
        try(Statement statement = dbConnection.createStatement())
        {
            ResultSet results = statement.executeQuery("SELECT * FROM cometbooks.transaction WHERE transactionID = 1");
            results.next();
            assertEquals("buyer", results.getString("completionStatus"));
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }

        // Also try finishing the seller as well.
        query = new UpdateTransactionStatusQuery(dbConnection, false, 1);
        query.doQuery();

        // Check and see if there's a record in the DB now.
        try(Statement statement = dbConnection.createStatement())
        {
            ResultSet results = statement.executeQuery("SELECT * FROM cometbooks.transaction WHERE transactionID = 1");
            results.next();
            assertEquals("buyerseller", results.getString("completionStatus"));
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}
