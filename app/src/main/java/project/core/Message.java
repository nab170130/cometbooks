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
		Message message ;
		message.add(messageContent);
		message.add(dateTimeSent);
		return message;
	}
	
}
