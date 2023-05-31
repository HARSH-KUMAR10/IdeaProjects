package erp.users;

public interface BasicDetails
{

    void setName(String name);

    void setEmail(String email);

    void setPassword(String password);

    void setModules(String[] modules);

    String getName();

    String getEmail();

    String[] getModules();
}
