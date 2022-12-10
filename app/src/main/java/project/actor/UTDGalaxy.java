package project.actor;

import project.core.User;
import project.record.AccountRecord;

public class UTDGalaxy 
{
    // public static User login(String netID, String password)
    // {
    //     User user = null;

    //     if(netID == "abc123456" && password == "pass1234")
    //     {

    //     }
    //     else if(netID == "zyx654321" && password == "pass4321")
    //     {
            
    //     }

    //     return user;
    // }

    public static AccountRecord getPublicAccountInfo(String netID)
    {
        AccountRecord returnRecord = null;

        if(netID == "abc123456")
        {
            returnRecord = new AccountRecord("Jeel Patel", "Jeel Patel", netID, 1234567890L, null, 6);
        }
        else if(netID == "zyx654321")
        {
            returnRecord = new AccountRecord("Tirth Mehta", "Tirth Mehta", netID, 9876543210L, null, 6);
        }

        return returnRecord;
    }
}
