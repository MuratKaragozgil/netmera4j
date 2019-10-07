package netmera4j.service;

import netmera4j.request.notification.CreateGeofenceRequest;
import netmera4j.request.notification.CreateTransactionalNotificationRequest;
import netmera4j.request.notification.SendBulkNotificationRequest;
import netmera4j.request.notification.SendTransactionalNotificationRequest;
import netmera4j.response.GetPushResultResponse;
import netmera4j.response.GetPushStatsInDateRangeResponse;
import netmera4j.response.GetPushStatsResponse;
import netmera4j.response.NotificationResponse;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * @author Murat Karagözgil
 */
public interface NotificationService {

    @POST("/rest/3.0/sendBulkNotification")
    Call<NotificationResponse> sendBulkNotification(@Body SendBulkNotificationRequest sendBulkNotificationRequest);

    @POST("/rest/3.0/sendNotification")
    Call<Void> sendNotification(@Body SendTransactionalNotificationRequest sendTransactionalNotificationRequest);

    @POST("/rest/3.0/sendNotification")
    Call<Void> sendNotificationInChunks(@Body List<SendBulkNotificationRequest> sendBulkNotificationRequests);

    @POST("/rest/3.0/createNotificationDefinition")
    Call<NotificationResponse> createNotificationDefinition(@Body CreateTransactionalNotificationRequest createTransactionalNotificationRequest);

    @GET("/rest/3.0/getPushStats")
    Call<GetPushStatsResponse> getPushStats(@Query("notificationKey") Integer notificationKey);

    @GET("/rest/3.0/getPushStats")
    Call<GetPushStatsInDateRangeResponse> getPushStatsInDateRange(@Query("start") Long start, @Query("end") Long end);

    @GET("/rest/3.0/getPushResult")
    Call<GetPushResultResponse> getPushResults(@Query("max") Integer max, @Query("notificationKey") Integer notificationKey, //
                                               @Query("extId") String extId, @Query("start") Long startDate, //
                                               @Query("end") Long endDate, @Query("token") String token);

    @GET
    Call<GetPushResultResponse> getPushResults(@Url String url);

    @POST("/rest/3.0/geofence/add")
    Call<Void> createGeofence(@Body CreateGeofenceRequest createGeofenceRequest);
}
