package netmera4j.service;

import netmera4j.request.notification.CreateTransactionalNotificationRequest;
import netmera4j.request.notification.SendBulkNotificationRequest;
import netmera4j.request.notification.SendTransactionalNotificationRequest;
import netmera4j.response.GetPushStatsResponse;
import netmera4j.response.NotificationResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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
}
