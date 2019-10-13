package netmera4j.model.notification;

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
public class SingleMessage extends Message {
    private Map<String, Object> params;

    public SingleMessage addParameter(String key, Object value) {
        if (params == null) {
            params = new HashMap<>(1);
        }
        params.put(key, value);
        return this;
    }
}
