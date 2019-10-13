package com.github.muratkaragozgil.netmera4j.model.device;

import com.github.muratkaragozgil.netmera4j.util.NotEmpty;
import lombok.*;

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
