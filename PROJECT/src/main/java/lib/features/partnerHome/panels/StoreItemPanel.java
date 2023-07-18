package lib.features.partnerHome.panels;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.k33ptoo.components.KButton;
import com.k33ptoo.components.KGradientPanel;

import lib.API.Database;
import lib.common.Helper;
import lib.common.UpdateItemDialog;
import lib.models.Product;
import lib.theme.AppColors;

public class StoreItemPanel extends KGradientPanel
{
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

        KButton deleteIconButton = new KButton();
        deleteIconButton.setPreferredSize(new Dimension(40, 40));
        deleteIconButton.setIcon(Helper.ScaleDown("PROJECT/src/main/assets/images/delete.png"));
        deleteIconButton.setBounds(900, 20, 35, 40);
        deleteIconButton.setkBorderRadius(30);
        deleteIconButton.setkStartColor(AppColors.lightGray);
        deleteIconButton.setkEndColor(AppColors.lightGray);
        deleteIconButton.setOpaque(false);
        deleteIconButton.setkHoverEndColor(AppColors.lightBrown);
        deleteIconButton.setkHoverStartColor(AppColors.lightBrown);
        deleteIconButton.setkHoverForeGround(AppColors.darkBlue);
        deleteIconButton.addActionListener(e ->
        {
            Database.deleteProduct(product.getId());
        });

        this.add(deleteIconButton);

        KButton updateIconButton = new KButton();
        updateIconButton.setPreferredSize(new Dimension(40, 40));
        updateIconButton.setIcon(Helper.ScaleDown("PROJECT/src/main/assets/images/edit.png"));
        updateIconButton.setBounds(850, 20, 35, 40);
        updateIconButton.setkBorderRadius(30);
        updateIconButton.setkStartColor(AppColors.lightGray);
        updateIconButton.setkEndColor(AppColors.lightGray);
        updateIconButton.setOpaque(false);
        updateIconButton.setkHoverEndColor(AppColors.lightBrown);
        updateIconButton.setkHoverStartColor(AppColors.lightBrown);
        updateIconButton.setkHoverForeGround(AppColors.darkBlue);
        updateIconButton.addActionListener(e ->
        {
            UpdateItemDialog updateItemDialog = new UpdateItemDialog(Database.partnerHomeScreen, product);
            updateItemDialog.setVisible(true);

            if (updateItemDialog.isSuccess() == true)
            {
                Database.updateProduct(product.getId(), updateItemDialog.getName(), updateItemDialog.getValue(),
                        updateItemDialog.getFile());
                
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Update Failed");


            }

        });

        this.add(updateIconButton);

        KButton hideIconButton = new KButton();
        hideIconButton.setPreferredSize(new Dimension(40, 40));
        hideIconButton.setIcon(
                Helper.ScaleDown(
                        product.getStatus().equals("available") ? "PROJECT/src/main/assets/images/hide.png" : "PROJECT/src/main/assets/images/showy.png"));
        hideIconButton.setBounds(800, 20, 35, 40);
        hideIconButton.setkBorderRadius(30);
        hideIconButton.setkStartColor(AppColors.lightGray);
        hideIconButton.setkEndColor(AppColors.lightGray);
        hideIconButton.setOpaque(false);
        hideIconButton.setkHoverEndColor(AppColors.lightBrown);
        hideIconButton.setkHoverStartColor(AppColors.lightBrown);
        hideIconButton.setkHoverForeGround(AppColors.darkBlue);
        hideIconButton.addActionListener(e ->
        {

            Database.changeProductStatus(product.getId(), product.getStatus().equals("available") ? "hidden" : "available");

        });

        this.add(hideIconButton);

    }

}
