package project.core;

public class Message {
	String messageContent;
	long dateTimeSent;
	long transactionID;
	
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
