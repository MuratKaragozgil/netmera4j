package netmera4j.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import netmera4j.util.NotEmpty;

/**
 * @author Murat Karagözgil
 */
@ToString
@Getter
@Setter
@AllArgsConstructor
public class GetProfileAttributesRequest {
    @NotEmpty
    private String externalId;
}
