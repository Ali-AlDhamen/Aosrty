package lib.features.Authentication.Customer.CustomerLoginScreen.panels;

import java.awt.*;

import com.k33ptoo.components.KGradientPanel;

public class CustomerSignInPanel extends KGradientPanel
{
    public CustomerSignInPanel()
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
        this.add(new CustomerLoginPanel());
        this.add(new CustomerNewPanel());
        this.setVisible(true);

    }

}
