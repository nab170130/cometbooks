package project.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientListenThread implements Runnable
{
    public NotificationServer   notificationServer;
    public Socket               listenSocket;


    public ClientListenThread(NotificationServer notificationServer_, Socket socket_)
    {
        notificationServer  = notificationServer_;
        listenSocket        = socket_;
    }

    
    public void run()
    {
        try
        {
            // Get a text reader from the socket.
            BufferedReader netIDReader      = new BufferedReader(new InputStreamReader(listenSocket.getInputStream()));
            BufferedWriter mySocketOutput   = new BufferedWriter(new OutputStreamWriter(listenSocket.getOutputStream()));

            // Read one line. This will be the netID of the sender, which gets added to the NotificationServer's map.
            String senderNetID = netIDReader.readLine();
            notificationServer.addNetIDOutputWriterMapping(senderNetID, mySocketOutput);

            // Indefinitely read incoming netIDs that we can notify.
            String incomingNetIDToNotify = null;
            while((incomingNetIDToNotify = netIDReader.readLine()) != null)
            {
                System.out.println("MESSAGE TO " + incomingNetIDToNotify);

                // Use the NotificationServer's hashmap to get the socket that we need to use.
                BufferedWriter notifyWriter = notificationServer.netIDToOutputWriter.get(incomingNetIDToNotify);

                // Only attempt to notify if there is actually a socket.
                if(notifyWriter != null)
                {
                    // Get a write connection, write the string, and close the resource.
                    notifyWriter.write("Update Conv");
                    notifyWriter.write("\n");
                    notifyWriter.flush();
                    System.out.println("JUST FLUSHED MESSAGE TO " + incomingNetIDToNotify);
                }
            }

            // After the sender has terminated their client, close the netIDReader AND this socket. Remove it from the map.
            notificationServer.removeNetIDOutputWriterMapping(senderNetID);
            netIDReader.close();
            mySocketOutput.close();
            listenSocket.close();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
