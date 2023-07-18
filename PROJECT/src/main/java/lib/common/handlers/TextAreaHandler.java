package lib.common.handlers;

import javax.swing.*;

import java.awt.*;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TextAreaHandler implements FocusListener
{
    String text;
    JTextArea textArea;

    public TextAreaHandler(String text, JTextArea textArea)
    {
        this.text = text;
        this.textArea = textArea;
    }

    @Override
    public void focusGained(FocusEvent arg0)
    {
        if (textArea.getText().equals(text))
        {
            textArea.setText("");
            textArea.setForeground(Color.BLACK);
        }

    }

    @Override
    public void focusLost(FocusEvent arg0)
    {

        if (textArea.getText().isEmpty())
        {
            textArea.setForeground(Color.LIGHT_GRAY);
            textArea.setText(text);
        }
    }
}