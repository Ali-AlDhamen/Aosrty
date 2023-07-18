package lib.common;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.k33ptoo.components.KButton;

import lib.models.Product;
import lib.theme.AppColors;

public class UpdateItemDialog extends JDialog
{
  private JTextField nameField;
  private JTextField valueField;
  private boolean success;
  private File file;

  public InputStream getFile()
  {
    try
    {
      return new FileInputStream(file);
    }
    catch (FileNotFoundException e)
    {
      JOptionPane.showMessageDialog(this, "File not found", "Error", JOptionPane.ERROR_MESSAGE);

      return null;
    }
    
  }

  public UpdateItemDialog(Frame parent, Product product)
  {
    super(parent, "Update Item", true);
    setSize(300, 600);
    setBackground(AppColors.lightGray);
    setLayout(new GridBagLayout());
    GridBagConstraints cs = new GridBagConstraints();

    cs.fill = GridBagConstraints.CENTER;

    JLabel nameLabel = new JLabel("Name: ");
    nameLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
    nameLabel.setForeground(AppColors.darkBlue);
    cs.gridx = 0;
    cs.gridy = 0;
    cs.gridwidth = 1;
    add(nameLabel, cs);

    nameField = new JTextField(12);
    nameField.setText(product.getName());
    cs.gridx = 1;
    cs.gridy = 0;
    cs.gridwidth = 2;
    add(nameField, cs);

    JLabel valueLabel = new JLabel("Price: ");
    valueLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
    valueLabel.setForeground(AppColors.darkBlue);

    cs.gridx = 0;
    cs.gridy = 1;
    cs.gridwidth = 1;
    add(valueLabel, cs);

    valueField = new JTextField(12);
    valueField.setText(Double.toString(product.getPrice()));
    cs.gridx = 1;
    cs.gridy = 1;
    cs.gridwidth = 2;
    add(valueField, cs);

    cs.gridx = 0;
    cs.gridy = 2;
    cs.gridwidth = 1;

    JLabel productImage = new JLabel();
    productImage.setText("Product Image: ");
    productImage.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
    productImage.setForeground(AppColors.darkBlue);
    this.add(productImage, cs);

    cs.gridx = 1;
    cs.gridy = 2;
    cs.gridwidth = 2;

    KButton productImageButton = new KButton();
    productImageButton.setText("Choose Image");
    productImageButton.setPreferredSize(new Dimension(100,30));
    productImageButton.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
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
    this.add(productImageButton, cs);

    cs.gridx = 0;
    cs.gridy = 3;
    cs.gridwidth = 1;

    KButton updateButton = new KButton();
    updateButton.setText("Update");
    updateButton.setPreferredSize(new Dimension(100,30));
    updateButton.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
    updateButton.setkStartColor(AppColors.darkBlue);
    updateButton.setkEndColor(AppColors.darkBlue);
    updateButton.setkBorderRadius(20);
    updateButton.setkHoverStartColor(AppColors.darkBlue);
    updateButton.setkHoverEndColor(AppColors.darkBlue);
    updateButton.setkHoverForeGround(AppColors.lightGray);
    updateButton.setOpaque(false);

    updateButton.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        
        try
        {
          Double.parseDouble(valueField.getText());
        }
        catch (NumberFormatException ex)
        {
          JOptionPane.showMessageDialog(UpdateItemDialog.this, "Price must be a number", "Error", JOptionPane.ERROR_MESSAGE);
          return;
        }
        if (nameField.getText().equals("") || valueField.getText().equals(""))
        {
          JOptionPane.showMessageDialog(UpdateItemDialog.this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
          return;
        }
        if (Double.parseDouble(valueField.getText()) < 0){
          JOptionPane.showMessageDialog(UpdateItemDialog.this, "Price must be a positive number", "Error", JOptionPane.ERROR_MESSAGE);
          return;
        }
        if (file == null)
        {
          JOptionPane.showMessageDialog(UpdateItemDialog.this, "Please select an image", "Error", JOptionPane.ERROR_MESSAGE);
          return;
        }
        success = true;
        setVisible(false);
      }
    });
    add(updateButton, cs);

    cs.gridx = 1;
    cs.gridy = 3;
    cs.gridwidth = 2;
    
    KButton cancelButton = new KButton();
    cancelButton.setText("Cancel");
    cancelButton.setPreferredSize(new Dimension(100,30));
    cancelButton.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
    cancelButton.setkStartColor(AppColors.darkBlue);
    cancelButton.setkEndColor(AppColors.darkBlue);
    cancelButton.setkBorderRadius(20);
    cancelButton.setkHoverStartColor(AppColors.darkBlue);
    cancelButton.setkHoverEndColor(AppColors.darkBlue);
    cancelButton.setkHoverForeGround(AppColors.lightGray);
    cancelButton.setOpaque(false);
    cancelButton.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        success = false;
        setVisible(false);
      }
    });
    add(cancelButton , cs);

    pack();
    setLocationRelativeTo(parent);
  }

  public boolean isSuccess()
  {
    return success;
  }

  public String getName()
  {
    return nameField.getText();
  }

  public double getValue()
  {
    return Double.parseDouble(valueField.getText());
  }
}
