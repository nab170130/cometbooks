package project.query;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestUpdateListingHoldStatusQuery extends TestQueryBase
{
    @BeforeEach
    public void init()
    {
        initializeDBState();
    }

    
    @Test
    public void testGetQueryString()
    {
        UpdateListingHoldStatusQuery query  = new UpdateListingHoldStatusQuery(dbConnection, 1);
        String retrievedQueryString         = query.getQueryString();

        assertEquals("UPDATE cometbooks.listing SET onHold = 1 WHERE listingID = 1;", retrievedQueryString);
    }


    @Test
    public void testDoQuery()
    {
        UpdateListingHoldStatusQuery query  = new UpdateListingHoldStatusQuery(dbConnection, 1);
        query.doQuery();

        // Check and see if there's a record in the DB now.
        try(Statement statement = dbConnection.createStatement())
        {
            ResultSet results = statement.executeQuery("SELECT * FROM cometbooks.listing WHERE listingID = 1");
            results.next();
            assertEquals(true, results.getBoolean("onHold"));
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}
