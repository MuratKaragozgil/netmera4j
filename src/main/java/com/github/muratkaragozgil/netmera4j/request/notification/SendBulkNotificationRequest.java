package com.github.muratkaragozgil.netmera4j.request.notification;

import com.github.muratkaragozgil.netmera4j.model.notification.BulkMessage;
import lombok.*;
import com.github.muratkaragozgil.netmera4j.model.notification.Target;
import com.github.muratkaragozgil.netmera4j.util.NotEmpty;

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
    private BulkMessage message;
    @NotEmpty
    private Target target;
}
