package project.core;
//import project.record.*;

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
	//Started Making chnages	

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
		return new Schedule(this.netID, this.password); // in this returning object we will be having list as  data-memeber for Schedule class, 
											//which(Data-member in Schedule class) Dhruvi already added in her Git-Branch
	}
	
	public List<Transaction> getTransactionsFromAccount()
	{	
		// TransactionDBAdapter is class yet to be created
		return TransactionDBAdapter.getInstance().getTransactionsWithNetID(this.netID); // This method's return type is List<Transaction>
	}
	
	public Wishlist getWishlist() 
	{
		return WishlistDBAdapter.getInstance().getUserWishlist(this.netID); // WishlistDBAdapter class is yet to create

	}
}
