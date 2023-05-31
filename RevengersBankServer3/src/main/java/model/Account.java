package model;


public class Account
{
    private final String sessionId;

    private final int accountNumber;

    private final String name;

    private final int age;

    private final String gender;

    private final String email;

    private final String pin;

    private double balance;


    public Account(String name, int age, String gender, String email, String pin, double balance,int count)
    {
        this.accountNumber = 1000+count;

        this.name = name;

        this.age = age;

        this.gender = gender;

        this.email = email;

        this.pin = pin;

        this.balance = balance;

        this.sessionId = email + Math.random();
    }

    public int getAccountNumber()
    {
        return accountNumber;
    }

    public String getName()
    {
        return name;
    }

    public int getAge()
    {
        return age;
    }

    public String getGender()
    {
        return gender;
    }

    public String getEmail()
    {
        return email;
    }

    public double getBalance()
    {
        return balance;
    }

    public String getPin()
    {
        return pin;
    }

    public String getSessionId()
    {
        return sessionId;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }

}
