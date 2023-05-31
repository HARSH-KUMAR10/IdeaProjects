package bankserver.service;

import bankserver.repo.AccountRepo;
import bankserver.repo.LoginRepo;
import constant.Constant;
import model.Account;
import model.Utility;
import org.json.JSONObject;
import validation.AccountValidation;

public class ServerAccountServices
{

    private static final String STATUS_CODE = "statusCode";

    private static final String MESSAGE = "message";

    private static final String DOUBLE_DEFAULT_STRING = "0.0";

    private static JSONObject responseObject;

    // Account::Create -> /10.20.40.194:89765==Account::Create->name;age;gender;email;pin
    public static String createAccount(JSONObject values)
    {
        try
        {

            responseObject = new JSONObject();

            if (
                    !values.isEmpty()
            )
            {
                String email = values.getString("email");
                String pin = values.getString("pin");
                String gender = values.getString("gender");
                String name = values.getString("name");
                String age = values.getString("age");
                if (email != null && pin != null
                    && gender != null && name != null
                    && age != null
                    && !AccountValidation.validateEmail(email)
                    && !AccountValidation.validatePin(pin)
                    && AccountValidation.validateGenderString(gender)
                    && AccountValidation.validateName(name)
                    && AccountValidation.validateAge(age)
                )
                {
                    // New account created successfully

                    responseObject.put(STATUS_CODE, Constant.StatusCodes.SUCCESS);

                    responseObject.put(MESSAGE, AccountRepo.getInstance().create(name, age,
                            gender, email, pin, DOUBLE_DEFAULT_STRING));

                    return responseObject.toString();

                }
                else
                {
                    //Request not proper
                    responseObject.put(STATUS_CODE, Constant.StatusCodes.FAILED);

                    responseObject.put(MESSAGE, Constant.Messages.REQ_NOT_PROPER);

                    return responseObject.toString();
                }
            }
            else
            {
                //Request not proper
                responseObject.put(STATUS_CODE, Constant.StatusCodes.FAILED);

                responseObject.put(MESSAGE, Constant.Messages.REQ_NOT_PROPER);

                return responseObject.toString();
            }
        }
        catch (Exception exception)
        {
            // Internal server error occurred
            responseObject.put(STATUS_CODE, Constant.StatusCodes.INTERNAL_SERVER_ERROR);

            responseObject.put(MESSAGE, Constant.Messages.INTERNAL_SERVER_ERROR);

            return responseObject.toString();
        }
    }

    // Account::Read -> /10.20.40.194:89765==Account::Read->email;pin
    public static String loginAccount(JSONObject values)
    {
        try
        {

            if (
                    !values.isEmpty()
                    && values.getString("email") != null
                    && values.getString("pin") != null
                    && !AccountValidation.validateEmail(values.getString("email"))
                    && !AccountValidation.validatePin(values.getString("pin"))
            )
            {

                String email = values.getString("email");

                String pin = values.getString("pin");

                Account account = AccountRepo.getInstance().read(email, pin);

                responseObject = new JSONObject();

                if (account != null)
                {
                    LoginRepo loginRepo = LoginRepo.getInstance();
                    if (!loginRepo.read(email))
                    {
                        // logging in
                        loginRepo.create(email);

                        responseObject.put(STATUS_CODE, Constant.StatusCodes.SUCCESS);

                        responseObject.put(MESSAGE, Constant.Messages.LOGIN_SUCCESS);

                        responseObject.put("sessionId", account.getSessionId());
                    }
                    else
                    {
                        // Already logged in
                        responseObject.put(STATUS_CODE, Constant.StatusCodes.FAILED);

                        responseObject.put(MESSAGE, Constant.Messages.ALREADY_LOGGED);

                    }
                    return responseObject.toString();
                }
                else
                {
                    // Account not found
                    responseObject.put(STATUS_CODE, Constant.StatusCodes.FAILED);

                    responseObject.put(MESSAGE, Constant.Messages.ACC_NOT_FOUND);

                    return responseObject.toString();

                }
            }
            else
            {
                // request not proper
                responseObject.put(STATUS_CODE, Constant.StatusCodes.FAILED);

                responseObject.put(MESSAGE, Constant.Messages.REQ_NOT_PROPER);

                return responseObject.toString();

            }
        }
        catch (Exception exception)
        {
            // Exception occurred
            responseObject.put(STATUS_CODE, Constant.StatusCodes.INTERNAL_SERVER_ERROR);

            responseObject.put(MESSAGE, Constant.Messages.INTERNAL_SERVER_ERROR);

            return responseObject.toString();

        }
    }

    public static String logoutAccount(String valuesString)
    {
        try
        {
            String[] values = valuesString.split(Utility.Delimiters.SEMI_COLON_DELIMITER);

            if (values.length == 2
                && !AccountValidation.validateEmail(values[0])
                && AccountValidation.validateSessionId(values[1]))
            {
                LoginRepo loginRepo = LoginRepo.getInstance();
                if (loginRepo.read(values[0]))
                {
                    loginRepo.delete(values[0]);
                }
                return Utility.Messages.LOGOUT_SUCCESS;
            }
            else
            {
                return Utility.Messages.WRONG_INPUT + ", " + Utility.Messages.RESTART_SERVER;
            }
        }
        catch (Exception exception)
        {
            return Utility.Messages.INTERNAL_SERVER_ERROR;
        }
    }
}
