package erp.logins;


import erp.Login;
import erp.database.LoginDB;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public abstract class AbstractCredentials
{
    LoginDB loginDB = new LoginDB();
    private String email = "";

    private String password = "";

    private final static String passPhraseSaltValue = "$camp@sigma6";

    private String userName = "";

    protected boolean validateEmail(String email)
    {
        return Pattern.matches("[\\S]+[@][\\S]+[.][\\S]+", email);
    }

    protected boolean validatePassword(String password)
    {
        return password.length() >= 8;
    }

    protected boolean setCredentials(String email, String password)
    {
        if (setEmail(email) && setPassword(password))
        {
            loginDB.addLogin(userName, this);
            return true;
        }
        else
        {
            return false;
        }
    }

    protected String getCredentials(String email, String password)
    {
        return loginDB.getLogin(email, password);
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    public String getUserName()
    {
        return userName;
    }

    public String getPassPhraseSaltValue()
    {
        return passPhraseSaltValue;
    }

    private boolean setEmail(String email)
    {
        try
        {
            this.email = email;
        }
        catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
        return setUserName(email);
    }

    private boolean setPassword(String password)
    {
        try
        {
            this.password = passPhraseSaltValue + password;
        }
        catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
        return true;
    }

    private boolean setUserName(String email)
    {
        try
        {
            this.userName = email.split("@")[0];
        }
        catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
        return true;
    }
    protected HashMap<String,AbstractCredentials> getAllUsers(){
        return loginDB.showAllUsers();
    }
}
