package netmera4j.util;

import netmera4j.exception.ValidationException;

/**
 * @author Murat Karagözgil
 */
public final class Assert {
    private Assert() {
    }

    public static void isTrue(boolean expression, String errorMessageFormat, Object... args) {
        if (!expression)
            throw new IllegalArgumentException(String.format(errorMessageFormat, args));
    }

    public static void mustBetween(int min, int max, Long value, String parameterName) {
        if (value < min || value > max) {
            throw new ValidationException(parameterName + " must be between (" + min + " - " + max + ")!");
        }
    }

    public static void mustGreaterThan(int min, Long value, String parameterName) {
        if (value < min) {
            throw new ValidationException(parameterName + " must be greater than " + min);
        }
    }

    public static void mustLowerThan(int max, Long value, String parameterName) {
        if (value > max) {
            throw new ValidationException(parameterName + " must be lower than " + max);
        }
    }

    public static void mustBetween(int min, int max, Integer value, String parameterName) {
        if (value < min || value > max) {
            throw new ValidationException(parameterName + " must be between (" + min + " - " + max + ")!");
        }
    }

    public static void mustGreaterThan(int min, Integer value, String parameterName) {
        if (value < min) {
            throw new ValidationException(parameterName + " must be greater than " + min);
        }
    }

    public static void mustLowerThan(int max, Integer value, String parameterName) {
        if (value > max) {
            throw new ValidationException(parameterName + " must be lower than " + max);
        }
    }

    public static <T> T notNull(T reference, String parameterName) {
        if (reference == null)
            throw new NullPointerException(parameterName + " cannot be null");
        return reference;
    }

    public static void state(boolean expression, String errorMessageFormat, Object... args) {
        if (!expression)
            throw new IllegalStateException(String.format(errorMessageFormat, args));
    }
}
