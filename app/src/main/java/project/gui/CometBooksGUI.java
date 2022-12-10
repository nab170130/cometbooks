package project.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

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

    public CometBooksGUI()
    {
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
            switchToConversationsWindow();
        }
        else if(ev.getSource().equals(transactionWindowOption))
        {
            switchToTransactionsWindow();
        }
    }

    public void buildGUI()
    {
        menuBar                     = buildBar();
        buyWindowContainer          = new BuyWindowContainer(); 
        conversationWindowContainer = new ConversationWindowContainer();
        loginWindowContainer        = new LoginWindowContainer(this);
        transactionWindowContainer  = new TransactionWindowContainer();

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

        revalidate();
        repaint();
    }

    public void switchToConversationsWindow()
    {
        getContentPane().removeAll();
        getContentPane().add(menuBar, BorderLayout.NORTH);
        getContentPane().add(conversationWindowContainer, BorderLayout.CENTER);

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

        revalidate();
        repaint();
    }

}
