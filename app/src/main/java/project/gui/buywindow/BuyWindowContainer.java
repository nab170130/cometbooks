package project.gui.buywindow;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;

import project.core.Controller;
import project.core.Textbook;
import project.gui.CometBooksGUI;

public class BuyWindowContainer extends JPanel
{
    /*
     * Represents the buy window option for the GUI, where two different panes exist for books and listings.
     */
    public BookListPanel       bookPanel;
    public ListingListPanel    listingPanel;

    public BuyWindowContainer(Controller sysController, CometBooksGUI parentContainer)
    {
        buildItem(sysController, parentContainer);
    }

    public void buildItem(Controller sysController, CometBooksGUI parentContainer)
    {
        setLayout(new GridLayout(1,2));

        listingPanel    = new ListingListPanel(sysController, parentContainer);
        bookPanel       = new BookListPanel(sysController, listingPanel);

        add(bookPanel);
        add(listingPanel);
    }

    public void setBooks(List<Textbook> textbooks)
    {
        bookPanel.setDisplayTextbooks(textbooks);
    }
}
