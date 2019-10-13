package com.github.muratkaragozgil.netmera4j.request.notification;

import lombok.*;
import com.github.muratkaragozgil.netmera4j.util.NotEmpty;

/**
 * @author Murat Karagözgil
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class GetPushStatsRequest {
    @NotEmpty
    private Integer notificationKey;
}
