package com.github.muratkaragozgil.netmera4j.request.device;

import com.github.muratkaragozgil.netmera4j.model.device.UserAndProfileAttributeList;
import com.github.muratkaragozgil.netmera4j.util.NotEmpty;
import lombok.*;

import java.util.List;

/**
 * @author Murat Karagözgil
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PullProfileAttributesFromUserRequest {
    @NotEmpty
    private List<UserAndProfileAttributeList> userAndProfileAttributeLists;
}
