package lib.features.Authentication;

import lib.App;
import lib.API.*;



import java.util.regex.Pattern;

import lib.exceptions.Validation;
import lib.features.customerHome.CustomerHomeScreen;

import javax.swing.JOptionPane;

public class Auth
{
    public static final Pattern VALID_EMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    static public void createCustomerAccount(String name, String email, String password)
    {

        if (validateCustomer(email, password))
        {
            if (Database.registerCustomer(name, email, password) == 1)
            {
                App.customerLoginScreen.dispose();
                App.customerHomeScreen = new CustomerHomeScreen();

            }
            else
            {
                JOptionPane.showMessageDialog(null, "Error, try again", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    static public void loginCustomer(String email, String password)
    {
        if (Database.loginCustomer(email, password) == 1)
        {
            App.customerLoginScreen.dispose();
            App.customerHomeScreen = new CustomerHomeScreen();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "email or password is incorrect", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    static public boolean validateCustomer(String email, String password)
    {

        try
        {
            checkEmail(email);
            checkPassword(password);
            return true;
        }
        catch (Validation e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;

        }

    }

    static public void checkEmail(String email) throws Validation
    {

        if (!VALID_EMAIL.matcher(email).find())
        {
            throw new Validation("Invalid email");
        }
    }

    static public void checkPassword(String password) throws Validation
    {
        if (password.length() < 8)
        {
            throw new Validation("Password must be at least 8 characters");
        }
        else if (!password.matches(".*[A-Z].*"))
        {
            throw new Validation("Password must contain at least one upper case letter");
        }
        else if (!password.matches(".*[a-z].*"))
        {
            throw new Validation("Password must contain at least one lower case letter");
        }
        else if (!password.matches(".*[0-9].*"))
        {
            throw new Validation("Password must contain at least one number");
        }
        else if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*"))
        {
            throw new Validation("Password must contain at least one special character");
        }
    }

}
