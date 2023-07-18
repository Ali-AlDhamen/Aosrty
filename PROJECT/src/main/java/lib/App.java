
package lib;

import lib.features.Authentication.Customer.CustomerLoginScreen.CustomerLoginScreen;
import lib.features.Authentication.Partners.SignInScreen.PartnerLoginScreen;
import lib.features.customerHome.CustomerHomeScreen;
import lib.features.partnerHome.PartnerHomeScreen;
import lib.features.welcome.WelcomeScreen;

public class App
{
    static public WelcomeScreen welcomeScreen;
    static public PartnerHomeScreen partnerHomeScreen;
    static public CustomerHomeScreen customerHomeScreen;
    static public CustomerLoginScreen customerLoginScreen;
    static public PartnerLoginScreen partnerLoginScreen;

    public static void main(String[] args)
    {
        
        welcomeScreen = new WelcomeScreen();
        welcomeScreen.setVisible(true);
    }

}
