package project.query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import project.record.BookRecord;

public class SearchBookQuery extends Query
{
    public List<BookRecord>         bookRecords;
    public HashMap<String, String>  searchParameters;


    public SearchBookQuery(Connection connection, HashMap<String, String> searchParameters_)
    {
        super(connection);
        searchParameters = searchParameters_;
    }


    public List<BookRecord> getBookRecords()
    {
        return bookRecords;
    }


    @Override
    public String getQueryString()
    {
        // Formulate the query string based on the transactionID.
        StringBuilder queryStringBuilder = new StringBuilder();
        queryStringBuilder.append("SELECT * FROM cometbooks.book WHERE ");

        // Add field matching to where clause
        String[] keys   = new String[searchParameters.size()];
        String[] values = new String[searchParameters.size()];
        int index       = 0;
        for(String key : searchParameters.keySet())
        {
            keys[index]     = key;
            values[index]   = searchParameters.get(key);
            index++;
        }

        for(int i = 0; i < keys.length; i++)
        {
            queryStringBuilder.append(keys[i]);
            queryStringBuilder.append(" = ");

            if(keys[i] == "isbn" || keys[i] == "edition" || keys[i] == "year")
            {
                queryStringBuilder.append(values[i]);
            }
            else
            {
                queryStringBuilder.append("'");
                queryStringBuilder.append(values[i]);
                queryStringBuilder.append("'");
            }
            
            if(i != keys.length - 1)
            {
                queryStringBuilder.append(" AND ");
            }
        }

        queryStringBuilder.append(";");

        return queryStringBuilder.toString();
    }


    @Override
    public void processResult(ResultSet resultSet)
    {
        try
        {
            List<BookRecord> retrievedBookRecords = new ArrayList<>();
            while(resultSet.next())
            {
                BookRecord bookRecord = new BookRecord(resultSet);
                retrievedBookRecords.add(bookRecord);
            }
            setBookRecords(retrievedBookRecords);
        }
        catch(SQLException ex)
        {
        }
    }

    
    public void setBookRecords(List<BookRecord> bookRecords_)
    {
        bookRecords = bookRecords_;
    }
}
