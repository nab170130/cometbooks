package project.query;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.record.MessageRecord;

public class TestMessageConversationQuery extends TestQueryBase
{
    @BeforeEach
    public void init()
    {
        initializeDBState();
    }

    @Test
    public void testGetQueryString()
    {
        MessageConversationQuery query  = new MessageConversationQuery(dbConnection, 1);
        String retrievedQueryString     = query.getQueryString();

        assertEquals("SELECT * FROM cometbooks.message WHERE transactionID = 1;", retrievedQueryString);
    }

    @Test
    public void testDoQuery()
    {
        MessageConversationQuery query  = new MessageConversationQuery(dbConnection, 1);
        query.doQuery();

        // Check to see if the correct records are present.
        List<MessageRecord> records = query.getMessageRecords();
        
        MessageRecord firstRecord = records.get(0);
        assertEquals("nab170130",               firstRecord.authorNetID);
        assertEquals(1,                         firstRecord.dateTimeSent);
        assertEquals("Hey, I want your book.",  firstRecord.messageContent);
        assertEquals(1,                         firstRecord.transactionID);

        MessageRecord secondRecord = records.get(1);
        assertEquals("abc123456",               secondRecord.authorNetID);
        assertEquals(2,                         secondRecord.dateTimeSent);
        assertEquals("Okay, sure.",             secondRecord.messageContent);
        assertEquals(1,                         secondRecord.transactionID);
    }
}
