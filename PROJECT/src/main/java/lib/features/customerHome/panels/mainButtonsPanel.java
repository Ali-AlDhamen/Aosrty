
package lib.features.customerHome.panels;

import java.awt.*;
import javax.swing.*;

import lib.common.Helper;
import lib.common.IconTextButton;
import lib.features.customerHome.CustomerHomeScreen;
import lib.theme.AppColors;

public class mainButtonsPanel extends JPanel
{

    mainButtonsPanel()
    {
        super();
        this.setBackground(AppColors.lightGray);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 5, 5, 5);
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.NONE;

        IconTextButton storesButton = new IconTextButton("Home", Helper.ScaleDown("PROJECT/src/main/assets/images/stores.png"));
        storesButton.addActionListener(e ->

        {
            CustomerHomeScreen.switchToStoresPanel();
        });
        this.add(storesButton, c);

        c.gridy = 1;
        IconTextButton cartButton = new IconTextButton("Cart", Helper.ScaleDown("PROJECT/src/main/assets/images/cart.png"));
        cartButton.addActionListener(e ->
        {
            CustomerHomeScreen.switchToCartPanel();
        });
        this.add(cartButton, c);

        c.gridy = 2;
        IconTextButton ordersButton = new IconTextButton("Orders", Helper.ScaleDown("PROJECT/src/main/assets/images/order.png"));
        ordersButton.addActionListener(e ->
        {
            CustomerHomeScreen.switchToOrdersPanel();
        });
        this.add(ordersButton, c);

        c.gridy = 3;
        IconTextButton contactUsButton = new IconTextButton("Contact Us", Helper.ScaleDown("PROJECT/src/main/assets/images/phoney.png"));
        contactUsButton.addActionListener(e -> {
            CustomerHomeScreen.switchToContactUsPanel();
        });

        this.add(contactUsButton, c);

        c.gridy = 4;
        IconTextButton reportButton = new IconTextButton("Report", Helper.ScaleDown("PROJECT/src/main/assets/images/report.png"));
        reportButton.addActionListener(e -> {
            CustomerHomeScreen.switchToReportPanel();
        });
        this.add(reportButton, c);

    }

}
