package project.adapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class DBAdapter 
{
    public static final String HOST = "localhost";
    public static final int PORT    = 3306;

    public Connection connection;

    public DBAdapter(String host, int portNumber)
    {
        // Try connecting to the database. First, establish a Properties object for the connection.
        Properties connectionProperties = new Properties();
        connectionProperties.put("user", "root");
        connectionProperties.put("password", "fall2022se6329project");

        StringBuilder urlStringBuilder = new StringBuilder();
        urlStringBuilder.append("jdbc:mysql://");                // Specify protocol
        urlStringBuilder.append(host);                               // Specify server name
        urlStringBuilder.append(":");
        urlStringBuilder.append(portNumber);                         // Specify port number
        urlStringBuilder.append("/");
        String urlString = urlStringBuilder.toString();

        // Establish a connection to the MYSQL server
        try
        {
            connection = DriverManager.getConnection(urlString, connectionProperties);
            System.out.println("Connection successfully established.");
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}
