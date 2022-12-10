package project.gui.conversationwindow;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import java.util.ArrayList;

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
import project.core.Conversation;
import project.core.Message;
import project.core.SalesListing;
import project.core.Seller;
import project.core.Textbook;
import project.core.Transaction;

public class ConversationListPanel extends JPanel implements ListCellRenderer<ConversationContainer>, ListSelectionListener
{
    JScrollPane                     conversationContainerPane;
    JList<ConversationContainer>    conversationList;
    JLabel                          paneTitle;
    ConversationViewPanel           conversationViewPanel;

    public ConversationListPanel(ConversationViewPanel panel)
    {
        conversationViewPanel = panel;
        buildItem();

        Textbook testBook = new Textbook();
        testBook.authors = new String[2];
        testBook.authors[0] = "Nathan Beck";
        testBook.authors[1] = "Connor Beck";
        testBook.isbn = 12345;
        testBook.edition = 1;
        testBook.title = "Get me an A";
        testBook.year = 1999;

        Account account = new Account("nab170130");
        account.currentAcademicYear = 6;
        account.displayName = "Nathan Beck";
        account.name = "Nathan Beck";
        account.netID = "nab170130";
        account.utdID = 1234567890;

        Seller seller = new Seller("nab170130");
        seller.account = account;

        SalesListing testListing = new SalesListing();
        testListing.condition = "Like new";
        testListing.description = "Only a smudge on the cover is all";
        testListing.listingPrice = 12.00;
        testListing.onHold = true;
        testListing.seller = seller;
        testListing.textbook = testBook;
        
        account = new Account("nab170131");
        account.currentAcademicYear = 6;
        account.displayName = "Nathan Beck 2";
        account.name = "Nathan Beck 2";
        account.netID = "nab170131";
        account.utdID = 1234567898;

        Buyer buyer = new Buyer("nab170131");
        buyer.account = account;

        Transaction testTransaction = new Transaction(buyer, testListing);
        testTransaction.buyer = buyer;
        testTransaction.listing = testListing;
        Conversation conversation = new Conversation();
        conversation.transaction = testTransaction;
        conversation.messages = new ArrayList<>();
        Message message = new Message();
        message.messageContent = "Hello there";
        message.author = buyer;
        conversation.messages.add(message);

        ConversationContainer container = new ConversationContainer(conversation);

        conversationList.setListData(new ConversationContainer[]{container});
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

    @Override
    public void valueChanged(ListSelectionEvent ev)
    {
        if(ev.getSource().equals(conversationList))
        {
            Conversation toFocusConversation = conversationList.getSelectedValue().conversation;
            conversationViewPanel.setConversation(toFocusConversation);
            invalidate();
            repaint();
        }
    }
}
