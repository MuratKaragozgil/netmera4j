package com.github.muratkaragozgil.netmera4j.constant;

import java.util.Arrays;

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
        return Arrays.stream(values())
                .filter(bl -> bl.name.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown send status identifier. [" + name + "]"));
    }
}
