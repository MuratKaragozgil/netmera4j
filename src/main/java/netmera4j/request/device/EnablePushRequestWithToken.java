package netmera4j.request.device;

import lombok.*;
import netmera4j.util.NotEmpty;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnablePushRequestWithToken {
    @NotEmpty
    private String deviceToken;
}
