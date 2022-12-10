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

public class BookListPanel extends JPanel implements ListCellRenderer<BookContainer>
{
    JScrollPane             bookContainerPane;
    JList<BookContainer>    bookList;
    JTextField              paneTitle;

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

    public void buildItem()
    {
        bookList = new JList<>();
        bookList.setCellRenderer(this);

        bookContainerPane = new JScrollPane(bookList);
        bookContainerPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        paneTitle = new JTextField("Textbooks");
        Font titleFont = new Font("Serif", Font.BOLD, 24);
        paneTitle.setFont(titleFont);
        paneTitle.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
        paneTitle.setHorizontalAlignment(JTextField.CENTER);
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(paneTitle);
        add(bookContainerPane);
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
