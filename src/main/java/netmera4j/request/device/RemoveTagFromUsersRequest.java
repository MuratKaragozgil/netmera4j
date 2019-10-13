package netmera4j.request.device;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import netmera4j.util.NotEmpty;

import java.util.List;

/**
 * @author Murat Karagözgil
 */
@Getter
@ToString
@AllArgsConstructor
public class RemoveTagFromUsersRequest {
    @NotEmpty
    private String tag;
    @NotEmpty
    private List<String> extIds;
}
