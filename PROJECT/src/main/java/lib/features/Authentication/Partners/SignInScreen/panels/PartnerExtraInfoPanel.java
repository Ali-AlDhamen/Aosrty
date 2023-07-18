
package lib.features.Authentication.Partners.SignInScreen.panels;

import com.k33ptoo.components.KButton;
import com.k33ptoo.components.KGradientPanel;

import lib.API.Database;
import lib.common.handlers.FocusHandler;
import lib.features.partnerHome.PartnerHomeScreen;
import lib.theme.AppColors;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.*;

public class PartnerExtraInfoPanel extends KGradientPanel
{
    File selectedFile;
    private Image storeImage;

    public PartnerExtraInfoPanel()
    {
        super();
        this.setkGradientFocus(0);
        this.setkFillBackground(false);
        this.setOpaque(false);
        this.setMaximumSize(getPreferredSize());
        this.setMinimumSize(getPreferredSize());
        this.setkBorderRadius(30);
        this.setkStartColor(Color.WHITE);
        this.setkEndColor(Color.WHITE);
        this.setkFillBackground(true);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 0, 0);

        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(100, 75));
        imageLabel.setOpaque(true);
        imageLabel.setBackground(AppColors.gray);
        imageLabel.setBorder(BorderFactory.createLineBorder(AppColors.darkBlue, 2));
        this.add(imageLabel, c);

        c.gridy = 1;
        c.insets = new Insets(10, 0, 0, 0);

        KButton selectImageButton = new KButton();
        selectImageButton.setkForeGround(Color.WHITE);
        selectImageButton.setPreferredSize(new Dimension(200, 50));
        selectImageButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        selectImageButton.setText("Select Image");
        selectImageButton.setkBorderRadius(30);
        selectImageButton.setkEndColor(AppColors.darkBlue);
        selectImageButton.setkStartColor(AppColors.darkBlue);
        selectImageButton.setkHoverEndColor(AppColors.darkBlue);
        selectImageButton.setkHoverStartColor(AppColors.darkBlue);
        selectImageButton.setkHoverForeGround(AppColors.lightGray);

        selectImageButton.addActionListener(e ->
        {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = fileChooser.getSelectedFile();
                try
                {
                    BufferedImage img = ImageIO.read(selectedFile);
                    storeImage = img.getScaledInstance(100, 75, Image.SCALE_SMOOTH);
                    imageLabel.setIcon(new ImageIcon(storeImage));
                    repaint();
                }
                catch (IOException ex)
                {
                    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Image error", JOptionPane.ERROR_MESSAGE);

                }
            }
        });

        this.add(selectImageButton, c);

        c.gridy = 2;
        c.insets = new Insets(10, 0, 0, 0);

        JTextField phoneField = new JTextField();
        phoneField.setText("Enter your Phone");
        phoneField.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        phoneField.setPreferredSize(new Dimension(300, 50));
        phoneField.setForeground(Color.LIGHT_GRAY);
        phoneField.setBackground(AppColors.gray);
        phoneField.addFocusListener(new FocusHandler("Enter your Phone", phoneField));
        this.add(phoneField, c);

        c.gridy = 3;

        JTextField addressField = new JTextField();
        addressField.setText("Enter your Address");
        addressField.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        addressField.setPreferredSize(new Dimension(300, 50));
        addressField.setForeground(Color.LIGHT_GRAY);
        addressField.setBackground(AppColors.gray);
        addressField.addFocusListener(new FocusHandler("Enter your Address", addressField));
        this.add(addressField, c);

        c.gridy = 4;
        KButton createButton = new KButton();
        createButton.setText("Sign Up");
        createButton.setkForeGround(Color.WHITE);
        createButton.setPreferredSize(new Dimension(180, 50));
        createButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        createButton.setkBorderRadius(50);
        createButton.setkEndColor(AppColors.darkBlue);
        createButton.setkStartColor(AppColors.darkBlue);
        createButton.setkHoverEndColor(AppColors.darkBlue);
        createButton.setkHoverStartColor(AppColors.darkBlue);
        createButton.setkHoverForeGround(AppColors.lightGray);
        createButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        createButton.addActionListener(
                e ->
                {
                    if (storeImage == null)
                    {
                        JOptionPane.showMessageDialog(this, "Please select an image", "Image error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (phoneField.getText().equals("Enter your Phone") || phoneField.getText().equals(""))
                    {
                        JOptionPane.showMessageDialog(this, "Please enter your phone number", "Phone error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (addressField.getText().equals("Enter your Address") || addressField.getText().equals(""))
                    {
                        JOptionPane.showMessageDialog(this, "Please enter your address", "Address error", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        try
                        {
                            InputStream inputStream = new FileInputStream(selectedFile);
                            String partnerName = PartnerHomeScreen.partner.getName();
                            String partnerEmail = PartnerHomeScreen.partner.getEmail();
                            String partnerPassword = PartnerHomeScreen.partner.getPassword();
                            String storeName = PartnerHomeScreen.store.getName();

                            String storephone = phoneField.getText();
                            String storeAddress = addressField.getText();

                            Database.registerStore(partnerName, storeName, partnerEmail, partnerPassword, inputStream, storephone, storeAddress);

                        }
                        catch (FileNotFoundException ex)
                        {
                            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Image error", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                });
        this.add(createButton, c);

    }

}
