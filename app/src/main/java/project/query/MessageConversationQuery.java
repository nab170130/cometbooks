package project.query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import project.record.MessageRecord;

public class MessageConversationQuery extends Query
{
    public List<MessageRecord>  retrievedMessageRecords;
    public long                 transactionID;


    public MessageConversationQuery(Connection dbConnection, long transactionID_)
    {
        super(dbConnection);
        transactionID = transactionID_;
    }


    public List<MessageRecord> getMessageRecords()
    {
        return retrievedMessageRecords;
    }


    @Override
    public String getQueryString()
    {
        // Formulate the query string based on the transactionID.
        StringBuilder queryStringBuilder = new StringBuilder();
        queryStringBuilder.append("SELECT * FROM cometbooks.message WHERE transactionID = ");
        queryStringBuilder.append(transactionID);
        queryStringBuilder.append(";");

        return queryStringBuilder.toString();
    }


    @Override
    public void processResult(ResultSet resultSet)
    {
        try
        {
            List<MessageRecord> retrievedMessageRecords = new ArrayList<>();

            while(resultSet.next())
            {
                MessageRecord newMessageRecord = new MessageRecord(resultSet);
                retrievedMessageRecords.add(newMessageRecord);
            }

            Collections.sort(retrievedMessageRecords);
            setMessageRecords(retrievedMessageRecords);
        }
        catch(SQLException ex)
        {
        }
    }

    
    public void setMessageRecords(List<MessageRecord> newMessageRecords)
    {
        retrievedMessageRecords = newMessageRecords;
    }
}
