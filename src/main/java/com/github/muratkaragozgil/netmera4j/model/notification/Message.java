package com.github.muratkaragozgil.netmera4j.model.notification;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Murat Karagözgil
 */
@Getter
@Setter
@ToString
public class Message {
    protected String ttl;
    protected Boolean inbox;
    protected Boolean doNotNotify;
    protected MediaSettings ios;
    protected MediaSettings android;
    protected Map<String, Object> customJson;

    protected Message addCustomJsonParameter(String key, Object value) {
        if (customJson == null) {
            customJson = new HashMap<>(1);
        }
        customJson.put(key, value);
        return this;
    }
}
