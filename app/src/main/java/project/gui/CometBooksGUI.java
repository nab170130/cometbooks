package project.gui;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.List;
import java.util.Vector;

import javax.swing.*;

import project.core.Controller;
import project.core.Conversation;
import project.core.Textbook;
import project.core.Transaction;
import project.gui.buywindow.BuyWindowContainer;
import project.gui.conversationwindow.ConversationWindowContainer;
import project.gui.loginwindow.LoginWindowContainer;
import project.gui.transactionwindow.TransactionWindowContainer;

public class CometBooksGUI extends JFrame implements ActionListener
{
    // Menu elements on the top
    private JMenuBar    menuBar;
    private JMenu       windowMenu;
    private JMenuItem   buyWindowOption;
    private JMenuItem   conversationWindowOption;
    private JMenuItem   transactionWindowOption;

    // GUI elements for each window
    BuyWindowContainer          buyWindowContainer;
    ConversationWindowContainer conversationWindowContainer;
    LoginWindowContainer        loginWindowContainer;
    TransactionWindowContainer  transactionWindowContainer;

    Controller controller;

    public CometBooksGUI(Controller sysController)
    {
        controller = sysController;
        buildGUI();
    }

    @Override
    public void actionPerformed(ActionEvent ev)
    {
        if(ev.getSource().equals(buyWindowOption))
        {
            switchToBuyWindow();
        }
        else if(ev.getSource().equals(conversationWindowOption))
        {
            switchToConversationsWindow(null);
        }
        else if(ev.getSource().equals(transactionWindowOption))
        {
            switchToTransactionsWindow();
        }
    }

    public void buildGUI()
    {
        menuBar                     = buildBar();
        buyWindowContainer          = new BuyWindowContainer(controller, this); 
        conversationWindowContainer = new ConversationWindowContainer(controller);
        loginWindowContainer        = new LoginWindowContainer(this, controller);
        transactionWindowContainer  = new TransactionWindowContainer(controller);

        setTitle("CometBooks");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 900);
        setVisible(true);
        switchToLoginWindow();
    }

    private JMenuBar buildBar()
    {
        buyWindowOption           = new JMenuItem("Buy");
        conversationWindowOption  = new JMenuItem("Conversations");
        transactionWindowOption   = new JMenuItem("Transactions");

        buyWindowOption.addActionListener(this);
        conversationWindowOption.addActionListener(this);
        transactionWindowOption.addActionListener(this);

        windowMenu = new JMenu("Windows");
        windowMenu.add(buyWindowOption);
        windowMenu.add(conversationWindowOption);
        windowMenu.add(transactionWindowOption);

        menuBar = new JMenuBar();
        menuBar.add(windowMenu);

        return menuBar;
    }

    public void switchToBuyWindow()
    {
        getContentPane().removeAll();
        getContentPane().add(menuBar, BorderLayout.NORTH);
        getContentPane().add(buyWindowContainer, BorderLayout.CENTER);

        buyWindowContainer.bookPanel.bookList.clearSelection();
        buyWindowContainer.listingPanel.listingList.setListData(new Vector<>());

        List<Textbook> initialRecommendations = controller.navigateToBuyWindow();
        buyWindowContainer.setBooks(initialRecommendations);

        revalidate();
        repaint();
    }

    public void switchToConversationsWindow(Conversation conversationFocus)
    {
        getContentPane().removeAll();
        getContentPane().add(menuBar, BorderLayout.NORTH);
        getContentPane().add(conversationWindowContainer, BorderLayout.CENTER);

        conversationWindowContainer.conversationPanel.conversationList.clearSelection();
        conversationWindowContainer.conversationViewPanel.conversationViewArea.setText("");

        List<Conversation> conversations = controller.navigateToConversationWindow();

        conversationWindowContainer.setConversations(conversations, conversationFocus);

        revalidate();
        repaint();
    }

    public void switchToLoginWindow()
    {
        getContentPane().removeAll();
        getContentPane().add(loginWindowContainer, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    public void switchToTransactionsWindow()
    {
        getContentPane().removeAll();
        getContentPane().add(menuBar, BorderLayout.NORTH);
        getContentPane().add(transactionWindowContainer, BorderLayout.CENTER);

        transactionWindowContainer.transactionPanel.transactionList.clearSelection();

        List<Transaction> transactions = controller.navigateToTransactionsWindow();

        transactionWindowContainer.setTransactions(transactions);

        revalidate();
        repaint();
    }

}
