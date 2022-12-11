package project.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import project.actor.UTDGalaxy;
import project.adapter.BookDBAdapter;
import project.record.AccountRecord;

public class Controller 
{
	public User 		user;
	public Conversation focusedConversation;

	public Controller()
	{
	}


	public boolean login(String netID, String password)
	{
		try
		{
			user = new User(netID, password);
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
			focusedConversation.addMessage("Hello, I am interested in your listing.", user);
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
		focusedConversation.addMessage(enteredMessage, user);

		return focusedConversation;
	}

	public Conversation setFocusedConversation(Conversation focusedConversation_)
	{
		Conversation upToDateConversation 	= focusedConversation_.transaction.getConversation();
		focusedConversation 				= upToDateConversation;
		return focusedConversation;
	}
}
