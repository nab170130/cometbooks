package project.core;

public class Message 
{
	public String messageContent;
	public long dateTimeSent;
	public long transactionID;
	public User author;
	
	public Message()
	{
		
	}

	public Message(Message messageRecord) 
	{ 
		Message message = new Message(messageRecord);
		return message;
	}
	
	public Message(String enteredMesage, long transactionID) 
	{
		Message message = new Message(enteredMesage, transactionID);
		message.setDateAndTime(dateTimeSent);
		return message;
		
	}

	// public setDateAndTime(long dateTimeSent)
	// {
		
	// } do we need to add this?
	
}
