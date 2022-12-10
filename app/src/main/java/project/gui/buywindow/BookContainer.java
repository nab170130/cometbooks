package project.gui.buywindow;

import java.awt.*;
import javax.swing.*;

import project.core.Textbook;

public class BookContainer extends JPanel
{
    Textbook    textbook;

    private JTextField  titleField;
    private JTextArea   descriptionField;

    public BookContainer(Textbook initialTextbook)
    {
        buildItem();
        setTextbook(initialTextbook);
    }

    public void buildItem()
    {
        titleField          = new JTextField();
        descriptionField    = new JTextArea();

        titleField.setEditable(false);
        descriptionField.setEditable(false);

        titleField.setBorder(BorderFactory.createEmptyBorder());
        descriptionField.setBorder(BorderFactory.createEmptyBorder());

        Font titleFont = new Font("Serif", Font.BOLD, 18);
        titleField.setFont(titleFont);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(titleField);
        add(descriptionField);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
    }

    public void setElementBackgrounds(Color color)
    {
        titleField.setBackground(color);
        descriptionField.setBackground(color);
    }

    public void setTextbook(Textbook textbook_)
    {
        textbook = textbook_;

        titleField.setText(textbook.title);

        StringBuilder descBuilder = new StringBuilder();
        descBuilder.append("Author(s): ");
        for(String author : textbook.authors)
        {
            descBuilder.append(author);
            descBuilder.append(", ");
        }

        descBuilder.append("\nYear: ");
        descBuilder.append(textbook.year);
        descBuilder.append("\nEdition: ");
        descBuilder.append(textbook.edition);
        descBuilder.append("\nISBN: ");
        descBuilder.append(textbook.isbn);

        descriptionField.setText(descBuilder.toString());
    }
}
