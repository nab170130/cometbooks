/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import project.core.Controller;
import project.gui.CometBooksGUI;

public class App 
{
    public static void main(String[] args) 
    {
        // FOR THE SAKE OF THE DEMO, INITIALIZE THE DB STATE TO SOMETHING REPRODUCIBLE.
        initializeDBStateForDemo();

        // Instantiate the controller and the GUI.
        Controller sysController    = new Controller();
        CometBooksGUI cometBooksGUI = new CometBooksGUI(sysController); 
    }

    public static void initializeDBStateForDemo()
    {
        // Contains code for initializing the database state for the demonstration. This would not be called in persistent sessions.
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
        Connection dbConnection = null;
        try
        {
            dbConnection = DriverManager.getConnection(urlString, connectionProperties);
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }

        try(Statement statement = dbConnection.createStatement())
        {
            // Delete all tuples from all tables
            statement.executeUpdate("DELETE FROM cometbooks.message;");
            statement.executeUpdate("DELETE FROM cometbooks.wishlist;");
            statement.executeUpdate("DELETE FROM cometbooks.transaction;");
            statement.executeUpdate("DELETE FROM cometbooks.listing;");
            statement.executeUpdate("DELETE FROM cometbooks.book;");

            // Add some example books into the tables.
            statement.executeUpdate("INSERT INTO cometbooks.book VALUES (0262033844, 'Introduction to Algorithms', 'Thomas Cormen;Charles Leiserson;Ronald Rivest;Clifford Stein', 3, 2009)");
            statement.executeUpdate("INSERT INTO cometbooks.book VALUES (1617296465, 'Parallel and High Performance Computing', 'Robert Robey;Yuliana Zamora', 1, 2021)");
            statement.executeUpdate("INSERT INTO cometbooks.book VALUES (0486806030, 'The Mad Weave Book: An Ancient Form of Triaxial Basket Weaving', 'Shereen LaPlantz', 1, 2016)");
            statement.executeUpdate("INSERT INTO cometbooks.book VALUES (0201896834, 'The Art of Computer Programming, Vol. 1: Fundamental Algorithms', 'Donald Knuth', 3, 1997)");
            
            statement.executeUpdate("INSERT INTO cometbooks.listing VALUES (1, 0262033844, 'Like New', 12.50, 'Only a smudge on the cover', 1, 'abc123456')");
            statement.executeUpdate("INSERT INTO cometbooks.listing VALUES (2, 0262033844, 'Poor', 1.50, 'Please just take this off me', 0, 'zyx654321')");
            statement.executeUpdate("INSERT INTO cometbooks.listing VALUES (3, 1617296465, 'New', 21.50, 'Still in its plastic wrap', 0, 'zyx654321')");

            statement.executeUpdate("INSERT INTO cometbooks.transaction VALUES (1, 1, '', 'zyx654321')");

            statement.executeUpdate("INSERT INTO cometbooks.wishlist VALUES ('abc123456', 0486806030)");
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}
