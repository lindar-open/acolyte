package lindar.acolyte.util;

import java.util.ArrayList;
import java.util.List;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

@UtilityClass
public class ListsAcolyte {

    public static boolean containsIgnoreCase(List<String> list, String item) {
        if (isEmpty(list)) {
            return false;
        }
        return list.stream().filter(i -> StringUtils.equalsIgnoreCase(item, i)).findAny().isPresent();
    }
    
    /**
     * Checks if list is null or empty
     * @param <T>
     * @param list
     * @return
     */
    public static <T> boolean isEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }
    
    /**
     * Checks that list is not null and not empty
     * @param <T>
     * @param list
     * @return
     */
    public static <T> boolean isNotEmpty(List<T> list) {
        return list != null && !list.isEmpty();
    }
    
    /**
     * If provided list is null returns a new ArrayList
     * @param <T>
     * @param list
     * @return
     */
    public static <T> List<T> defaultIfNull(List<T> list) {
        return list == null ? new ArrayList<>() : list;
    }
    
    public static <T> List<T> defaultIfNull(List<T> list, List<T> defaultVal) {
        return list == null ? defaultVal : list;
    }
    
    public static <T> List<T> defaultIfEmpty(List<T> list, List<T> defaultVal) {
        return list == null || list.isEmpty() ? defaultVal : list;
    }
    
}