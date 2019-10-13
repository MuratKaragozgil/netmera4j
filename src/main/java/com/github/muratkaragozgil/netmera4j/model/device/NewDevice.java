package com.github.muratkaragozgil.netmera4j.model.device;

import com.github.muratkaragozgil.netmera4j.constant.Platform;
import lombok.*;

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
