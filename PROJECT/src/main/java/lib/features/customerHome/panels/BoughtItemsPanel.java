package lib.features.customerHome.panels;

import com.k33ptoo.components.KGradientPanel;

import lib.theme.AppColors;

import java.awt.*;

import javax.swing.*;

public class BoughtItemsPanel extends KGradientPanel
{

    public BoughtItemsPanel(String ItemName, String itemPrice, String ItemQuantity, String ItemTotal)
    {
        super();
        this.setBackground(AppColors.lightGray);
        this.setkStartColor(AppColors.lightGray);
        this.setkEndColor(AppColors.lightGray);
        this.setkBorderRadius(20);
        this.setkFillBackground(true);
        this.setOpaque(false);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 0;
        c2.gridy = 0;
        c2.insets = new Insets(10, 50, 5, 30);
        c2.anchor = GridBagConstraints.NORTH;
        c2.fill = GridBagConstraints.BOTH;

        JLabel itemName = new JLabel();
        itemName.setText(ItemName);
        itemName.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        itemName.setForeground(AppColors.darkBlue);
        this.add(itemName, c2);

        c2.gridx = 1;

        JLabel itemPriceLabel = new JLabel();
        itemPriceLabel.setText(itemPrice);
        itemPriceLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        itemPriceLabel.setForeground(AppColors.darkBlue);
        this.add(itemPriceLabel, c2);

        c2.gridx = 2;

        JLabel itemQuantityLabel = new JLabel();
        itemQuantityLabel.setText(ItemQuantity);
        itemQuantityLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));

        itemQuantityLabel.setForeground(AppColors.darkBlue);
        this.add(itemQuantityLabel, c2);

        c2.gridx = 3;

        JLabel itemTotalLabel = new JLabel();
        itemTotalLabel.setText(ItemTotal);
        itemTotalLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        itemTotalLabel.setForeground(AppColors.darkBlue);
        this.add(itemTotalLabel, c2);

    }

}
