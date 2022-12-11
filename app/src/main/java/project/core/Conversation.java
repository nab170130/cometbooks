package project.core;

import java.util.ArrayList;
import java.util.List;

import project.adapter.MessageDBAdapter;
import project.record.MessageRecord;

public class Conversation 
{
	public long 			ID;
	public Transaction 		transaction;
	public List<Message>	messages;


	public Conversation(List<MessageRecord> messageRecords, Transaction transaction_) 
	{
		this.ID 			= transaction_.transactionID;
		this.transaction 	= transaction_;
		this.messages 		= new ArrayList<>();

		for(MessageRecord record : messageRecords)
		{
			Message newMessage = new Message(record);
			this.messages.add(newMessage);
		}
	}
	

	public Conversation(Transaction transaction_)
	{
		this.ID 			= transaction_.transactionID;
		this.transaction 	= transaction_;
		this.messages 		= new ArrayList<>();
	}

	
	public void addMessage(String enteredMessage, User user) 
	{
		Message newMessageToAdd = new Message(enteredMessage, ID, user);
		this.messages.add(newMessageToAdd);
		MessageDBAdapter.getInstance().storeMessage(newMessageToAdd);
	}
}
