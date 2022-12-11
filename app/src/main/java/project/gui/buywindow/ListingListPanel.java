package project.gui.buywindow;

import java.awt.event.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ScrollPaneConstants;

import project.core.Textbook;
import project.gui.CometBooksGUI;
import project.core.Account;
import project.core.Controller;
import project.core.Conversation;
import project.core.SalesListing;
import project.core.Seller;

public class ListingListPanel extends JPanel implements ListCellRenderer<ListingContainer>, ActionListener
{
    public JButton                 checkoutButton;
    public JScrollPane             listingContainerPane;
    public JList<ListingContainer> listingList;
    public JLabel                  paneTitle;

    CometBooksGUI   parentContainer;

    Controller      controller;

    public ListingListPanel(Controller sysController, CometBooksGUI parentContainer_)
    {
        controller      = sysController;
        parentContainer = parentContainer_;
        buildItem();
    }

    @Override
    public void actionPerformed(ActionEvent ev)
    {
        if(ev.getSource().equals(checkoutButton))
        {
            // Create a transaction, swap to conversation window with conversation as focus.
            SalesListing listing = listingList.getSelectedValue().salesListing;

            if(listing.onHold)
            {
                OnHoldFailDialog failDialog = new OnHoldFailDialog();
            }
            else
            {
                Conversation conversation = controller.checkoutListing(listing);
                parentContainer.switchToConversationsWindow(conversation);
            }
        }
    }

    public void buildItem()
    {
        listingList = new JList<>();
        listingList.setCellRenderer(this);

        listingContainerPane = new JScrollPane(listingList);
        listingContainerPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        paneTitle = new JLabel("Listings");
        Font titleFont = new Font("Serif", Font.BOLD, 24);
        paneTitle.setFont(titleFont);
        paneTitle.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
        paneTitle.setHorizontalAlignment(JLabel.CENTER);

        checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(this);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(paneTitle);
        add(listingContainerPane);
        add(checkoutButton);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends ListingContainer> list, ListingContainer value, int index, boolean isSelected, boolean cellHasFocus)
    {
        ListingContainer renderItem = list.getModel().getElementAt(index);

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

    public void setDisplayListings(List<SalesListing> listings)
    {
        Vector<ListingContainer> listingContainers = new Vector<>();

        for(SalesListing listing : listings)
        {
            ListingContainer displayContainer = new ListingContainer(listing);
            listingContainers.add(displayContainer);
        }

        listingList.setListData(listingContainers);
    }
}
