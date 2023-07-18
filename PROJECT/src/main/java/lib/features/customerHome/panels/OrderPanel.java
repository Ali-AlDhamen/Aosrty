package lib.features.customerHome.panels;

import com.k33ptoo.components.KGradientPanel;

import lib.API.Database;
import lib.common.Helper;
import lib.models.Order;
import lib.theme.AppColors;

import java.awt.*;

import javax.swing.*;

public class OrderPanel extends KGradientPanel
{

    OrderPanel(Order order)
    {
        super();
        this.setPreferredSize(new Dimension(800, 150));
        this.setkStartColor(AppColors.lightGray);
        this.setkEndColor(AppColors.lightGray);
        this.setkBorderRadius(20);
        this.setkFillBackground(true);
        this.setOpaque(false);
        this.setLayout(null);
        this.setMinimumSize(getPreferredSize());
        this.setMaximumSize(getPreferredSize());

        JLabel orderIdLabel = new JLabel("#" + String.valueOf(order.getId()));
        orderIdLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        orderIdLabel.setForeground(AppColors.darkBlue);
        orderIdLabel.setBounds(50, 20, 300, 30);
        this.add(orderIdLabel);

        JLabel storeNameLabel = new JLabel(Database.getStoreNameById(order.getStore_id()));
        storeNameLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        storeNameLabel.setForeground(AppColors.darkBlue);
        storeNameLabel.setBounds(50, 60, 300, 30);

        this.add(storeNameLabel);

        String date = Helper.convertTimestamp(order.getTime());
        JLabel timeLabel = new JLabel(date);
        timeLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        timeLabel.setForeground(AppColors.darkBlue);
        timeLabel.setBounds(50, 100, 300, 30);
        this.add(timeLabel);

        JLabel statusLabel = new JLabel(order.getStatus());
        statusLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        statusLabel.setForeground(AppColors.darkBlue);
        statusLabel.setBounds(650, 20, 150, 30);
        this.add(statusLabel);

        JLabel totalLabel = new JLabel(String.valueOf(order.getCost()) + "$");
        totalLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        totalLabel.setForeground(AppColors.darkBlue);
        totalLabel.setBounds(650, 60, 150, 30);
        this.add(totalLabel);

        JLabel deliveryLabel = new JLabel(order.getAddress().equals("ON SITE") ? "ON SITE" : "DELIVERY");
        deliveryLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        deliveryLabel.setForeground(AppColors.darkBlue);
        deliveryLabel.setBounds(650, 100, 150, 30);
        this.add(deliveryLabel);

    }

}
