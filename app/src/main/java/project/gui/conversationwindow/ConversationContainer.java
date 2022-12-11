package project.gui.conversationwindow;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

import project.core.Conversation;

public class ConversationContainer extends JPanel
{
    JTextField bookField;
    JTextField sellerField;
    JTextField recentMessageField;

    Conversation conversation;

    public ConversationContainer(Conversation conversation_)
    {
        buildItem();
        setConversation(conversation_);
    }

    public void buildItem()
    {
        bookField           = new JTextField();
        sellerField         = new JTextField();
        recentMessageField  = new JTextField();

        bookField.setEditable(false);
        sellerField.setEditable(false);
        recentMessageField.setEditable(false);

        bookField.setBorder(BorderFactory.createEmptyBorder());
        sellerField.setBorder(BorderFactory.createEmptyBorder());
        recentMessageField.setBorder(BorderFactory.createEmptyBorder());

        Font titleFont = new Font("Serif", Font.BOLD, 18);
        bookField.setFont(titleFont);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(bookField);
        add(sellerField);
        add(recentMessageField);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
    }

    public void setElementBackgrounds(Color color)
    {
        bookField.setBackground(color);
        sellerField.setBackground(color);
        recentMessageField.setBackground(color);
    }

    public void setConversation(Conversation conversation_)
    {
        conversation = conversation_;

        bookField.setText(conversation.transaction.listing.textbook.title);
        sellerField.setText("Seller: " + conversation.transaction.listing.seller.account.displayName);
        
        if(conversation.messages.size() > 0)
        {
            int indexToRetrieve = conversation.messages.size() - 1;
            String recentMessage = conversation.messages.get(indexToRetrieve).messageContent;
            recentMessageField.setText(recentMessage);
        }
        else
        {
            recentMessageField.setText("");
        }
    }
}
