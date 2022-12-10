package project.core;

import java.util.List;

import project.record.MessageRecord;

public class Conversation 
{
	public int 				ID;
	public Transaction 		transaction;
	public List<Message>	messages;


	public Conversation(List<MessageRecord> messageRecords) 
	{
		
	}
	
	public Conversation()
	{

	}

	public void addMessage(Message enteredMessage, User user) 
	{
		
	}
}
