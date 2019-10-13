package netmera4j.request.device;

import lombok.*;
import netmera4j.model.device.Category;
import netmera4j.util.NotEmpty;

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
