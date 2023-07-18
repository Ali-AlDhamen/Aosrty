package lib.features.customerHome.panels;

import java.awt.Dimension;

import javax.swing.JLabel;

import com.k33ptoo.components.KGradientPanel;

import lib.models.OrderItem;
import lib.theme.AppColors;

import java.awt.*;

public class OrderItemPanel extends KGradientPanel
{

    public OrderItemPanel(OrderItem item)
    {
        super();
        this.setBackground(AppColors.lightGray);
        this.setOpaque(false);
        this.setkStartColor(AppColors.lightGray);
        this.setkEndColor(AppColors.lightGray);
        this.setkGradientFocus(0);
        this.setkFillBackground(true);
        this.setkBorderRadius(30);
        this.setMaximumSize(getPreferredSize());
        this.setMinimumSize(getPreferredSize());
        this.setLayout(null);
        this.setPreferredSize(new Dimension(700, 100));

        double total = item.getOrderitem_quantity() * item.getOrderitem_price();

        JLabel itemName = new JLabel(item.getOrderitem_name());
        itemName.setBounds(20, 50, 140, 40);
        itemName.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        itemName.setForeground(AppColors.darkBlue);

        this.add(itemName);

        JLabel itemPrice = new JLabel(String.valueOf(
                item.getOrderitem_price()) + "$");
        itemPrice.setBounds(200, 50, 140, 40);
        itemPrice.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        itemPrice.setForeground(AppColors.darkBlue);

        this.add(itemPrice);

        JLabel itemQuantity = new JLabel(String.valueOf(
                item.getOrderitem_quantity()));

        itemQuantity.setBounds(380, 50, 140, 40);
        itemQuantity.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        itemQuantity.setForeground(AppColors.darkBlue);

        this.add(itemQuantity);

        JLabel itemTotal = new JLabel(String.valueOf(total) + "$");
        itemTotal.setBounds(560, 50, 140, 40);
        itemTotal.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        itemTotal.setForeground(AppColors.darkBlue);

        this.add(itemTotal);

    }

}
