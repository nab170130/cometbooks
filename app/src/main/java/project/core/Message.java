package project.core;

import java.util.Date;

import project.record.MessageRecord;

public class Message 
{
	public String messageContent;
	public long dateTimeSent;
	public long transactionID;
	public User author;


	public Message(MessageRecord messageRecord) 
	{ 
		messageContent 	= messageRecord.messageContent;
		dateTimeSent 	= messageRecord.dateTimeSent;
		transactionID 	= messageRecord.transactionID;

		try
		{
			author = new User(messageRecord.authorNetID);
		}
		catch(Exception ex)
		{
		}
	}
	
	
	public Message(String enteredMessage, long transactionID_, User author_) 
	{
		messageContent 	= enteredMessage;
		dateTimeSent	= new Date().getTime();
		transactionID 	= transactionID_;
		author 			= author_;
	}
}
