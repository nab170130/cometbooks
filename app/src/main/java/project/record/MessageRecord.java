package project.record;

import java.sql.ResultSet;
import java.sql.SQLException;

import project.core.Message;

public class MessageRecord implements Comparable<MessageRecord>
{
    public String   authorNetID;
    public long     dateTimeSent;
    public String   messageContent;
    public long     transactionID;


    public MessageRecord(ResultSet resultSet)
    {
        // Pull information from the currently focused tuple.
        try
        {
            authorNetID     = resultSet.getString("authorNetID");
            dateTimeSent    = resultSet.getLong("dateTimeSent");
            messageContent  = resultSet.getString("messageContent");
            transactionID   = resultSet.getLong("transactionID");
        }
        catch(SQLException ex)
        {
        }
    }


    public MessageRecord(Message message)
    {
        authorNetID     = message.author.account.netID;
        dateTimeSent    = message.dateTimeSent;
        messageContent  = message.messageContent;
        transactionID   = message.transactionID;
    }


    public MessageRecord()
    {
    }

    
    @Override
    public int compareTo(MessageRecord otherRecord)
    {
        if(dateTimeSent < otherRecord.dateTimeSent)
        {
            return -1;
        }
        else if(dateTimeSent == otherRecord.dateTimeSent)
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }
}
