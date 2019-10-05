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
public class Target {
    private boolean sendToAll;
}
