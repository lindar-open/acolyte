package lindar.acolyte.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public class EmailValidator {


    private static final String EMAIL_PATTERN = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,20}$";

    private static Pattern pattern;
    private static Matcher matcher;

    /**
     * Validate email - Minimum 8 chars, at least one digit, lower case char, upper case char
     * No more than 256 chars allowed
     * @param email password for validation
     * @return true valid email, false invalid email
     */
    public static boolean validate(final String email) {
        return validate(email, EMAIL_PATTERN);
    }
    
    public static boolean validate(final String email, final String emailRegex) {
        if (StringUtils.isBlank(email)) {
            return false;
        }
        pattern = Pattern.compile(emailRegex);
        matcher = pattern.matcher(email.toLowerCase());
        return matcher.matches();
    }

    private EmailValidator() {
    }
    
}
