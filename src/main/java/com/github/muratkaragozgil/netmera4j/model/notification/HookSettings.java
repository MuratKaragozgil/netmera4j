package com.github.muratkaragozgil.netmera4j.model.notification;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Murat Karagözgil
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class HookSettings {
    private String name;
    private Map<String, String> params;

    public HookSettings addParameter(String key, String value) {
        if (params == null) {
            params = new HashMap<>(1);
        }
        params.put(key, value);
        return this;
    }
}
