package project.record;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ListingRecord 
{
    public String   condition;
    public String   description;
    public long     isbn;
    public long     listingID;
    public double   listingPrice;
    public boolean  onHold;
    public String   sellerNetID;

    public ListingRecord(ResultSet resultSet)
    {
        // Pull information from the currently focused tuple.
        try
        {
            condition       = resultSet.getString("condition");
            description     = resultSet.getString("description");
            isbn            = resultSet.getLong("isbn");
            listingID       = resultSet.getLong("listingID");
            listingPrice    = resultSet.getDouble("listingPrice");
            onHold          = resultSet.getBoolean("onHold");
            sellerNetID     = resultSet.getString("sellerNetID");
        }
        catch(SQLException ex)
        {
        }
    }

    public ListingRecord()
    {
    }
}
