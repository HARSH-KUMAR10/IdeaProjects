package erp;

import erp.logins.AbstractCredentials;
import erp.logins.LoginInterface;
import erp.logins.SignUp;

import java.util.HashMap;
import java.util.Map;

public class Login extends AbstractCredentials implements LoginInterface, SignUp
{
    @Override
    public boolean signUp(String email, String password)
    {
        if (validateEmail(email) && validatePassword(password))
        {
            if (setCredentials(email, password))
            {
                System.out.println("Signup successful, please login");
                return true;
            }
            else
            {
                System.out.println("Unable to add user.");
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    @Override
    public String logIn(String email, String password)
    {
        if (validateEmail(email) && validatePassword(password))
        {
            String userName = getCredentials(email, password);
            if (userName == null)
            {
                System.out.println("Error: unable to login");
            }
            else if (userName.equals(""))
            {
                System.out.println("Wrong email or password, please try again.");
            }
            else
            {
                System.out.println("Logged in successfully");
            }
            return userName;
        }
        else
        {
            return null;
        }
    }
    public HashMap<String,AbstractCredentials> showAllUsers(){
        return getAllUsers();
    }
}
