package lindar.acolyte.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

@UtilityClass
public class UsernameValidator {
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9._-]{3,20}$";

    private static Pattern pattern;
    private static Matcher matcher;


    /**
     * Validate username - 3 to 20 characters with any lowercase or uppercase character, digit or special symbol “_-” only
     *
     * @param username username for validation
     * @return true valid username, false invalid username
     */
    public static boolean validate(final String username) {
        if (StringUtils.isBlank(username)) {
            return false;
        }
        pattern = Pattern.compile(USERNAME_PATTERN);
        matcher = pattern.matcher(username);
        return matcher.matches();

    }
}