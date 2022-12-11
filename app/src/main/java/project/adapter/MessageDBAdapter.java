package project.adapter;

import java.util.List;

import project.core.Conversation;
import project.core.Message;
import project.core.SalesListing;
import project.core.Textbook;
import project.core.Transaction;
import project.query.MessageConversationQuery;
import project.query.StoreMessageQuery;
import project.record.BookRecord;
import project.record.ListingRecord;
import project.record.MessageRecord;
import project.record.TransactionRecord;

public class MessageDBAdapter extends DBAdapter 
{
    private static final MessageDBAdapter instance = new MessageDBAdapter(HOST, PORT);

    public MessageDBAdapter(String host, int portNumber)
    {
        super(host, portNumber);
    }

    public static MessageDBAdapter getInstance()
    {
        return instance;
    }

    public Conversation getConversationFromTransaction(long transactionID)
    {
        MessageConversationQuery messageConversationQuery = new MessageConversationQuery(connection, transactionID);
        messageConversationQuery.doQuery();

        List<MessageRecord> messageRecords  = messageConversationQuery.getMessageRecords();
        TransactionRecord transactionRecord = messageConversationQuery.getTransactionRecord();
        ListingRecord listingRecord         = messageConversationQuery.getListingRecord();
        BookRecord bookRecord               = messageConversationQuery.getBookRecord();

        // Reconstruct the transaction object by building up from its components
        Textbook textbook       = new Textbook(bookRecord);
        textbook.setSuggestedPrice();
        SalesListing listing    = new SalesListing(listingRecord, textbook);
        Transaction transaction = new Transaction(transactionRecord, listing);

        Conversation conversation = new Conversation(messageRecords, transaction);

        return conversation;
    }

    public void storeMessage(Message message)
    {
        MessageRecord messageRecord         = new MessageRecord(message);
        StoreMessageQuery storeMessageQuery = new StoreMessageQuery(connection, messageRecord);
        storeMessageQuery.doQuery();
    }
}
