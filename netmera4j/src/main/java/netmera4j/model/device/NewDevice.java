package netmera4j.model.device;

import lombok.*;
import netmera4j.constant.Platform;

@Getter
@Setter
@Builder
@ToString
public class NewDevice {
    @NonNull
    private String deviceToken;
    @NonNull
    private Platform platform;
    private String extId;
    private String email;
}
