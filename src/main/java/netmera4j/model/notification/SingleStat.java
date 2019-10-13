package netmera4j.model.notification;

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
public class SingleStat {
    private Platform platform;
    private Integer sent;
    private Integer failed;
    private Integer clicked;
}
