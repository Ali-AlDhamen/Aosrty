package lib.features.partnerHome.panels;

import com.k33ptoo.components.KGradientPanel;

import lib.API.Database;
import lib.features.partnerHome.PartnerHomeScreen;
import lib.theme.AppColors;

import javax.swing.*;

import java.awt.*;
public class StoreItemsPanel extends KGradientPanel
{
    public StoreItemsPanel()
    {
        this.setBackground(AppColors.darkBlue);
        this.setOpaque(false);
        this.setkStartColor(AppColors.darkBlue);
        this.setkEndColor(AppColors.darkBlue);
        this.setkGradientFocus(0);
        this.setkFillBackground(true);
        this.setkBorderRadius(0);
        Database.getProducts(Database.currentStore.getId());
        this.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(1000, 800));
        scrollPane.setBounds(50,50, 1000, 800);

        scrollPane.getVerticalScrollBar().setOpaque(false);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));

        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        KGradientPanel panel = new KGradientPanel();
        panel.setPreferredSize(new Dimension(1000, PartnerHomeScreen.products.size() * 105));
        panel.setBackground(AppColors.darkBlue);
        panel.setOpaque(false);
        panel.setkStartColor(AppColors.darkBlue);
        panel.setkEndColor(AppColors.darkBlue);
        panel.setkGradientFocus(0);
        panel.setkFillBackground(true);
        panel.setkBorderRadius(0);

        for (int i = 0; i < PartnerHomeScreen.products.size(); i++)
        {
            StoreItemPanel storeItemPanel = new StoreItemPanel(PartnerHomeScreen.products.get(i));
            panel.add(storeItemPanel);
        }
        scrollPane.setViewportView(panel);
        this.add(scrollPane);

    }

}
