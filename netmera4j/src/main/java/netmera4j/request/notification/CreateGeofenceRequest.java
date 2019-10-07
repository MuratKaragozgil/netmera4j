package netmera4j.request.notification;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import netmera4j.constant.RadiusUnit;
import netmera4j.util.NotEmpty;

import java.util.List;

/**
 * @author Murat Karagözgil
 */
@Getter
@Builder
@ToString
public class CreateGeofenceRequest {
    @NotEmpty
    private String title;
    @NotEmpty
    private Double latitude;
    @NotEmpty
    private Double longitude;
    @NotEmpty
    private Integer radius;
    private RadiusUnit unit;
    private List<String> groups;
}
