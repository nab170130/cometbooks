package project;

import project.server.NotificationServer;

public class Server 
{
    public static void main(String[] args)
    {
        NotificationServer server = new NotificationServer();
        server.acceptNewConnections();
    }
}
