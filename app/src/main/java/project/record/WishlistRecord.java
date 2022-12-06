package project.record;

public class WishlistRecord 
{
    public long     isbn;
    public String   netID;

    public WishlistRecord(String netID_, long isbn_)
    {
        isbn    = isbn_;
        netID   = netID_;
    }

    public WishlistRecord()
    {
    }
}
