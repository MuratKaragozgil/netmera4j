package com.github.muratkaragozgil.netmera4j.model.notification;

import com.github.muratkaragozgil.netmera4j.constant.Platform;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Murat Karagözgil
 */
@Getter
@Setter
@ToString
public class SingleStat {
    private Platform platform;
    private Integer sent;
    private Integer failed;
    private Integer clicked;
}
