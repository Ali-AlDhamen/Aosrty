package lib.features.Authentication.Partners.SignInScreen.panels;

import java.awt.*;


import com.k33ptoo.components.KGradientPanel;


public class PartnerSignUpPanel extends KGradientPanel{
    static Container contentPane;
    public PartnerSignUpPanel(){
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
        this.add(new PartnerWelcomePanel());
        this.add(new PartnerInfoPanel());
        this.setVisible(true);

    }
    // method to replace partner info panel to extra info panel
     public void replacePanel(){
        this.remove(1);
        this.add(new PartnerExtraInfoPanel());
        this.revalidate();
        this.repaint();
    }

    
}
