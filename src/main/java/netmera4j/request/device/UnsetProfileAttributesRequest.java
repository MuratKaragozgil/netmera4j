package netmera4j.request.device;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import netmera4j.model.device.SingleUnsetObject;
import netmera4j.util.NotEmpty;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UnsetProfileAttributesRequest {
    @NotEmpty
    private List<SingleUnsetObject> singleUnsetObjects;

    public UnsetProfileAttributesRequest(UnsetProfileAttributesRequestBuilder unsetProfileAttributesRequestBuilder) {
        this.singleUnsetObjects = unsetProfileAttributesRequestBuilder.userAndProfileAttributeLists;
    }

    public static final class UnsetProfileAttributesRequestBuilder {
        private List<SingleUnsetObject> userAndProfileAttributeLists;

        private UnsetProfileAttributesRequestBuilder() {
        }

        public static UnsetProfileAttributesRequestBuilder UnsetProfileAttributesRequest() {
            return new UnsetProfileAttributesRequestBuilder();
        }

        public UnsetProfileAttributesRequestBuilder userAndProfileAttributeLists(List<SingleUnsetObject> singleUnsetObjects) {
            this.userAndProfileAttributeLists = singleUnsetObjects;
            return this;
        }

        public UnsetProfileAttributesRequestBuilder addSingleUnsetObject(SingleUnsetObject singleUnsetObject) {
            if (this.userAndProfileAttributeLists == null) {
                this.userAndProfileAttributeLists = new ArrayList<>(1);
            }
            this.userAndProfileAttributeLists.add(singleUnsetObject);
            return this;
        }

        public UnsetProfileAttributesRequest build() {
            return new UnsetProfileAttributesRequest(this);
        }
    }
}
