package bankserver.repo;

import java.util.ArrayList;

public class LoginRepo implements OperationStructure
{
    private static LoginRepo logins = null;

    private static final ArrayList<String> loggedInAccounts = new ArrayList<>();

    private LoginRepo()
    {
    }

    public static synchronized LoginRepo getInstance()
    {
        if (logins == null)
        {
            logins = new LoginRepo();
        }
        return logins;
    }

    @Override
    public Boolean create(String... values)
    {
        return loggedInAccounts.add(values[0]);
    }

    @Override
    public Boolean delete(String... values)
    {
        return loggedInAccounts.remove(values[0]);
    }

    @Override
    public Boolean read(String... values)
    {
        return loggedInAccounts.contains(values[0]);
    }

    @Override
    public Object update(String... args)
    {
        return null;
    }

}
