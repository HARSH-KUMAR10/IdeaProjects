package erp;

import erp.users.BasicDetails;

import java.util.HashMap;
import java.util.Map;

public class Users implements BasicDetails
{

    /* Basic Details - Variables and Methods*/

    String empCode = "";

    String name = "";

    String email = "";

    String password = "";

    String[] modules;

    public Users(String name, String email, String password, String... modules)
    {
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.setModules(modules);
        String empCode = "" + Math.random() * 5000;
        this.setEmpCode(empCode);
    }

    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public void setEmail(String email)
    {
        this.email = email;
    }

    @Override
    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public void setModules(String[] modules)
    {
        this.modules = modules;
    }

    public void setEmpCode(String empCode)
    {
        this.empCode = empCode;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public String getEmail()
    {
        return email;
    }

    @Override
    public String[] getModules()
    {
        return modules;
    }

    public String getEmpCode()
    {
        return empCode;
    }
}
