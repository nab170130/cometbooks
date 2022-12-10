package project.core;

import java.util.*;

public class Account 
{
	public String name;
	public String displayName;
	public String netID;
	public long utdID;
	private String password;
	public int currentAcademicYear;

	public Account(String netID) 
	{
		this.netID = netID;
	}
	
	public void addBookToWishlist(Textbook selectedBook) 
	{
		
	}

//	public void setAccountInfo(AccountRecord publicInfoAccountRecord) { // AccountRecord is the class
//		
//	}
	
	public Schedule getUserSchedule() 
	{
		Schedule schedule = new Schedule(netID, password);
		return schedule;
	}
	
	public List<Transaction> getTransactionsFromAccount()
	{
		return new ArrayList<>(); // dummy list
	}
	
	public Wishlist getWishlist() 
	{
		return new Wishlist(); // dummy object
	}
}
