package validation;

import java.util.regex.Pattern;

public class BankValidation
{
    private static final String AMOUNT_REGEX = "\\d+\\.\\d{1,2}";

    private static final String ACC_NO_REGEX = "^\\d{4}$";

    public static boolean validateAmount(String amount)
    {
        return Pattern.matches(AMOUNT_REGEX, amount) && Double.parseDouble(amount)!=0.0;
    }

    public static boolean validateAccountNumber(String accountNumber)
    {
        return Pattern.matches(ACC_NO_REGEX, accountNumber);
    }

}
