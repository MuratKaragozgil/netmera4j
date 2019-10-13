package com.github.muratkaragozgil.netmera4j.request.device;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.github.muratkaragozgil.netmera4j.util.NotEmpty;

/**
 * @author Murat Karagözgil
 */
@ToString
@Getter
@Setter
@AllArgsConstructor
public class GetProfileAttributesRequest {
    @NotEmpty
    private String externalId;
}
