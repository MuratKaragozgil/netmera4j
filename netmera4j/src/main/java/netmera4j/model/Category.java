package netmera4j.model;

import lombok.*;
import netmera4j.util.NotEmpty;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @NotEmpty
    private String extId;
    @NotEmpty
    private String category;
    @NotEmpty
    private Boolean enable;
}
