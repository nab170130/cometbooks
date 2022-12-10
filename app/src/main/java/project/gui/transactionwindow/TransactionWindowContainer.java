package project.gui.transactionwindow;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class TransactionWindowContainer extends JPanel
{
    /*
     * Represents the buy window option for the GUI, where two different panes exist for books and listings.
     */
    TransactionListPanel transactionPanel;
    TransactionViewPanel transactionViewPanel;

    public TransactionWindowContainer()
    {
        buildItem();
    }

    public void buildItem()
    {
        setLayout(new GridLayout(1,2));

        transactionViewPanel   = new TransactionViewPanel();
        transactionPanel       = new TransactionListPanel(transactionViewPanel);

        add(transactionPanel);
        add(transactionViewPanel);
    }
}
