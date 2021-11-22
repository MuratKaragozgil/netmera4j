package com.github.muratkaragozgil.netmera4j;

import com.github.muratkaragozgil.netmera4j.callback.NetmeraCallBack;
import com.github.muratkaragozgil.netmera4j.constant.NetmeraApiContants;
import com.github.muratkaragozgil.netmera4j.exception.NetmeraError;
import com.github.muratkaragozgil.netmera4j.exception.ValidationException;
import com.github.muratkaragozgil.netmera4j.model.api.NetmeraRetryPolicy;
import com.github.muratkaragozgil.netmera4j.request.device.*;
import com.github.muratkaragozgil.netmera4j.request.event.FireEventsRequest;
import com.github.muratkaragozgil.netmera4j.request.notification.*;
import com.github.muratkaragozgil.netmera4j.response.*;
import com.github.muratkaragozgil.netmera4j.service.EventService;
import com.github.muratkaragozgil.netmera4j.service.NotificationService;
import com.github.muratkaragozgil.netmera4j.service.UserService;
import com.github.muratkaragozgil.netmera4j.util.Assert;
import com.github.muratkaragozgil.netmera4j.util.NetmeraProxy;
import net.jodah.failsafe.Failsafe;
import net.jodah.failsafe.RetryPolicy;
import okhttp3.ConnectionPool;
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

/**
 * @author Murat Karagözgil
 */
public class NetmeraApi implements Netmera {

    private final UserService userService;
    private final EventService eventService;
    private final NotificationService notificationService;
    private final Converter<ResponseBody, NetmeraError> errorConverter;

    private NetmeraApi(NetmeraApiBuilder netmeraApiBuilder) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(netmeraApiBuilder.connectionTimeout, TimeUnit.SECONDS);
        httpClient.readTimeout(netmeraApiBuilder.readTimeout, TimeUnit.SECONDS);
        httpClient.writeTimeout(netmeraApiBuilder.writeTimeout, TimeUnit.SECONDS);
        httpClient.callTimeout(netmeraApiBuilder.callTimeout, TimeUnit.SECONDS);
        httpClient.connectionPool(netmeraApiBuilder.connectionPool);

        RetryPolicy<okhttp3.Response> retryPolicy = new RetryPolicy<okhttp3.Response>()
                .handle(SocketException.class)
                .handleResultIf(result -> result.code() > 499)
                .withBackoff(netmeraApiBuilder.netmeraRetryPolicy.getDelay(), netmeraApiBuilder.netmeraRetryPolicy.getMaxDelay(), netmeraApiBuilder.netmeraRetryPolicy.getUnit())
                .onFailedAttempt(e -> logger.error("Failed Attempt!::{}", e.getLastResult().code()))
                .withMaxRetries(netmeraApiBuilder.maxRetryCount);

