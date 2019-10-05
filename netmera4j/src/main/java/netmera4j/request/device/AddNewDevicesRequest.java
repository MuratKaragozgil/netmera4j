package netmera4j.request.device;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import netmera4j.model.device.NewDevice;
import netmera4j.util.NotEmpty;
import netmera4j.util.NotNull;

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
