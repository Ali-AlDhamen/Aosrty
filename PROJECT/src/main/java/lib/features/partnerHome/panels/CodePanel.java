package lib.features.partnerHome.panels;

import com.k33ptoo.components.KButton;
import com.k33ptoo.components.KGradientPanel;

import lib.API.Database;
import lib.models.DiscountCode;
import lib.theme.AppColors;

import java.awt.*;

import javax.swing.*;

public class CodePanel extends KGradientPanel
{
    public CodePanel(DiscountCode code)
    {
        super();
        this.setPreferredSize(new Dimension(800, 100));
        this.setkStartColor(AppColors.lightGray);
        this.setkEndColor(AppColors.lightGray);
        this.setkBorderRadius(20);
        this.setkFillBackground(true);
        this.setOpaque(false);
        this.setMaximumSize(getPreferredSize());
        this.setMinimumSize(getPreferredSize());
        this.setLayout(new GridBagLayout());
        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 0;
        c2.gridy = 0;
        c2.insets = new Insets(10, 20, 5, 5);

        JLabel codeName = new JLabel();
        codeName.setText(code.getCode_name());
        codeName.setHorizontalAlignment(JLabel.CENTER);
        codeName.setBackground(AppColors.lightGray);
        codeName.setPreferredSize(new Dimension(200, 50));
        codeName.setForeground(AppColors.darkBlue);
        codeName.setBorder(BorderFactory.createLineBorder(AppColors.gray, 2));
        codeName.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        codeName.setOpaque(true);
        codeName.setMaximumSize(getPreferredSize());
        codeName.setMinimumSize(getPreferredSize());

        this.add(codeName, c2);

        c2.gridy = 0;
        c2.gridx = 1;

        JLabel discount = new JLabel();

        discount.setText(code.getCode_value() + "%");
        discount.setHorizontalAlignment(JLabel.CENTER);
        discount.setPreferredSize(new Dimension(200, 50));
        discount.setForeground(AppColors.darkBlue);
        discount.setBackground(AppColors.lightGray);
        discount.setBorder(BorderFactory.createLineBorder(AppColors.gray, 2));
        discount.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        discount.setOpaque(true);
        discount.setMaximumSize(getPreferredSize());
        discount.setMinimumSize(getPreferredSize());

        this.add(discount, c2);

        c2.gridx = 2;
        c2.insets = new Insets(10, 100, 5, 5);

        KButton deleteCodeButton = new KButton();
        deleteCodeButton.setText("Delete Code");
        deleteCodeButton.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        deleteCodeButton.setPreferredSize(new Dimension(150, 50));
        deleteCodeButton.setkStartColor(AppColors.darkBlue);
        deleteCodeButton.setkEndColor(AppColors.darkBlue);
        deleteCodeButton.setkHoverStartColor(AppColors.darkBlue);
        deleteCodeButton.setkHoverEndColor(AppColors.darkBlue);
        deleteCodeButton.setkHoverForeGround(AppColors.lightGray);
        deleteCodeButton.setkForeGround(Color.WHITE);
        deleteCodeButton.setkBorderRadius(20);
        deleteCodeButton.addActionListener(e ->
        {
            Database.deleteDiscountCode(code.getCode_id());
        });

        this.add(deleteCodeButton, c2);
    }

}
