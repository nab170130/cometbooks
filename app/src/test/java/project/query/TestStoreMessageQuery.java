package project.query;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.record.MessageRecord;

public class TestStoreMessageQuery extends TestQueryBase
{
    @BeforeEach
    public void init()
    {
        initializeDBState();
    }

    @Test
    public void testGetQueryString()
    {
        MessageRecord messageRecord     = new MessageRecord();
        messageRecord.authorNetID       = "nab170130";
        messageRecord.dateTimeSent      = 20;
        messageRecord.messageContent    = "Hey, I am present at the library.";
        messageRecord.transactionID     = 1;

        StoreMessageQuery query     = new StoreMessageQuery(dbConnection, messageRecord);
        String retrievedQueryString = query.getQueryString();

        assertEquals("INSERT INTO cometbooks.message VALUES (1,'nab170130',20,'Hey, I am present at the library.');", retrievedQueryString);
    }

    @Test
    public void testDoQuery()
    {
        MessageRecord messageRecord     = new MessageRecord();
        messageRecord.authorNetID       = "nab170130";
        messageRecord.dateTimeSent      = 20;
        messageRecord.messageContent    = "Hey, I am present at the library.";
        messageRecord.transactionID     = 1;

        StoreMessageQuery query = new StoreMessageQuery(dbConnection, messageRecord);
        query.doQuery();

        // Check and see if there's a record in the DB now.
        try(Statement statement = dbConnection.createStatement())
        {
            ResultSet results = statement.executeQuery("SELECT * FROM cometbooks.message ORDER BY dateTimeSent DESC LIMIT 1");
            results.next();
            assertEquals(1,                                     results.getInt("transactionID"));
            assertEquals("nab170130",                           results.getString("authorNetID"));
            assertEquals(20,                                    results.getInt("dateTimeSent"));
            assertEquals("Hey, I am present at the library.",   results.getString("messageContent"));
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}
