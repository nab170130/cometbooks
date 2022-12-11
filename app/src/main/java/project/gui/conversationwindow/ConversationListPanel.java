package project.gui.conversationwindow;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import project.core.Account;
import project.core.Buyer;
import project.core.Controller;
import project.core.Conversation;
import project.core.Message;
import project.core.SalesListing;
import project.core.Seller;
import project.core.Textbook;
import project.core.Transaction;

public class ConversationListPanel extends JPanel implements ListCellRenderer<ConversationContainer>, ListSelectionListener
{
    public JScrollPane                     conversationContainerPane;
    public JList<ConversationContainer>    conversationList;
    public JLabel                          paneTitle;
    public ConversationViewPanel           conversationViewPanel;

    Controller controller;

    public ConversationListPanel(ConversationViewPanel panel, Controller controller_)
    {
        conversationViewPanel   = panel;
        controller              = controller_;
        buildItem();
    }

    public void buildItem()
    {
        conversationList = new JList<>();
        conversationList.setCellRenderer(this);
        conversationList.addListSelectionListener(this);

        conversationContainerPane = new JScrollPane(conversationList);
        conversationContainerPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        paneTitle = new JLabel("Conversations");
        Font titleFont = new Font("Serif", Font.BOLD, 24);
        paneTitle.setFont(titleFont);
        paneTitle.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
        paneTitle.setHorizontalAlignment(JLabel.CENTER);
        paneTitle.setMaximumSize(paneTitle.getPreferredSize());

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(paneTitle);
        add(conversationContainerPane);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends ConversationContainer> list, ConversationContainer value, int index, boolean isSelected, boolean cellHasFocus)
    {
        ConversationContainer renderItem = list.getModel().getElementAt(index);

        if(isSelected)
        {
            renderItem.setElementBackgrounds(Color.GREEN);
        }
        else
        {
            renderItem.setElementBackgrounds(Color.WHITE);
        }

        return renderItem;
    }

    public void setDisplayConversations(List<Conversation> conversations, Conversation toFocusConversation)
    {
        Vector<ConversationContainer> conversationContainers = new Vector<>();

        for(Conversation conversation : conversations)
        {
            ConversationContainer displayContainer = new ConversationContainer(conversation);
            conversationContainers.add(displayContainer);
        }

        conversationList.setListData(conversationContainers);

        if(toFocusConversation != null)
        {
            for(int i = 0; i < conversationContainers.size(); i++)
            {
                Conversation conversation = conversationContainers.get(i).conversation;

                if(conversation.ID == toFocusConversation.ID)
                {
                    conversationList.setSelectedIndex(i);
                    return;
                }
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent ev)
    {
        if(ev.getSource().equals(conversationList))
        {
            if(conversationList.getSelectedValue() == null)
            {
                return;
            }

            Conversation toFocusConversation = conversationList.getSelectedValue().conversation;
            conversationViewPanel.setConversation(toFocusConversation);
        }
    }
}
