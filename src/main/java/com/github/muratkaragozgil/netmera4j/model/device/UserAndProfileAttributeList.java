package com.github.muratkaragozgil.netmera4j.model.device;

import lombok.*;
import com.github.muratkaragozgil.netmera4j.util.NotEmpty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Murat Karagözgil
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAndProfileAttributeList {
    @NotEmpty
    private String extId;
    @NotEmpty
    private Map<String, List<Object>> profile;

    public UserAndProfileAttributeList addProfileAttributeArray(String key, List<Object> values) {
        if (profile == null) {
            profile = new HashMap<>();
        }
        profile.put(key, values);
        return this;
    }
}
