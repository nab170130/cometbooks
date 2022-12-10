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
			
	}

	public List<Transaction> getTransactions()
	{
		return account.getTransactionsFromAccount();
	}

	public List<Textbook> addBookToWishlist(Textbook selectedBook) 
	{
		return null;
	}

	public List<Textbook> getRecommendedTextbooks()
	{
		return new ArrayList<>();
	}
}
