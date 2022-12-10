package project.query;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Statement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.record.BookRecord;
import project.record.TransactionRecord;

public class TestISBNBookQuery extends TestQueryBase
{
    @BeforeEach
    public void init()
    {
        initializeDBState();
    }

    @Test
    public void testGetQueryString()
    {
        ISBNBookQuery query = new ISBNBookQuery(dbConnection, 12345);
        String retrievedQueryString     = query.getQueryString();

        assertEquals("SELECT * FROM cometbooks.book WHERE isbn = 12345;", retrievedQueryString);
    }

    @Test
    public void testDoQuery()
    {
        ISBNBookQuery query = new ISBNBookQuery(dbConnection, 12345);
        query.doQuery();

        // Check to see if the correct record is in the query.
        BookRecord record = query.getBookRecord();
        assertEquals("Nathan Beck", record.authors[0]);
        assertEquals(1, record.edition);
        assertEquals(12345, record.isbn);
        assertEquals("Test Title", record.title);
        assertEquals(1999, record.year);
    }
}
