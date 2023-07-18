package lib.features.welcome;

import javax.swing.*;

import com.k33ptoo.components.KButton;

import lib.App;
import lib.API.Database;

import lib.features.Authentication.Customer.CustomerLoginScreen.CustomerLoginScreen;
import lib.features.Authentication.Partners.SignInScreen.PartnerLoginScreen;

import lib.theme.AppColors;

import java.awt.*;

import java.awt.event.*;

public class WelcomeScreen extends JFrame
{
    public WelcomeScreen()
    {
        super("Welcome Screen");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        addWindowListener(
                new java.awt.event.WindowAdapter()
                {
                    @Override
                    public void windowClosing(WindowEvent windowEvent)
                    {
                        Database.closeDB();
                        System.exit(0);
                    }
                });


        ImageIcon image = new ImageIcon("PROJECT/src/main/assets/images/welcome1.png");
        Image img = image.getImage();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Image newimg = img.getScaledInstance(screenSize.width, screenSize.height, java.awt.Image.SCALE_SMOOTH);
        image = new ImageIcon(newimg);
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(image);
        this.setContentPane(backgroundLabel);

        JLabel welcomeLabel = new JLabel();
        welcomeLabel.setText("Welcome to our app");
        welcomeLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 30));
        welcomeLabel.setForeground(AppColors.lightBrown);
        int x = (int) (screenSize.width / 3);
        int x1 = (int) (screenSize.width / 2);
        int y = (int) (screenSize.height / 2);
        welcomeLabel.setBounds((int) (screenSize.width / 2.5), 100, 300, 50);
        this.add(welcomeLabel);

        KButton customerButton = new KButton();
        customerButton.setBounds(x, y, 200, 50);
        customerButton.setText("Customer");
        customerButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 30));
        customerButton.setOpaque(false);
        customerButton.setkStartColor(AppColors.lightBlue);
        customerButton.setkEndColor(AppColors.lightBlue);
        customerButton.setkHoverStartColor(AppColors.lightBlue);
        customerButton.setkHoverEndColor(AppColors.lightBlue);
        customerButton.setkHoverForeGround(AppColors.lightBrown);
        customerButton.setkBorderRadius(20);
        customerButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        customerButton.addActionListener(e -> {
            this.dispose();
            App.customerLoginScreen = new CustomerLoginScreen();


        });
        this.add(customerButton);

        KButton partnerButton = new KButton();
        partnerButton.setBounds(x1, y, 200, 50);
        partnerButton.setText("Partner");
        partnerButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 30));
        partnerButton.setOpaque(false);
        partnerButton.setkStartColor(AppColors.lightBlue);
        partnerButton.setkEndColor(AppColors.lightBlue);
        partnerButton.setkHoverStartColor(AppColors.lightBlue);
        partnerButton.setkHoverEndColor(AppColors.lightBlue);
        partnerButton.setkHoverForeGround(AppColors.lightBrown);
        partnerButton.setkBorderRadius(20);
        partnerButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        partnerButton.addActionListener(e -> {
            this.dispose();
            App.partnerLoginScreen = new PartnerLoginScreen();
        });
        this.add(partnerButton);

        this.setVisible(true);
    }

}
