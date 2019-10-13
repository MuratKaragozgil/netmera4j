package netmera4j.request.notification;

import lombok.*;
import netmera4j.util.NotEmpty;

/**
 * @author Murat Karagözgil
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class GetPushStatsInDateRangeRequest {
    @NotEmpty
    private Long startDate;
    @NotEmpty
    private Long endDate;
}
