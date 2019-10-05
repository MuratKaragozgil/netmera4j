package netmera4j.model.notification;

import lombok.*;
import netmera4j.constant.Platform;
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
public class BulkMessage {
    @NotEmpty
    private String title;
    @NotEmpty
    private String text;
    @NotEmpty
    private List<Platform> platforms;
    /**
     * Web Push fields
     */
    private String wpChromeImage;
    private ClickAction click;
    private boolean interactionRequired;
}
