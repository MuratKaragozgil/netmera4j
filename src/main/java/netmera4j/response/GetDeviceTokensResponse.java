package netmera4j.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import netmera4j.model.device.Device;

import java.util.List;

/**
 * @author Murat Karagözgil
 */

@Getter
@Setter
@ToString
public class GetDeviceTokensResponse {
    private String nextPage;
    private Integer total;
    private List<Device> devices;
}
