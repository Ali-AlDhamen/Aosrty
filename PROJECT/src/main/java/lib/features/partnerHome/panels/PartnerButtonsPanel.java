package lib.features.partnerHome.panels;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

import lib.API.Database;
import lib.common.Helper;
import lib.common.IconTextButton;
import lib.features.partnerHome.PartnerHomeScreen;
import lib.theme.AppColors;

import java.io.*;
import java.nio.file.Files;

public class PartnerButtonsPanel extends JPanel
{
    public PartnerButtonsPanel()
    {
        super();
        this.setBackground(AppColors.lightGray);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 5, 5, 5);
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.NONE;

        IconTextButton storesButton = new IconTextButton("Store", Helper.ScaleDown("PROJECT/src/main/assets/images/stores.png"));
        storesButton.addActionListener(e ->
        {

            PartnerHomeScreen.switchToStoreItemsPanel();

        });
        this.add(storesButton, c);

        c.gridy = 1;
        IconTextButton addItemButton = new IconTextButton("Add Item", Helper.ScaleDown("PROJECT/src/main/assets/images/additem.png"));
        addItemButton.addActionListener(e ->
        {
            PartnerHomeScreen.switchToAddItemPanel();
        });
        this.add(addItemButton, c);

        c.gridy = 2;
        IconTextButton ordersButton = new IconTextButton("Orders", Helper.ScaleDown("PROJECT/src/main/assets/images/order.png"));
        ordersButton.addActionListener(e ->
        {
            PartnerHomeScreen.switchToStoreOrdersPanel();
        });
        this.add(ordersButton, c);

        c.gridy = 3;
        IconTextButton discountButton = new IconTextButton("Discount Code", Helper.ScaleDown("PROJECT/src/main/assets/images/discount.png"));
        discountButton.addActionListener(e ->
        {
            PartnerHomeScreen.switchToDiscountCodePanel();
        });
        this.add(discountButton, c);

        c.gridy = 4;
        IconTextButton exportButton = new IconTextButton("Export", Helper.ScaleDown("PROJECT/src/main/assets/images/export.png"));
        exportButton.addActionListener(e ->
        {
            try
            {
                String fileName = "data.csv";
                FileWriter fileWriter = new FileWriter(fileName);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                Database.getStoreOrders();

                // Write data to the CSV file
                bufferedWriter.write("Order ID,Order Time,Order Status, Order Cost , Order Address");
                bufferedWriter.newLine();

                for (int i = 0; i < PartnerHomeScreen.storeOrders.size(); i++)
                {
                    bufferedWriter.write(PartnerHomeScreen.storeOrders.get(i).getId() + ","
                            + Helper.convertTimestamp(PartnerHomeScreen.storeOrders.get(i).getTime()) + "," + PartnerHomeScreen.storeOrders.get(i).getStatus()
                            + "," + PartnerHomeScreen.storeOrders.get(i).getCost() + "," + PartnerHomeScreen.storeOrders.get(i).getAddress());
                    bufferedWriter.newLine();
                }
                bufferedWriter.close();
                fileWriter.close();

                // Prompt user to save the file
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setSelectedFile(new File(fileName));
                int result = fileChooser.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION)
                {
                    File selectedFile = fileChooser.getSelectedFile();
                    Files.copy(new File(fileName).toPath(), selectedFile.toPath());
                    Desktop.getDesktop().open(selectedFile);
                }
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        });

        this.add(exportButton, c);

    }

}
