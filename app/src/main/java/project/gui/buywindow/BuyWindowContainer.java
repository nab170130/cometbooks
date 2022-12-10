package project.gui.buywindow;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class BuyWindowContainer extends JPanel
{
    /*
     * Represents the buy window option for the GUI, where two different panes exist for books and listings.
     */
    BookListPanel       bookPanel;
    ListingListPanel    listingPanel;

    public BuyWindowContainer()
    {
        buildItem();
    }

    public void buildItem()
    {
        setLayout(new GridLayout(1,2));

        bookPanel       = new BookListPanel();
        listingPanel    = new ListingListPanel();

        add(bookPanel);
        add(listingPanel);
    }
}
