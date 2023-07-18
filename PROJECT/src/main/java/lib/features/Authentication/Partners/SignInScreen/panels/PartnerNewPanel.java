package lib.features.Authentication.Partners.SignInScreen.panels;

import javax.swing.*;

import com.k33ptoo.components.KButton;
import com.k33ptoo.components.KGradientPanel;

import lib.features.Authentication.Partners.SignInScreen.PartnerLoginScreen;
import lib.theme.AppColors;

import java.awt.*;

public class PartnerNewPanel extends KGradientPanel
{
    public PartnerNewPanel()
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

        JLabel title = new JLabel("Hello, Partner!");
        title.setFont(new Font("Trebuchet MS", Font.PLAIN, 30));
        title.setForeground(AppColors.darkBlue);
        this.add(title, c);

        c.gridy = 1;
        JLabel subtitle = new JLabel("Enter your details and start journey with us");
        subtitle.setForeground(AppColors.darkBlue);
        subtitle.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
        this.add(subtitle, c);

        c.gridy = 2;
        KButton signUpButton = new KButton();
        signUpButton.setText("Sign Up");
        signUpButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        signUpButton.setPreferredSize(new Dimension(180, 50));
        signUpButton.setkBorderRadius(50);
        signUpButton.setkEndColor(AppColors.darkBlue);
        signUpButton.setkStartColor(AppColors.darkBlue);
        signUpButton.setkHoverEndColor(AppColors.darkBlue);
        signUpButton.setkHoverStartColor(AppColors.darkBlue);
        signUpButton.setkHoverForeGround(AppColors.lightGray);
        signUpButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        signUpButton.setkForeGround(Color.WHITE);
        signUpButton.addActionListener(e -> {
            PartnerLoginScreen.switchToSignUpPanel();
        });
        this.add(signUpButton, c);
    }

}
