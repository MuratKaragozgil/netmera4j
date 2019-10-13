package netmera4j.model.device;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import netmera4j.util.NotNull;

import java.util.List;

/**
 * @author Murat Karagözgil
 */
@Getter
@Builder
@ToString
public class SingleUnsetObject {
    @NotNull
    private String extId;
    @NotNull
    private List<Object> profile;
}
