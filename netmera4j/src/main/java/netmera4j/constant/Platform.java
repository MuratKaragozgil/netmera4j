package netmera4j.constant;

import java.util.Arrays;

/**
 * @author Murat Karagözgil
 */
public enum Platform {

    ANDROID("ANDROID"),
    IOS("IOS"),
    CHROME("CHROME"),
    MACOS("MACOS"),
    FIREFOX("FIREFOX");

    private String name;

    Platform(String name) {
        this.name = name;
    }

    public static Platform getPlatform(String name) {
        return Arrays.stream(values())
                .filter(bl -> bl.name.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown platform identifier. [" + name + "]"));
    }
}