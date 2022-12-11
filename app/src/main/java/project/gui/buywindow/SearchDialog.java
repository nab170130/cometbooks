package project.gui.buywindow;

import java.awt.event.*;
import java.util.HashMap;
import java.util.List;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import project.core.Controller;
import project.core.Textbook;

public class SearchDialog extends JDialog implements ActionListener
{
    JPanel fieldsPanel;

    JLabel isbnLabel;
    JLabel yearLabel;
    JLabel authorLabel;
    JLabel editionLabel;
    JLabel titleLabel;

    JTextField isbnField;
    JTextField yearField;
    JTextField authorField;
    JTextField editionField;
    JTextField titleField;

    JButton confirmButton;

    BookListPanel parentPanel;

    Controller controller;

    public SearchDialog(Controller controller_, BookListPanel parentPanel_)
    {
        controller  = controller_;
        parentPanel = parentPanel_;
        buildItem();
    }

    @Override
    public void actionPerformed(ActionEvent ev)
    {
        if(confirmButton.equals(ev.getSource()))
        {
            // Create a HashMap that maps field to value.
            HashMap<String, String> searchParameters = new HashMap<>();

            String isbn     = isbnField.getText();
            String year     = yearField.getText();
            String authors  = authorField.getText();
            String edition  = editionField.getText();
            String title    = titleField.getText();

            if(!isbn.equals(""))
            {
                searchParameters.put("isbn", isbn);
            }
            
            if(!year.equals(""))
            {
                searchParameters.put("year", year);
            }

            if(!authors.equals(""))
            {
                searchParameters.put("author", authors);
            }

            if(!edition.equals(""))
            {
                searchParameters.put("edition", edition);
            }

            if(!title.equals(""))
            {
                searchParameters.put("title", title);
            }

            // TODO: PUT CONTROLLER CALL TO performSearch!
            List<Textbook> matchingTextbooks = controller.performSearch(searchParameters);
            parentPanel.setDisplayTextbooks(matchingTextbooks);

            setVisible(false);
        }
    }

    public void buildItem()
    {
        isbnLabel       = new JLabel("ISBN:");
        yearLabel       = new JLabel("Year:");
        authorLabel     = new JLabel("Author(s):");
        editionLabel    = new JLabel("Edition:");
        titleLabel      = new JLabel("Title:");

        isbnField       = new JTextField();
        yearField       = new JTextField();
        authorField     = new JTextField();
        editionField    = new JTextField();
        titleField      = new JTextField();

        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(this);

        fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(5,2));
        fieldsPanel.add(titleLabel);
        fieldsPanel.add(titleField);
        fieldsPanel.add(authorLabel);
        fieldsPanel.add(authorField);
        fieldsPanel.add(yearLabel);
        fieldsPanel.add(yearField);
        fieldsPanel.add(editionLabel);
        fieldsPanel.add(editionField);
        fieldsPanel.add(isbnLabel);
        fieldsPanel.add(isbnField);

        add(fieldsPanel, BorderLayout.CENTER);
        add(confirmButton, BorderLayout.SOUTH);

        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);
        setTitle("Search Book");
    }
}
