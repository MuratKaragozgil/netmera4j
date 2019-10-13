package com.github.muratkaragozgil.netmera4j.response;

import com.github.muratkaragozgil.netmera4j.model.device.Device;
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
public class GetUserDevicesResponse {
    private String extId;
    private List<Device> devices;
}