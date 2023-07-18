package lib.features.Authentication.Partners.SignInScreen.panels;


import com.k33ptoo.components.KGradientPanel;

import java.awt.*;

public class PartnerSignInPanel extends KGradientPanel
{
    

    public PartnerSignInPanel()
    {
        super();
        this.setBounds(300, 200, 800, 400);
        this.setLayout(new GridLayout(1, 2));
        this.setBackground(Color.WHITE);
        this.setBackground(Color.WHITE);
        this.setkStartColor(new Color(255, 255, 255));
        this.setkEndColor(new Color(255, 255, 255));
        this.setkGradientFocus(0);
        this.setkFillBackground(true);
        this.setOpaque(false);
        this.setMaximumSize(getPreferredSize());
        this.setMinimumSize(getPreferredSize());
        this.setkBorderRadius(30);
        this.add(new PartnerLoginPanel());
        this.add(new PartnerNewPanel());
        this.setVisible(true);

    }

    

}
