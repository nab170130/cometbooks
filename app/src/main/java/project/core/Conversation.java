package project.core;

import java.util.ArrayList;
import java.util.List;

import project.record.MessageRecord;

public class Conversation 
{
	public long 			ID;
	public Transaction 		transaction;
	public List<Message>	messages;

	public Conversation()
	{
	}

	public Conversation(List<MessageRecord> messageRecords) 
	{
		
	}
	
	public Conversation(Transaction transaction_)
	{
		ID 			= transaction_.transactionID;
		transaction = transaction_;
		messages 	= new ArrayList<>();
	}

	public void addMessage(Message enteredMessage, User user) 
	{
		
	}
}
