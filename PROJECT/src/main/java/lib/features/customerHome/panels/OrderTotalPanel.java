package lib.features.customerHome.panels;

import com.k33ptoo.components.KGradientPanel;

import lib.theme.AppColors;

import javax.swing.*;

import java.awt.*;

public class OrderTotalPanel extends KGradientPanel
{
    public OrderTotalPanel(double subTotal, double taxes, double discount, boolean delivery)
    {
        super();
        setPreferredSize(new Dimension(700, 350));
        setBackground(AppColors.lightGray);
        setkStartColor(AppColors.lightGray);
        setkEndColor(AppColors.lightGray);
        setkBorderRadius(20);
        setkFillBackground(true);
        setOpaque(false);
        setLayout(null);
        setBounds(20, 450, 660, 350);

        JLabel subTotalLabel = new JLabel("Sub Total: ");
        subTotalLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        subTotalLabel.setForeground(AppColors.darkBlue);
        subTotalLabel.setBounds(20, 20, 200, 40);

        JLabel subTotalValueLabel = new JLabel(String.valueOf(subTotal) + "$");
        subTotalValueLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        subTotalValueLabel.setForeground(AppColors.darkBlue);
        subTotalValueLabel.setBounds(240, 20, 400, 40);

        JLabel taxesLabel = new JLabel("Taxes: ");
        taxesLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        taxesLabel.setForeground(AppColors.darkBlue);
        taxesLabel.setBounds(20, 60, 200, 40);

        JLabel taxesValueLabel = new JLabel(String.valueOf(taxes) + "$");
        taxesValueLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        taxesValueLabel.setForeground(AppColors.darkBlue);
        taxesValueLabel.setBounds(240, 60, 400, 40);

        JLabel deliveryFeesLabel = new JLabel("Delivery Fees: ");
        deliveryFeesLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        deliveryFeesLabel.setForeground(AppColors.darkBlue);
        deliveryFeesLabel.setBounds(20, 100, 200, 40);

        JLabel deliveryFeesValueLabel = new JLabel(delivery ? "10$" : "free");
        deliveryFeesValueLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));

        deliveryFeesValueLabel.setForeground(AppColors.darkBlue);
        deliveryFeesValueLabel.setBounds(240, 100, 400, 40);

        JLabel discountCodeLabel = new JLabel("Discount Code: ");
        discountCodeLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        discountCodeLabel.setForeground(AppColors.darkBlue);
        discountCodeLabel.setBounds(20, 140, 200, 40);

        JLabel discountCodeValueLabel = new JLabel(String.valueOf(discount) + "$");
        discountCodeValueLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        discountCodeValueLabel.setForeground(AppColors.darkBlue);
        discountCodeValueLabel.setBounds(240, 140, 400, 40);

        JSeparator separator = new JSeparator();
        separator.setBounds(20, 180, 620, 10);

        double total = subTotal + taxes + (delivery ? 10 : 0) - discount;

        JLabel orderTotalLabel = new JLabel("Order Total: ");
        orderTotalLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        orderTotalLabel.setForeground(AppColors.darkBlue);
        orderTotalLabel.setBounds(20, 210, 200, 40);

        JLabel orderTotalValueLabel = new JLabel(String.valueOf(total) + "$");
        orderTotalValueLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        orderTotalValueLabel.setForeground(AppColors.darkBlue);
        orderTotalValueLabel.setBounds(240, 210, 400, 40);

        this.add(subTotalLabel);
        this.add(orderTotalLabel);
        this.add(subTotalValueLabel);
        this.add(taxesLabel);
        this.add(taxesValueLabel);
        this.add(deliveryFeesLabel);
        this.add(deliveryFeesValueLabel);
        this.add(discountCodeLabel);
        this.add(discountCodeValueLabel);
        this.add(separator);
        this.add(orderTotalLabel);
        this.add(orderTotalValueLabel);
    }

}
