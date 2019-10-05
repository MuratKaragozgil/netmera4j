package netmera4j.request.notification;

import lombok.*;
import netmera4j.model.notification.BulkMessage;
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
public class SendBulkNotificationRequest {
    @NotEmpty
    private BulkMessage bulkMessage;
    @NotEmpty
    private Target target;
}
