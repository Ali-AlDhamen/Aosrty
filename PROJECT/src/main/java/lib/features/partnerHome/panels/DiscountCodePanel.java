package lib.features.partnerHome.panels;

import com.k33ptoo.components.KGradientPanel;
import com.k33ptoo.components.KButton;

import lib.API.Database;
import lib.common.handlers.FocusHandler;
import lib.features.partnerHome.PartnerHomeScreen;
import lib.theme.AppColors;

import java.awt.*;
import javax.swing.*;

public class DiscountCodePanel extends KGradientPanel
{
    public DiscountCodePanel()
    {
        super();
        Database.getDiscountCodes(Database.currentStore.getId());
        this.setPreferredSize(new Dimension(800, 200));
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
        c.insets = new Insets(10, 10, 5, 5);

        JLabel titleLabel = new JLabel("Make new Discount Code");
        titleLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
        titleLabel.setForeground(AppColors.lightGray);

        this.add(titleLabel, c);

        c.gridy = 1;
        c.insets = new Insets(10, 5, 5, 5);
        KGradientPanel panel = new KGradientPanel();
        panel.setPreferredSize(new Dimension(600, 150));
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

        JTextField codeField = new JTextField();
        codeField.setText("Enter Code Name");
        codeField.setPreferredSize(new Dimension(200, 50));
        codeField.setForeground(Color.LIGHT_GRAY);
        codeField.setBackground(AppColors.gray);
        codeField.addFocusListener(new FocusHandler("Enter Code Name", codeField));
        codeField.setBorder(BorderFactory.createLineBorder(AppColors.gray, 2));
        codeField.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        codeField.setOpaque(true);
        codeField.setMaximumSize(getPreferredSize());
        codeField.setMinimumSize(getPreferredSize());

        panel.add(codeField, c1);

        c1.gridy = 0;
        c1.gridx = 1;

        JTextField discountField = new JTextField();
        discountField.setText("Enter Discount in %");
        discountField.setPreferredSize(new Dimension(200, 50));
        discountField.setForeground(Color.LIGHT_GRAY);
        discountField.setBackground(AppColors.gray);
        discountField.addFocusListener(new FocusHandler("Enter Discount in %", discountField));
        discountField.setBorder(BorderFactory.createLineBorder(AppColors.gray, 2));
        discountField.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        discountField.setOpaque(true);
        discountField.setMaximumSize(getPreferredSize());
        discountField.setMinimumSize(getPreferredSize());

        panel.add(discountField, c1);

        c1.gridx = 2;
        // create button

        KButton addCodeButton = new KButton();
        addCodeButton.setText("Add Code");
        addCodeButton.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        addCodeButton.setPreferredSize(new Dimension(150, 50));
        addCodeButton.setkStartColor(AppColors.darkBlue);
        addCodeButton.setkEndColor(AppColors.darkBlue);
        addCodeButton.setkHoverStartColor(AppColors.darkBlue);
        addCodeButton.setkHoverEndColor(AppColors.darkBlue);
        addCodeButton.setkHoverForeGround(AppColors.lightGray);
        addCodeButton.setkForeGround(Color.WHITE);
        addCodeButton.setkBorderRadius(20);
        addCodeButton.addActionListener(e ->
        {

            // check if text fields not empty and code value have valid input
            if (!codeField.getText().equals("") && !discountField.getText().equals("") && !codeField.getText().equals("Enter Code Name")
                    && !discountField.getText().equals("Enter Discount in %"))
            {
                try
                {
                    double discount = Double.parseDouble(discountField.getText());
                    if (discount > 0 && discount < 100)
                    {

                        Database.addDiscountCode(codeField.getText(), discount, Database.currentStore.getId());
                        codeField.setText("Enter Code Name");
                        discountField.setText("Enter Discount in %");

                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Discount value must be between 1 and 99", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch (NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(null, "Discount value must be a number", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });

        panel.add(addCodeButton, c1);

        this.add(panel, c);

        c.gridy = 2;
        c.insets = new Insets(10, 5, 5, 5);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(1000, 500));
        scrollPane.getVerticalScrollBar().setOpaque(false);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));

        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        KGradientPanel codesPanel = new KGradientPanel();
        codesPanel.setPreferredSize(new Dimension(1000, PartnerHomeScreen.products.size() * 180));
        codesPanel.setBackground(AppColors.darkBlue);
        codesPanel.setOpaque(false);
        codesPanel.setkStartColor(AppColors.darkBlue);
        codesPanel.setkEndColor(AppColors.darkBlue);
        codesPanel.setkGradientFocus(0);
        codesPanel.setkFillBackground(true);
        codesPanel.setkBorderRadius(0);
        

        for (int i = 0; i < PartnerHomeScreen.discountCodes.size(); i++)
        {
            CodePanel codePanel = new CodePanel(PartnerHomeScreen.discountCodes.get(i));
            codesPanel.add(codePanel);
        }

        scrollPane.setViewportView(codesPanel);

        add(scrollPane, c);

    }

}
