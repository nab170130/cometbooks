package project.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import project.core.Textbook;
import project.query.ISBNBookQuery;
import project.query.SearchBookQuery;
import project.record.BookRecord;

public class BookDBAdapter extends DBAdapter
{
    private static final BookDBAdapter instance = new BookDBAdapter(HOST, PORT);

    
    public BookDBAdapter(String host, int portNumber)
    {
        super(host, portNumber);
    }


    public static BookDBAdapter getInstance()
    {
        return instance;
    }


    public List<Textbook> getMatchingBooks(HashMap<String, String> searchParameters)
    {
        SearchBookQuery searchBookQuery = new SearchBookQuery(connection, searchParameters);
        searchBookQuery.doQuery();

        List<BookRecord> bookRecords = searchBookQuery.getBookRecords();

        List<Textbook> textbooks = new ArrayList<>();

        for(BookRecord bookRecord : bookRecords)
        {
            Textbook textbook = new Textbook(bookRecord);
            textbooks.add(textbook);
        }

        return textbooks;
    }


    public Textbook getBookFromISBN(long isbnNumber)
    {
        ISBNBookQuery isbnBookQuery = new ISBNBookQuery(connection, isbnNumber);
        isbnBookQuery.doQuery();

        BookRecord bookRecord   = isbnBookQuery.getBookRecord();
        Textbook textbook       = new Textbook(bookRecord);

        return textbook;
    }
}
