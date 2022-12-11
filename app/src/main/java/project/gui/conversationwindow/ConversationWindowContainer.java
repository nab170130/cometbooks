package project.gui.conversationwindow;

import java.awt.GridLayout;
import java.io.IOException;
import java.util.List;

import javax.swing.JPanel;

import project.core.Controller;
import project.core.Conversation;

public class ConversationWindowContainer extends JPanel implements Runnable
{
    /*
     * Represents the buy window option for the GUI, where two different panes exist for books and listings.
     */
    public ConversationListPanel conversationPanel;
    public ConversationViewPanel conversationViewPanel;

    Controller controller;


    public ConversationWindowContainer(Controller controller_)
    {
        controller = controller_;
        buildItem(controller);
    }


    public void buildItem(Controller controller)
    {
        setLayout(new GridLayout(1,2));

        conversationViewPanel           = new ConversationViewPanel(controller);
        conversationPanel               = new ConversationListPanel(conversationViewPanel, controller);
        conversationViewPanel.listPanel = conversationPanel;

        add(conversationPanel);
        add(conversationViewPanel);
    }


    public void setConversations(List<Conversation> conversations, Conversation toFocusConversation)
    {
        conversationPanel.setDisplayConversations(conversations, toFocusConversation);
        
        if(toFocusConversation != null)
        {
            conversationViewPanel.setConversation(toFocusConversation);
        }
    }


    public void startListeningThread()
    {
        Thread listenForNotifcationThread = new Thread(this);
        listenForNotifcationThread.start();
    }

    
    public void run()
    {
        // Indefinitely accept notification requests from controller's socket.
        try
        {
            String receivedLine = null;
            while((receivedLine = controller.notificationReceptor.readLine()) != null)
            {
                // Trigger an update on conversations.
                List<Conversation> newConversations = controller.navigateToConversationWindow();

                // See if there's a focused conversation. If so, get its new variant and make it selected.
                ConversationContainer selectedValue = conversationPanel.conversationList.getSelectedValue();
                Conversation newFocusedConversation = null;
                if(selectedValue != null)
                {
                    Conversation oldSelectedConversation = selectedValue.conversation;
                    for(Conversation potentialNewSelectedConversation : newConversations)
                    {
                        if(oldSelectedConversation.ID == potentialNewSelectedConversation.ID)
                        {
                            newFocusedConversation = potentialNewSelectedConversation;
                            break;
                        }
                    }
                }

                setConversations(newConversations, newFocusedConversation);
            }
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
