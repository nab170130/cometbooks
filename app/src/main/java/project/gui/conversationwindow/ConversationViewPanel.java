package project.gui.conversationwindow;

import java.awt.event.*;
import java.util.Date;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import project.core.Controller;
import project.core.Conversation;
import project.core.Message;

public class ConversationViewPanel extends JPanel implements ActionListener
{
    public JTextArea       conversationViewArea;
    public JScrollPane     conversationViewPane;

    public JTextArea       conversationEditArea;
    public JScrollPane     conversationEditPane;
    public JButton         conversationSendButton;

    public JLabel          paneTitle;

    public ConversationListPanel listPanel;

    Controller controller;


    public ConversationViewPanel(Controller controller_)
    {
        controller = controller_;
        buildItem();
    }


    @Override
    public void actionPerformed(ActionEvent ev)
    {
        if(ev.getSource().equals(conversationSendButton))
        {
            // DO SEND MESSAGE CASE
            String messageToSend = conversationEditArea.getText();
            
            Conversation updatedConversation = controller.sendMessage(messageToSend);
            setConversation(updatedConversation);
            ConversationContainer container = listPanel.conversationList.getSelectedValue();
            container.setConversation(updatedConversation);
        }
    }


    public void buildItem()
    {
        conversationViewArea = new JTextArea();
        conversationViewArea.setEditable(false);

        conversationViewPane = new JScrollPane(conversationViewArea);
        conversationViewPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        conversationEditArea = new JTextArea();
        conversationEditArea.setEditable(true);

        conversationEditPane = new JScrollPane(conversationEditArea);
        conversationEditPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        conversationSendButton = new JButton("Send Message");
        conversationSendButton.addActionListener(this);

        paneTitle = new JLabel("Messages");
        Font titleFont = new Font("Serif", Font.BOLD, 24);
        paneTitle.setFont(titleFont);
        paneTitle.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
        paneTitle.setHorizontalAlignment(JLabel.CENTER);
        paneTitle.setMaximumSize(paneTitle.getPreferredSize());

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(paneTitle);
        add(conversationViewPane);
        add(conversationEditPane);
        add(conversationSendButton);
    }

    
    public void setConversation(Conversation conversation)
    {
        // Go through each message in the conversation and generate a display text.
        StringBuilder displayTextBuilder = new StringBuilder();

        for(Message message : conversation.messages)
        {
            displayTextBuilder.append(message.author.account.displayName);
            displayTextBuilder.append(" -- ");
            displayTextBuilder.append(new Date(message.dateTimeSent).toString());
            displayTextBuilder.append("\n");
            displayTextBuilder.append(message.messageContent);
            displayTextBuilder.append("\n\n");
        }

        conversationViewArea.setText(displayTextBuilder.toString());
        conversationEditArea.setText("");
    }
}
