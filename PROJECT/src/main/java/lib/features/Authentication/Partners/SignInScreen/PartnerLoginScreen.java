package lib.features.Authentication.Partners.SignInScreen;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Container;

import lib.API.Database;
import lib.common.Helper;
import lib.features.Authentication.Partners.SignInScreen.panels.PartnerSignInPanel;
import lib.features.Authentication.Partners.SignInScreen.panels.PartnerSignUpPanel;
import lib.theme.AppColors;

import java.awt.event.WindowEvent;

public class PartnerLoginScreen extends JFrame
{

    static public Container contentPane;
    static public PartnerSignInPanel partnerSignInPanel;
    static public PartnerSignUpPanel partnerSignUpPanel;

    public PartnerLoginScreen()
    {
        super("Sign In");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(AppColors.darkBlue);

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
        partnerSignInPanel = new PartnerSignInPanel();
        partnerSignUpPanel = new PartnerSignUpPanel();

        setLayout(null);
        // create label for logo


        JLabel logo = new JLabel();
        logo.setBounds(600, 10, 200, 200);
        logo.setIcon(Helper.logo("PROJECT/src/main/assets/images/logo1.png"));
        
        add(logo);


        add(new PartnerSignInPanel());
        setVisible(true);
    }

    static public void switchToSignInPanel()
    {
        contentPane.removeAll();
        JLabel logo = new JLabel();
        logo.setBounds(600, 10, 200, 200);
        logo.setIcon(Helper.logo("PROJECT/src/main/assets/images/logo1.png"));
        
        contentPane.add(logo);
        contentPane.add(partnerSignInPanel);
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
        contentPane.add(partnerSignUpPanel);
        contentPane.revalidate();
        contentPane.repaint();
    }

}
