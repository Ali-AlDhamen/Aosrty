package lib.features.Authentication.Customer.CustomerLoginScreen.panels;

import javax.swing.*;

import com.k33ptoo.components.KButton;
import com.k33ptoo.components.KGradientPanel;

import lib.features.Authentication.Customer.CustomerLoginScreen.CustomerLoginScreen;
import lib.theme.AppColors;

import java.awt.*;

public class CustomerWelcomePanel extends KGradientPanel
{
    public CustomerWelcomePanel()
    {
        super();
        this.setkGradientFocus(0);
        this.setkFillBackground(false);
        this.setOpaque(false);
        this.setMaximumSize(getPreferredSize());
        this.setMinimumSize(getPreferredSize());
        this.setkBorderRadius(30);
        this.setkStartColor(AppColors.lightGray);
        this.setkEndColor(AppColors.lightGray);
        this.setkFillBackground(true);
        this.setLayout(new GridBagLayout());
        this.setBackground(AppColors.lightPurple);
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;

        c.insets = new Insets(5, 5, 20, 5);
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.NONE;

        JLabel title = new JLabel("Welcome Back!");
        title.setFont(new Font("Trebuchet MS", Font.PLAIN, 30));
        title.setForeground(AppColors.darkBlue);
        this.add(title, c);

        c.gridy = 1;
        JLabel subtitle = new JLabel("to keep connected with us please login");
        subtitle.setForeground(AppColors.darkBlue);
        subtitle.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        this.add(subtitle, c);

        c.gridy = 2;
        KButton signInButton = new KButton();
        signInButton.setText("Sign In");
        signInButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        signInButton.setPreferredSize(new Dimension(180, 50));
        signInButton.setkBorderRadius(50);
        signInButton.setkEndColor(AppColors.darkBlue);
        signInButton.setkStartColor(AppColors.darkBlue);
        signInButton.setkHoverEndColor(AppColors.darkBlue);
        signInButton.setkHoverStartColor(AppColors.darkBlue);
        signInButton.setkForeGround(Color.white);
        signInButton.setkHoverForeGround(AppColors.lightGray);
        signInButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        signInButton.addActionListener(e ->
        {
            CustomerLoginScreen.switchToSignInPanel();

        });
        this.add(signInButton, c);

        setVisible(true);

    }

}
