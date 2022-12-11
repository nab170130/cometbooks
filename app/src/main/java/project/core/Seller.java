package project.core;

import project.actor.NoAccountException;

public class Seller extends User 
{
	public Seller(String sellerNetID) throws NoAccountException
	{
		super(sellerNetID);
	}
}
