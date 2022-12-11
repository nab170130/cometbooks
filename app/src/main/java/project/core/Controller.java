package project.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import project.adapter.BookDBAdapter;
import project.server.NotificationServer;

public class Controller 
{
	public User 		user;
	public Conversation focusedConversation;
	private Socket 		notificationSocket;
	public BufferedReader notificationReceptor;
	public BufferedWriter notificationTransmitter;


	public Controller()
	{
	}


	public boolean login(String netID, String password)
	{
		try
		{
			// Attempt to establish a user. Additionally, try to connect to the notification server. Send the user's netID.
			user = new User(netID, password);
			notificationSocket = new Socket("localhost", NotificationServer.SERVER_PORT);

			notificationReceptor 	= new BufferedReader(new InputStreamReader(notificationSocket.getInputStream()));
			notificationTransmitter = new BufferedWriter(new OutputStreamWriter(notificationSocket.getOutputStream()));

			notificationTransmitter.write(netID);
			notificationTransmitter.write("\n");
			notificationTransmitter.flush();
		}
		catch(Exception ex)
		{
			return false;
		}

		return true;
	}


	public List<SalesListing> selectBook(Textbook selectedTextbook)
	{
		return selectedTextbook.getMatchingListings();
	}
	

	public List<Textbook> performSearch(HashMap<String, String> searchParameters)
	{
		return BookDBAdapter.getInstance().getMatchingBooks(searchParameters);
	}
	

	public List<Conversation> navigateToConversationWindow()
	{
		List<Transaction> userTransactions 	= user.getTransactions();
		List<Conversation> conversations 	= new ArrayList<>();

		for(Transaction transaction : userTransactions)
		{
			Conversation transactionConversation = transaction.getConversation();
			conversations.add(transactionConversation);
		}

		return conversations;
	}
	

	public List<Transaction> navigateToTransactionsWindow()
	{
		return user.getTransactions();
	}
	

	public List<Textbook> navigateToBuyWindow()
	{
		try
		{
			return user.getRecommendedTextbooks();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return new ArrayList<>();
		}
	}
	

	public Conversation checkoutListing(SalesListing selectedListing)
	{
		selectedListing.placeOnHold();

		try
		{
			Transaction t 		= new Transaction(new Buyer(user), selectedListing);
			focusedConversation = new Conversation(t);
			sendMessage("Hello, I am interested in your listing.");
		}
		catch(Exception ex)
		{
		}

		return focusedConversation;
	}
	

	public List<Textbook> addBookToWishlist(Textbook selectedBook)
	{
		try
		{
			return user.addBookToWishlist(selectedBook);
		}
		catch(Exception ex)
		{
			return new ArrayList<>();
		}
	}
	

	public void completeTransaction(Transaction buyerSellerTransaction)
	{
		if(user.account.netID.equals(buyerSellerTransaction.buyer.account.netID))
		{
			buyerSellerTransaction.markBuyerComplete();
		}
		else if(user.account.netID.equals(buyerSellerTransaction.listing.seller.account.netID))
		{
			buyerSellerTransaction.markSellerComplete();
		}
	}
	

	public Conversation sendMessage(String enteredMessage)
	{
		// Add the message to the DB. Alert the notification server about the netID that should update their conversations.
		focusedConversation.addMessage(enteredMessage, user);
		
		try
		{
			String possibleNetIDTarget 	= focusedConversation.transaction.buyer.account.netID;
			String possibleNetIDTarget2	= focusedConversation.transaction.listing.seller.account.netID;
			
			// Write the netID of the recipient.
			if(user.account.netID.equals(possibleNetIDTarget))
			{
				notificationTransmitter.write(possibleNetIDTarget2);
			}
			else
			{
				notificationTransmitter.write(possibleNetIDTarget);
			}

			notificationTransmitter.write("\n");
			notificationTransmitter.flush();
		}
		catch(IOException ex)
		{
			
		}

		return focusedConversation;
	}

	
	public Conversation setFocusedConversation(Conversation focusedConversation_)
	{
		Conversation upToDateConversation 	= focusedConversation_.transaction.getConversation();
		focusedConversation 				= upToDateConversation;
		return focusedConversation;
	}
}
