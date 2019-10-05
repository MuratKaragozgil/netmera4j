package netmera4j.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import netmera4j.util.NotEmpty;
import netmera4j.util.NotNull;

/**
 * @author Murat Karagözgil
 */
@Getter
@ToString
@AllArgsConstructor
public class DeleteProfileAttributeFromAllUsersRequest {
    @NotNull
    private String key;
    @NotEmpty
    private String value;
}
