package lindar.acolyte.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringEscapeUtils;

@UtilityClass
public class UserInputUtil {
    public static String safeHtml(String html) {
        return StringEscapeUtils.escapeHtml4(html);
    }
}
