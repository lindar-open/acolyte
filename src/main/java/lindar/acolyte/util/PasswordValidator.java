package lindar.acolyte.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

@UtilityClass
public class PasswordValidator {

    /*
        ^                 # start-of-string
        (?=.*[0-9])       # a digit must occur at least once
        (?=.*[a-z])       # a lower case letter must occur at least once
        (?=.*[A-Z])       # an upper case letter must occur at least once
        .{8,256}          # between 8 and 256 chars
        $                 # end-of-string
     */
    private static final String PASS_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,256}$";

    private static Pattern pattern;
    private static Matcher matcher;

    /**
     * Validate password - Minimum 8 chars, at least one digit, lower case char, upper case char
     * No more than 256 chars allowed though... just so we don't save entire blobs here :) 
     *
     * @param password password for validation
     * @return true valid password, false invalid password
     */
    public static boolean validate(final String password) {
        if (StringUtils.isBlank(password)) {
            return false;
        }
        pattern = Pattern.compile(PASS_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();

    }
}