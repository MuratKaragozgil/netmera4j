package netmera4j.model.notification;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
public class BulkMessage extends Message {
    @NotEmpty
    private String title;
    @NotEmpty
    private String text;
    @NotEmpty
    private List<Platform> platforms;
    private Schedule schedule;
    private Speed speed;
    /**
     * Web Push fields
     */
    private String wpChromeImage;
    private ClickAction click;
    private boolean interactionRequired;
}
