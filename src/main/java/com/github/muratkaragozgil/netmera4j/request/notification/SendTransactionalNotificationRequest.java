package com.github.muratkaragozgil.netmera4j.request.notification;

import com.github.muratkaragozgil.netmera4j.model.notification.SingleMessage;
import com.github.muratkaragozgil.netmera4j.model.notification.Target;
import lombok.*;
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
public class SendTransactionalNotificationRequest {
    @NotEmpty
    private String notificationKey;
    private SingleMessage message;
    @NotEmpty
    private Target target;

}
