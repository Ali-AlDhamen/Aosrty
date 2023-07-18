package lib.features.customerHome.panels;

import com.k33ptoo.components.KButton;
import com.k33ptoo.components.KGradientPanel;

import lib.common.Helper;
import lib.features.customerHome.CustomerHomeScreen;
import lib.models.OrderItem;
import lib.models.Product;
import lib.theme.AppColors;

import javax.swing.*;

import java.awt.*;

public class StoreItemPanel extends KGradientPanel
{

    int numOfItem = 0;

    public StoreItemPanel(Product product)
    {
        super();
        this.setPreferredSize(new Dimension(1000, 100));
        this.setBackground(AppColors.darkBlue);
        this.setOpaque(false);
        this.setkStartColor(AppColors.lightGray);
        this.setkEndColor(AppColors.lightGray);
        this.setkGradientFocus(0);
        this.setkFillBackground(true);
        this.setkBorderRadius(30);
        this.setMaximumSize(getPreferredSize());
        this.setMinimumSize(getPreferredSize());
        this.setLayout(null);

        JLabel storeImage = new JLabel();
        storeImage.setIcon(product.getImage());
        storeImage.setBounds(20, 0, 100, 100);

        this.add(storeImage);

        JLabel itemName = new JLabel();
        itemName.setText(product.getName());
        itemName.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        itemName.setBounds(150, 20, 200, 25);
        this.add(itemName);

        JLabel itemPrice = new JLabel();
        itemPrice.setText(product.getPrice() + "$");
        itemPrice.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        itemPrice.setBounds(150, 50, 200, 25);
        this.add(itemPrice);

        JTextField numOfItemField = new JTextField();
        numOfItemField.setText(String.valueOf(numOfItem));
        numOfItemField.setBounds(700, 40, 40, 40);
        numOfItemField.setEditable(false);
        numOfItemField.setHorizontalAlignment(JTextField.CENTER);
        numOfItemField.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        numOfItemField.setBackground(AppColors.lightGray);
        this.add(numOfItemField);

        KButton plusButton = new KButton();
        plusButton.setPreferredSize(new Dimension(40, 40));
        plusButton.setIcon(Helper.ScaleDown("PROJECT/src/main/assets/images/plus.png"));
        plusButton.setBounds(650, 35, 40, 40);
        plusButton.setkBorderRadius(25);
        plusButton.setkStartColor(AppColors.lightGray);
        plusButton.setkEndColor(AppColors.lightGray);
        plusButton.setOpaque(false);
        plusButton.setkHoverEndColor(AppColors.lightGray);
        plusButton.setkHoverStartColor(AppColors.lightGray);
        plusButton.addActionListener(e ->
        {
            numOfItem++;
            numOfItemField.setText(String.valueOf(numOfItem));
        });
        this.add(plusButton);

        KButton minusButton = new KButton();
        minusButton.setPreferredSize(new Dimension(40, 40));
        minusButton.setIcon(Helper.ScaleDown("PROJECT/src/main/assets/images/minus.png"));
        minusButton.setBounds(750, 35, 40, 40);
        minusButton.setkBorderRadius(25);
        minusButton.setkStartColor(AppColors.lightGray);
        minusButton.setkEndColor(AppColors.lightGray);
        minusButton.setOpaque(false);
        minusButton.setkHoverEndColor(AppColors.lightGray);
        minusButton.setkHoverStartColor(AppColors.lightGray);
        minusButton.addActionListener(e ->
        {
            if (numOfItem > 0)
            {
                numOfItem--;
                numOfItemField.setText(String.valueOf(numOfItem));
            }

        });
        this.add(minusButton);

        KButton addToCartButton = new KButton();
        addToCartButton.setText("Add to cart");
        addToCartButton.setBounds(850, 30, 100, 50);
        addToCartButton.setkBorderRadius(25);
        addToCartButton.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
        addToCartButton.setkStartColor(AppColors.darkBlue);
        addToCartButton.setkEndColor(AppColors.darkBlue);
        addToCartButton.setOpaque(false);
        addToCartButton.setkHoverEndColor(AppColors.darkBlue);
        addToCartButton.setkHoverStartColor(AppColors.darkBlue);
        addToCartButton.setkForeGround(AppColors.lightGray);
        addToCartButton.setkHoverForeGround(AppColors.lightGray);
        addToCartButton.addActionListener(e ->
        {
            if (numOfItem > 0)
            {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrderitem_product_id(product.getId());
                orderItem.setOrderitem_quantity(numOfItem);
                orderItem.setOrderitem_price(product.getPrice());
                orderItem.setOrderitem_name(product.getName());

                numOfItem = 0;
                numOfItemField.setText(String.valueOf(numOfItem));
                CustomerHomeScreen.cart.add(orderItem);
                CustomerHomeScreen.refreshCartPanel();
                
            }
        });
        this.add(addToCartButton);

    }

}
