package lib.features.Authentication.Customer.CustomerLoginScreen;

import javax.swing.*;

import lib.API.Database;
import lib.common.Helper;
import lib.features.Authentication.Customer.CustomerLoginScreen.panels.CustomerSignInPanel;
import lib.features.Authentication.Customer.CustomerLoginScreen.panels.CustomerSignUpPanel;
import lib.theme.AppColors;
import java.awt.*;

import java.awt.event.WindowEvent;



public class CustomerLoginScreen extends JFrame
{
    static private  Container contentPane;
    static private CustomerSignInPanel customerSignInPanel;
    static private CustomerSignUpPanel customerSignUpPanel;
    

    public CustomerLoginScreen()
    {
        super("Sign In");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(AppColors.darkBlue);
        setLayout(null);

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

        contentPane = getContentPane();
        customerSignInPanel = new CustomerSignInPanel();
        customerSignUpPanel = new CustomerSignUpPanel();

        JLabel logo = new JLabel();
        logo.setBounds(600, 10, 200, 200);
        logo.setIcon(Helper.logo("PROJECT/src/main/assets/images/logo1.png"));
        
        add(logo);

        add(new CustomerSignInPanel());
        setVisible(true);
    }

    static public void switchToSignInPanel()
    {
        
        contentPane.removeAll();
        JLabel logo = new JLabel();
        logo.setBounds(600, 10, 200, 200);
        logo.setIcon(Helper.logo("PROJECT/src/main/assets/images/logo1.png"));
        
        contentPane.add(logo);
        contentPane.add(customerSignInPanel);
        contentPane.revalidate();
        contentPane.repaint();
    }

    static public void switchToSignUpPanel()
    {
        
        contentPane.removeAll();
        JLabel logo = new JLabel();
        logo.setBounds(600, 10, 200, 200);
        logo.setIcon(Helper.logo("PROJECT/src/main/assets/images/logo1.png"));
        
        contentPane.add(logo);
        contentPane.add(customerSignUpPanel);
        contentPane.revalidate();
        contentPane.repaint();
    }

    

    

}
