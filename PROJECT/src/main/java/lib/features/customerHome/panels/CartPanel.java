package lib.features.customerHome.panels;

import java.awt.*;

import javax.swing.*;

import com.k33ptoo.components.KButton;
import com.k33ptoo.components.KGradientPanel;

import lib.API.Database;

import lib.common.IconTextButton;

import lib.features.customerHome.CustomerHomeScreen;

import lib.theme.AppColors;

public class CartPanel extends KGradientPanel
{
    IconTextButton deliveryButton, takeAwayButton;

    static CheckOutPanel checkout;

    public CartPanel()
    {
        this.setPreferredSize(new Dimension(800, 800));
        this.setkStartColor(AppColors.darkBlue);
        this.setkEndColor(AppColors.darkBlue);
        this.setkBorderRadius(20);
        this.setkFillBackground(true);
        this.setOpaque(false);
        this.setMaximumSize(getPreferredSize());
        this.setMinimumSize(getPreferredSize());
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 250, 5, 5);
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.BOTH;

        JLabel cartLabel = new JLabel("Cart");
        cartLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
        cartLabel.setForeground(AppColors.lightGray);

        this.add(cartLabel, c);

        c.gridy = 1;
        c.insets = new Insets(10, 5, 5, 5);
        KGradientPanel cartPanel = new KGradientPanel();
        cartPanel.setBackground(AppColors.lightGray);
        cartPanel.setkStartColor(AppColors.lightGray);
        cartPanel.setkEndColor(AppColors.lightGray);
        cartPanel.setkBorderRadius(20);
        cartPanel.setkFillBackground(true);
        cartPanel.setOpaque(false);
        cartPanel.setLayout(new BorderLayout());
        cartPanel.setPreferredSize(new Dimension(700, 600));
        cartPanel.setMinimumSize(getPreferredSize());
        cartPanel.setMaximumSize(getPreferredSize());

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(1000, 500));
        scrollPane.setBounds(50, 50, 1000, 800);

        scrollPane.getVerticalScrollBar().setOpaque(false);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));

        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        KGradientPanel cartItemsPanel = new KGradientPanel();
        cartItemsPanel.setPreferredSize(new Dimension(700, 100));
        cartItemsPanel.setBackground(AppColors.lightGray);
        cartItemsPanel.setkStartColor(AppColors.lightGray);
        cartItemsPanel.setkEndColor(AppColors.lightGray);
        cartItemsPanel.setkBorderRadius(20);
        cartItemsPanel.setkFillBackground(true);
        cartItemsPanel.setOpaque(false);
        cartItemsPanel.setLayout(null);

        JLabel itemLabel = new JLabel();
        itemLabel.setBounds(20, 20, 140, 40);
        itemLabel.setText("Product Name");
        itemLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        itemLabel.setForeground(AppColors.darkBlue);

        cartItemsPanel.add(itemLabel);

        JLabel priceLabel = new JLabel();
        priceLabel.setText("Product Price");
        priceLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        priceLabel.setForeground(AppColors.darkBlue);
        priceLabel.setBounds(200, 20, 140, 40);

        cartItemsPanel.add(priceLabel);

        JLabel quantityLabel = new JLabel();
        quantityLabel.setText("Product Quantity");
        quantityLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        quantityLabel.setForeground(AppColors.darkBlue);
        quantityLabel.setBounds(380, 20, 140, 40);

        cartItemsPanel.add(quantityLabel);

        JLabel totalLabel = new JLabel();
        totalLabel.setText("Product Total");
        totalLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        totalLabel.setForeground(AppColors.darkBlue);
        totalLabel.setBounds(560, 20, 140, 40);

        cartItemsPanel.add(totalLabel);

        cartPanel.add(cartItemsPanel, BorderLayout.NORTH);

        KGradientPanel boughtItemsPanel = new KGradientPanel();
        boughtItemsPanel.setPreferredSize(new Dimension(700, CustomerHomeScreen.cart.size() * 105));
        boughtItemsPanel.setBackground(AppColors.lightGray);
        boughtItemsPanel.setkStartColor(AppColors.lightGray);
        boughtItemsPanel.setkEndColor(AppColors.lightGray);
        boughtItemsPanel.setkBorderRadius(20);
        boughtItemsPanel.setkFillBackground(true);
        boughtItemsPanel.setOpaque(false);
        boughtItemsPanel.setLayout(new FlowLayout());

        for (int i = 0; i < CustomerHomeScreen.cart.size(); i++)
        {
            OrderItemPanel orderItemPanel = new OrderItemPanel(CustomerHomeScreen.cart.get(i));
            boughtItemsPanel.add(orderItemPanel);
        }

        scrollPane.setViewportView(boughtItemsPanel);

        cartPanel.add(scrollPane, BorderLayout.CENTER);

        checkout = new CheckOutPanel();
        if (CustomerHomeScreen.firstPage)
        {
            this.add(cartPanel, c);
        }
        else
        {
            this.add(checkout, c);
        }

        c.gridy = 2;
        c.insets = new Insets(10, 5, 5, 5);

        if (CustomerHomeScreen.firstPage)
        {
            KButton buyButton = new KButton();
            buyButton.setText("Next");
            buyButton.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
            buyButton.setkBorderRadius(20);
            buyButton.setkStartColor(AppColors.lightGray);
            buyButton.setkEndColor(AppColors.lightGray);
            buyButton.setkHoverStartColor(AppColors.lightGray);
            buyButton.setkHoverEndColor(AppColors.lightGray);
            buyButton.setkHoverForeGround(AppColors.darkBlue);
            buyButton.addActionListener(e ->
            {
                if (CustomerHomeScreen.cart.size() == 0)
                {
                    JOptionPane.showMessageDialog(null, "Your cart is empty!");
                    return;
                }
                CustomerHomeScreen.firstPage = false;
                CustomerHomeScreen.order.setStore_id(CustomerHomeScreen.openedStore.getId());
                CustomerHomeScreen.order.setCustomer_id(Database.currentUser.getId());

                CustomerHomeScreen.refreshCartPanel();

            });
            this.add(buyButton, c);
        }
        else
        {
            KButton buyButton = new KButton();
            buyButton.setText("Buy");
            buyButton.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
            buyButton.setkBorderRadius(20);
            buyButton.setkStartColor(AppColors.lightGray);
            buyButton.setkEndColor(AppColors.lightGray);
            buyButton.setkHoverStartColor(AppColors.lightGray);
            buyButton.setkHoverEndColor(AppColors.lightGray);
            buyButton.setkHoverForeGround(AppColors.darkBlue);
            buyButton.addActionListener(e ->
            {

                if (CheckOutPanel.addressTextField.getText().isEmpty() || CheckOutPanel.addressTextField.getText().equals("Enter your address"))
                {
                    JOptionPane.showMessageDialog(null, "Please enter your address");
                    CustomerHomeScreen.refreshCartPanel();
                    return;
                }
                CustomerHomeScreen.order.setAddress(CheckOutPanel.delivery ? CheckOutPanel.addressTextField.getText() : "ON SITE");
                Database.addOrder(CustomerHomeScreen.order);
                Database.addOrderItems(CustomerHomeScreen.cart);

                CheckOutPanel.addressTextField.setText("Enter your address");
                CustomerHomeScreen.cart.clear();
                CheckOutPanel.codeValue = 0;
                CustomerHomeScreen.firstPage = true;

            });
            this.add(buyButton, c);

        }

    }
}
