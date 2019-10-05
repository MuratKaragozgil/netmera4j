package netmera4j.constant;

/**
 * @author Murat Karagözgil
 */
public enum SendStatus {
    FINISHED("FINISHED"),
    SENDING("SENDING"),
    STARTING("STARTING"),
    ACTIVE("ACTIVE"),
    DEACTIVE("DEACTIVE"),
    STOPPED("STOPPED");

    private String name;

    SendStatus(String name) {
        this.name = name;
    }

    public static SendStatus getSendStatus(String name) {
        String nameUpper = name.toUpperCase();
        for (SendStatus value : values()) {
            if (value.name().equals(nameUpper)) {
                return value;
            }
        }

        throw new IllegalArgumentException("Unknown send status identifier. [" + name + "]");
    }
}
