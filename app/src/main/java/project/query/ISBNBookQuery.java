package project.query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import project.record.BookRecord;

public class ISBNBookQuery extends Query
{
    public long         isbn;
    public BookRecord   storedBookRecord;

    public ISBNBookQuery(Connection connection, long isbn_)
    {
        super(connection);
        isbn = isbn_;
    }

    public BookRecord getBookRecord()
    {
        return storedBookRecord;
    }

    @Override
    public String getQueryString()
    {
        // Formulate the query string based on the transactionID.
        StringBuilder queryStringBuilder = new StringBuilder();
        queryStringBuilder.append("SELECT * FROM cometbooks.book WHERE isbn = ");
        queryStringBuilder.append(isbn);
        queryStringBuilder.append(";");

        return queryStringBuilder.toString();
    }

    @Override
    public void processResult(ResultSet resultSet)
    {
        try
        {
            resultSet.next();    
            BookRecord bookRecord = new BookRecord(resultSet);
            setBookRecord(bookRecord);
        }
        catch(SQLException ex)
        {
        }
    }

    public void setBookRecord(BookRecord bookRecord)
    {
        storedBookRecord = bookRecord;
    }
}
