package project.core;
//import project.record.*;

import java.util.*;

public class Account 
{
	public String name;
	public String displayName;
	public String netID;
	public long utdID;
	private String password;
	public int currentAcademicYear;
	//Started Making chnages	
	public Account(String netID) 
	{
		this.netID = netID;
	}
	
	public void addBookToWishlist(Textbook selectedBook) 
	{
		TransactionDBAdapter td = new TransactionDBAdapter(); // TransactionDBAdapter is class yet to be created
		td.addBookToWishlist(this.netID, selectedBook);

		new Wishlist().add(selectedBook);
	}

	//public void setAccountInfo(AccountRecord publicInfoAccountRecord) { // AccountRecord is not defined in the classDiagram........??????????
		
	//}
	
	public Schedule getUserSchedule() 
	{
		return new Schedule(this.netID, this.password); // in this returning object we will be having list as  data-memeber for Schedule class, 
											//which(Data-member in Schedule class) Dhruvi already added in her Git-Branch
	}
	
	public List<Transaction> getTransactionsFromAccount()
	{	
		TransactionDBAdapter td = new TransactionDBAdapter(); // TransactionDBAdapter is class yet to be created

		return td.getTransactionWithNetID(this.netID); // This method's return type is List<Transaction>
	}
	
	public Wishlist getWishlist() 
	{
		return new WishlistDBAdapter().getUserWishlist(this.netID); // WishlistDBAdapter class is yet to create

	}
}
