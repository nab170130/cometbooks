package project.gui.buywindow;

import java.awt.*;
import javax.swing.*;

import project.core.SalesListing;

public class ListingContainer extends JPanel
{
    SalesListing salesListing;

    private JTextField  sellerField;
    private JTextField  conditionField;
    private JTextField  priceField;
    private JTextField  holdStatusField;
    private JTextArea   descriptionField;


    public ListingContainer(SalesListing initialListing)
    {
        buildItem();
        setListing(initialListing);
    }


    public void buildItem()
    {
        sellerField         = new JTextField();
        conditionField      = new JTextField();
        priceField          = new JTextField();
        holdStatusField     = new JTextField();
        descriptionField    = new JTextArea();

        sellerField.setEditable(false);
        conditionField.setEditable(false);
        priceField.setEditable(false);
        holdStatusField.setEditable(false);
        descriptionField.setEditable(false);

        sellerField.setBorder(BorderFactory.createEmptyBorder());
        conditionField.setBorder(BorderFactory.createEmptyBorder());
        priceField.setBorder(BorderFactory.createEmptyBorder());
        holdStatusField.setBorder(BorderFactory.createEmptyBorder());
        descriptionField.setBorder(BorderFactory.createEmptyBorder());

        Font sellerFont = new Font("Serif", Font.BOLD, 18);
        sellerField.setFont(sellerFont);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(sellerField);
        add(priceField);
        add(conditionField);
        add(descriptionField);
        add(holdStatusField);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
    }


    public void setElementBackgrounds(Color color)
    {
        sellerField.setBackground(color);
        conditionField.setBackground(color);
        priceField.setBackground(color);
        holdStatusField.setBackground(color);
        descriptionField.setBackground(color);
    }

    
    public void setListing(SalesListing listing_)
    {
        salesListing = listing_;

        sellerField.setText(salesListing.seller.account.displayName);
        conditionField.setText("Condition: " + salesListing.condition);
        priceField.setText("Price: " + String.format("$%.2f", salesListing.listingPrice) + " (Suggested: " + String.format("$%.2f", salesListing.textbook.suggestedPrice) + ")");
        holdStatusField.setText(salesListing.onHold ? "HOLD STATUS: ON HOLD" : "HOLD STATUS: AVAILABLE");
        descriptionField.setText("Description: " + salesListing.description);
    }
}
