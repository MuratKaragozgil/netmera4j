package com.github.muratkaragozgil.netmera4j.response;

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
public class GetPushStatsInDateRangeResponse {
    private List<GetPushStatsResponse> messages;
}
