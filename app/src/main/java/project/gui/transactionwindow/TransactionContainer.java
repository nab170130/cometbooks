package project.gui.transactionwindow;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

import project.core.Transaction;

public class TransactionContainer extends JPanel
{
    JTextField bookTitle;
    JTextField buyerName;
    JTextField sellerName;
    
    Transaction transaction;

    public TransactionContainer(Transaction transaction_)
    {
        buildItem();
        setTransaction(transaction_);
    }

    public void buildItem()
    {
        bookTitle   = new JTextField();
        buyerName   = new JTextField();
        sellerName  = new JTextField();

        bookTitle.setEditable(false);
        buyerName.setEditable(false);
        sellerName.setEditable(false);

        bookTitle.setBorder(BorderFactory.createEmptyBorder());
        buyerName.setBorder(BorderFactory.createEmptyBorder());
        sellerName.setBorder(BorderFactory.createEmptyBorder());

        Font titleFont = new Font("Serif", Font.BOLD, 18);
        bookTitle.setFont(titleFont);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(bookTitle);
        add(buyerName);
        add(sellerName);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
    }

    public void setElementBackgrounds(Color color)
    {
        bookTitle.setBackground(color);
        buyerName.setBackground(color);
        sellerName.setBackground(color);
    }

    public void setTransaction(Transaction conversation_)
    {
        transaction = conversation_;

        bookTitle.setText(transaction.listing.textbook.title);
        buyerName.setText(transaction.buyer.account.displayName);
        sellerName.setText(transaction.listing.seller.account.displayName);
    }
}
