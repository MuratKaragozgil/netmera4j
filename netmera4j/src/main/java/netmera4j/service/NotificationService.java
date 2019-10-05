package netmera4j.service;

import netmera4j.request.notification.CreateTransactionalNotificationRequest;
import netmera4j.request.notification.SendBulkNotificationRequest;
import netmera4j.request.notification.SendTransactionalNotificationRequest;
import netmera4j.response.NotificationResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

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
}
