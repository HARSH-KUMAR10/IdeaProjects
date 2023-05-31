package bankserver.service;

import bankserver.repo.AccountRepo;
import bankserver.repo.TransactionRepo;
import constant.Constant;
import model.Transaction;
import model.Utility;
import org.json.JSONObject;
import validation.AccountValidation;
import validation.BankValidation;

import java.util.ArrayList;

public class ServerBankServices
{

    private static final String STATUS_CODE = "statusCode";

    private static final String MESSAGE = "message";

    private static JSONObject responseObject;


    // Bank::Deposit -> /10.20.40.194:91729==Bank::Deposit->sessionId;email;amount
    public static String deposit(JSONObject values)
    {
        try
        {

            if (values != null)
            {
                responseObject = new JSONObject();

                String sessionId = values.getString("sessionId");

                String email = values.getString("email");

                String amount = values.getString("amount");

                if (sessionId != null && email != null && amount != null
                    && AccountValidation.validateSessionId(sessionId)
                    && !AccountValidation.validateEmail(email)
                    && BankValidation.validateAmount(amount))
                {
                    responseObject.put(STATUS_CODE, Constant.StatusCodes.SUCCESS);

                    responseObject.put(MESSAGE, AccountRepo.getInstance().update(sessionId, email, amount, Utility.Keyword.TRUE));

                    return responseObject.toString();

                }
                else
                {

                    responseObject.put(STATUS_CODE, Constant.StatusCodes.FAILED);

                    responseObject.put(MESSAGE, Constant.Messages.REQ_NOT_PROPER);

                    return responseObject.toString();

                }
            }
            else
            {
                responseObject.put(STATUS_CODE, Constant.StatusCodes.FAILED);

                responseObject.put(MESSAGE, Constant.Messages.REQ_NOT_PROPER);

                return responseObject.toString();
            }
        }
        catch (Exception exception)
        {
            responseObject.put(STATUS_CODE, Constant.StatusCodes.INTERNAL_SERVER_ERROR);

            responseObject.put(MESSAGE, Constant.Messages.INTERNAL_SERVER_ERROR);

            return responseObject.toString();
        }
    }

    // Bank::Withdraw -> /10.20.40.194:89174==Bank::Withdrawal->sessionId;email;amount
    public static String withdrawal(JSONObject values)
    {
        try
        {

            if (values != null)
            {
                responseObject = new JSONObject();

                String sessionId = values.getString("sessionId");

                String email = values.getString("email");

                String amount = values.getString("amount");

                if (sessionId != null && email != null && amount != null
                    && AccountValidation.validateSessionId(sessionId)
                    && !AccountValidation.validateEmail(email)
                    && BankValidation.validateAmount(amount))
                {
                    responseObject.put(STATUS_CODE, Constant.StatusCodes.SUCCESS);

                    responseObject.put(MESSAGE,
                            AccountRepo.getInstance().update(sessionId, email, amount, Utility.Keyword.FALSE));

                    return responseObject.toString();

                }
                else
                {

                    responseObject.put(STATUS_CODE, Constant.StatusCodes.FAILED);

                    responseObject.put(MESSAGE, Constant.Messages.REQ_NOT_PROPER);

                    return responseObject.toString();

                }
            }
            else
            {
                responseObject.put(STATUS_CODE, Constant.StatusCodes.FAILED);

                responseObject.put(MESSAGE, Constant.Messages.REQ_NOT_PROPER);

                return responseObject.toString();
            }
        }
        catch (Exception exception)
        {
            responseObject.put(STATUS_CODE, Constant.StatusCodes.INTERNAL_SERVER_ERROR);

            responseObject.put(MESSAGE, Constant.Messages.INTERNAL_SERVER_ERROR);

            return responseObject.toString();
        }
    }

    // Bank::Details -> /10.20.40.194:89102==Bank::Details->sessionId;email
    // Response needs to be ended with ;;
    public static String details(String valuesString)
    {
        try
        {
            String[] values = valuesString.split(Utility.Delimiters.SEMI_COLON_DELIMITER);

            if (values.length == 2 && AccountValidation.validateSessionId(values[0])
                && !AccountValidation.validateEmail(values[1]))
            {
                return AccountRepo.getInstance().read(values[0], values[1], true);
            }
            else
            {
                return Utility.Messages.WRONG_INPUT + Utility.Delimiters.DOUBLE_SEMI_COLON_DELIMITER;
            }
        }
        catch (Exception exception)
        {
            return Utility.Messages.INTERNAL_SERVER_ERROR + Utility.Delimiters.DOUBLE_SEMI_COLON_DELIMITER;
        }
    }


    // Bank::Transfer -> /10.20.40.194:89213==Bank::Transfer->sessionId;email;receiverAccNo;receiverEmail;amount
    public static String fundTransfer(String valuesString)
    {
        try
        {
            String[] values = valuesString.split(Utility.Delimiters.SEMI_COLON_DELIMITER);

            if (values.length == 5 && AccountValidation.validateSessionId(values[0])
                && !AccountValidation.validateEmail(values[1]) && BankValidation.validateAccountNumber(values[2])
                && !AccountValidation.validateEmail(values[3]) && BankValidation.validateAmount(values[4]))
            {
                return AccountRepo.update(values[0], values[1], values[2], values[3], values[4]);
            }
            else
            {
                return Utility.Messages.WRONG_INPUT;
            }
        }
        catch (Exception exception)
        {
            return Utility.Messages.INTERNAL_SERVER_ERROR;
        }
    }

    // get all passbook data.
    public static String passbook(String valuesString)
    {
        try
        {
            String[] values = valuesString.split(Utility.Delimiters.SEMI_COLON_DELIMITER);

            if (values.length == 2
                && AccountValidation.validateSessionId(values[0])
                && !AccountValidation.validateEmail(values[1]))
            {
                ArrayList<Transaction> transactions = TransactionRepo.getInstance().read(values[1]);
                if (transactions != null)
                {
                    String passbookEntries = "Date\t\t\t\tParticular\t\t\tDeposit\t\t\tWithdrawal\t\tBalance\n";
                    for (Transaction transaction : transactions)
                    {
                        passbookEntries += passbookEntry(transaction);
                    }
                    return passbookEntries;
                }
                else
                {
                    return "";
                }
            }
            else
            {
                return Utility.Messages.WRONG_INPUT + Utility.Delimiters.DOUBLE_SEMI_COLON_DELIMITER;
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return Utility.Messages.INTERNAL_SERVER_ERROR;
        }
    }

    private static String passbookEntry(Transaction transaction)
    {
        return transaction.getDate() + Utility.Delimiters.TAB_DELIMITER
               + Utility.Delimiters.TAB_DELIMITER + Utility.Delimiters.TAB_DELIMITER
               + transaction.getParticulars() + Utility.Delimiters.TAB_DELIMITER
               + Utility.Delimiters.TAB_DELIMITER + Utility.Delimiters.TAB_DELIMITER
               + transaction.getDeposit() + Utility.Delimiters.TAB_DELIMITER
               + Utility.Delimiters.TAB_DELIMITER + Utility.Delimiters.TAB_DELIMITER
               + transaction.getWithdrawal() + Utility.Delimiters.TAB_DELIMITER
               + Utility.Delimiters.TAB_DELIMITER + Utility.Delimiters.TAB_DELIMITER
               + transaction.getBalance() + Utility.Delimiters.NEW_LINE;
    }
}
