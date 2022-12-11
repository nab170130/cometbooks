package project.gui.loginwindow;

import java.awt.event.*;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class FailDialog extends JDialog implements ActionListener
{
    JLabel  failLabel;
    JButton confirmButton;


    public FailDialog()
    {
        buildItem();
    }


    @Override
    public void actionPerformed(ActionEvent ev)
    {
        if(confirmButton.equals(ev.getSource()))
        {
            setVisible(false);
        }
    }

    
    public void buildItem()
    {

        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(this);

        failLabel = new JLabel("Username or password invalid.");

        setLayout(new FlowLayout());
        add(failLabel);
        add(confirmButton);

        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setSize(500, 100);
        setVisible(true);
    }
}
