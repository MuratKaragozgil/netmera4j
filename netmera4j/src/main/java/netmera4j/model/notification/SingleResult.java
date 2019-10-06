package netmera4j.model.notification;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import netmera4j.constant.Platform;
import netmera4j.constant.SendStatus;

/**
 * @author Murat Karagözgil
 */
@Getter
@Setter
@ToString
public class SingleResult {
    private Platform platform;
    private SendStatus status;
    private String errorCode;
    private String installationId;
    private String extId;
    private String errorMsg;
    private Long timeStamp;
    private String msgTitle;
    private String msgText;
}
