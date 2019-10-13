package com.github.muratkaragozgil.netmera4j.request.device;

import lombok.*;
import com.github.muratkaragozgil.netmera4j.util.NotEmpty;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DisablePushRequestWithExternalId {
    @NotEmpty
    private String extId;
}
