package project.query;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.record.WishlistRecord;

public class TestAddWishlistQuery extends TestQueryBase
{
    @BeforeEach
    public void init()
    {
        initializeDBState();
    }


    @Test
    public void testGetQueryString()
    {
        WishlistRecord wishlistRecord   = new WishlistRecord("nab170130", 12345);
        AddWishlistQuery query          = new AddWishlistQuery(dbConnection, wishlistRecord);
        String retrievedQueryString     = query.getQueryString();

        assertEquals(retrievedQueryString, "INSERT INTO cometbooks.wishlist VALUES ('nab170130',12345);");
    }

    
    @Test
    public void testDoQuery()
    {
        WishlistRecord wishlistRecord   = new WishlistRecord("nab170130", 12345);
        AddWishlistQuery query          = new AddWishlistQuery(dbConnection, wishlistRecord);
        query.doQuery();

        // Check and see if there's a record in the DB now.
        try(Statement statement = dbConnection.createStatement())
        {
            ResultSet results = statement.executeQuery("SELECT * FROM cometbooks.wishlist WHERE isbn=12345;");
            results.next();
            assertEquals("nab170130", results.getString("netID"));
            assertEquals(12345, results.getInt("isbn"));
        }
        catch(SQLException ex)
        {
        }
    }
}
