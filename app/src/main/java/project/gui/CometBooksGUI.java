package project.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import project.gui.buywindow.BuyWindowContainer;
import project.gui.conversationwindow.ConversationWindowContainer;
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
            getContentPane().removeAll();
            getContentPane().add(menuBar, BorderLayout.NORTH);
            getContentPane().add(buyWindowContainer, BorderLayout.CENTER);
            buyWindowContainer.revalidate();
            buyWindowContainer.repaint();
        }
        else if(ev.getSource().equals(conversationWindowOption))
        {
            getContentPane().removeAll();
            getContentPane().add(menuBar, BorderLayout.NORTH);
            getContentPane().add(conversationWindowContainer, BorderLayout.CENTER);
            conversationWindowContainer.revalidate();
            conversationWindowContainer.repaint();
        }
        else if(ev.getSource().equals(transactionWindowOption))
        {
            getContentPane().removeAll();
            getContentPane().add(menuBar, BorderLayout.NORTH);
            getContentPane().add(transactionWindowContainer, BorderLayout.CENTER);
            transactionWindowContainer.revalidate();
            transactionWindowContainer.repaint();
        }

        revalidate();
        repaint();
    }

    public void buildGUI()
    {
        menuBar                     = buildBar();
        buyWindowContainer          = new BuyWindowContainer(); 
        conversationWindowContainer = new ConversationWindowContainer();
        transactionWindowContainer  = new TransactionWindowContainer();

        setTitle("CometBooks");
        getContentPane().add(menuBar, BorderLayout.NORTH);
        getContentPane().add(buyWindowContainer, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(900, 900);
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
}
