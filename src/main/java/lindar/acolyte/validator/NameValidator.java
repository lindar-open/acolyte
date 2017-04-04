package lindar.acolyte.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public class NameValidator {
    private static final String NAME_PATTERN = "^[a-zA-Z]{1,500}$";

    private static Pattern pattern;
    private static Matcher matcher;


    /**
     * Validate name - 1 to 500 characters with any lowercase or uppercase character
     *
     * @param name name for validation
     * @return true valid name, false invalid name
     */
    public static boolean validate(final String name) {
        if (StringUtils.isBlank(name)) {
            return false;
        }
        pattern = Pattern.compile(NAME_PATTERN);
        matcher = pattern.matcher(name);
        return matcher.matches();

    }

    private NameValidator() {
    }
}
