package com.github.muratkaragozgil.netmera4j.request.device;

import com.github.muratkaragozgil.netmera4j.model.device.NewDevice;
import com.github.muratkaragozgil.netmera4j.util.Assert;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * @author Murat Karagözgil
 */
@Getter
@ToString
public class AddNewDevicesRequest {
    private List<NewDevice> deviceList;

    public static final class AddNewDevicesRequestBuilder {
        private List<NewDevice> deviceList;

        private AddNewDevicesRequestBuilder(List<NewDevice> deviceList) {
            Assert.notNull(deviceList, "Device List");
            this.deviceList = deviceList;
        }

        public static AddNewDevicesRequestBuilder withDeviceList(List<NewDevice> deviceList) {
            return new AddNewDevicesRequestBuilder(deviceList);
        }

        public AddNewDevicesRequest build() {
            AddNewDevicesRequest addNewDevicesRequest = new AddNewDevicesRequest();
            addNewDevicesRequest.deviceList = this.deviceList;
            return addNewDevicesRequest;
        }
    }
}
