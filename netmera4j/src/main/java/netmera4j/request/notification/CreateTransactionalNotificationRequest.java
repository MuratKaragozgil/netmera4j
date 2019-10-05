package netmera4j.request.notification;

import lombok.*;
import netmera4j.constant.Platform;
import netmera4j.model.notification.ClickAction;
import netmera4j.model.notification.HookSettings;
import netmera4j.model.notification.MediaSettings;
import netmera4j.util.NotEmpty;

import java.util.List;

/**
 * @author Murat Karagözgil
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class CreateTransactionalNotificationRequest {
    @NotEmpty
    private String title;
    @NotEmpty
    private String message;
    private String personalizedMessage;
    private boolean inbox;
    @NotEmpty
    private List<Platform> platforms;
    private ClickAction action;
    private MediaSettings ios;
    private MediaSettings android;
    private HookSettings hook;
}
