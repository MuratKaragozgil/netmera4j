package netmera4j;

import netmera4j.callback.NetmeraCallBack;
import netmera4j.request.device.*;
import netmera4j.request.notification.SendBulkNotificationRequest;
import netmera4j.response.GetDeviceTokensResponse;
import netmera4j.response.GetProfileAttributesResponse;
import netmera4j.response.GetUserDevicesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Murat Karagözgil
 */
public interface Netmera {

    Logger logger = LoggerFactory.getLogger(Netmera.class);

    void sendRequest(AddNewDevicesRequest addNewDevicesRequest, NetmeraCallBack<Void> callBack);

    void sendRequest(DisablePushRequestWithExternalId disablePushRequestWithExternalId, NetmeraCallBack<Void> callBack);

    void sendRequest(DisablePushRequestWithToken disablePushRequestWithToken, NetmeraCallBack<Void> callBack);

    void sendRequest(EnablePushRequestWithExternalId enablePushRequestWithExternalId, NetmeraCallBack<Void> callBack);

    void sendRequest(EnablePushRequestWithToken enablePushRequestWithToken, NetmeraCallBack<Void> callBack);

    void sendRequest(AddTagToUsersRequest addTagToUsersRequest, NetmeraCallBack<Void> callBack);

    void sendRequest(RemoveTagFromUsersRequest removeTagFromUsersRequest, NetmeraCallBack<Void> callBack);

    void sendRequest(SetCategoryPreferenceRequest setCategoryPreferenceRequest, NetmeraCallBack<Void> callBack);

    void sendRequest(AddProfileAttributeRequest addProfileAttributeRequest, NetmeraCallBack<Void> callBack);

    void sendRequest(UnsetProfileAttributesRequest unsetProfileAttributesRequest, NetmeraCallBack<Void> callBack);

    void sendRequest(GetProfileAttributesRequest getProfileAttributesRequest, NetmeraCallBack<GetProfileAttributesResponse> callBack);

    void sendRequest(PushProfileAttributesToUserRequest pushProfileAttributesToUserRequest, NetmeraCallBack<Void> callBack);

    void sendRequest(PullProfileAttributesFromUserRequest pullProfileAttributesFromUserRequest, NetmeraCallBack<Void> callBack);

    void sendRequest(DeleteProfileAttributeFromAllUsersRequest deleteProfileAttributeFromAllUsersRequest, NetmeraCallBack<Void> callBack);

    void sendRequest(GetUserDevicesRequest getUserDevices, NetmeraCallBack<GetUserDevicesResponse> callBack);

    void sendRequest(GetDeviceTokensRequest getDeviceTokensRequest, NetmeraCallBack<GetDeviceTokensResponse> callBack);

    void sendRequest(GetDeviceTokensResponse getDeviceTokensResponse, NetmeraCallBack<GetDeviceTokensResponse> callBack);

    // Notification Requests
    void sendRequest(SendBulkNotificationRequest sendBulkNotificationRequest, NetmeraCallBack<Void> callBack);
}