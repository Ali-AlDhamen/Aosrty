
package lib.common;

import javax.swing.*;

import com.k33ptoo.components.KButton;

import lib.theme.AppColors;

import java.awt.*;

public class IconTextButton extends KButton
{

    public IconTextButton(String text, ImageIcon icon)
    {
        this.setLayout(new BorderLayout());
        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(icon);
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabel.setVerticalAlignment(SwingConstants.CENTER);
        iconLabel.setPreferredSize(new Dimension(30, 30));
        this.add(iconLabel, BorderLayout.WEST);

        this.setText(text);
        this.setIconTextGap(25);
        this.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
        this.setkStartColor(AppColors.lightGray);
        this.setkEndColor(AppColors.lightGray);
        this.setkHoverStartColor(AppColors.lightGray1);
        this.setkHoverEndColor(AppColors.lightGray1);
        this.setkForeGround(Color.white);
        this.setkHoverForeGround(AppColors.darkBlue);
        this.setPreferredSize(new Dimension(350, 60));
        this.setMinimumSize(getPreferredSize());
        this.setMaximumSize(getPreferredSize());
        this.setkBorderRadius(30);
        
    }

}