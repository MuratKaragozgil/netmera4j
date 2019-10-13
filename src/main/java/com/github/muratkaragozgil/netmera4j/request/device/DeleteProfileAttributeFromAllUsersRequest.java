package com.github.muratkaragozgil.netmera4j.request.device;

import com.github.muratkaragozgil.netmera4j.util.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import com.github.muratkaragozgil.netmera4j.util.NotEmpty;

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
