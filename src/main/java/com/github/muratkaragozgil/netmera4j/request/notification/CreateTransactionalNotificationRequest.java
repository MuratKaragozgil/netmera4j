package com.github.muratkaragozgil.netmera4j.request.notification;

import lombok.*;
import com.github.muratkaragozgil.netmera4j.constant.Platform;
import com.github.muratkaragozgil.netmera4j.model.notification.ClickAction;
import com.github.muratkaragozgil.netmera4j.model.notification.HookSettings;
import com.github.muratkaragozgil.netmera4j.model.notification.MediaSettings;
import com.github.muratkaragozgil.netmera4j.util.NotEmpty;

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
