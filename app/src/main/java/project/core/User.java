package project.core;

import java.util.*;

import org.w3c.dom.Text;

import project.actor.NoAccountException;
import project.actor.NoCourseException;
import project.actor.NoScheduleException;

public class User 
{
	public Account account;//ACCOUNT is a class

	public User(String netID) throws NoAccountException
	{
		account = new Account(netID);
	}

	public User(String netID, String password) throws NoAccountException
	{
		account = new Account(netID, password);
	}

	public List<Transaction> getTransactions()
	{
		return account.getTransactionsFromAccount();
	}

	public List<Textbook> addBookToWishlist(Textbook selectedBook) throws NoScheduleException, NoCourseException
	{
		account.addBookToWishlist(selectedBook);
		return getRecommendedTextbooks();
	}

	public List<Textbook> getRecommendedTextbooks() throws NoScheduleException, NoCourseException
	{
		List<Textbook> recommendedTextbooks = new ArrayList<>();
		Schedule userSchedule 				= account.getUserSchedule();
		Wishlist userWishlist				= account.getWishlist();

		recommendedTextbooks.addAll(userWishlist.textbooks);
		for(Course course : userSchedule.courses)
		{
			recommendedTextbooks.addAll(course.textbooks);
		}

		// Remove duplicates.
		Set<Textbook> setOfTextbooks 	= new TreeSet<>(recommendedTextbooks);
		recommendedTextbooks 			= new ArrayList<>();
		for(Textbook textbook : setOfTextbooks)
		{
			recommendedTextbooks.add(textbook);
		}

		return recommendedTextbooks;
	}
}
