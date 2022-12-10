package project.gui.buywindow;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ScrollPaneConstants;

import project.core.Textbook;
import project.core.Account;
import project.core.SalesListing;
import project.core.Seller;

public class ListingListPanel extends JPanel implements ListCellRenderer<ListingContainer>
{
    JScrollPane             listingContainerPane;
    JList<ListingContainer> listingList;
    JTextField              paneTitle;

    public ListingListPanel()
    {
        buildItem();

        // For testing, just add items.
        Vector<ListingContainer> allListings = new Vector<>();

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
        ListingContainer dispListing = new ListingContainer(testListing);
        allListings.add(dispListing);

        testListing = new SalesListing();
        testListing.condition = "Old";
        testListing.description = "Spilled coffee all over it";
        testListing.listingPrice = 1.00;
        testListing.onHold = false;
        testListing.seller = seller;
        dispListing = new ListingContainer(testListing);
        allListings.add(dispListing);

        listingList.setListData(allListings);
    }

    public void buildItem()
    {
        listingList = new JList<>();
        listingList.setCellRenderer(this);

        listingContainerPane = new JScrollPane(listingList);
        listingContainerPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        paneTitle = new JTextField("Listings");
        Font titleFont = new Font("Serif", Font.BOLD, 24);
        paneTitle.setFont(titleFont);
        paneTitle.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
        paneTitle.setHorizontalAlignment(JTextField.CENTER);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(paneTitle);
        add(listingContainerPane);
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
}
