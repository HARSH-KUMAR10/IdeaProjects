package client.service;

import model.SocketControllers;
import model.UserSession;
import model.Utility;
import org.json.JSONObject;
import validation.AccountValidation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ClientAccountServices
{
    private static SocketControllers socketControllers;

    private static JSONObject requestBody;

    private static int chanceLeft = 3;

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

    public static void signUp()
    {
        try
        {
            BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));

            // Taking user name input

            System.out.print("Enter name: ");

            String name = userInputReader.readLine();

            while (!AccountValidation.validateName(name) && chanceLeft > 0)
            {
                chanceLeft--;

                System.out.print("Wrong input, please enter name again: ");

                name = userInputReader.readLine();
            }

            if (chanceLeft == 0) return;

            chanceLeft = 3;

            // Taking user age input

            System.out.print("Enter age: ");

            String age = userInputReader.readLine();

            while (!AccountValidation.validateAge(age) && chanceLeft > 0)
            {
                chanceLeft--;

                System.out.print("Wrong input, please enter age(12-99) again: ");

                age = userInputReader.readLine();
            }

            if (chanceLeft == 0) return;

            chanceLeft = 3;

            // Taking user gender input

            System.out.print("1. Female\n2. Male\nEnter choice: ");

            String genderChoice = userInputReader.readLine();

            while (!AccountValidation.validateGender(genderChoice) && chanceLeft > 0)
            {
                System.out.print("Wrong input, please enter gender(1. Female, 2. Male) again: ");

                genderChoice = userInputReader.readLine();

                chanceLeft--;
            }

            if (chanceLeft == 0) return;

            chanceLeft = 3;

            // Taking user email input

            System.out.print("Enter email: ");

            String email = userInputReader.readLine();

            while (AccountValidation.validateEmail(email) && chanceLeft > 0)
            {
                chanceLeft--;

                System.out.print("Wrong input, please enter email again: ");

                email = userInputReader.readLine();
            }

            if (chanceLeft == 0) return;

            chanceLeft = 3;

            // Taking user pin input

            System.out.print("Enter PIN(4 digit): ");

            String pin = userInputReader.readLine();

            while (AccountValidation.validatePin(pin) && chanceLeft > 0)
            {
                chanceLeft--;

                System.out.print("Wrong input, please enter PIN(4 digit) again: ");

                pin = userInputReader.readLine();
            }


            if (chanceLeft == 0) return;

            chanceLeft = 3;

            requestBody = new JSONObject();

            requestBody.put("route", Utility.Keyword.API_ACTION_ACCOUNT);

            requestBody.put("operation", Utility.Keyword.CREATE);

            requestBody.put("name", name);

            requestBody.put("age", age);

            requestBody.put("gender", (genderChoice.equals("1") ? "Female" : "Male"));

            requestBody.put("email", email);

            requestBody.put("pin", pin);

            String response = processRequestResponse();

            System.out.println(Utility.Messages.OUTPUT_DIVIDER);


            if (response != null)
            {

                JSONObject responseBody = new JSONObject(response);

                if (!responseBody.isEmpty() && responseBody.getString("message") != null)
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
            System.out.println(Utility.Messages.CLIENT_ERROR_RESTART
                               + Utility.Delimiters.COLON_DELIMITER + " " + exception.getMessage());
        }
    }

    public static void login()
    {
        try
        {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Enter email: ");

            String email = bufferedReader.readLine();

            while (AccountValidation.validateEmail(email) && chanceLeft > 0)
            {
                chanceLeft--;

                System.out.print("Wrong input, please enter email again: ");

                email = bufferedReader.readLine();

            }


            if (chanceLeft == 0) return;

            chanceLeft = 3;

            System.out.print("Enter pin: ");

            String pin = bufferedReader.readLine();

            while (AccountValidation.validatePin(pin) && chanceLeft > 0)
            {
                chanceLeft--;

                System.out.print("Wrong input, please enter pin again: ");

                pin = bufferedReader.readLine();

            }

            if (chanceLeft == 0) return;

            chanceLeft = 3;

            requestBody = new JSONObject();

            // Taking route to call
            requestBody.put("route", Utility.Keyword.API_ACTION_ACCOUNT);

            requestBody.put("operation", Utility.Keyword.READ);

            requestBody.put("email", email);

            requestBody.put("pin", pin);

            String response = processRequestResponse();

            System.out.println(Utility.Messages.OUTPUT_DIVIDER);

            if (response != null)
            {
                JSONObject responseBody = new JSONObject(response);

                if (!responseBody.isEmpty()
                    && responseBody.getInt("statusCode")==200)
                {
                    UserSession userSession = new UserSession(email, responseBody.getString("sessionId"));

                    System.out.println(Utility.Messages.LOGIN_SUCCESS);

                    boolean menuFlag = true;

                    while (menuFlag)
                    {

                        System.out.print("1. Deposit\n2. Withdrawal\n3. Account Details\n4. Fund Transfer\n5. Print passbook\n0. Logout\nEnter your choice: ");

                        switch (bufferedReader.readLine())
                        {
                            case Utility.Keyword.ONE -> ClientBankServices.deposit(userSession);

                            case Utility.Keyword.TWO -> ClientBankServices.withdrawal(userSession);

                            case Utility.Keyword.THREE -> ClientBankServices.getAccountDetail(userSession);

                            case Utility.Keyword.FOUR -> ClientBankServices.fundTransfer(userSession);

                            case Utility.Keyword.FIVE -> ClientBankServices.passbook(userSession);

                            case Utility.Keyword.ZERO ->
                            {
                                if (logout(userSession))
                                {
                                    userSession = null;

                                    menuFlag = false;

                                    System.out.println(Utility.Messages.LOGOUT_MESSAGE);
                                }
                                else
                                {
                                    System.out.println(Utility.Messages.UNABLE_TO_LOGOUT);
                                }
                            }
                            default -> System.out.println(Utility.Messages.WRONGINPUT);
                        }
                    }
                    System.out.println(Utility.Messages.OUTPUT_DIVIDER);

                }
                else
                {
                    System.out.println(responseBody.getString("message"));
                }
            }
            else
            {
                System.out.println(Utility.Messages.CLIENT_ERROR_RESTART);
            }

            System.out.println(Utility.Messages.OUTPUT_DIVIDER);

        }
        catch (SocketTimeoutException socketTimeoutException)
        {
            System.out.println(Utility.Messages.SERVER_BUSY);
        }
        catch (ConnectException connectException)
        {
            System.out.println(Utility.Messages.UNABLE_TO_CONN_SERVER);
        }
        catch (Exception exception)
        {
            System.out.println(Utility.Messages.CLIENT_ERROR_RESTART
                               + Utility.Delimiters.COLON_DELIMITER + " " + exception.getMessage());
        }
    }


    // Account::Logout -> /10.20.40.194:89765==Account::Logout->email;sessionId
    public static boolean logout(UserSession userSession)
    {
        try
        {

            socketControllers = new SocketControllers(new Socket(Utility.Dependencies.IP, Utility.Dependencies.PORT));

            socketControllers.getWriter()
                    .println(socketControllers.getSocket().getLocalSocketAddress().toString()
                             + Utility.Delimiters.DOUBLE_EQUAL_DELIMITER + Utility.Keyword.API_ACTION_ACCOUNT
                             + Utility.Delimiters.DOUBLE_COLON_DELIMITER + Utility.Keyword.LOGOUT
                             + Utility.Delimiters.ARROW_DELIMITER + userSession.getEmail()
                             + Utility.Delimiters.SEMI_COLON_DELIMITER + userSession.getSessionId());

            String response = socketControllers.getReader().readLine();

            socketControllers.getWriter().close();

            socketControllers.getReader().close();

            socketControllers.getSocket().close();

            if (response.equalsIgnoreCase(Utility.Messages.LOGOUT_SUCCESS))
            {
                return true;
            }
            else
            {
                System.out.println(response);
                return false;
            }
        }
        catch (Exception exception)
        {
            System.out.println(Utility.Messages.CLIENT_ERROR_RESTART);
            return false;
        }
    }

}
