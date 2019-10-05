package netmera4j.constant;

/**
 * @author Murat Karagözgil
 */
public enum Platform {

    ANDROID("ANDROID"),
    IOS("IOS");

    private String name;

    Platform(String name) {
        this.name = name;
    }

    public static Platform getPlatform(String name) {
        String nameUpper = name.toUpperCase();
        for (Platform value : values()) {
            if (value.name().equals(nameUpper)) {
                return value;
            }
        }

        throw new IllegalArgumentException("Unknown platform identifier. [" + name + "]");
    }
}