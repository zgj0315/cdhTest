package org.after90.utils;

/**
 * Created by zhaogj on 21/04/2017.
 */
public class StringUtils {
    public static boolean isNull(Object object) {
        if (object == null) {
            return true;
        } else {
            if ("".equals(object) && "null".equals(object.toString().toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
