package project.record;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRecord 
{
    public String   author;
    public int      edition;
    public long     isbn;
    public String   title;
    public int      year;

    public BookRecord(ResultSet resultSet)
    {
        // Pull information from the currently focused tuple.
        try
        {
            author  = resultSet.getString("author");
            edition = resultSet.getInt("edition");
            isbn    = resultSet.getLong("isbn");
            title   = resultSet.getString("title");
            year    = resultSet.getInt("year");
        }
        catch(SQLException ex)
        {
        }
    }

    public BookRecord()
    {
    }
}
