package netmera4j.model.device;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import netmera4j.constant.Platform;

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