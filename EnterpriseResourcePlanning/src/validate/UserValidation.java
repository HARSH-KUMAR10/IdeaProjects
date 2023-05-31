package validate;

public class UserValidation
{
    public static boolean checkStringEmpty(String stringToCheck){
        if(stringToCheck=="" || stringToCheck==null){
            return true;
        }
        return false;
    }
}
