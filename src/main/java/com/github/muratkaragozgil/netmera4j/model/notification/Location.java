package com.github.muratkaragozgil.netmera4j.model.notification;

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
public class Location {
    private Double latitude;
    private Double longitude;
    private Integer radius;
}
