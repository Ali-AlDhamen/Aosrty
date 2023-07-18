package lib.features.Authentication.Customer.CustomerLoginScreen.panels;

import javax.swing.*;

import com.k33ptoo.components.KButton;
import com.k33ptoo.components.KGradientPanel;

import lib.common.handlers.FocusHandler;
import lib.common.handlers.PasswordHandler;
import lib.features.Authentication.Auth;
import lib.theme.AppColors;

import java.awt.*;

public class CustomerLoginPanel extends KGradientPanel
{
    public CustomerLoginPanel()
    {
        super();
        this.setkGradientFocus(0);
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

        JLabel SignInLabel = new JLabel("Sign In");
        SignInLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
        SignInLabel.setForeground(AppColors.darkBlue);

        this.add(SignInLabel, c);
        c.insets = new Insets(5, 5, 5, 5);
        c.gridy = 1;
        JTextField emailField = new JTextField();
        emailField.setText("Enter your Email");
        emailField.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        emailField.setPreferredSize(new Dimension(300, 50));
        emailField.setForeground(Color.LIGHT_GRAY);
        emailField.setBackground(AppColors.gray);
        emailField.addFocusListener(new FocusHandler("Enter your Email", emailField));
        this.add(emailField, c);
        c.gridy = 2;
        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(300, 50));
        passwordField.setText("Enter your password");
        passwordField.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        passwordField.setForeground(Color.LIGHT_GRAY);
        passwordField.setEchoChar((char) 0);
        passwordField.setBackground(AppColors.gray);
        passwordField.addFocusListener(new PasswordHandler("Enter your password", passwordField));
        this.add(passwordField, c);

        c.gridy = 3;
        JLabel forgetPasswordJLabel = new JLabel("Forget Password?");
        forgetPasswordJLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
        forgetPasswordJLabel.setForeground(Color.LIGHT_GRAY);
        this.add(forgetPasswordJLabel, c);

        c.gridy = 4;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.NONE;
        KButton signInButton = new KButton();
        signInButton.setText("Sign In");
        signInButton.setkStartColor(AppColors.darkBlue);
        signInButton.setkEndColor(AppColors.darkBlue);
        signInButton.setkHoverStartColor(AppColors.darkBlue);
        signInButton.setkHoverEndColor(AppColors.darkBlue);
        signInButton.setkHoverForeGround(AppColors.lightGray);
        signInButton.setkForeGround(Color.WHITE);
        signInButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        signInButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        signInButton.setkBorderRadius(50);
        signInButton.setPreferredSize(new Dimension(180, 50));

        signInButton.addActionListener(e ->
        {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            Auth.loginCustomer(email, password);
        });
        this.add(signInButton, c);
        setVisible(true);

    }

}
