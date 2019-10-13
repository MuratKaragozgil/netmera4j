package com.github.muratkaragozgil.netmera4j.request.device;

import lombok.*;
import com.github.muratkaragozgil.netmera4j.model.device.Category;
import com.github.muratkaragozgil.netmera4j.util.NotEmpty;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SetCategoryPreferenceRequest {
    @NotEmpty
    private List<Category> categories;

    public SetCategoryPreferenceRequest addCategory(Category category) {
        if (categories == null) {
            categories = new ArrayList<>();
        }
        categories.add(category);
        return this;
    }
}
