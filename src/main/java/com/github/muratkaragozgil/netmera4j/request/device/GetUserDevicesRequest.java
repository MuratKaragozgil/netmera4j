package com.github.muratkaragozgil.netmera4j.request.device;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import com.github.muratkaragozgil.netmera4j.util.NotEmpty;

@Builder(builderMethodName = "hiddenBuilder")
@ToString
@Getter
public class GetUserDevicesRequest {
    @NotEmpty
    private String externalId;
    private Boolean pushPermitted;

    public static GetUserDevicesRequestBuilder Builder(String externalId) {
        return hiddenBuilder().externalId(externalId).pushPermitted(Boolean.FALSE);
    }
}
