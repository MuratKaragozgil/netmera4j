package netmera4j.request.notification;

import lombok.*;
import netmera4j.model.notification.SingleMessage;
import netmera4j.model.notification.Target;
import netmera4j.util.NotEmpty;

/**
 * @author Murat Karagözgil
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class SendTransactionalNotificationRequest {
    @NotEmpty
    private String notificationKey;
    private SingleMessage message;
    @NotEmpty
    private Target target;

}
