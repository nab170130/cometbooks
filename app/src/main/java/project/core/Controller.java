package project.core;

public class Controller 
{
	public List<SalesListing> selectBook(Textbook selectedTextbook)
	{
		return selectedTextbook.getMatchingListings();
	}
	
	// public HashMap<String, String> openSearchDialog()
	// {
		
	// }
	
	public List<Textbook> performSearch(HashMap<String, String> searchParameters)
	{
		return BookDBAdapter.getInstance().getMatchingBooks(searchParameters);
	}
	// Needs to completed
	public List<Conversation> navigateToConversationWindow()
	{
		Conversation cn = new Transaction().getConversation();
	}
	
	public List<Transaction> navigateToTransactionsWindow()
	{
		return new User().getTransactions();
	}
	
	public List<Textbook> navigateToBuyWindow()
	{
		return new Buyer().getRecommendedTextbooks();
	}
	
	public Conversation checkoutListing(SalesListing selectedListing)
	{
		selectedListing.placeOnHold();
		Transaction t = new Transaction(buyer, selectedListing); // need to add Buyer as member variable for controller class
		return new Conversation(); // ?????
	}
	
	public List<Textbook> addBookToWishlist(Textbook selectedBook)
	{
		return new Buyer().addBookToWishlist(selectedBook); // which user is this method creating? no netid?
	}
	
	public void completeTransaction(Transaction buyerSellerTransaction)
	{
		
		// will write after we add buyer and seller as data members
	}
	
	public Conversation sendMessage(Message enteredMessage)
	{
		// will write after we add buyer and seller as data members
	}
	
	public void selectConversation(Conversation conversation)
	{
		
	}
	
	public void selectTransaction(Transaction buyerSellerTransaction)
	{
		
	}
}
