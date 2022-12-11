package project.query;

import java.sql.Connection;
import java.sql.ResultSet;

import project.record.MessageRecord;

public class StoreMessageQuery extends Query
{
    public MessageRecord messageRecordToStore;


    public StoreMessageQuery(Connection connection, MessageRecord messageRecord)
    {
        super(connection);
        messageRecordToStore = messageRecord;
    }


    @Override
    public String getQueryString()
    {
        // Formulate the query string based on the transactionID.
        StringBuilder queryStringBuilder = new StringBuilder();
        queryStringBuilder.append("INSERT INTO cometbooks.message VALUES (");
        queryStringBuilder.append(messageRecordToStore.transactionID);
        queryStringBuilder.append(",'");
        queryStringBuilder.append(messageRecordToStore.authorNetID);
        queryStringBuilder.append("',");
        queryStringBuilder.append(messageRecordToStore.dateTimeSent);
        queryStringBuilder.append(",'");
        queryStringBuilder.append(messageRecordToStore.messageContent);
        queryStringBuilder.append("');");

        return queryStringBuilder.toString();
    }

    
    @Override
    public void processResult(ResultSet resultSet)
    {
    }
}
