package com.github.muratkaragozgil.netmera4j.request.device;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import com.github.muratkaragozgil.netmera4j.model.device.NewDevice;
import com.github.muratkaragozgil.netmera4j.util.NotEmpty;
import com.github.muratkaragozgil.netmera4j.util.NotNull;

import java.util.List;

/**
 * @author Murat Karagözgil
 */
@Getter
@ToString
@AllArgsConstructor
public class AddNewDevicesRequest {
    @NotEmpty
    @NotNull
    private List<NewDevice> deviceList;
}
