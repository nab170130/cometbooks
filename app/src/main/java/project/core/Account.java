package project.core;

import java.util.*;

import project.actor.NoAccountException;
import project.actor.NoCourseException;
import project.actor.NoScheduleException;
import project.actor.UTDGalaxy;
import project.adapter.TransactionDBAdapter;
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


	public Account(String netID) throws NoAccountException
	{
		this.netID = netID;
		AccountRecord record = UTDGalaxy.getPublicAccountInfo(netID);
		setAccountInfo(record);
	}


	public Account(String netID, String password) throws NoAccountException
	{
		this.netID 				= netID;
		this.password 			= password;
		AccountRecord record 	= UTDGalaxy.login(netID, password);
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
	

	public Schedule getUserSchedule() throws NoScheduleException, NoCourseException
	{
		return new Schedule(this.netID, this.password);
	}
	

	public List<Transaction> getTransactionsFromAccount()
	{	
		return TransactionDBAdapter.getInstance().getTransactionsWithNetID(this.netID);
	}
	
	
	public Wishlist getWishlist() 
	{
		return WishlistDBAdapter.getInstance().getUserWishlist(this.netID);

	}
}
