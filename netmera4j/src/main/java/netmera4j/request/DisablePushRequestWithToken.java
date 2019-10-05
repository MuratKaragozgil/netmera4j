package netmera4j.request;

import lombok.*;
import netmera4j.util.NotEmpty;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DisablePushRequestWithToken {
    @NotEmpty
    private String deviceToken;
}
