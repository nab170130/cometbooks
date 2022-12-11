package project.gui.transactionwindow;

import java.awt.GridLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;

import project.core.Controller;
import project.core.Transaction;

public class TransactionWindowContainer extends JPanel
{
    /*
     * Represents the buy window option for the GUI, where two different panes exist for books and listings.
     */
    public TransactionListPanel transactionPanel;
    public TransactionViewPanel transactionViewPanel;

    public TransactionWindowContainer(Controller controller)
    {
        buildItem(controller);
    }

    public void buildItem(Controller controller)
    {
        setLayout(new GridLayout(1,2));

        transactionViewPanel            = new TransactionViewPanel(controller);
        transactionPanel                = new TransactionListPanel(transactionViewPanel, controller);
        transactionViewPanel.listPanel  = transactionPanel;

        add(transactionPanel);
        add(transactionViewPanel);
    }

    public void setTransactions(List<Transaction> transactions)
    {
        transactionPanel.setDisplayTransactions(transactions);
    }
}
