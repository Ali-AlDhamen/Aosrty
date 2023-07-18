package lib.features.partnerHome.panels;

import java.awt.*;
import javax.swing.*;

import lib.theme.AppColors;

public class PartnerNavBar extends JPanel {
    public PartnerNavBar() {
        super();
        this.setBackground(AppColors.lightGray);
        this.setLayout(new BorderLayout());
        this.add(new PartnerButtonsPanel(), BorderLayout.NORTH);
        this.add(new PartnerLogoutButtonsPanel(), BorderLayout.SOUTH);
    }
    
}
