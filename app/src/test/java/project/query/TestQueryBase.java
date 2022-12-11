package project.query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class TestQueryBase 
{
    public static final String dbHostDomain = "localhost";
    public static final int port            = 3306; 
    public Connection dbConnection;

    
    public void initializeDBState()
    {
        // Try connecting to the database. First, establish a Properties object for the connection.
        Properties connectionProperties = new Properties();
        connectionProperties.put("user", "root");
        connectionProperties.put("password", "fall2022se6329project");

        // Create a URL string to identify the location of the server.
        int portNumber  = 3306;
        String host     = "localhost";

        StringBuilder urlStringBuilder = new StringBuilder();
        urlStringBuilder.append("jdbc:mysql://");                // Specify protocol
        urlStringBuilder.append(host);                               // Specify server name
        urlStringBuilder.append(":");
        urlStringBuilder.append(portNumber);                         // Specify port number
        urlStringBuilder.append("/");
        String urlString = urlStringBuilder.toString();

        // Establish a connection to the MYSQL server
        Connection dbConnection_ = null;
        try
        {
            dbConnection_ = DriverManager.getConnection(urlString, connectionProperties);
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }

        dbConnection = dbConnection_;

        try(Statement statement = dbConnection.createStatement())
        {
            // Delete all tuples from all tables
            statement.executeUpdate("DELETE FROM cometbooks.message;");
            statement.executeUpdate("DELETE FROM cometbooks.wishlist;");
            statement.executeUpdate("DELETE FROM cometbooks.transaction;");
            statement.executeUpdate("DELETE FROM cometbooks.listing;");
            statement.executeUpdate("DELETE FROM cometbooks.book;");

            // Add some example tuples to each table
            statement.executeUpdate("INSERT INTO cometbooks.book VALUES (12345, 'Test Title', 'Nathan Beck', 1, 1999)");
            statement.executeUpdate("INSERT INTO cometbooks.book VALUES (12346, 'Test Title 2', 'Nathan Beck', 2, 2000)");
            statement.executeUpdate("INSERT INTO cometbooks.book VALUES (12347, 'Test Title 3', 'Bathan Neck', 3, 2001)");
            
            statement.executeUpdate("INSERT INTO cometbooks.listing VALUES (1, 12345, 'Like New', 12.50, 'Only a smudge on the cover', 0, 'abc123456')");
            statement.executeUpdate("INSERT INTO cometbooks.listing VALUES (2, 12345, 'Poor', 1.50, 'Please just take this off me', 0, 'zyx654321')");
            statement.executeUpdate("INSERT INTO cometbooks.listing VALUES (3, 12346, 'New', 21.50, 'Still in its plastic wrap', 0, 'nab170130')");

            statement.executeUpdate("INSERT INTO cometbooks.transaction VALUES (1, 1, '', 'nab170130')");
            statement.executeUpdate("INSERT INTO cometbooks.transaction VALUES (2, 2, '', 'nab170131')");
            statement.executeUpdate("INSERT INTO cometbooks.transaction VALUES (3, 3, '', 'nab170132')");

            statement.executeUpdate("INSERT INTO cometbooks.wishlist VALUES ('nab170130', 12345)");
            statement.executeUpdate("INSERT INTO cometbooks.wishlist VALUES ('nab170130', 12346)");
            statement.executeUpdate("INSERT INTO cometbooks.wishlist VALUES ('nab170131', 12345)");

            statement.executeUpdate("INSERT INTO cometbooks.message VALUES (1, 'nab170130', 1, 'Hey, I want your book.')");
            statement.executeUpdate("INSERT INTO cometbooks.message VALUES (1, 'abc123456', 2, 'Okay, sure.')");
            statement.executeUpdate("INSERT INTO cometbooks.message VALUES (2, 'nab170131', 1, 'Hey, can I have your book?')");
            statement.executeUpdate("INSERT INTO cometbooks.message VALUES (2, 'nab170131', 10, 'Hello?')");
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}
