package project.query;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.record.BookRecord;
import project.record.ListingRecord;
import project.record.TransactionRecord;

public class TestUserTransactionQuery extends TestQueryBase
{
    @BeforeEach
    public void init()
    {
        initializeDBState();
    }

    @Test
    public void testGetQueryString()
    {
        UserTransactionQuery query = new UserTransactionQuery(dbConnection, "nab170130");
        String retrievedQueryString = query.getQueryString();

        assertEquals("SELECT * FROM cometbooks.book NATURAL JOIN cometbooks.listing NATURAL JOIN cometbooks.transaction WHERE (buyerNetID = 'nab170130' OR sellerNetID = 'nab170130');", retrievedQueryString);
    }

    @Test
    public void testDoQuery()
    {
        UserTransactionQuery query = new UserTransactionQuery(dbConnection, "nab170130");
        query.doQuery();

        // Check to see if the correct records are present.
        List<BookRecord> bookRecords                = query.getBookRecords();
        List<ListingRecord> listingRecords          = query.getListingRecords();
        List<TransactionRecord> transactionRecords  = query.getTransactionRecords();
  
        // First index should correspond to transaction where nab170130 is a buyer.
        BookRecord firstBookRecord                  = bookRecords.get(0);
        ListingRecord firstListingRecord            = listingRecords.get(0);
        TransactionRecord firstTransactionRecord    = transactionRecords.get(0);

        assertEquals("Nathan Beck", firstBookRecord.author);
        assertEquals(1,             firstBookRecord.edition);
        assertEquals(12345,         firstBookRecord.isbn);
        assertEquals("Test Title",  firstBookRecord.title);
        assertEquals(1999,          firstBookRecord.year);

        assertEquals("Like New",                    firstListingRecord.condition);
        assertEquals("Only a smudge on the cover",  firstListingRecord.description);
        assertEquals(12345,                         firstListingRecord.isbn);
        assertEquals(12.50,                         firstListingRecord.listingPrice);
        assertEquals(false,                         firstListingRecord.onHold);
        assertEquals("abc123456",                   firstListingRecord.sellerNetID);

        assertEquals("nab170130", firstTransactionRecord.buyerNetID);
        assertEquals("", firstTransactionRecord.completionStatus);
        assertEquals(1, firstTransactionRecord.listingID);
        assertEquals(1, firstTransactionRecord.transactionID);

        // Second index should correspond to transaction where nab170130 is a seller.
        BookRecord secondBookRecord                  = bookRecords.get(1);
        ListingRecord secondListingRecord            = listingRecords.get(1);
        TransactionRecord secondTransactionRecord    = transactionRecords.get(1);

        assertEquals("Nathan Beck", secondBookRecord.author);
        assertEquals(2,             secondBookRecord.edition);
        assertEquals(12346,         secondBookRecord.isbn);
        assertEquals("Test Title 2",  secondBookRecord.title);
        assertEquals(2000,          secondBookRecord.year);

        assertEquals("New",                    secondListingRecord.condition);
        assertEquals("Still in its plastic wrap",  secondListingRecord.description);
        assertEquals(12346,                         secondListingRecord.isbn);
        assertEquals(21.50,                         secondListingRecord.listingPrice);
        assertEquals(false,                         secondListingRecord.onHold);
        assertEquals("nab170130",                   secondListingRecord.sellerNetID);

        assertEquals("nab170132", secondTransactionRecord.buyerNetID);
        assertEquals("", secondTransactionRecord.completionStatus);
        assertEquals(3, secondTransactionRecord.listingID);
        assertEquals(3, secondTransactionRecord.transactionID);

        // Check that only two results were obtained.
        assertEquals(bookRecords.size(), 2);
        assertEquals(listingRecords.size(), 2);
        assertEquals(transactionRecords.size(), 2);
    }
}
