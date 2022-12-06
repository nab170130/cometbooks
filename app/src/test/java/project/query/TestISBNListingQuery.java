package project.query;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.record.ListingRecord;

public class TestISBNListingQuery extends TestQueryBase
{
    @BeforeEach
    public void init()
    {
        initializeDBState();
    }

    @Test
    public void testGetQueryString()
    {
        ISBNListingQuery query      = new ISBNListingQuery(dbConnection, 12345);
        String retrievedQueryString = query.getQueryString();

        assertEquals("SELECT * FROM cometbooks.listing WHERE isbn = 12345;", retrievedQueryString);
    }

    @Test
    public void testDoQuery()
    {
        ISBNListingQuery query = new ISBNListingQuery(dbConnection, 12345);
        query.doQuery();

        // Check to see if the correct records are present.
        List<ListingRecord> records = query.getListingRecords();
        
        ListingRecord firstRecord = records.get(0);
        assertEquals("Like New",                    firstRecord.condition);
        assertEquals("Only a smudge on the cover",  firstRecord.description);
        assertEquals(12345,                         firstRecord.isbn);
        assertEquals(12.50,                         firstRecord.listingPrice);
        assertEquals(false,                         firstRecord.onHold);
        assertEquals("abc123456",                   firstRecord.sellerNetID);

        ListingRecord secondRecord = records.get(1);
        assertEquals("Poor",                            secondRecord.condition);
        assertEquals("Please just take this off me",    secondRecord.description);
        assertEquals(12345,                             secondRecord.isbn);
        assertEquals(1.50,                              secondRecord.listingPrice);
        assertEquals(false,                             secondRecord.onHold);
        assertEquals("zyx654321",                       secondRecord.sellerNetID);

        // Check that only two results were obtained.
        assertEquals(records.size(), 2);
    }
}
