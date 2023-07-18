package lib.features.Authentication.Customer.CustomerLoginScreen.panels;

import com.k33ptoo.components.KButton;
import com.k33ptoo.components.KGradientPanel;

import lib.common.handlers.FocusHandler;
import lib.common.handlers.PasswordHandler;
import lib.features.Authentication.Auth;
import lib.theme.AppColors;

import java.awt.*;
import javax.swing.*;

public class CustomerInfoPanel extends KGradientPanel
{
    public CustomerInfoPanel()
    {
        super();
        this.setkGradientFocus(0);
        this.setkFillBackground(false);
        this.setOpaque(false);
        this.setMaximumSize(getPreferredSize());
        this.setMinimumSize(getPreferredSize());
        this.setkBorderRadius(30);
        this.setkStartColor(Color.WHITE);
        this.setkEndColor(Color.WHITE);
        this.setkFillBackground(true);

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.insets = new Insets(5, 5, 30, 5);

        JLabel createAccountLabel = new JLabel("Create Account");
        createAccountLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 30));
        createAccountLabel.setForeground(AppColors.darkBlue);

        this.add(createAccountLabel, c);
        c.insets = new Insets(5, 5, 5, 5);
        c.gridy = 1;
        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(300, 50));
        nameField.setForeground(Color.LIGHT_GRAY);
        nameField.setText("Enter your name");
        nameField.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        nameField.setBackground(AppColors.gray);
        nameField.addFocusListener(new FocusHandler("Enter your name", nameField));
        this.add(nameField, c);
        c.gridy = 2;
        JTextField emailField = new JTextField();
        emailField.setText("Enter your Email");
        emailField.setPreferredSize(new Dimension(300, 50));
        emailField.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        emailField.setForeground(Color.LIGHT_GRAY);
        emailField.setBackground(AppColors.gray);
        emailField.addFocusListener(new FocusHandler("Enter your Email", emailField));
        this.add(emailField, c);
        c.gridy = 3;
        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(300, 50));
        passwordField.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        passwordField.setText("Enter your password");
        passwordField.setForeground(Color.LIGHT_GRAY);
        passwordField.setEchoChar((char) 0);
        passwordField.setBackground(AppColors.gray);
        passwordField.addFocusListener(new PasswordHandler("Enter your password", passwordField));
        this.add(passwordField, c);

        c.gridy = 4;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.NONE;
        KButton signUpButton = new KButton();
        signUpButton.setText("Sign Up");
        signUpButton.setkStartColor(AppColors.darkBlue);
        signUpButton.setkEndColor(AppColors.darkBlue);
        signUpButton.setkHoverStartColor(AppColors.darkBlue);
        signUpButton.setkHoverEndColor(AppColors.darkBlue);
        signUpButton.setkForeGround(Color.WHITE);
        signUpButton.setkHoverForeGround(AppColors.lightGray);
        signUpButton.setkBorderRadius(50);
        signUpButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        signUpButton.setPreferredSize(new Dimension(180, 50));
        signUpButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        signUpButton.addActionListener(e ->
        {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            if (name.equals("Enter your name") || email.equals("Enter your Email") || password.equals("Enter your password"))
            {
                JOptionPane.showMessageDialog(null, "Please fill all the fields");
            }
            else
            {
                Auth.createCustomerAccount(name , email , password);
            }

        });

        this.add(signUpButton, c);
        setVisible(true);

    }

}
