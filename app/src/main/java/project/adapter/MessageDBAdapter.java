package project.adapter;

import java.util.List;

import project.core.Conversation;
import project.core.Message;
import project.query.MessageConversationQuery;
import project.query.StoreMessageQuery;
import project.record.MessageRecord;

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
        List<MessageRecord> messageRecords = messageConversationQuery.getMessageRecords();

        Conversation conversation = new Conversation(messageRecords);

        return conversation;
    }

    public void storeMessage(Message message)
    {
        MessageRecord messageRecord         = new MessageRecord(message);
        StoreMessageQuery storeMessageQuery = new StoreMessageQuery(connection, messageRecord);
        storeMessageQuery.doQuery();
    }
}
