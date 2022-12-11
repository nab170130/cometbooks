package project.adapter;

import java.util.List;

import project.core.Textbook;
import project.core.Wishlist;
import project.query.AddWishlistQuery;
import project.query.UserWishlistQuery;
import project.record.BookRecord;
import project.record.WishlistRecord;

public class WishlistDBAdapter extends DBAdapter 
{
    private static final WishlistDBAdapter instance = new WishlistDBAdapter(HOST, PORT);


    public WishlistDBAdapter(String host, int portNumber)
    {
        super(host, portNumber);
    }


    public static WishlistDBAdapter getInstance()
    {
        return instance;
    }


    public Wishlist getUserWishlist(String netID)
    {
        UserWishlistQuery userWishlistQuery = new UserWishlistQuery(connection, netID);
        userWishlistQuery.doQuery();

        List<BookRecord> bookRecords = userWishlistQuery.getWishlistBookRecords();
        Wishlist wishlist = new Wishlist(bookRecords);

        return wishlist;
    }

    
    public void addBookToWishlist(String netID, Textbook textbook)
    {
        WishlistRecord wishlistRecord       = new WishlistRecord(netID, textbook.isbn);
        AddWishlistQuery addWishlistQuery   = new AddWishlistQuery(connection, wishlistRecord);
        addWishlistQuery.doQuery();
    }
}
