package client.service;

import model.SocketControllers;
import model.UserSession;
import model.Utility;
import org.json.JSONObject;
import validation.AccountValidation;
import validation.BankValidation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientBankServices
{

    private static SocketControllers socketControllers;

    private static JSONObject requestBody;

    private static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private static final String DOT = ".";

    private static final String DEFAULT_DECIMAL = ".00";

    private static String getAmount()
    {
        try
        {

            System.out.print("Enter amount: ");

            String amount = bufferedReader.readLine();

            if (!amount.contains(DOT))
            {
                amount += DEFAULT_DECIMAL;
            }

            while (!BankValidation.validateAmount(amount))
            {
                System.out.print("Wrong input, please enter amount(ex: 10000.00) again: ");

                amount = bufferedReader.readLine();

                if (!amount.contains(DOT))
                {
                    amount += DEFAULT_DECIMAL;
                }
            }
            return amount;
        }
        catch (Exception exception)
        {
            return "0.0";
        }
    }

    private static String processRequestResponse()
    {
        try
        {
            // making connection
            socketControllers = new SocketControllers(new Socket(Utility.Dependencies.IP, Utility.Dependencies.PORT));

            requestBody.put("clientAddress", socketControllers.getSocket().getLocalSocketAddress().toString());

            socketControllers.getWriter()
                    .println(requestBody.toString());

            String response = socketControllers.getReader().readLine();

            socketControllers.getReader().close();

            socketControllers.getWriter().close();

            socketControllers.getSocket().close();

            return response;
        }
        catch (Exception exception)
        {

            return ("{\"message\":\"" + Utility.Messages.CLIENT_ERROR_RESTART
                    + Utility.Delimiters.COLON_DELIMITER + " " + exception.getMessage() + "\"}");

        }
    }

    public static void deposit(UserSession userSession)
    {
        try
        {
            requestBody = new JSONObject();

            String amount = getAmount();

            requestBody.put("route", Utility.Keyword.API_ACTION_BANK);

            requestBody.put("operation", Utility.Keyword.DEPOSIT);

            requestBody.put("sessionId", userSession.getSessionId());

            requestBody.put("email", userSession.getEmail());

            requestBody.put("amount", amount);


            // RESPONSE

            String response = processRequestResponse();

            System.out.println(Utility.Messages.OUTPUT_DIVIDER);

            if (response != null)
            {
                JSONObject responseBody = new JSONObject(response);
                if (!responseBody.isEmpty())
                {
                    System.out.println(responseBody.getString("message"));
                }
                else
                {
                    System.out.println(Utility.Messages.CLIENT_ERROR_RESTART);
                }
            }
            else
            {

                System.out.println(Utility.Messages.CLIENT_ERROR_RESTART);
            }

            System.out.println(Utility.Messages.OUTPUT_DIVIDER);

        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            System.out.println(Utility.Messages.CLIENT_ERROR_RESTART);
        }
    }

    public static void withdrawal(UserSession userSession)
    {
        try
        {

            requestBody = new JSONObject();

            String amount = getAmount();

            requestBody.put("route", Utility.Keyword.API_ACTION_BANK);

            requestBody.put("operation", Utility.Keyword.WITHDRAWAL);

            requestBody.put("sessionId", userSession.getSessionId());

            requestBody.put("email", userSession.getEmail());

            requestBody.put("amount", amount);


            // RESPONSE

            String response = processRequestResponse();

            System.out.println(response);

            System.out.println(Utility.Messages.OUTPUT_DIVIDER);

            if (response != null)
            {
                JSONObject responseBody = new JSONObject(response);
                if (!responseBody.isEmpty())
                {
                    System.out.println(responseBody.getString("message"));
                }
                else
                {
                    System.out.println(Utility.Messages.CLIENT_ERROR_RESTART);
                }
            }
            else
            {

                System.out.println(Utility.Messages.CLIENT_ERROR_RESTART);
            }

            System.out.println(Utility.Messages.OUTPUT_DIVIDER);


        }
        catch (Exception exception)
        {
            System.out.println(Utility.Messages.CLIENT_ERROR_RESTART);
        }
    }

    public static void getAccountDetail(UserSession userSession)
    {
        try
        {

            requestBody = new JSONObject();

            requestBody.put("route",Utility.Keyword.API_ACTION_BANK);

            requestBody.put("operation",Utility.Keyword.DETAILS);

            requestBody.put("sessionId",userSession.getSessionId());

            requestBody.put("email",userSession.getEmail());

            socketControllers = new SocketControllers(new Socket(Utility.Dependencies.IP, Utility.Dependencies.PORT));

            socketControllers.getWriter()
                    .println(socketControllers.getSocket().getLocalSocketAddress().toString()
                             + Utility.Delimiters.DOUBLE_EQUAL_DELIMITER + Utility.Keyword.API_ACTION_BANK
                             + Utility.Delimiters.DOUBLE_COLON_DELIMITER + Utility.Keyword.DETAILS
                             + Utility.Delimiters.ARROW_DELIMITER + userSession.getSessionId()
                             + Utility.Delimiters.SEMI_COLON_DELIMITER + userSession.getEmail());

            String response;

            System.out.println(Utility.Messages.OUTPUT_DIVIDER);

            while (!(response = socketControllers.getReader().readLine())
                    .equalsIgnoreCase(Utility.Delimiters.DOUBLE_SEMI_COLON_DELIMITER))
            {
                System.out.println(response);
            }

            System.out.println(Utility.Messages.OUTPUT_DIVIDER);

            socketControllers.getReader().close();

            socketControllers.getWriter().close();

            socketControllers.getSocket().close();

        }
        catch (Exception exception)
        {
            System.out.println(Utility.Messages.CLIENT_ERROR_RESTART);
        }
    }

    public static void fundTransfer(UserSession userSession)
    {
        try
        {
            System.out.print("Enter Receiver AccNo.: ");

            String receiverAccountNo = bufferedReader.readLine();

            while (!BankValidation.validateAccountNumber(receiverAccountNo))
            {

                System.out.print("Wrong input, Please enter AccNo. again: ");

                receiverAccountNo = bufferedReader.readLine();

            }

            System.out.print("Enter receiver email: ");

            String receiverEmail = bufferedReader.readLine();

            while (AccountValidation.validateEmail(receiverEmail))
            {

                System.out.print("Wrong input, please enter email again: ");

                receiverEmail = bufferedReader.readLine();

            }

            String amount = getAmount();

            socketControllers = new SocketControllers(new Socket(Utility.Dependencies.IP, Utility.Dependencies.PORT));

            socketControllers.getWriter()
                    .println(socketControllers.getSocket().getLocalSocketAddress().toString()
                             + Utility.Delimiters.DOUBLE_EQUAL_DELIMITER + Utility.Keyword.API_ACTION_BANK
                             + Utility.Delimiters.DOUBLE_COLON_DELIMITER + Utility.Keyword.TRANSFER
                             + Utility.Delimiters.ARROW_DELIMITER + userSession.getSessionId()
                             + Utility.Delimiters.SEMI_COLON_DELIMITER + userSession.getEmail()
                             + Utility.Delimiters.SEMI_COLON_DELIMITER + receiverAccountNo
                             + Utility.Delimiters.SEMI_COLON_DELIMITER + receiverEmail
                             + Utility.Delimiters.SEMI_COLON_DELIMITER + amount);

            String response;

            System.out.println(Utility.Messages.OUTPUT_DIVIDER);

            while (!(response = socketControllers.getReader().readLine())
                    .equalsIgnoreCase(Utility.Delimiters.DOUBLE_SEMI_COLON_DELIMITER))
            {
                System.out.println(response);
            }

            System.out.println(Utility.Messages.OUTPUT_DIVIDER);

            socketControllers.getReader().close();

            socketControllers.getWriter().close();

            socketControllers.getSocket().close();


        }
        catch (Exception exception)
        {
            System.out.println(Utility.Messages.CLIENT_ERROR_RESTART);
        }
    }

    public static void passbook(UserSession userSession)
    {
        try
        {

            socketControllers = new SocketControllers(new Socket(Utility.Dependencies.IP, Utility.Dependencies.PORT));

            socketControllers.getWriter()
                    .println(socketControllers.getSocket().getLocalSocketAddress().toString()
                             + Utility.Delimiters.DOUBLE_EQUAL_DELIMITER + Utility.Keyword.API_ACTION_BANK
                             + Utility.Delimiters.DOUBLE_COLON_DELIMITER + Utility.Keyword.PASSBOOK
                             + Utility.Delimiters.ARROW_DELIMITER + userSession.getSessionId()
                             + Utility.Delimiters.SEMI_COLON_DELIMITER + userSession.getEmail());

            String response;

            while ((response = socketControllers.getReader().readLine()) != null && response.length() > 0)
            {
                System.out.println(response);
            }

            socketControllers.getReader().close();

            socketControllers.getWriter().close();

            socketControllers.getSocket().close();
        }
        catch (Exception exception)
        {
            System.out.println(Utility.Messages.CLIENT_ERROR_RESTART);
        }
    }
}
