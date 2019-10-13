package com.github.muratkaragozgil.netmera4j.request.device;

import lombok.*;
import com.github.muratkaragozgil.netmera4j.util.NotEmpty;

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
