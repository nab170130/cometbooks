package project.gui.transactionwindow;

import java.awt.event.*;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import project.core.Transaction;

public class TransactionViewPanel extends JPanel implements ActionListener
{
    JLabel textbookSectionHeader;
    JLabel bookTitle;
    JLabel authors;
    JLabel year;
    JLabel edition;
    JLabel isbn;

    JLabel listingSectionHeader;
    JLabel condition;
    JLabel listingPrice;
    JLabel description;
    
    JLabel sellerSectionHeader;
    JLabel sellerName;
    JLabel sellerNetID;

    JLabel buyerSectionHeader;
    JLabel buyerName;
    JLabel buyerNetID;

    JButton completeTransactionButton;

    JLabel paneTitle;

    public TransactionViewPanel()
    {
        buildItem();
    }

    @Override
    public void actionPerformed(ActionEvent ev)
    {
        if(ev.getSource().equals(completeTransactionButton))
        {
            // DO COMPLETE TRANSACTION
            System.out.println("COMPLETE TRANSACTION");
        }
    }

    public void buildItem()
    {
        paneTitle = new JLabel("Transaction Details");
        Font titleFont = new Font("Serif", Font.BOLD, 24);
        paneTitle.setFont(titleFont);
        paneTitle.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
        paneTitle.setHorizontalAlignment(JLabel.CENTER);
        paneTitle.setMaximumSize(paneTitle.getPreferredSize());

        Font sectionFont = new Font("Serif", Font.BOLD, 16);

        textbookSectionHeader = new JLabel("Textbook Details");
        textbookSectionHeader.setFont(sectionFont);
        bookTitle = new JLabel();
        authors = new JLabel();
        year = new JLabel();
        edition = new JLabel();
        isbn = new JLabel();

        listingSectionHeader = new JLabel("Listing Details");
        listingSectionHeader.setFont(sectionFont);
        listingSectionHeader.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        condition = new JLabel();
        listingPrice = new JLabel();
        description = new JLabel();

        sellerSectionHeader = new JLabel("Seller Details");
        sellerSectionHeader.setFont(sectionFont);
        sellerSectionHeader.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        sellerName = new JLabel();
        sellerNetID = new JLabel();

        buyerSectionHeader = new JLabel("Buyer Details");
        buyerSectionHeader.setFont(sectionFont);
        buyerSectionHeader.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        buyerName = new JLabel();
        buyerNetID = new JLabel();

        completeTransactionButton = new JButton("Complete Transaction");
        completeTransactionButton.addActionListener(this);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(paneTitle);
        add(textbookSectionHeader);
        add(bookTitle);
        add(authors);
        add(year);
        add(edition);
        add(isbn);
        add(listingSectionHeader);
        add(condition);
        add(listingPrice);
        add(description);
        add(sellerSectionHeader);
        add(sellerName);
        add(sellerNetID);
        add(buyerSectionHeader);
        add(buyerName);
        add(buyerNetID);
        add(completeTransactionButton);
    }

    public void setTransaction(Transaction transaction)
    {
        bookTitle.setText("Title: " + transaction.listing.textbook.title);
        
        StringBuilder authorStringBuilder = new StringBuilder();
        for(String author : transaction.listing.textbook.authors)
        {
            authorStringBuilder.append(author);
            authorStringBuilder.append(", ");
        }
        authors.setText("Author: " + authorStringBuilder.toString());

        year.setText("Year: " + transaction.listing.textbook.year);
        edition.setText("Edition: " + transaction.listing.textbook.edition);
        isbn.setText("ISBN: " + transaction.listing.textbook.isbn);

        condition.setText("Condition: " + transaction.listing.condition);
        listingPrice.setText("Listing Price: " + transaction.listing.listingPrice);
        description.setText("Description: " + transaction.listing.description);

        sellerName.setText("Name: " + transaction.listing.seller.account.displayName);
        sellerNetID.setText("NetID: " + transaction.listing.seller.account.netID);

        buyerName.setText("Name: " + transaction.buyer.account.displayName);
        buyerNetID.setText("NetID: " + transaction.buyer.account.netID);
    }
}
