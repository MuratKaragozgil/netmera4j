package netmera4j;

import netmera4j.callback.NetmeraCallBack;
import netmera4j.request.device.*;
import netmera4j.request.notification.*;
import netmera4j.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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
    void sendRequest(SendBulkNotificationRequest sendBulkNotificationRequest, NetmeraCallBack<NotificationResponse> callBack);

    void sendRequest(SendTransactionalNotificationRequest sendTransactionalNotificationRequest, NetmeraCallBack<Void> callBack);

    void sendRequest(List<SendBulkNotificationRequest> sendBulkNotificationRequests, NetmeraCallBack<Void> callBack);

    void sendRequest(CreateTransactionalNotificationRequest createTransactionalNotificationRequest, NetmeraCallBack<NotificationResponse> callBack);

    void sendRequest(GetPushStatsRequest getPushStatsRequest, NetmeraCallBack<GetPushStatsResponse> callBack);

    void sendRequest(GetPushStatsInDateRangeRequest getPushStatsInDateRangeRequest, NetmeraCallBack<GetPushStatsInDateRangeResponse> callBack);

    void sendRequest(GetPushResultsRequest getPushResultsRequest, NetmeraCallBack<GetPushResultResponse> callBack);

    void sendRequest(GetPushResultResponse getPushResultResponse, NetmeraCallBack<GetPushResultResponse> callBack);

    void sendRequest(CreateGeofenceRequest createGeofenceRequest, NetmeraCallBack<Void> callBack);
}