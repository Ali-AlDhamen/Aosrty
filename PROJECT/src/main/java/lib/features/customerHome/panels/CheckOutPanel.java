package lib.features.customerHome.panels;

import javax.swing.*;

import com.k33ptoo.components.KGradientPanel;

import lib.API.Database;
import lib.common.Helper;
import lib.common.IconTextButton;
import lib.common.handlers.FocusHandler;
import lib.features.customerHome.CustomerHomeScreen;
import lib.features.partnerHome.PartnerHomeScreen;
import lib.theme.AppColors;

import java.awt.*;

public class CheckOutPanel extends KGradientPanel
{

    private IconTextButton takeAwayButton;
    private IconTextButton deliveryButton;
    static boolean delivery = false;
    static double codeValue = 0;
    static OrderTotalPanel orderTotalPanel;
    static public JTextField addressTextField;

    public CheckOutPanel()
    {
        super();
        this.setPreferredSize(new Dimension(700, 770));
        this.setBackground(AppColors.lightGray);
        this.setkStartColor(AppColors.lightGray);
        this.setkEndColor(AppColors.lightGray);
        this.setkBorderRadius(20);
        this.setkFillBackground(true);
        this.setOpaque(false);
        this.setLayout(null);

        KGradientPanel deliveryInfo = new KGradientPanel();
        deliveryInfo.setPreferredSize(new Dimension(700, 200));
        deliveryInfo.setBackground(AppColors.lightGray);
        deliveryInfo.setkStartColor(AppColors.lightGray);
        deliveryInfo.setkEndColor(AppColors.lightGray);
        deliveryInfo.setkBorderRadius(20);
        deliveryInfo.setkFillBackground(true);
        deliveryInfo.setOpaque(false);

        deliveryInfo.setBounds(20, 240, 660, 200);
        deliveryInfo.setLayout(null);

        addressTextField = new JTextField(delivery ? "Enter your address" : "Come to the store to pick it up :p");
        if (!delivery)
        {
            addressTextField.setEditable(false);

        }
        else {
            addressTextField.setEditable(true);
        }
        addressTextField.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        addressTextField.setForeground(Color.LIGHT_GRAY);
        addressTextField.addFocusListener(new FocusHandler("Enter your address", addressTextField));
        addressTextField.setBounds(20, 20, 620, 40);

        JTextField discountCodeTextField = new JTextField("Enter your discount code");
        discountCodeTextField.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        discountCodeTextField.setForeground(Color.LIGHT_GRAY);
        discountCodeTextField.addFocusListener(new FocusHandler("Enter your discount code", discountCodeTextField));
        discountCodeTextField.setBounds(20, 80, 400, 40);

        IconTextButton applyDiscountButton = new IconTextButton("Apply", null);
        applyDiscountButton.setText("Apply");
        applyDiscountButton.setPreferredSize(new Dimension(200, 40));
        applyDiscountButton.setBounds(450, 80, 200, 40);
        applyDiscountButton.addActionListener(e ->
        {

            Database.getDiscountCodes(CustomerHomeScreen.openedStore.getId());
            for (int i = 0; i < PartnerHomeScreen.discountCodes.size(); i++)
            {
                if (discountCodeTextField.getText().equals(PartnerHomeScreen.discountCodes.get(i).getCode_name()))
                {
                    JOptionPane.showMessageDialog(null, "Discount code applied successfully!");
                    codeValue = PartnerHomeScreen.discountCodes.get(i).getCode_value();
                    break;
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Invalid discount code!");
                    break;
                }
            }
            CustomerHomeScreen.refreshCartPanel();
        });

        deliveryInfo.add(discountCodeTextField);
        deliveryInfo.add(applyDiscountButton);
        deliveryInfo.add(addressTextField);

        takeAwayButton = new IconTextButton("Take Away", Helper.ScaleDown("PROJECT/src/main/assets/images/take.png", 1));
        takeAwayButton.setPreferredSize(new Dimension(300, 200));
        takeAwayButton.addActionListener(e ->
        {
            takeAwayButton.setBorder(BorderFactory.createLineBorder(AppColors.darkBlue, 5));
            deliveryButton.setBorder(BorderFactory.createLineBorder(AppColors.lightGray, 5));

            addressTextField.setEditable(false);
            addressTextField.setText("Come to the store to pick it up :p");
            delivery = false;
            CustomerHomeScreen.refreshCartPanel();

        });
        takeAwayButton.setBounds(20, 20, 300, 200);

        deliveryButton = new IconTextButton("Delivery", Helper.ScaleDown("PROJECT/src/main/assets/images/delivery.png", 1));
        deliveryButton.setPreferredSize(new Dimension(300, 200));
        deliveryButton.addActionListener(e ->
        {
            deliveryButton.setBorder(BorderFactory.createLineBorder(AppColors.darkBlue, 5));
            takeAwayButton.setBorder(BorderFactory.createLineBorder(AppColors.lightGray, 5));

            addressTextField.setEditable(true);
            addressTextField.setText("Enter your address");
            delivery = true;
            CustomerHomeScreen.refreshCartPanel();
        });
        deliveryButton.setBounds(380, 20, 300, 200);

        this.add(takeAwayButton);
        this.add(deliveryButton);
        this.add(deliveryInfo);
        double orderTotal = 0;
        for (int i = 0; i < CustomerHomeScreen.cart.size(); i++)
        {
            orderTotal += CustomerHomeScreen.cart.get(i).getOrderitem_price() * CustomerHomeScreen.cart.get(i).getOrderitem_quantity();
        }
        double tax = 0.15 * orderTotal;
        double discount = codeValue / 100.0 * orderTotal;

        orderTotal = Math.round(orderTotal * 100.0) / 100.0;
        tax = Math.round(tax * 100.0) / 100.0;
        discount = Math.round(discount * 100.0) / 100.0;

        KGradientPanel orderTotalPanel = new KGradientPanel();

        orderTotalPanel.setPreferredSize(new Dimension(700, 350));
        orderTotalPanel.setBackground(AppColors.lightGray);
        orderTotalPanel.setkStartColor(AppColors.lightGray);
        orderTotalPanel.setkEndColor(AppColors.lightGray);
        orderTotalPanel.setkBorderRadius(20);
        orderTotalPanel.setkFillBackground(true);
        orderTotalPanel.setOpaque(false);
        orderTotalPanel.setLayout(null);
        orderTotalPanel.setBounds(20, 450, 660, 350);

        JLabel subTotalLabel = new JLabel("Sub Total: ");
        subTotalLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        subTotalLabel.setForeground(AppColors.darkBlue);
        subTotalLabel.setBounds(20, 20, 200, 40);

        JLabel subTotalValueLabel = new JLabel(String.valueOf(orderTotal) + "$");
        subTotalValueLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        subTotalValueLabel.setForeground(AppColors.darkBlue);
        subTotalValueLabel.setBounds(240, 20, 400, 40);

        JLabel taxesLabel = new JLabel("Taxes: ");
        taxesLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        taxesLabel.setForeground(AppColors.darkBlue);
        taxesLabel.setBounds(20, 60, 200, 40);

        JLabel taxesValueLabel = new JLabel(String.valueOf(tax) + "$");
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

        JLabel discountCodeValueLabel = new JLabel(discount > 0 ? "-" + String.valueOf(discount) + "$" : String.valueOf(discount) + "$");
        discountCodeValueLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        discountCodeValueLabel.setForeground(AppColors.darkBlue);
        discountCodeValueLabel.setBounds(240, 140, 400, 40);

        JSeparator separator = new JSeparator();
        separator.setBounds(20, 180, 620, 10);

        double total = orderTotal + tax + (delivery ? 10 : 0) - discount;
        total = Math.round(total * 100.0) / 100.0;
        CustomerHomeScreen.order.setCost(total);
        CustomerHomeScreen.order.setStatus("Pending");

        JLabel orderTotalLabel = new JLabel("Order Total: ");
        orderTotalLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        orderTotalLabel.setForeground(AppColors.darkBlue);
        orderTotalLabel.setBounds(20, 210, 200, 40);

        JLabel orderTotalValueLabel = new JLabel(String.valueOf(total) + "$");
        orderTotalValueLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        orderTotalValueLabel.setForeground(AppColors.darkBlue);
        orderTotalValueLabel.setBounds(240, 210, 400, 40);

        orderTotalPanel.add(subTotalLabel);
        orderTotalPanel.add(orderTotalLabel);
        orderTotalPanel.add(subTotalValueLabel);
        orderTotalPanel.add(taxesLabel);
        orderTotalPanel.add(taxesValueLabel);
        orderTotalPanel.add(deliveryFeesLabel);
        orderTotalPanel.add(deliveryFeesValueLabel);
        orderTotalPanel.add(discountCodeLabel);
        orderTotalPanel.add(discountCodeValueLabel);
        orderTotalPanel.add(separator);
        orderTotalPanel.add(orderTotalLabel);
        orderTotalPanel.add(orderTotalValueLabel);

        this.add(orderTotalPanel);

    }

}
