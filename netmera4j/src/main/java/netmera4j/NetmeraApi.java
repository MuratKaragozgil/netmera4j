package netmera4j;

import netmera4j.callback.NetmeraCallBack;
import netmera4j.exception.NetmeraError;
import netmera4j.request.device.*;
import netmera4j.request.notification.CreateTransactionalNotificationRequest;
import netmera4j.request.notification.GetPushStatsRequest;
import netmera4j.request.notification.SendBulkNotificationRequest;
import netmera4j.request.notification.SendTransactionalNotificationRequest;
import netmera4j.response.*;
import netmera4j.service.NotificationService;
import netmera4j.service.UserService;
import netmera4j.util.NetmeraProxy;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static netmera4j.constant.NetmeraApiContants.NETMERA_HEADER_KEY;

/**
 * @author Murat Karagözgil
 */
public class NetmeraApi implements Netmera {

    private UserService userService;
    private NotificationService notificationService;
    private Converter<ResponseBody, NetmeraError> errorConverter;

    private NetmeraApi(String targetHost, String apiKey) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(30, TimeUnit.SECONDS);
        httpClient.readTimeout(30, TimeUnit.SECONDS);
        // TODO maybe implement other features

        httpClient.addInterceptor(chain -> {
            Request request = chain.request().newBuilder().addHeader(NETMERA_HEADER_KEY, apiKey).build();
            return chain.proceed(request);
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(targetHost)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        errorConverter = retrofit.responseBodyConverter(NetmeraError.class, new Annotation[0]);

        userService = retrofit.create(UserService.class);
        notificationService = retrofit.create(NotificationService.class);
    }

    public static Netmera Build(String targetHost, String apiKey) {
        return NetmeraProxy.newInstance(new NetmeraApi(targetHost, apiKey));
    }

    public void sendRequest(AddNewDevicesRequest addNewDevicesRequest, NetmeraCallBack<Void> callBack) {
        callBack.setErrorConverter(errorConverter);
        logger.debug("SendRequest::started::request::{}", addNewDevicesRequest);
        Call<Void> call = userService.createNewDevices(addNewDevicesRequest.getDeviceList());
        call.enqueue(callBack);
    }

    public void sendRequest(DisablePushRequestWithExternalId disablePushRequestWithExternalId, NetmeraCallBack<Void> callBack) {
        callBack.setErrorConverter(errorConverter);
        logger.debug("SendRequest::started::request::{}", disablePushRequestWithExternalId);
        Call<Void> call = userService.disablePushWithExternalId(disablePushRequestWithExternalId);
        call.enqueue(callBack);
    }

    public void sendRequest(DisablePushRequestWithToken disablePushRequestWithToken, NetmeraCallBack<Void> callBack) {
        callBack.setErrorConverter(errorConverter);
        logger.debug("SendRequest::started::request::{}", disablePushRequestWithToken);
        Call<Void> call = userService.disablePushWithDeviceToken(disablePushRequestWithToken);
        call.enqueue(callBack);
    }

    public void sendRequest(EnablePushRequestWithExternalId enablePushRequestWithExternalId, NetmeraCallBack<Void> callBack) {
        callBack.setErrorConverter(errorConverter);
        logger.debug("SendRequest::started::request::{}", enablePushRequestWithExternalId);
        Call<Void> call = userService.enablePushWithExternalId(enablePushRequestWithExternalId);
        call.enqueue(callBack);
    }

    public void sendRequest(EnablePushRequestWithToken enablePushRequestWithToken, NetmeraCallBack<Void> callBack) {
        callBack.setErrorConverter(errorConverter);
        logger.debug("SendRequest::started::request::{}", enablePushRequestWithToken);
        Call<Void> call = userService.enablePushWithDeviceToken(enablePushRequestWithToken);
        call.enqueue(callBack);
    }

    public void sendRequest(AddTagToUsersRequest addTagToUsersRequest, NetmeraCallBack<Void> callBack) {
        callBack.setErrorConverter(errorConverter);
        logger.debug("SendRequest::started::request::{}", addTagToUsersRequest);
        Call<Void> call = userService.addTagToUsers(addTagToUsersRequest);
        call.enqueue(callBack);
    }

    public void sendRequest(RemoveTagFromUsersRequest removeTagFromUsersRequest, NetmeraCallBack<Void> callBack) {
        callBack.setErrorConverter(errorConverter);
        logger.debug("SendRequest::started::request::{}", removeTagFromUsersRequest);
        Call<Void> call = userService.removeTagFromUsers(removeTagFromUsersRequest);
        call.enqueue(callBack);
    }

    public void sendRequest(SetCategoryPreferenceRequest setCategoryPreferenceRequest, NetmeraCallBack<Void> callBack) {
        callBack.setErrorConverter(errorConverter);
        logger.debug("SendRequest::started::request::{}", setCategoryPreferenceRequest);
        Call<Void> call = userService.setCategoryPreferences(setCategoryPreferenceRequest.getCategories());
        call.enqueue(callBack);
    }

    public void sendRequest(AddProfileAttributeRequest addProfileAttributeRequest, NetmeraCallBack<Void> callBack) {
        callBack.setErrorConverter(errorConverter);
        logger.debug("SendRequest::started::request::{}", addProfileAttributeRequest);
        Call<Void> call = userService.setProfileAttributes(addProfileAttributeRequest.getUserAndProfileAttributeMaps());
        call.enqueue(callBack);
    }

    public void sendRequest(UnsetProfileAttributesRequest unsetProfileAttributesRequest, NetmeraCallBack<Void> callBack) {
        callBack.setErrorConverter(errorConverter);
        logger.debug("SendRequest::started::request::{}", unsetProfileAttributesRequest);
        Call<Void> call = userService.unsetProfileAttributes(unsetProfileAttributesRequest.getUserAndProfileAttributeLists());
        call.enqueue(callBack);
    }

    public void sendRequest(GetProfileAttributesRequest getProfileAttributesRequest, NetmeraCallBack<GetProfileAttributesResponse> callBack) {
        callBack.setErrorConverter(errorConverter);
        logger.debug("SendRequest::started::request::{}", getProfileAttributesRequest);
        Call<GetProfileAttributesResponse> call = userService.getProfileAttributes(getProfileAttributesRequest.getExternalId());
        call.enqueue(callBack);
    }

    public void sendRequest(PushProfileAttributesToUserRequest pushProfileAttributesToUserRequest, NetmeraCallBack<Void> callBack) {
        callBack.setErrorConverter(errorConverter);
        logger.debug("SendRequest::started::request::{}", pushProfileAttributesToUserRequest);
        Call<Void> call = userService.pushProfileAttributesToUser(pushProfileAttributesToUserRequest.getUserAndProfileAttributeLists());
        call.enqueue(callBack);
    }

    public void sendRequest(PullProfileAttributesFromUserRequest pullProfileAttributesFromUserRequest, NetmeraCallBack<Void> callBack) {
        callBack.setErrorConverter(errorConverter);
        logger.debug("SendRequest::started::request::{}", pullProfileAttributesFromUserRequest);
        Call<Void> call = userService.pullProfileAttributesToUser(pullProfileAttributesFromUserRequest.getUserAndProfileAttributeLists());
        call.enqueue(callBack);
    }

    public void sendRequest(DeleteProfileAttributeFromAllUsersRequest deleteProfileAttributeFromAllUsersRequest, NetmeraCallBack<Void> callBack) {
        callBack.setErrorConverter(errorConverter);
        logger.debug("SendRequest::started::request::{}", deleteProfileAttributeFromAllUsersRequest);
        Call<Void> call = userService.deleteProfileAttributeFromAllUsers(deleteProfileAttributeFromAllUsersRequest);
        call.enqueue(callBack);
    }

    @Override
    public void sendRequest(GetUserDevicesRequest getUserDevicesRequest, NetmeraCallBack<GetUserDevicesResponse> callBack) {
        callBack.setErrorConverter(errorConverter);
        logger.debug("SendRequest::started::request::{}", getUserDevicesRequest);
        Call<GetUserDevicesResponse> call = userService.getUserDevices(getUserDevicesRequest.getExternalId(), getUserDevicesRequest.getPushPermitted());
        call.enqueue(callBack);
    }

    public void sendRequest(GetDeviceTokensRequest getDeviceTokensRequest, NetmeraCallBack<GetDeviceTokensResponse> callBack) {
        callBack.setErrorConverter(errorConverter);
        logger.debug("SendRequest::started::request::{}", getDeviceTokensRequest);
        Call<GetDeviceTokensResponse> call = userService.getDeviceTokens(getDeviceTokensRequest.getMax(), getDeviceTokensRequest.getOffSet());
        call.enqueue(callBack);
    }

    public void sendRequest(GetDeviceTokensResponse getDeviceTokensResponse, NetmeraCallBack<GetDeviceTokensResponse> callBack) {
        callBack.setErrorConverter(errorConverter);
        logger.debug("SendRequest::started::request::{}", getDeviceTokensResponse);
        Call<GetDeviceTokensResponse> call = userService.getDeviceTokens(getDeviceTokensResponse.getNextPage());
        call.enqueue(callBack);
    }

    // Notification Requests
    public void sendRequest(SendBulkNotificationRequest sendBulkNotificationRequest, NetmeraCallBack<NotificationResponse> callBack) {
        callBack.setErrorConverter(errorConverter);
        logger.debug("SendRequest::started::request::{}", sendBulkNotificationRequest);
        Call<NotificationResponse> call = notificationService.sendBulkNotification(sendBulkNotificationRequest);
        call.enqueue(callBack);
    }

    @Override
    public void sendRequest(SendTransactionalNotificationRequest sendTransactionalNotificationRequest, NetmeraCallBack<Void> callBack) {
        callBack.setErrorConverter(errorConverter);
        logger.debug("SendRequest::started::request::{}", sendTransactionalNotificationRequest);
        Call<Void> call = notificationService.sendNotification(sendTransactionalNotificationRequest);
        call.enqueue(callBack);
    }

    @Override
    public void sendRequest(List<SendBulkNotificationRequest> sendBulkNotificationRequests, NetmeraCallBack<Void> callBack) {
        callBack.setErrorConverter(errorConverter);
        logger.debug("SendRequest::started::request::{}", sendBulkNotificationRequests);
        Call<Void> call = notificationService.sendNotificationInChunks(sendBulkNotificationRequests);
        call.enqueue(callBack);
    }

    @Override
    public void sendRequest(CreateTransactionalNotificationRequest createTransactionalNotificationRequest, NetmeraCallBack<NotificationResponse> callBack) {
        callBack.setErrorConverter(errorConverter);
        logger.debug("SendRequest::started::request::{}", createTransactionalNotificationRequest);
        Call<NotificationResponse> call = notificationService.createNotificationDefinition(createTransactionalNotificationRequest);
        call.enqueue(callBack);
    }

    @Override
    public void sendRequest(GetPushStatsRequest getPushStatsRequest, NetmeraCallBack<GetPushStatsResponse> callBack) {
        callBack.setErrorConverter(errorConverter);
        logger.debug("SendRequest::started::request::{}", getPushStatsRequest);
        Call<GetPushStatsResponse> call = notificationService.getPushStats(getPushStatsRequest.getNotificationKey());
        call.enqueue(callBack);
    }
}