package lib.features.Authentication.Customer.CustomerLoginScreen.panels;

import com.k33ptoo.components.KGradientPanel;

import java.awt.*;

public class CustomerSignUpPanel extends KGradientPanel
{
    public CustomerSignUpPanel()
    {
        super();
        this.setBounds(300, 200, 800, 400);
        this.setLayout(new GridLayout(1, 2));
        this.setBackground(Color.WHITE);
        this.setkStartColor(new Color(255, 255, 255));
        this.setkEndColor(new Color(255, 255, 255));
        this.setkGradientFocus(0);
        this.setkFillBackground(true);
        this.setOpaque(false);
        this.setMaximumSize(getPreferredSize());
        this.setMinimumSize(getPreferredSize());
        this.setkBorderRadius(30);
        this.add(new CustomerWelcomePanel());
        this.add(new CustomerInfoPanel());
        this.setVisible(true);

    }

}
