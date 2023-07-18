package lib.features.customerHome.panels;

import com.k33ptoo.components.KGradientPanel;
import com.k33ptoo.components.KButton;

import lib.API.Database;
import lib.common.handlers.TextAreaHandler;
import lib.theme.AppColors;

import java.awt.*;
import javax.swing.*;

public class ReportPanel extends KGradientPanel
{
    public ReportPanel()
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

        JLabel alertLabel = new JLabel("Write out your complaints");
        alertLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
        alertLabel.setForeground(AppColors.lightGray);

        this.add(alertLabel, c);

        c.gridy = 1;
        c.insets = new Insets(10, 5, 5, 5);
        JTextArea alertTextArea = new JTextArea();
        alertTextArea.setLineWrap(true);
        alertTextArea.setBackground(AppColors.lightGray);
        alertTextArea.setFont(new Font("Trebuchet MS", Font.PLAIN, 30));
        alertTextArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 5));
        alertTextArea.setText("Enter your complaints here. ");
        alertTextArea.setForeground(Color.LIGHT_GRAY);
        alertTextArea.addFocusListener(new TextAreaHandler("Enter your complaints here. ", alertTextArea));
        alertTextArea.setPreferredSize(new Dimension(600, 600));
        alertTextArea.setMinimumSize(getPreferredSize());
        alertTextArea.setMaximumSize(getPreferredSize());

        this.add(alertTextArea, c);

        c.gridy = 2;

        KButton alertButton = new KButton();
        alertButton.setText("Submit");
        alertButton.setkStartColor(AppColors.lightGray);
        alertButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 30));
        alertButton.setkEndColor(AppColors.lightGray);
        alertButton.setkHoverStartColor(AppColors.lightGray);
        alertButton.setkHoverEndColor(AppColors.lightGray);
        alertButton.setkHoverForeGround(AppColors.darkBlue);
        alertButton.setkBorderRadius(20);
        alertButton.addActionListener(e ->
        {

            Database.sendReport(alertTextArea.getText());
            alertTextArea.setText("Enter your complaints here. ");
            alertTextArea.setForeground(Color.LIGHT_GRAY);
        });
        this.add(alertButton, c);
    }

}
