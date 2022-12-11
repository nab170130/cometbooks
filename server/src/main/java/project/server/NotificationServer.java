package project.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class NotificationServer
{
    public ServerSocket                     connectionPoint;
    public HashMap<String, BufferedWriter>  netIDToOutputWriter;

    public static final int SERVER_PORT = 54321;


    public NotificationServer()
    {
        try
        {
            connectionPoint     = new ServerSocket(SERVER_PORT);
            netIDToOutputWriter = new HashMap<>();
        }
        catch(IOException ex)
        {
        }
    }


    public synchronized void addNetIDOutputWriterMapping(String netID, BufferedWriter outputWriter)
    {
        netIDToOutputWriter.put(netID, outputWriter);
    }


    public synchronized void removeNetIDOutputWriterMapping(String netID)
    {
        netIDToOutputWriter.remove(netID);
    }

    
    public void acceptNewConnections()
    {
        while(true)
        {
            try
            {
                // In a loop, accept connections for clients. Start a thread that will listen and respond to requests.
                Socket incomingClientConnection = connectionPoint.accept();

                Thread listenThread = new Thread(new ClientListenThread(this, incomingClientConnection));
                listenThread.start();
            }
            catch(IOException ex)
            {
            }
        }
    }
}
