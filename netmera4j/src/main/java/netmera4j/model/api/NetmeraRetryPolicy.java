package netmera4j.model.api;

import lombok.*;

import java.time.temporal.ChronoUnit;

/**
 * @author Murat Karagözgil
 */
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NetmeraRetryPolicy {
    private long delay = 30;
    private long maxDelay = 60 * 10;
    private ChronoUnit unit = ChronoUnit.SECONDS;
}
