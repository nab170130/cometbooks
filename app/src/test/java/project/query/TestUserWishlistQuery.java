package project.query;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.record.BookRecord;

public class TestUserWishlistQuery extends TestQueryBase
{
    @BeforeEach
    public void init()
    {
        initializeDBState();
    }

    
    @Test
    public void testGetQueryString()
    {
        UserWishlistQuery query     = new UserWishlistQuery(dbConnection, "nab170130");
        String retrievedQueryString = query.getQueryString();

        assertEquals("SELECT * FROM cometbooks.wishlist NATURAL JOIN cometbooks.book WHERE netID = 'nab170130';", retrievedQueryString);
    }


    @Test
    public void testDoQuery()
    {
        UserWishlistQuery query = new UserWishlistQuery(dbConnection, "nab170130");
        query.doQuery();

        // Check to see if the correct records are present.
        List<BookRecord> records = query.getWishlistBookRecords();
        
        BookRecord firstBookRecord = records.get(0);
        assertEquals("Nathan Beck", firstBookRecord.authors[0]);
        assertEquals(1,             firstBookRecord.edition);
        assertEquals(12345,         firstBookRecord.isbn);
        assertEquals("Test Title",  firstBookRecord.title);
        assertEquals(1999,          firstBookRecord.year);

        BookRecord secondBookRecord = records.get(1);
        assertEquals("Nathan Beck",     secondBookRecord.authors[0]);
        assertEquals(2,                 secondBookRecord.edition);
        assertEquals(12346,             secondBookRecord.isbn);
        assertEquals("Test Title 2",    secondBookRecord.title);
        assertEquals(2000,              secondBookRecord.year);

        // Check that only two results were obtained.
        assertEquals(records.size(), 2);
    }
}
