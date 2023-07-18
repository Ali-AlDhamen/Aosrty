package lib.common.handlers;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;
import java.awt.*;

public class PasswordHandler implements FocusListener {
    private String defaultText;
    private JPasswordField passwordField;

    public PasswordHandler(String defaultText, JPasswordField passwordField) {
        this.defaultText = defaultText;
        this.passwordField = passwordField;
    }

    @Override
            public void focusGained(FocusEvent e)
            {
                if (new String(passwordField.getPassword()).equals(defaultText))
                {
                    passwordField.setText("");
                    passwordField.setEchoChar('●');
                    passwordField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e)
            {
                if (passwordField.getPassword().length == 0)
                {
                    passwordField.setEchoChar((char) 0);
                    passwordField.setText("Enter your password");
                    passwordField.setForeground(Color.LIGHT_GRAY);
                }
            }
}