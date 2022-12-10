package project.core;

import java.util.*;

public class Buyer extends User 
{
	public Buyer(String buyerNetID) 
	{
		
	}

	public Buyer(User userAsBuyer)
	{
		this.account = userAsBuyer.account;
	}
}