package model;

public class Utility
{

    // Dependencies
    public static class Dependencies
    {
        public static final int PORT = 8080;

        public static final String IP = "10.20.40.194";
    }


    // String constants

    // Delimiters
    public static class Delimiters
    {
        public static final String SEMI_COLON_DELIMITER = ";";

        public static final String COLON_DELIMITER = ":";

        public static final String DOUBLE_SEMI_COLON_DELIMITER = ";;";

        public static final String DOUBLE_EQUAL_DELIMITER = "==";

        public static final String ARROW_DELIMITER = "->";

        public static final String DOUBLE_COLON_DELIMITER = "::";

        public static final String NEW_LINE = "\n";

        public static final String WHITE_SPACE = " ";

        public static final String TAB_DELIMITER = "\t";
    }


    // Keywords

    public static class Keyword
    {
        public static final String API_ACTION_ACCOUNT = "Account";

        public static final String API_ACTION_BANK = "Bank";

        public static final String CREATE = "Create";

        public static final String READ = "Read";

        public static final String DEPOSIT = "Deposit";

        public static final String WITHDRAWAL = "Withdrawal";

        public static final String DETAILS = "Details";

        public static final String TRANSFER = "Transfer";

        public static final String LOGOUT = "Logout";

        public static final String NAME = "Name";

        public static final String EMAIL = "Email";

        public static final String AGE = "Age";

        public static final String GENDER = "Gender";

        public static final String BALANCE = "Balance";

        public static final String FROM = "From";

        public static final String TO = "To";

        public static final String AMOUNT = "Amount";

        public static final String TRUE = "true";

        public static final String FALSE = "false";

        public static final String SERVER_CONN_CLIENT = "server-reading-client-";

        public static final String ONE = "1";

        public static final String TWO = "2";

        public static final String ZERO = "0";

        public static final String THREE = "3";

        public static final String FOUR = "4";

        public static final String FIVE = "5";

        public static final String FEMALE = "Female";

        public static final String MALE = "Male";

        public static final String PASSBOOK = "Passbook";

    }

    //Messages
    public static class Messages
    {
        public static final String WELCOME = "Welcome to Revenger's Bank";

        public static final String WRONG_INPUT = "Unable to process request: check inputs";

        public static final String LOGOUT_SUCCESS = "Logged out successfully";

        public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";

        public static final String ACCOUNT_CREATED = "Account created.";

        public static final String ALREADY_CREATED = "Account already exists with same email, please login";

        public static final String AUTH_FAIL = "Authentication failed.";

        public static final String OUTPUT_DIVIDER = "========================================";

        public static final String DEPOSIT_SUCCESSFUL_BALANCE = "Deposited amount successfully. Updated balance: ";

        public static final String WITHDRAW_SUCCESSFUL_BALANCE = "withdrawn amount successfully. Updated balance: ";

        public static final String INSUFFICIENT_BALANCE = "Insufficient balance.";

        public static final String ACCOUNT_DETAILS = "Account details";

        public static final String ACC_NO = "Account number";

        public static final String FUND_TRANSFER_UPDATE = "Fund transfer update: ";

        public static final String TRANSFER_FAILED = "Transfer failed, please check entered details";

        public static final String RESTART_SERVER = "Restart server.";

        public static final String NEW_CONNECTION = "New connection established, ";

        public static final String READ_CONNECTIONS_THREAD = "read-connections-thread";

        public static final String SERVER_ERROR_RESTART = "Server error: please restart server.";

        public static final String BAD_REQUEST = "Bad request.";

        public static final String ROUTE_UNSUPPORTED = " route doesn't support this operation";

        public static final String UNABLE_TO_PARSE = "Unable to parse request.";

        public static final String WRONGINPUT = "Wrong input";

        public static final String CLIENT_ERROR_RESTART = "Error: please restart.";

        public static final String UNABLE_TO_CONN_SERVER = "Unable to connect to server.";

        public static final String UNABLE_TO_LOGOUT = "Unable to logout.";

        public static final String LOGOUT_MESSAGE = "logging out ...\nClearing session ...";

        public static final String LOGIN_SUCCESS = "Login successful.";

        public static final String SERVER_BUSY = "Server is busy at the moment. please try again after some time.";

    }
}
