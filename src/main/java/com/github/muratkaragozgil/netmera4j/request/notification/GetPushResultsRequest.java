package com.github.muratkaragozgil.netmera4j.request.notification;

import lombok.*;

/**
 * @author Murat Karagözgil
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class GetPushResultsRequest {
    private Integer max;
    private Integer notificationKey;
    private String extId;
    private String token;
    private Long start;
    private Long end;
}
