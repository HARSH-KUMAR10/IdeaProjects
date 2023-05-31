package bankserver.repo;

import model.Account;
import model.Utility;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

public class AccountRepo implements OperationStructure
{

    private static AccountRepo accountsObj = null;

    public static ConcurrentHashMap<String, Account> accounts = new ConcurrentHashMap<>();

    private AccountRepo()
    {
    }

    public static synchronized AccountRepo getInstance()
    {
        if (accountsObj == null)
        {
            accountsObj = new AccountRepo();
        }
        return accountsObj;
    }

    private static String getDate(){
        Date date = new Date();
        return date.getDate()+"-"+(date.getMonth()+1)+"-"+(date.getYear()+1900);
    }

    // Create new account
    @Override
    public String create(String... values)
    {

        Account account = new Account(values[0], Integer.parseInt(values[1]), values[2],
                values[3], values[4], Double.parseDouble(values[5]), accounts.size());

        return accounts.putIfAbsent(account.getEmail(), account) == null ?
                Utility.Messages.ACCOUNT_CREATED : Utility.Messages.ALREADY_CREATED;

    }

    // Login to account
    @Override
    public Account read(String... values)
    {
        Account account = accounts.get(values[0]);

        if (account != null && account.getPin().equals(values[1]))
        {
            return account;
        }
        else
        {
            return null;
        }
    }

    // deposit and withdrawal amount from account
    @Override
    public String update(String... values)
    {
        Account account = accounts.get(values[1]);

        if (account.getSessionId().equalsIgnoreCase(values[0]))
        {
            double amountToAdd = Double.parseDouble(values[2]);
            if (Boolean.parseBoolean(values[3]))
            {
                account.setBalance(account.getBalance() + amountToAdd);

                TransactionRepo.getInstance().create(account.getEmail(),getDate()
                        ,"Amount deposit","",amountToAdd+"",account.getBalance()+"");

                return Utility.Messages.DEPOSIT_SUCCESSFUL_BALANCE + account.getBalance();
            }
            else
            {
                if (account.getBalance() >= amountToAdd)
                {
                    account.setBalance(account.getBalance() - Double.parseDouble(values[2]));

                    TransactionRepo.getInstance().create(account.getEmail(),getDate()
                            ,"Amount withdraw",amountToAdd+"","",account.getBalance()+"");

                    return Utility.Messages.WITHDRAW_SUCCESSFUL_BALANCE + account.getBalance();
                }
                else
                {
                    return Utility.Messages.INSUFFICIENT_BALANCE;
                }
            }
        }
        else
        {
            return Utility.Messages.AUTH_FAIL;
        }
    }

    @Override
    public Object delete(String... args)
    {
        return null;
    }


    // Get account details
    public String read(String userSessionId, String userSessionEmail, boolean allDetails)
    {
        Account account = accounts.get(userSessionEmail);

        if (account.getSessionId().equalsIgnoreCase(userSessionId) && allDetails)
        {
            return Utility.Messages.ACCOUNT_DETAILS + Utility.Delimiters.NEW_LINE
                   + Utility.Messages.ACC_NO + Utility.Delimiters.COLON_DELIMITER
                   + Utility.Delimiters.WHITE_SPACE + account.getAccountNumber()
                   + Utility.Delimiters.NEW_LINE + Utility.Keyword.NAME + Utility.Delimiters.COLON_DELIMITER
                   + Utility.Delimiters.WHITE_SPACE + account.getName()
                   + Utility.Delimiters.NEW_LINE + Utility.Keyword.EMAIL + Utility.Delimiters.COLON_DELIMITER
                   + Utility.Delimiters.WHITE_SPACE + account.getEmail()
                   + Utility.Delimiters.NEW_LINE + Utility.Keyword.AGE + Utility.Delimiters.COLON_DELIMITER
                   + Utility.Delimiters.WHITE_SPACE + account.getAge()
                   + Utility.Delimiters.NEW_LINE + Utility.Keyword.GENDER + Utility.Delimiters.COLON_DELIMITER
                   + Utility.Delimiters.WHITE_SPACE + account.getGender()
                   + Utility.Delimiters.NEW_LINE + Utility.Keyword.BALANCE + Utility.Delimiters.COLON_DELIMITER
                   + Utility.Delimiters.WHITE_SPACE + account.getBalance()
                   + Utility.Delimiters.NEW_LINE + Utility.Delimiters.DOUBLE_SEMI_COLON_DELIMITER;
        }
        else
        {
            return Utility.Messages.AUTH_FAIL + Utility.Delimiters.NEW_LINE
                   + Utility.Delimiters.DOUBLE_SEMI_COLON_DELIMITER;
        }

    }

    // Transfer from one account to another
    public static String update(String userSessionId, String userSessionEmail,
                                String receiverAccountNumber, String receiverEmail, String amount)
    {
        Account fromAccount = accounts.get(userSessionEmail);

        Account toAccount = accounts.get(receiverEmail);

        if (fromAccount != null && toAccount != null
            && fromAccount.getSessionId().equals(userSessionId)
            && toAccount.getAccountNumber() == Integer.parseInt(receiverAccountNumber))
        {
            double amountTransfer = Double.parseDouble(amount);

            double fromAccountBalance = fromAccount.getBalance();

            double toAccountBalance = toAccount.getBalance();

            if (fromAccountBalance >= amountTransfer)
            {

                fromAccountBalance -= amountTransfer;

                toAccountBalance += amountTransfer;

                fromAccount.setBalance(fromAccountBalance);

                toAccount.setBalance(toAccountBalance);

                TransactionRepo.getInstance().create(fromAccount.getEmail(),getDate()
                        ,"Fund transfer to "+toAccount.getEmail(),amountTransfer+"","",fromAccount.getBalance()+"");

                TransactionRepo.getInstance().create(toAccount.getEmail(),getDate()
                        ,"Fund transfer from "+fromAccount.getEmail(),"",amountTransfer+"",toAccount.getBalance()+"");

                return Utility.Messages.FUND_TRANSFER_UPDATE + Utility.Delimiters.NEW_LINE
                       + Utility.Keyword.FROM + Utility.Delimiters.COLON_DELIMITER
                       + Utility.Delimiters.WHITE_SPACE + userSessionEmail + Utility.Delimiters.NEW_LINE
                       + Utility.Keyword.TO + Utility.Delimiters.COLON_DELIMITER
                       + Utility.Delimiters.WHITE_SPACE + receiverEmail + Utility.Delimiters.NEW_LINE
                       + Utility.Keyword.AMOUNT + Utility.Delimiters.COLON_DELIMITER
                       + Utility.Delimiters.WHITE_SPACE + amount + Utility.Delimiters.NEW_LINE
                       + Utility.Delimiters.DOUBLE_SEMI_COLON_DELIMITER;
            }
            else
            {
                return Utility.Messages.INSUFFICIENT_BALANCE
                       + Utility.Delimiters.NEW_LINE + Utility.Delimiters.DOUBLE_SEMI_COLON_DELIMITER;
            }
        }
        else
        {
            return Utility.Messages.TRANSFER_FAILED
                   + Utility.Delimiters.NEW_LINE + Utility.Delimiters.DOUBLE_SEMI_COLON_DELIMITER;
        }
    }
}
