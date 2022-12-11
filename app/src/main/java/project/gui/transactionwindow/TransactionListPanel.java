package project.gui.transactionwindow;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import project.core.Controller;
import project.core.Transaction;

public class TransactionListPanel extends JPanel implements ListCellRenderer<TransactionContainer>, ListSelectionListener
{
    public JScrollPane                 transactionContainerPane;
    public JList<TransactionContainer> transactionList;
    public JLabel                      paneTitle;
    public TransactionViewPanel        transactionViewPanel;

    Controller controller;


    public TransactionListPanel(TransactionViewPanel panel, Controller controller_)
    {
        transactionViewPanel    = panel;
        controller              = controller_;
        buildItem();
    }


    public void buildItem()
    {
        transactionList = new JList<>();
        transactionList.setCellRenderer(this);
        transactionList.addListSelectionListener(this);

        transactionContainerPane = new JScrollPane(transactionList);
        transactionContainerPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        paneTitle = new JLabel("Transactions");
        Font titleFont = new Font("Serif", Font.BOLD, 24);
        paneTitle.setFont(titleFont);
        paneTitle.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
        paneTitle.setHorizontalAlignment(JLabel.CENTER);
        paneTitle.setMaximumSize(paneTitle.getPreferredSize());

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(paneTitle);
        add(transactionContainerPane);
    }


    @Override
    public Component getListCellRendererComponent(JList<? extends TransactionContainer> list, TransactionContainer value, int index, boolean isSelected, boolean cellHasFocus)
    {
        TransactionContainer renderItem = list.getModel().getElementAt(index);

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


    public void setDisplayTransactions(List<Transaction> transactions)
    {
        Vector<TransactionContainer> transactionContainers = new Vector<>();

        for(Transaction transaction : transactions)
        {
            TransactionContainer displayContainer = new TransactionContainer(transaction);
            transactionContainers.add(displayContainer);
        }

        transactionList.setListData(transactionContainers);
    }

    
    @Override
    public void valueChanged(ListSelectionEvent ev)
    {
        if(ev.getSource().equals(transactionList))
        {
            if(transactionList.getSelectedValue() == null)
            {
                return;
            }

            Transaction toFocusTransaction = transactionList.getSelectedValue().transaction;
            transactionViewPanel.setTransaction(toFocusTransaction);
            invalidate();
            repaint();
        }
    }
}
