package com.github.muratkaragozgil.netmera4j.model.device;

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
public class Device {
    private Platform platform;
    private String token;
    private String deviceModel;
    private String installationId;
    private boolean pushPermitted;
}