package project.query;

import java.sql.Connection;
import java.sql.ResultSet;

import project.record.WishlistRecord;

public class AddWishlistQuery extends Query
{
    WishlistRecord wishlistRecordToAdd;


    public AddWishlistQuery(Connection dbConnection, WishlistRecord wishlistRecord)
    {
        super(dbConnection);
        wishlistRecordToAdd = wishlistRecord;
    }    


    @Override
    public String getQueryString()
    {
        // Formulate the query string based on the WishlistRecord object.
        StringBuilder queryStringBuilder = new StringBuilder();
        queryStringBuilder.append("INSERT INTO cometbooks.wishlist VALUES ('");
        queryStringBuilder.append(wishlistRecordToAdd.netID);
        queryStringBuilder.append("',");
        queryStringBuilder.append(wishlistRecordToAdd.isbn);
        queryStringBuilder.append(");");

        return queryStringBuilder.toString();
    }

    
    @Override
    public void processResult(ResultSet resultset)
    {
    }
}
