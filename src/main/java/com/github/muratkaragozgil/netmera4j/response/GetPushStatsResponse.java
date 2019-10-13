package com.github.muratkaragozgil.netmera4j.response;

import com.github.muratkaragozgil.netmera4j.constant.SendStatus;
import com.github.muratkaragozgil.netmera4j.model.notification.SingleStat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
