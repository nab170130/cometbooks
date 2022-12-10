package project.core;

import java.util.*;

import project.adapter.TransactionDBAdapter;

public class User 
{
	public Account account;//ACCOUNT is a class

	public User() 
	{
			
	}

	public User(String netID) 
	{
		account = new Account(netID);
	}

	public List<Transaction> getTransactions()
	{
		return account.getTransactionsFromAccount();
	}

	public List<Textbook> addBookToWishlist(Textbook selectedBook) 
	{
		account.addBookToWishlist(selectedBook);
		return getRecommendedTextbooks();
	}

	public List<Textbook> getRecommendedTextbooks()
	{
		List<Textbook> recommendedTextbooks = new ArrayList<>();
		Schedule userSchedule 				= account.getUserSchedule();
		Wishlist userWishlist				= account.getWishlist();

		recommendedTextbooks.addAll(userWishlist.textbooks);
		for(Course course : userSchedule.courses)
		{
			recommendedTextbooks.addAll(course.textbooks);
		}

		return recommendedTextbooks;
	}
}
