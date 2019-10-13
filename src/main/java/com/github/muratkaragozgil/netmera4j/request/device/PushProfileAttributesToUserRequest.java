package com.github.muratkaragozgil.netmera4j.request.device;

import com.github.muratkaragozgil.netmera4j.model.device.UserAndProfileAttributeList;
import lombok.*;
import com.github.muratkaragozgil.netmera4j.util.NotEmpty;

import java.util.List;

/**
 * @author Murat Karagözgil
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PushProfileAttributesToUserRequest {
    @NotEmpty
    private List<UserAndProfileAttributeList> userAndProfileAttributeLists;
}
