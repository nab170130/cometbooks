package project.gui.loginwindow;

import java.awt.event.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import project.gui.CometBooksGUI;
import project.gui.buywindow.BuyWindowContainer;

public class LoginWindowContainer extends JPanel implements ActionListener
{
    JLabel netIDLabel;
    JLabel passwordLabel;

    JTextField netIDField;
    JTextField passwordField;

    JButton loginButton;

    CometBooksGUI cometBooksGUI;

    public LoginWindowContainer(CometBooksGUI cometBooksGUI_)
    {
        cometBooksGUI = cometBooksGUI_;
        buildItem();
    }

    @Override
    public void actionPerformed(ActionEvent ev)
    {
        if(ev.getSource().equals(loginButton))
        {
            // TODO: VALIDATE INFO CALL HERE
            String netID    = netIDField.getText();
            String password = passwordField.getText();

            // IF SUCCESSFUL, SWITCH TO BUY WINDOW.
            cometBooksGUI.switchToBuyWindow();
        }
    }

    public void buildItem()
    {
        netIDLabel      = new JLabel("NetID:");
        passwordLabel   = new JLabel("Password:");

        netIDField      = new JTextField(9);
        passwordField   = new JTextField(30);

        JPanel netIDPanel = new JPanel();
        netIDPanel.add(netIDLabel);
        netIDPanel.add(netIDField);
        netIDPanel.setMaximumSize(netIDPanel.getPreferredSize());

        JPanel passwordPanel = new JPanel();
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        passwordPanel.setMaximumSize(passwordPanel.getPreferredSize());

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(netIDPanel);
        add(passwordPanel);
        add(loginButton);
    }
}
