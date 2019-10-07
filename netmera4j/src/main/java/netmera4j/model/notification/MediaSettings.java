package netmera4j.model.notification;

import lombok.*;

/**
 * @author Murat Karagözgil
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class MediaSettings {
    private String mediaUrl;
    private String title;
    private String subtitle;
    private String sound;
    private boolean vibration;
    private boolean badge;
}
