package netmera4j.model.device;

import lombok.*;
import netmera4j.util.NotEmpty;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Murat Karagözgil
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserAndProfileAttributeMap {
    @NotEmpty
    private String extId;
    @NotEmpty
    private Map<String, Object> profile;

    public void addProfileAttribute(String key, Object value) {
        if (profile == null) {
            profile = new HashMap<>();
        }
        profile.put(key, value);
    }
}
