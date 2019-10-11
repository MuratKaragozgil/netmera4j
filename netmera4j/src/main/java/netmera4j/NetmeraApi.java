package netmera4j;

import net.jodah.failsafe.Failsafe;
import net.jodah.failsafe.RetryPolicy;
import netmera4j.callback.NetmeraCallBack;
import netmera4j.exception.NetmeraError;
import netmera4j.model.api.NetmeraRetryPolicy;
import netmera4j.request.device.*;
import netmera4j.request.event.FireEventsRequest;
import netmera4j.request.notification.*;
import netmera4j.response.*;
import netmera4j.service.EventService;
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
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static netmera4j.constant.NetmeraApiContants.NETMERA_HEADER_KEY;

/**
 * @author Murat Karagözgil
 */
public class NetmeraApi implements Netmera {

    private UserService userService;
    private EventService eventService;
    private NotificationService notificationService;
    private Converter<ResponseBody, NetmeraError> errorConverter;

    private final String TARGET_HOST;

    private OkHttpClient.Builder httpClient;
    private int connectionTimeout, readTimeout, writeTimeout, callTimeout;

    private NetmeraApi(String targetHost, String apiKey, NetmeraRetryPolicy netmeraRetryPolicy, Integer maxRetryCount) {
        httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(connectionTimeout, TimeUnit.SECONDS);
        httpClient.readTimeout(readTimeout, TimeUnit.SECONDS);
        httpClient.writeTimeout(writeTimeout, TimeUnit.SECONDS);
        httpClient.callTimeout(callTimeout, TimeUnit.SECONDS);
        this.TARGET_HOST = targetHost;

        RetryPolicy<okhttp3.Response> retryPolicy = new RetryPolicy<okhttp3.Response>()
                .handle(SocketException.class)
                .handleResultIf(result -> result.code() > 499)
                .withBackoff(netmeraRetryPolicy.getDelay(), netmeraRetryPolicy.getMaxDelay(), netmeraRetryPolicy.getUnit())
                .onFailedAttempt(e -> logger.error("Failed Attempt!::{}", e.getLastResult().code()))
                .withMaxRetries(maxRetryCount);

        httpClient.interceptors().add(chain -> {
            Request request = chain.request().newBuilder().addHeader(NETMERA_HEADER_KEY, apiKey).build();
            // try the request
            okhttp3.Response response = chain.proceed(request);
            if (response.code() > 499) {
                // retry the request
                response = Failsafe.with(retryPolicy).get(() -> chain.proceed(request));
            }
            // otherwise just pass the original response on
            return response;
        });
    }

    private void connect() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TARGET_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        errorConverter = retrofit.responseBodyConverter(NetmeraError.class, new Annotation[0]);
        userService = retrofit.create(UserService.class);
        eventService = retrofit.create(EventService.class);
        notificationService = retrofit.create(NotificationService.class);
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
        Call<Void> call = userService.unsetProfileAttributes(unsetProfileAttributesRequest.getSingleUnsetObjects());
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

    @Override
    public void sendRequest(GetPushStatsInDateRangeRequest getPushStatsInDateRangeRequest, NetmeraCallBack<GetPushStatsInDateRangeResponse> callBack) {
        callBack.setErrorConverter(errorConverter);
        logger.debug("SendRequest::started::request::{}", getPushStatsInDateRangeRequest);
        Call<GetPushStatsInDateRangeResponse> call = notificationService.getPushStatsInDateRange(getPushStatsInDateRangeRequest.getStartDate(), getPushStatsInDateRangeRequest.getEndDate());
        call.enqueue(callBack);
    }

    @Override
    public void sendRequest(GetPushResultsRequest getPushResultsRequest, NetmeraCallBack<GetPushResultResponse> callBack) {
        callBack.setErrorConverter(errorConverter);
        logger.debug("SendRequest::started::request::{}", getPushResultsRequest);
        Call<GetPushResultResponse> call = notificationService.getPushResults(getPushResultsRequest.getMax(), getPushResultsRequest.getNotificationKey(), //
                getPushResultsRequest.getExtId(), getPushResultsRequest.getStart(), getPushResultsRequest.getEnd(), getPushResultsRequest.getToken());
        call.enqueue(callBack);
    }

    @Override
    public void sendRequest(GetPushResultResponse getPushResultResponse, NetmeraCallBack<GetPushResultResponse> callBack) {
        callBack.setErrorConverter(errorConverter);
        logger.debug("SendRequest::started::request::{}", getPushResultResponse.getNextPage());
        Call<GetPushResultResponse> call = notificationService.getPushResults(getPushResultResponse.getNextPage());
        call.enqueue(callBack);
    }

    @Override
    public void sendRequest(CreateGeofenceRequest createGeofenceRequest, NetmeraCallBack<Void> callBack) {
        callBack.setErrorConverter(errorConverter);
        logger.debug("SendRequest::started::request::{}", createGeofenceRequest);
        Call<Void> call = notificationService.createGeofence(createGeofenceRequest);
        call.enqueue(callBack);
    }

    @Override
    public void sendRequest(FireEventsRequest fireEventsRequest, NetmeraCallBack<Void> callBack) {
        callBack.setErrorConverter(errorConverter);
        logger.debug("SendRequest::started::request::{}", fireEventsRequest.getEventList());
        List<Map<String, Object>> eventData = new ArrayList<>(fireEventsRequest.getEventList().size());
        fireEventsRequest.getEventList().forEach(e -> eventData.add(e.getParameters()));
        Call<Void> call = eventService.fireEvent(eventData);
        call.enqueue(callBack);
    }

    public static final class NetmeraApiBuilder {
        private NetmeraRetryPolicy netmeraRetryPolicy = new NetmeraRetryPolicy();
        private int maxRetryCount = 3;
        private String targetHost;
        private String apiKey;
        private int connectionTimeout = 30, readTimeout = 30, writeTimeout = 30, callTimeout = 30;

        public NetmeraApiBuilder(String targetHost, String apiKey) {
            this.targetHost = targetHost;
            this.apiKey = apiKey;
        }

        public static NetmeraApiBuilder NetmeraApi(String targetHost, String apiKey) {
            return new NetmeraApiBuilder(targetHost, apiKey);
        }

        public NetmeraApiBuilder withNetmeraRetryPolicy(NetmeraRetryPolicy netmeraRetryPolicy) {
            this.netmeraRetryPolicy = netmeraRetryPolicy;
            return this;
        }

        public NetmeraApiBuilder withReadTimeout(int readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        public NetmeraApiBuilder withConnectionTimeout(int connectionTimeout) {
            this.connectionTimeout = connectionTimeout;
            return this;
        }

        public NetmeraApiBuilder withWriteTimeout(int writeTimeout) {
            this.writeTimeout = writeTimeout;
            return this;
        }

        public NetmeraApiBuilder withCallTimeout(int callTimeout) {
            this.callTimeout = callTimeout;
            return this;
        }

        public NetmeraApiBuilder withMaxRetryCount(int maxRetryCount) {
            this.maxRetryCount = maxRetryCount;
            return this;
        }

        public Netmera build() {
            NetmeraApi netmeraApi = new NetmeraApi(targetHost, apiKey, netmeraRetryPolicy, maxRetryCount);
            netmeraApi.callTimeout = this.callTimeout;
            netmeraApi.readTimeout = this.readTimeout;
            netmeraApi.writeTimeout = this.writeTimeout;
            netmeraApi.connectionTimeout = this.connectionTimeout;
            netmeraApi.connect();
            return NetmeraProxy.newInstance(netmeraApi);
        }
    }
}