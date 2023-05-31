package constant;

public class Constant
{
    public static class StatusCodes{
        public static int SUCCESS = 200;

        public static int FAILED = 400;

        public static int INTERNAL_SERVER_ERROR = 500;
    }

    public static class Messages{
        public static String LOGIN_SUCCESS = "Login successful.";

        public static String INTERNAL_SERVER_ERROR = "Internal Server Error";

        public static String ALREADY_LOGGED = "Account already logged in";

        public static String ACC_NOT_FOUND = "Account not found";

        public static String REQ_NOT_PROPER = "Request not proper";
    }
}
