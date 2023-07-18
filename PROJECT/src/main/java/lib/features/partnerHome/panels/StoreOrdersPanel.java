package lib.features.partnerHome.panels;

import com.k33ptoo.components.KGradientPanel;

import lib.API.Database;
import lib.features.partnerHome.PartnerHomeScreen;
import lib.theme.AppColors;

import java.awt.*;
import javax.swing.*;

public class StoreOrdersPanel extends JPanel
{

    public StoreOrdersPanel()
    {
        super();
        this.setBackground(AppColors.darkBlue);
        this.setLayout(null);
        Database.getStoreOrders();

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(1000, 800));
        scrollPane.setBounds(50, 50, 1000, 800);

        scrollPane.getVerticalScrollBar().setOpaque(false);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));

        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        KGradientPanel panel = new KGradientPanel();
        panel.setPreferredSize(new Dimension(1000, PartnerHomeScreen.storeOrders.size() * 155));
        panel.setBackground(AppColors.darkBlue);
        panel.setOpaque(false);
        panel.setkStartColor(AppColors.darkBlue);
        panel.setkEndColor(AppColors.darkBlue);
        panel.setkGradientFocus(0);
        panel.setkFillBackground(true);
        panel.setkBorderRadius(0);

        for (int i = 0; i < PartnerHomeScreen.storeOrders.size(); i++)
        {
            StoreOrderPanel storeOrderPanel = new StoreOrderPanel(PartnerHomeScreen.storeOrders.get(i));
            panel.add(storeOrderPanel);
        }

        scrollPane.setViewportView(panel);

        this.add(scrollPane);

    }
}