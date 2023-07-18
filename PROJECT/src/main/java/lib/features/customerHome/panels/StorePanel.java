package lib.features.customerHome.panels;

import java.awt.*;
import java.awt.event.*;
import com.k33ptoo.components.KGradientPanel;

import javax.swing.*;

import lib.common.Helper;
import lib.features.customerHome.CustomerHomeScreen;
import lib.models.Store;
import lib.theme.AppColors;

public class StorePanel extends KGradientPanel
{
    public StorePanel(Store store)
    {
        super();
        this.setPreferredSize(new Dimension(1000, 150));
        this.setkStartColor(AppColors.lightGray);
        this.setkEndColor(AppColors.lightGray);
        this.setkBorderRadius(20);
        this.setkFillBackground(true);
        this.setOpaque(false);
        this.setMaximumSize(getPreferredSize());
        this.setMinimumSize(getPreferredSize());
        this.addMouseListener(
                new MouseAdapter()
                {

                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        super.mouseClicked(e);
                        CustomerHomeScreen.switchToStoreItemsPanel(store.getId());
                        CustomerHomeScreen.openedStore = store;

                    }

                });

        this.setLayout(null);

        // create storeImage label and add image to it
        JLabel storeImage = new JLabel();
        storeImage.setBounds(0, 0, 200, 150);
        storeImage.setIcon(store.getImage());
        this.add(storeImage);

        // create storeEmail label and add text to it
        JLabel storeName = new JLabel();
        storeName.setBounds(250, 20, 300, 30);
        storeName.setText(store.getName());
        storeName.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
        this.add(storeName);

        JLabel storeAddress = new JLabel();
        storeAddress.setIcon(Helper.ScaleDown("PROJECT/src/main/assets/images/location.png"));
        storeAddress.setBounds(700, 20, 300, 30);
        storeAddress.setText(store.getAddress());
        storeAddress.setEnabled(true);

        storeAddress.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
        this.add(storeAddress);

        JLabel storePhone = new JLabel();
        storePhone.setIcon(Helper.ScaleDown("PROJECT/src/main/assets/images/phoney.png"));
        storePhone.setBounds(700, 100, 300, 30);
        storePhone.setText(store.getPhoneNumber());
        storePhone.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
        this.add(storePhone);

    }

}
