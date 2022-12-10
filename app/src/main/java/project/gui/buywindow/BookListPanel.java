package project.gui.buywindow;

import java.awt.event.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
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

public class BookListPanel extends JPanel implements ListCellRenderer<BookContainer>, ActionListener
{
    JScrollPane             bookContainerPane;
    JList<BookContainer>    bookList;
    JLabel                  paneTitle;
    JButton                 searchButton;
    JButton                 addWishlistButton;

    public BookListPanel()
    {
        buildItem();

        // For testing, just add items.
        Vector<BookContainer> allBooks = new Vector<>();

        Textbook testBook = new Textbook();
        testBook.authors = new String[2];
        testBook.authors[0] = "Nathan Beck";
        testBook.authors[1] = "Connor Beck";
        testBook.isbn = 12345;
        testBook.edition = 1;
        testBook.title = "Get me an A";
        testBook.year = 1999;
        BookContainer dispBook = new BookContainer(testBook);
        allBooks.add(dispBook);

        testBook = new Textbook();
        testBook.authors = new String[1];
        testBook.authors[0] = "Nathan Beck";
        testBook.isbn = 123455;
        testBook.edition = 2;
        testBook.title = "Get me an C";
        testBook.year = 1998;
        dispBook = new BookContainer(testBook);
        allBooks.add(dispBook);

        bookList.setListData(allBooks);
    }

    public void actionPerformed(ActionEvent ev)
    {
        if(ev.getSource().equals(searchButton))
        {
            SearchDialog searchDialog = new SearchDialog();
        }
        else if(ev.getSource().equals(addWishlistButton))
        {
            Textbook textbook = bookList.getSelectedValue().textbook;

            // TODO: HAVE ADD WISHLIST BUTTON HERE!
            System.out.println("WISHLIST ADD: " + textbook.title);
        }
    }

    public void buildItem()
    {
        bookList = new JList<>();
        bookList.setCellRenderer(this);

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
}
