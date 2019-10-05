package netmera4j.request;

import lombok.*;

/**
 * @author Murat Karagözgil
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetDeviceTokensRequest {
    private Integer max;
    private Integer offSet;
}
