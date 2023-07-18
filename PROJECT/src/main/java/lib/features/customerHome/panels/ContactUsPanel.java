package lib.features.customerHome.panels;

import com.k33ptoo.components.KGradientPanel;

import lib.common.Helper;
import lib.theme.AppColors;

import java.awt.*;

import javax.swing.*;

public class ContactUsPanel extends KGradientPanel
{

    public ContactUsPanel()
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
        c.insets = new Insets(10, 30, 5, 5);
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.BOTH;

        JLabel contactUsLabel = new JLabel("You can find us here");
        contactUsLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
        contactUsLabel.setForeground(AppColors.lightGray);

        this.add(contactUsLabel, c);

        c.gridy = 1;

        c.insets = new Insets(10, 5, 5, 5);
        KGradientPanel contactUsPanel = new KGradientPanel();
        contactUsPanel.setPreferredSize(new Dimension(350, 300));
        contactUsPanel.setBackground(AppColors.lightGray);
        contactUsPanel.setkStartColor(AppColors.lightGray);
        contactUsPanel.setkEndColor(AppColors.lightGray);
        contactUsPanel.setkBorderRadius(20);
        contactUsPanel.setkFillBackground(true);
        contactUsPanel.setOpaque(false);
        contactUsPanel.setLayout(null);
        contactUsPanel.setMinimumSize(getPreferredSize());
        contactUsPanel.setMaximumSize(getPreferredSize());

        // create icon

        JLabel twitterIcon = new JLabel("@Aosrty");
        twitterIcon.setIconTextGap(15);
        twitterIcon.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
        twitterIcon.setForeground(AppColors.darkBlue);
        twitterIcon.setBounds(10, 10, 50, 50);
        twitterIcon.setIcon(Helper.ScaleDown("PROJECT/src/main/assets/images/twitter.png"));
        contactUsPanel.add(twitterIcon);

        twitterIcon.setBounds(20, 20, 300, 50);

        JLabel instgramIcon = new JLabel("@Aosrty");
        instgramIcon.setIconTextGap(15);

        instgramIcon.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
        instgramIcon.setForeground(AppColors.darkBlue);
        instgramIcon.setBounds(10, 10, 50, 50);
        instgramIcon.setIcon(Helper.ScaleDown("PROJECT/src/main/assets/images/instgram.png"));
        contactUsPanel.add(instgramIcon);

        instgramIcon.setBounds(20, 80, 300, 50);

        JLabel phoneIcon = new JLabel("+966 598141031");
        phoneIcon.setIconTextGap(15);
        phoneIcon.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
        phoneIcon.setForeground(AppColors.darkBlue);
        phoneIcon.setBounds(20, 140, 300, 50);
        phoneIcon.setIcon(Helper.ScaleDown("PROJECT/src/main/assets/images/phoney.png"));
        contactUsPanel.add(phoneIcon);

        JLabel emailIcon = new JLabel("Aosrty@gmail.com");
        emailIcon.setIconTextGap(15);
        emailIcon.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
        emailIcon.setForeground(AppColors.darkBlue);
        emailIcon.setBounds(20, 200, 300, 50);
        emailIcon.setIcon(Helper.ScaleDown("PROJECT/src/main/assets/images/emailo.png"));
        contactUsPanel.add(emailIcon);

        this.add(contactUsPanel, c);

    }

}
