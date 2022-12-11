package project.gui.loginwindow;

import java.awt.event.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import project.core.Controller;
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

    Controller controller;

    public LoginWindowContainer(CometBooksGUI cometBooksGUI_, Controller controller_)
    {
        cometBooksGUI   = cometBooksGUI_;
        controller      = controller_;
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
            if(controller.login(netID, password))
            {
                cometBooksGUI.switchToBuyWindow();
            }
            else // OTHERWISE, DISPLAY FAIL DIALOG.
            {
                FailDialog dialog = new FailDialog();
            }
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
