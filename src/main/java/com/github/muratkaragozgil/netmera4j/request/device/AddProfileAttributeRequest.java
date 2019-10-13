package com.github.muratkaragozgil.netmera4j.request.device;

import lombok.*;
import com.github.muratkaragozgil.netmera4j.model.device.UserAndProfileAttributeMap;
import com.github.muratkaragozgil.netmera4j.util.NotEmpty;

import java.util.List;

/**
 * @author Murat Karagözgil
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AddProfileAttributeRequest {
    @NotEmpty
    private List<UserAndProfileAttributeMap> userAndProfileAttributeMaps;
}
