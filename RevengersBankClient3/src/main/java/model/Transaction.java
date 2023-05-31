package model;

public class Transaction
{
    private final String email;

    private final String date;

    private final String particulars;

    private final String withdrawal;

    private final String deposit;

    private final String balance;

    public Transaction(String email, String date, String particulars, String withdrawal, String deposit, String balance){

        this.email = email;

        this.date = date;

        this.particulars = particulars;

        this.withdrawal = withdrawal;

        this.deposit = deposit;

        this.balance = balance;

    }

    public String getEmail()
    {
        return email;
    }

    public String getDate()
    {
        return date;
    }

    public String getParticulars()
    {
        return particulars;
    }

    public String getWithdrawal()
    {
        return withdrawal;
    }

    public String getDeposit()
    {
        return deposit;
    }

    public String getBalance()
    {
        return balance;
    }
}
