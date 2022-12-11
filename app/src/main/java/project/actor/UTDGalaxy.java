package project.actor;

import project.record.AccountRecord;

public class UTDGalaxy 
{
    public static AccountRecord login(String netID, String password) throws NoAccountException
    {
        AccountRecord returnRecord = null;

        if(netID.equals("abc123456") && password.equals("pass1234"))
        {
            returnRecord = new AccountRecord("Jeel Patel", "Jeel Patel", netID, 1234567890L, password, 6);
        }
        else if(netID.equals("zyx654321") && password.equals("pass4321"))
        {
            returnRecord = new AccountRecord("Tirth Mehta", "Tirth Mehta", netID, 9876543210L, password, 6);
        }
        else
        {
            throw new NoAccountException();
        }

        return returnRecord;
    }

    
    public static AccountRecord getPublicAccountInfo(String netID) throws NoAccountException
    {
        AccountRecord returnRecord = null;

        if(netID.equals("abc123456"))
        {
            returnRecord = new AccountRecord("Jeel Patel", "Jeel Patel", netID, 1234567890L, null, 6);
        }
        else if(netID.equals("zyx654321"))
        {
            returnRecord = new AccountRecord("Tirth Mehta", "Tirth Mehta", netID, 9876543210L, null, 6);
        }
        else
        {
            throw new NoAccountException();
        }

        return returnRecord;
    }
}
