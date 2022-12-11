package project.gui.conversationwindow;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;

import project.core.Controller;
import project.core.Conversation;

public class ConversationWindowContainer extends JPanel 
{
    /*
     * Represents the buy window option for the GUI, where two different panes exist for books and listings.
     */
    public ConversationListPanel conversationPanel;
    public ConversationViewPanel conversationViewPanel;

    public ConversationWindowContainer(Controller controller)
    {
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
}
