package netmera4j.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import netmera4j.constant.SendStatus;
import netmera4j.model.notification.SingleStat;

import java.util.List;

/**
 * @author Murat Karagözgil
 */
@Getter
@Setter
@ToString
public class GetPushStatsResponse {
    private Integer notificationKey;
    private SendStatus status;
    private Long startDate;
    private Long endDate;
    private List<SingleStat> stats;
}
