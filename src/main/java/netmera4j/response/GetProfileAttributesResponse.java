package netmera4j.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/**
 * @author Murat Karagözgil
 */

@Getter
@Setter
@ToString
public class GetProfileAttributesResponse {
    private String extId;
    private Map<String, Object> profile;
}
