package project.adapter;

import java.util.ArrayList;
import java.util.List;

import project.core.SalesListing;
import project.core.Textbook;
import project.core.Transaction;
import project.query.CreateTransactionQuery;
import project.query.UpdateTransactionStatusQuery;
import project.query.UserTransactionQuery;
import project.record.BookRecord;
import project.record.ListingRecord;
import project.record.TransactionRecord;

public class TransactionDBAdapter extends DBAdapter 
{
    private static final TransactionDBAdapter instance = new TransactionDBAdapter(HOST, PORT);

    public TransactionDBAdapter(String host, int portNumber)
    {
        super(host, portNumber);
    }

    public static TransactionDBAdapter getInstance()
    {
        return instance;
    }

    public List<Transaction> getTransactionsWithNetID(String netID)
    {
        UserTransactionQuery userTransactionQuery = new UserTransactionQuery(connection, netID);
        userTransactionQuery.doQuery();

        List<TransactionRecord> transactionRecords  = userTransactionQuery.getTransactionRecords();
        List<ListingRecord> listingRecords          = userTransactionQuery.getListingRecords();
        List<BookRecord> bookRecords                = userTransactionQuery.getBookRecords();

        List<Transaction> transactions = new ArrayList<>();

        for(int i = 0; i < bookRecords.size(); i++)
        {
            // Reconstruct the transaction object by building up from its components
            Textbook textbook       = new Textbook(bookRecords.get(i));
            textbook.setSuggestedPrice();
            SalesListing listing    = new SalesListing(listingRecords.get(i), textbook);
            Transaction transaction = new Transaction(transactionRecords.get(i), listing);
            transactions.add(transaction);
        }

        return transactions;
    }

    public long storeTransaction(Transaction transaction)
    {
        TransactionRecord transactionRecord             = new TransactionRecord(transaction);
        CreateTransactionQuery createTransactionQuery   = new CreateTransactionQuery(connection, transactionRecord);
        createTransactionQuery.doQuery();
        return createTransactionQuery.newTransactionID;
    }

    public void markBuyerComplete(Transaction transaction)
    {
        UpdateTransactionStatusQuery updateTransactionStatusQuery = new UpdateTransactionStatusQuery(connection, true, transaction.transactionID);
        updateTransactionStatusQuery.doQuery();
    }

    public void markSellerComplete(Transaction transaction)
    {
        UpdateTransactionStatusQuery updateTransactionStatusQuery = new UpdateTransactionStatusQuery(connection, false, transaction.transactionID);
        updateTransactionStatusQuery.doQuery();
    }
}
