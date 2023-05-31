package validation;

import model.Utility;

import java.util.regex.Pattern;

public class AccountValidation
{

    private static final String NAME_REGEX = "^[A-Za-z ]+$";

    private static final String AGE_REGEX = "^\\d{2}$";

    private static final String EMAIL_REGEX = "^[a-zA-Z.]+[0-9]*[a-zA-Z]*@[A-Za-z]+(\\.[A-Za-z]+)+$";

    private static final String FOUR_DIGIT_PIN_REGEX = "^\\d{4}$";

    public static boolean validateName(String name)
    {
        return name.length() >= 5 && Pattern.matches(NAME_REGEX, name);
    }

    public static boolean validateAge(String age)
    {
        return Pattern.matches(AGE_REGEX, age) && Integer.parseInt(age) > 11;
    }

    public static boolean validateGender(String genderChoice)
    {
        return genderChoice.equals(Utility.Keyword.ONE) || genderChoice.equals(Utility.Keyword.TWO);
    }

    public static boolean validateGenderString(String genderString)
    {
        return genderString.equalsIgnoreCase(Utility.Keyword.FEMALE) || genderString.equalsIgnoreCase(Utility.Keyword.MALE);
    }

    public static boolean validateEmail(String email)
    {
        return !Pattern.matches(EMAIL_REGEX, email);
    }

    public static boolean validatePin(String pin)
    {
        return !Pattern.matches(FOUR_DIGIT_PIN_REGEX, pin);
    }

    public static boolean validateSessionId(String sessionId)
    {
        return sessionId != null && sessionId.length() > 0;
    }
}
