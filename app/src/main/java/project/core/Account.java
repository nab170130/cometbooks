package project.core;

import java.util.*;

import project.actor.UTDGalaxy;
import project.adapter.WishlistDBAdapter;
import project.record.AccountRecord;

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
		AccountRecord record = UTDGalaxy.getPublicAccountInfo(netID);
		setAccountInfo(record);
	}
	
	public void addBookToWishlist(Textbook selectedBook) 
	{
		WishlistDBAdapter.getInstance().addBookToWishlist(netID, selectedBook);
	}

	public void setAccountInfo(AccountRecord publicInfoAccountRecord) 
	{ 
		this.name 					= publicInfoAccountRecord.name;
		this.displayName 			= publicInfoAccountRecord.displayName;
		this.netID 					= publicInfoAccountRecord.netID;
		this.utdID 					= publicInfoAccountRecord.utdID;
		this.password 				= publicInfoAccountRecord.password;
		this.currentAcademicYear 	= publicInfoAccountRecord.currentAcademicYear;
	}
	
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
