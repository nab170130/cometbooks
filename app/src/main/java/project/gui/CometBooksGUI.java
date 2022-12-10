package project.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import project.gui.buywindow.BuyWindowContainer;

public class CometBooksGUI extends JFrame implements ActionListener
{
    // Menu elements on the top
    private JMenuBar    menuBar;
    private JMenu       windowMenu;
    private JMenuItem   buyWindowOption;
    private JMenuItem   conversationWindowOption;
    private JMenuItem   transactionWindowOption;

    // GUI elements for the buy window
    BuyWindowContainer buyWindowContainer;

    public CometBooksGUI()
    {
        buildGUI();
    }

    @Override
    public void actionPerformed(ActionEvent ev)
    {
        System.out.println("test");
    }

    public void buildGUI()
    {
        menuBar             = buildBar();
        buyWindowContainer  = new BuyWindowContainer(); 

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
