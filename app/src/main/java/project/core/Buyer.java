package project.core;

import project.actor.NoAccountException;

public class Buyer extends User 
{
	public Buyer(String buyerNetID) throws NoAccountException 
	{
		super(buyerNetID);
	}

	
	public Buyer(User userAsBuyer) throws NoAccountException
	{
		super(userAsBuyer.account.netID);
		this.account = userAsBuyer.account;
	}
}