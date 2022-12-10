package project.gui.conversationwindow;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class ConversationWindowContainer extends JPanel 
{
    /*
     * Represents the buy window option for the GUI, where two different panes exist for books and listings.
     */
    ConversationListPanel conversationPanel;
    ConversationViewPanel conversationViewPanel;

    public ConversationWindowContainer()
    {
        buildItem();
    }

    public void buildItem()
    {
        setLayout(new GridLayout(1,2));

        conversationViewPanel   = new ConversationViewPanel();
        conversationPanel       = new ConversationListPanel(conversationViewPanel);

        add(conversationPanel);
        add(conversationViewPanel);
    }
}
