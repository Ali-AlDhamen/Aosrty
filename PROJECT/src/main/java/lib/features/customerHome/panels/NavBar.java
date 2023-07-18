package lib.features.customerHome.panels;

import java.awt.*;
import javax.swing.*;

import lib.theme.AppColors;

public class NavBar extends JPanel
{
    public NavBar()
    {
        super();
        this.setBackground(AppColors.lightGray);
        this.setLayout(new BorderLayout());
        this.add(new mainButtonsPanel(), BorderLayout.NORTH);
        this.add(new LogoutButtonPanel(), BorderLayout.SOUTH);

    }

}
