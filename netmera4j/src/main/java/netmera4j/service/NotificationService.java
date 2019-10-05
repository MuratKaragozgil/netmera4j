package netmera4j.service;

import netmera4j.request.notification.SendBulkNotificationRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author Murat Karagözgil
 */
public interface NotificationService {

    @POST("/rest/3.0/sendBulkNotification")
    Call<Void> sendBulkNotification(@Body SendBulkNotificationRequest sendBulkNotificationRequest);
}
