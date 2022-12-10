package project.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import project.adapter.BookDBAdapter;

public class Controller 
{
	public User 		user;
	public Conversation focusedConversation;

	public Controller()
	{
	}

	public List<SalesListing> selectBook(Textbook selectedTextbook)
	{
		return selectedTextbook.getMatchingListings();
	}
	
	public List<Textbook> performSearch(HashMap<String, String> searchParameters)
	{
		return BookDBAdapter.getInstance().getMatchingBooks(searchParameters);
	}
	// Needs to completed
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
		return user.getRecommendedTextbooks();
	}
	
	public Conversation checkoutListing(SalesListing selectedListing)
	{
		selectedListing.placeOnHold();
		Transaction t 		= new Transaction(new Buyer(user), selectedListing); // need to add Buyer as member variable for controller class
		focusedConversation = new Conversation(t);

		Message initialMessage = new Message("Hello, I am interested in your listing.", t.transactionID, user);
		focusedConversation.addMessage(initialMessage, user);

		return focusedConversation;
	}
	
	public List<Textbook> addBookToWishlist(Textbook selectedBook)
	{
		return user.addBookToWishlist(selectedBook);
	}
	
	public void completeTransaction(Transaction buyerSellerTransaction)
	{
		if(user.account.netID == buyerSellerTransaction.buyer.account.netID)
		{
			buyerSellerTransaction.markBuyerComplete();
		}
		else if(user.account.netID == buyerSellerTransaction.listing.seller.account.netID)
		{
			buyerSellerTransaction.markSellerComplete();
		}
	}
	
	public Conversation sendMessage(String enteredMessage)
	{
		Message initialMessage = new Message(enteredMessage, focusedConversation.ID, user);
		focusedConversation.addMessage(initialMessage, user);

		return focusedConversation;
	}
}
