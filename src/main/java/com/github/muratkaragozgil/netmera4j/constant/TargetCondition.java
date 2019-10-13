package com.github.muratkaragozgil.netmera4j.constant;

import java.util.Arrays;

/**
 * @author Murat Karagözgil
 */
public enum TargetCondition {
    AND("$and"),
    OR("$or"),
    NOT("$not");

    private String name;

    TargetCondition(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static TargetCondition getTargetCondition(String name) {
        return Arrays.stream(values())
                .filter(bl -> bl.name.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown target condition identifier. [" + name + "]"));
    }

    @Override
    public String toString() {
        return this.name;
    }
}
