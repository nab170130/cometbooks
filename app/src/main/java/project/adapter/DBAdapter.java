package project.adapter;

import java.sql.Connection;

public abstract class DBAdapter 
{
    public Connection connection;

    public DBAdapter(String dbHostDomain, int portNumber)
    {
        /* 
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
            System.out.println("Connection successfully established.");
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        */
    }
}