        httpClient.interceptors().add(chain -> {
            Request request = chain.request().newBuilder().addHeader(NetmeraApiContants.NETMERA_HEADER_KEY, netmeraApiBuilder.apiKey).build();
            // try the request
            okhttp3.Response response = chain.proceed(request);
            if (response.code() > 499) {
                // retry the request
                response = Failsafe.with(retryPolicy).get(() -> chain.proceed(request));
            }
            // otherwise, just pass the original response on
            return response;
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(netmeraApiBuilder.targetHost)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        errorConverter = retrofit.responseBodyConverter(NetmeraError.class, new Annotation[0]);
        userService = retrofit.create(UserService.class);
        eventService = retrofit.create(EventService.class);
        notificationService = retrofit.create(NotificationService.class);
    }

    /**
     * Following request can be used to register multiple devices at the same time.
     * Instead of registering single device, you can post array of devices for bulk registration.
     * This method will help you to import your devices into Netmera easily.
     *
     * @param addNewDevicesRequest
     * @param callBack
     * @throws ValidationException if {@code addNewDevicesRequest} parameters is null or empty
     */
    public void sendRequest(AddNewDevicesRequest addNewDevicesRequest, NetmeraCallBack<Void> callBack) {
        callBack.setErrorConverter(errorConverter);
        logger.debug("SendRequest::started::request::{}", addNewDevicesRequest);
        Call<Void> call = userService.createNewDevices(addNewDevicesRequest.getDeviceList());
        call.enqueue(callBack);
    }

    /**
     * Following request can be used to opt-out the devices of a user or a single device from push notifications.
     * All of the devices of that user will be opted-out from push notifications.
     *
     * @param disablePushRequestWithExternalId if {@code disablePushRequestWithExternalId} parameters is null or empty
     * @param callBack
     */
    public void sendRequest(DisablePushRequestWithExternalId disablePushRequestWithExternalId, NetmeraCallBack<Void> callBack) {
        callBack.setErrorConverter(errorConverter);
        logger.debug("SendRequest::started::request::{}", disablePushRequestWithExternalId);
        Call<Void> call = userService.disablePushWithExternalId(disablePushRequestWithExternalId);
        call.enqueue(callBack);
    }

    /**
     * Following request can be used to opt-out the devices of a user or a single device from push notifications.
     * Only one devices which matched with given device token will be opted-out from push notifications.
     *
     * @param disablePushRequestWithToken if {@code disablePushRequestWithToken} parameters is null or empty
     * @param callBack
     */
    public void sendRequest(DisablePushRequestWithToken disablePushRequestWithToken, NetmeraCallBack<Void> callBack) {
        callBack.setErrorConverter(errorConverter);
        logger.debug("SendRequest::started::request::{}", disablePushRequestWithToken);
        Call<Void> call = userService.disablePushWithDeviceToken(disablePushRequestWithToken);
        call.enqueue(callBack);
    }

    /**
     * Following request can be used to opt-in the devices of a user or a single device from push notifications.
     * If you opt-in a user using extId, all of the devices of that user will be opted-in from push notifications.
     *
     * @param enablePushRequestWithExternalId if {@code disablePushRequestWithToken} parameters is null or empty
     * @param callBack
     */
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
        private NetmeraRetryPolicy netmeraRetryPolicy = new NetmeraRetryPolicy.NetmeraRetryPolicyBuilder().build();
        private int maxRetryCount = 3;
        private String targetHost;
        private String apiKey;
        private int connectionTimeout = 30, readTimeout = 30, writeTimeout = 30, callTimeout = 30;
        private ConnectionPool connectionPool = new ConnectionPool();

        public NetmeraApiBuilder(String targetHost, String restApiKey) {
            Assert.notNullOrEmpty(targetHost, "Target Host");
            Assert.notNullOrEmpty(restApiKey, "Rest Api Key");
            this.targetHost = targetHost;
            this.apiKey = restApiKey;
        }

        /**
         * @param targetHost Rest Api endpoint url(netmera cloud endpoint is https://restapi.netmera.com)
         * @param restApiKey Api Key from netmera panel
         * @throws ValidationException if {@code targetHost} or {@code restApiKey} is null
         */
        public static NetmeraApiBuilder NetmeraApi(String targetHost, String restApiKey) {
            Assert.notNullOrEmpty(targetHost, "Target Host");
            Assert.notNullOrEmpty(restApiKey, "Rest Api Key");
            return new NetmeraApiBuilder(targetHost, restApiKey);
        }

        public NetmeraApiBuilder withNetmeraRetryPolicy(NetmeraRetryPolicy netmeraRetryPolicy) {
            this.netmeraRetryPolicy = netmeraRetryPolicy;
            return this;
        }

        public NetmeraApiBuilder withReadTimeout(int readTimeout) {
            Assert.mustBetween(0, Integer.MAX_VALUE, readTimeout, "Read Timeout");
            this.readTimeout = readTimeout;
            return this;
        }

        public NetmeraApiBuilder withConnectionTimeout(int connectionTimeout) {
            Assert.mustBetween(0, Integer.MAX_VALUE, connectionTimeout, "Connection Timeout");
            this.connectionTimeout = connectionTimeout;
            return this;
        }

        public NetmeraApiBuilder withWriteTimeout(int writeTimeout) {
            Assert.mustBetween(0, Integer.MAX_VALUE, writeTimeout, "Write Timeout");
            this.writeTimeout = writeTimeout;
            return this;
        }

        public NetmeraApiBuilder withCallTimeout(int callTimeout) {
            Assert.mustBetween(0, Integer.MAX_VALUE, callTimeout, "Call Timeout");
            this.callTimeout = callTimeout;
            return this;
        }

        public NetmeraApiBuilder withMaxRetryCount(int maxRetryCount) {
            Assert.mustBetween(0, 50, maxRetryCount, "Max Retry Count");
            this.maxRetryCount = maxRetryCount;
            return this;
        }

        public NetmeraApiBuilder withConnectionPool(ConnectionPool connectionPool) {
            Assert.notNull(connectionPool, "Connection Pool");
            this.connectionPool = connectionPool;
            return this;
        }

        public Netmera build() {
            return NetmeraProxy.newInstance(new NetmeraApi(this));
        }
    }
}