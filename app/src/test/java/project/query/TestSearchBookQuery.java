package project.query;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.record.BookRecord;

public class TestSearchBookQuery extends TestQueryBase
{
    @BeforeEach
    public void init()
    {
        initializeDBState();
    }


    @Test
    public void testGetQueryString()
    {
        HashMap<String, String> exampleParams = new HashMap<>();
        exampleParams.put("isbn", "12345");
        exampleParams.put("author", "Nathan Beck");

        SearchBookQuery query       = new SearchBookQuery(dbConnection, exampleParams);
        String retrievedQueryString = query.getQueryString();

        assertEquals("SELECT * FROM cometbooks.book WHERE author = 'Nathan Beck' AND isbn = 12345;", retrievedQueryString);
    }

    
    @Test
    public void testDoQuery()
    {
        HashMap<String, String> exampleParams = new HashMap<>();
        exampleParams.put("author", "Nathan Beck");
        SearchBookQuery query = new SearchBookQuery(dbConnection, exampleParams);
        query.doQuery();

        // Check to see if the correct records are present.
        List<BookRecord> records = query.getBookRecords();
        
        BookRecord firstRecord = records.get(0);
        assertEquals("Nathan Beck", firstRecord.authors[0]);
        assertEquals(1,             firstRecord.edition);
        assertEquals(12345,         firstRecord.isbn);
        assertEquals("Test Title",  firstRecord.title);
        assertEquals(1999,          firstRecord.year);

        BookRecord secondRecord = records.get(1);
        assertEquals("Nathan Beck", secondRecord.authors[0]);
        assertEquals(2,             secondRecord.edition);
        assertEquals(12346,         secondRecord.isbn);
        assertEquals("Test Title 2",  secondRecord.title);
        assertEquals(2000,          secondRecord.year);

        // Check that only two results were obtained.
        assertEquals(records.size(), 2);
    }
}
