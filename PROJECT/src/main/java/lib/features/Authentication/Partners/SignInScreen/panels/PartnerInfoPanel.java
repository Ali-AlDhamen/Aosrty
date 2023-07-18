package lib.features.Authentication.Partners.SignInScreen.panels;

import javax.swing.*;

import com.k33ptoo.components.KButton;
import com.k33ptoo.components.KGradientPanel;

import lib.exceptions.Validation;

import lib.common.handlers.FocusHandler;
import lib.common.handlers.PasswordHandler;
import lib.features.Authentication.Auth;
import lib.features.Authentication.Partners.SignInScreen.PartnerLoginScreen;
import lib.features.partnerHome.PartnerHomeScreen;
import lib.models.Partner;
import lib.models.Store;
import lib.theme.AppColors;

import java.awt.*;

public class PartnerInfoPanel extends KGradientPanel
{
    public PartnerInfoPanel()
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
        JTextField storeNameField = new JTextField();
        storeNameField.setPreferredSize(new Dimension(300, 50));
        storeNameField.setForeground(Color.LIGHT_GRAY);
        storeNameField.setText("Enter your store name");
        storeNameField.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        storeNameField.setBackground(AppColors.gray);
        storeNameField.addFocusListener(new FocusHandler("Enter your store name", storeNameField));
        this.add(storeNameField, c);

        c.gridy = 2;
        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(300, 50));
        nameField.setForeground(Color.LIGHT_GRAY);

        nameField.setText("Enter your name");
        nameField.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        nameField.setBackground(AppColors.gray);
        nameField.addFocusListener(new FocusHandler("Enter your name", nameField));
        this.add(nameField, c);

        c.gridy = 3;

        JTextField emailField = new JTextField();
        emailField.setText("Enter your Email");
        emailField.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        emailField.setPreferredSize(new Dimension(300, 50));
        emailField.setForeground(Color.LIGHT_GRAY);
        emailField.setBackground(AppColors.gray);
        emailField.addFocusListener(new FocusHandler("Enter your Email", emailField));
        this.add(emailField, c);

        c.gridy = 4;
        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(300, 50));
        passwordField.setText("Enter your password");
        passwordField.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        passwordField.setForeground(Color.LIGHT_GRAY);
        passwordField.setEchoChar((char) 0);
        passwordField.setBackground(AppColors.gray);
        passwordField.addFocusListener(new PasswordHandler("Enter your password", passwordField));
        this.add(passwordField, c);

        c.gridy = 5;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.NONE;
        KButton signUpButton = new KButton();
        signUpButton.setText("Next");
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
            String storeName = storeNameField.getText();
            String name = nameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            if (storeName.equals("Enter your store name") || name.equals("Enter your name") || email.equals("Enter your Email")
                    || password.equals("Enter your password"))
            {
                JOptionPane.showMessageDialog(null, "Please fill all the fields");
            }
            else
            {
                try
                {
                    Auth.checkEmail(email);
                    Auth.checkPassword(password);
                    PartnerHomeScreen.store = new Store();
                    PartnerHomeScreen.partner = new Partner();
                    PartnerHomeScreen.partner.setName(name);
                    PartnerHomeScreen.partner.setEmail(email);
                    PartnerHomeScreen.partner.setPassword(password);
                    PartnerHomeScreen.store.setName(storeName);
                    PartnerLoginScreen.partnerSignUpPanel.replacePanel();
                }
                catch (Validation err)
                {
                    JOptionPane.showMessageDialog(null, err.getMessage());
                    return;
                }

            }
        });
        this.add(signUpButton, c);
    }

}
