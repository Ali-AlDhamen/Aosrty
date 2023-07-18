package lib.features.partnerHome.panels;

import com.k33ptoo.components.KButton;
import com.k33ptoo.components.KGradientPanel;

import lib.API.Database;
import lib.theme.AppColors;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AddItemPanel extends KGradientPanel
{
    File file;

    public AddItemPanel()
    {
        super();
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
        c.insets = new Insets(10, 100, 5, 5);
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.BOTH;

        JLabel itemName = new JLabel();
        itemName.setText("Add new Product to your Menu");
        itemName.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
        itemName.setForeground(AppColors.lightGray);

        this.add(itemName, c);

        c.gridy = 1;
        c.gridx = 0;
        c.insets = new Insets(10, 5, 5, 5);
        KGradientPanel panel = new KGradientPanel();
        panel.setPreferredSize(new Dimension(600, 600));
        panel.setkStartColor(AppColors.lightGray);
        panel.setkEndColor(AppColors.lightGray);
        panel.setkBorderRadius(20);
        panel.setkFillBackground(true);
        panel.setOpaque(false);
        panel.setMaximumSize(getPreferredSize());
        panel.setMinimumSize(getPreferredSize());
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c1 = new GridBagConstraints();
        c1.gridx = 0;
        c1.gridy = 0;
        c1.insets = new Insets(10, 5, 5, 5);
        c1.anchor = GridBagConstraints.NORTH;
        c1.fill = GridBagConstraints.BOTH;

        // take product image
        JLabel productImage = new JLabel();
        productImage.setText("Product Image: ");
        productImage.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
        productImage.setForeground(AppColors.darkBlue);
        panel.add(productImage, c1);

        c1.gridx = 1;
        c1.gridy = 0;

        KButton productImageButton = new KButton();
        productImageButton.setText("Choose Image");
        productImageButton.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        productImageButton.setkStartColor(AppColors.darkBlue);
        productImageButton.setkEndColor(AppColors.darkBlue);
        productImageButton.setkBorderRadius(20);
        productImageButton.setkHoverStartColor(AppColors.darkBlue);
        productImageButton.setkHoverEndColor(AppColors.darkBlue);
        productImageButton.setkHoverForeGround(AppColors.lightGray);
        productImageButton.setOpaque(false);
        productImageButton.setMaximumSize(getPreferredSize());
        productImageButton.setMinimumSize(getPreferredSize());
        productImageButton.addActionListener(e ->
        {
            // Create a new JFileChooser
            JFileChooser fileChooser = new JFileChooser();

            // Set the file filter to only show image files
            fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif"));

            // Show the file chooser and check if the user selected a file
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                // Get the selected file
                file = fileChooser.getSelectedFile();

                // Do something with the selected file
                // ...
            }
        });

        panel.add(productImageButton, c1);

        c1.gridx = 0;
        c1.gridy = 1;
        JLabel productName = new JLabel();
        productName.setText("Product Name: ");
        productName.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
        productName.setForeground(AppColors.darkBlue);
        panel.add(productName, c1);

        c1.gridx = 1;
        c1.gridy = 1;

        JTextField productNameField = new JTextField();
        productNameField.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        productNameField.setForeground(AppColors.darkBlue);
        productNameField.setOpaque(false);
        productNameField.setMaximumSize(getPreferredSize());
        productNameField.setMinimumSize(getPreferredSize());
        panel.add(productNameField, c1);

        c1.gridx = 0;
        c1.gridy = 2;

        JLabel productPrice = new JLabel();

        productPrice.setText("Product Price: ");
        productPrice.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
        productPrice.setForeground(AppColors.darkBlue);
        panel.add(productPrice, c1);

        c1.gridx = 1;
        c1.gridy = 2;

        JTextField productPriceField = new JTextField();
        productPriceField.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        productPriceField.setForeground(AppColors.darkBlue);
        productPriceField.setOpaque(false);
        productPriceField.setMaximumSize(getPreferredSize());
        productPriceField.setMinimumSize(getPreferredSize());

        panel.add(productPriceField, c1);

        c1.gridx = 0;
        c1.gridy = 3;
        c1.insets = new Insets(100, 200, 5, 5);

        KButton addItemButton = new KButton();
        addItemButton.setText("Add Item");
        addItemButton.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        addItemButton.setkStartColor(AppColors.darkBlue);
        addItemButton.setkEndColor(AppColors.darkBlue);
        addItemButton.setkBorderRadius(20);
        addItemButton.setkHoverStartColor(AppColors.darkBlue);
        addItemButton.setkHoverEndColor(AppColors.darkBlue);
        addItemButton.setkHoverForeGround(AppColors.lightGray);
        addItemButton.setOpaque(false);
        addItemButton.setMaximumSize(getPreferredSize());
        addItemButton.setMinimumSize(getPreferredSize());
        addItemButton.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {

                        try
                        {
                            if (file == null)
                            {
                                JOptionPane.showMessageDialog(null, "Please choose an image", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            InputStream image = new FileInputStream(file);
                            String name = productNameField.getText();
                            String price = productPriceField.getText();
                            if (price.matches(".*[a-zA-Z]+.*"))
                            {
                                JOptionPane.showMessageDialog(null, "Please enter a valid price", "Error", JOptionPane.ERROR_MESSAGE);
                                image.close();
                                return;

                            }

                            if (name.isEmpty() || price.isEmpty())
                            {
                                JOptionPane.showMessageDialog(null, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
                                image.close();
                                return;
                            }

                            if (Double.parseDouble(price) < 0)
                            {
                                JOptionPane.showMessageDialog(null, "Please enter a valid price", "Error", JOptionPane.ERROR_MESSAGE);
                                image.close();
                                return;
                            }

                            Database.addProduct(name, Double.parseDouble(price), Database.currentStore.getId(), image);
                            productNameField.setText("");
                            productPriceField.setText("");
                            file = null;

                            image.close();

                        }
                        catch (FileNotFoundException ex)
                        {
                            JOptionPane.showMessageDialog(null, "Please choose an image", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        catch (IOException ex)
                        {
                            JOptionPane.showMessageDialog(null, "Please choose an image", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    }
                });

        panel.add(addItemButton, c1);

        this.add(panel, c);

    }

}
