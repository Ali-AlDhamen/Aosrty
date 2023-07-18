package lib.common.handlers;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;
import java.awt.*;

public class FocusHandler implements FocusListener
{
    private String defaultText;
    private JTextField textField;

    public FocusHandler(String defaultText, JTextField textField)
    {
        this.defaultText = defaultText;
        this.textField = textField;

    }

    @Override
    public void focusGained(FocusEvent e)
    {
        if (textField.getText().equals(defaultText))
        {
            textField.setText("");
            textField.setForeground(Color.BLACK);
        }
    }

    @Override
    public void focusLost(FocusEvent e)
    {
        if (textField.getText().equals(""))
        {
            textField.setText(defaultText);
            textField.setForeground(Color.LIGHT_GRAY);
        }
    }
}
