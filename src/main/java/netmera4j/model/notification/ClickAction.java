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
public class ClickAction {
    private String url;
    private boolean doNothing;
}
