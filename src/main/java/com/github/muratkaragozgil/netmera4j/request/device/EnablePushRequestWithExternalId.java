package com.github.muratkaragozgil.netmera4j.request.device;

import com.github.muratkaragozgil.netmera4j.util.NotEmpty;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnablePushRequestWithExternalId {
    @NotEmpty
    private String extId;
}
