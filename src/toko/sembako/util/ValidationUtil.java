package toko.sembako.util;

public class ValidationUtil {
    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    public static boolean isNumeric(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDateTimeFormat(String value) {
        try {
            DateUtil.parseDateTime(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}