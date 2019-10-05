package netmera4j.request;

import lombok.*;
import netmera4j.util.NotEmpty;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DisablePushRequestWithExternalId {
    @NotEmpty
    private String extId;
}
