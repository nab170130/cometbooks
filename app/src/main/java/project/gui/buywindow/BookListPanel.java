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
import javax.swing.ListCellRenderer;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import project.core.Controller;
import project.core.SalesListing;
import project.core.Textbook;

public class BookListPanel extends JPanel implements ListCellRenderer<BookContainer>, ActionListener, ListSelectionListener
{
    public JScrollPane             bookContainerPane;
    public JList<BookContainer>    bookList;
    public JLabel                  paneTitle;
    public JButton                 searchButton;
    public JButton                 addWishlistButton;

    public ListingListPanel        listingPanel;

    Controller controller;


    public BookListPanel(Controller sysController, ListingListPanel listingPanel_)
    {
        controller      = sysController;
        listingPanel    = listingPanel_;
        buildItem();
    }


    public void actionPerformed(ActionEvent ev)
    {
        if(ev.getSource().equals(searchButton))
        {
            SearchDialog searchDialog = new SearchDialog(controller, this);
        }
        else if(ev.getSource().equals(addWishlistButton))
        {
            Textbook textbook = bookList.getSelectedValue().textbook;

            // TODO: HAVE ADD WISHLIST BUTTON HERE!
            List<Textbook> newRecList = controller.addBookToWishlist(textbook);
            setDisplayTextbooks(newRecList);
        }
    }


    public void buildItem()
    {
        bookList = new JList<>();
        bookList.setCellRenderer(this);
        bookList.addListSelectionListener(this);

        bookContainerPane = new JScrollPane(bookList);
        bookContainerPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        paneTitle = new JLabel("Textbooks");
        Font titleFont = new Font("Serif", Font.BOLD, 24);
        paneTitle.setFont(titleFont);
        paneTitle.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
        paneTitle.setHorizontalAlignment(JLabel.CENTER);
        
        searchButton        = new JButton("Search for Book");
        addWishlistButton   = new JButton("Add to Wishlist");
        
        searchButton.addActionListener(this);
        addWishlistButton.addActionListener(this);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(paneTitle);
        add(bookContainerPane);
        add(searchButton);
        add(addWishlistButton);
    }


    @Override
    public Component getListCellRendererComponent(JList<? extends BookContainer> list, BookContainer value, int index, boolean isSelected, boolean cellHasFocus)
    {
        BookContainer renderItem = list.getModel().getElementAt(index);

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


    public void setDisplayTextbooks(List<Textbook> textbooks)
    {
        Vector<BookContainer> bookContainers = new Vector<>();

        for(Textbook textbook : textbooks)
        {
            BookContainer displayContainer = new BookContainer(textbook);
            bookContainers.add(displayContainer);
        }

        bookList.setListData(bookContainers);
    }

    
    @Override
    public void valueChanged(ListSelectionEvent ev)
    {
        if(ev.getSource().equals(bookList))
        {
            if(bookList.getSelectedValue() == null)
            {
                return;
            }

            Textbook getListingsTextbook    = bookList.getSelectedValue().textbook;
            List<SalesListing> listings     = controller.selectBook(getListingsTextbook);
            
            listingPanel.setDisplayListings(listings);
        }
    }
}
