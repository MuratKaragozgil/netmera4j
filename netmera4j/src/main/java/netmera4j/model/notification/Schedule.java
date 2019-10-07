package netmera4j.model.notification;

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
public class Schedule {
    private String startTime;
    private boolean localTimezone;
}
