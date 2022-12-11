package project.query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Query
{ 
    private Connection dbConnection;
    private Statement  statement;


    public Query(Connection dbConnection_)
    {
        dbConnection = dbConnection_;
    }


    public void closeStatement()
    {
        try
        {
            if(!statement.isClosed())
            {
                statement.close();
            }
        }
        catch(SQLException ex)
        {
        }
    }


    public void doQuery()
    {
        String queryToExecute   = getQueryString();
        ResultSet resultOfQuery = executeSQL(queryToExecute);
        processResult(resultOfQuery);
        closeStatement();
    }


    public ResultSet executeSQL(String queryToExecute)
    {
        // Execute the query.
        ResultSet resultOfQuery = null;
        try
        {
            statement = dbConnection.createStatement();
            if(queryToExecute.startsWith("SELECT"))
            {
                resultOfQuery = statement.executeQuery(queryToExecute);
            }
            else
            {
                statement.executeUpdate(queryToExecute);
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }

        return resultOfQuery;
    }


    public abstract String getQueryString();

    
    public abstract void processResult(ResultSet queryResult);
}
