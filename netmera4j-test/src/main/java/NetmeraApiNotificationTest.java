import netmera4j.Netmera;
import netmera4j.NetmeraApi;
import netmera4j.callback.NetmeraCallBack;
import netmera4j.constant.Platform;
import netmera4j.model.notification.BulkMessage;
import netmera4j.model.notification.SingleMessage;
import netmera4j.model.notification.Target;
import netmera4j.request.notification.CreateTransactionalNotificationRequest;
import netmera4j.request.notification.GetPushStatsRequest;
import netmera4j.request.notification.SendBulkNotificationRequest;
import netmera4j.request.notification.SendTransactionalNotificationRequest;
import netmera4j.response.GetPushStatsResponse;
import netmera4j.response.NotificationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Murat Karagözgil
 */
public class NetmeraApiNotificationTest {

    private Logger logger = LoggerFactory.getLogger(NetmeraApiDeviceTest.class);

    private Netmera netmeraApi = NetmeraApi.Build(TARGET_HOST, API_KEY);

    private static final String EXTERNAL_ID = "murat1";
    private static final String TOKEN = "token1";
    private static final String API_KEY = "N79vhZlSKZD5upipyh7NdHIgPOIassVYRRAdPjga2sv00ORK4DHaBg";
    private static final String TARGET_HOST = "http://nova.sdpaas.com";
    private static final Integer TRANSACTIONAL_NOTIFICATION_KEY = 4665;

    // Message Parameters
    private static final String TITLE = "title";
    private static final String MESSAGE = "message";

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NetmeraApiNotificationTest netmeraApiNotificationTest = new NetmeraApiNotificationTest();

        CompletableFuture completableFuture = new CompletableFuture();
        netmeraApiNotificationTest.testSendBasicBulkNotification(completableFuture);
        completableFuture.get();

        completableFuture = new CompletableFuture();
        netmeraApiNotificationTest.testSendTransactionalNotification(completableFuture);
        completableFuture.get();

        completableFuture = new CompletableFuture();
        netmeraApiNotificationTest.testCreateTransactionalNotificationDefinition(completableFuture);
        completableFuture.get();

        completableFuture = new CompletableFuture();
        netmeraApiNotificationTest.testGetPushStats(completableFuture);
        completableFuture.get();
    }

    private void testSendBasicBulkNotification(CompletableFuture completableFuture) {
        netmeraApi.sendRequest(SendBulkNotificationRequest.builder()
                .message(BulkMessage.builder() //
                        .title(TITLE) //
                        .text(MESSAGE) //
                        .platforms(Arrays.asList(Platform.ANDROID, Platform.IOS)) //
                        .build()) //
                .target(Target.builder() //
                        .sendToAll(true) //
                        .build()) //
                .build(), getNotificationCallBack(completableFuture));
    }

    private void testSendTransactionalNotification(CompletableFuture completableFuture) {
        netmeraApi.sendRequest(SendTransactionalNotificationRequest.builder() //
                .message(SingleMessage.builder().build() //
                        .addParameter("customMessage", "Thanks!!!")) //
                .notificationKey(TRANSACTIONAL_NOTIFICATION_KEY.toString()) //
                .target(Target.builder() //
                        .extId(EXTERNAL_ID) //
                        .build()) //
                .build(), getStandardCallBack(completableFuture));
    }

    private void testCreateTransactionalNotificationDefinition(CompletableFuture completableFuture) {
        netmeraApi.sendRequest(CreateTransactionalNotificationRequest.builder() //
                .title(TITLE) //
                .message(MESSAGE) //
                .platforms(Arrays.asList(Platform.ANDROID, Platform.IOS)) //
                .build(), getNotificationCallBack(completableFuture));
    }

    private void testGetPushStats(CompletableFuture completableFuture) {
        netmeraApi.sendRequest(GetPushStatsRequest.builder().notificationKey(TRANSACTIONAL_NOTIFICATION_KEY).build(), new NetmeraCallBack<GetPushStatsResponse>() {
            @Override
            protected void handleResponseCode(int httpStatus) {
                logger.info("------------------------------------");
                completableFuture.complete(httpStatus);
            }

            @Override
            protected void handleResponseData(GetPushStatsResponse data) {

            }

            @Override
            protected void handleError(Response<GetPushStatsResponse> response) {

            }

            @Override
            protected void handleException(Exception t) {

            }
        });
    }

    private NetmeraCallBack<NotificationResponse> getNotificationCallBack(CompletableFuture completableFuture) {
        return new NetmeraCallBack<NotificationResponse>() {

            @Override
            protected void handleResponseCode(int httpStatus) {
                logger.info("------------------------------------");
                completableFuture.complete(httpStatus);
            }

            @Override
            protected void handleResponseData(NotificationResponse data) {
            }

            @Override
            protected void handleError(Response<NotificationResponse> response) {
            }

            @Override
            protected void handleException(Exception t) {
                t.printStackTrace();
            }
        };
    }

    private NetmeraCallBack<Void> getStandardCallBack(CompletableFuture completableFuture) {
        return new NetmeraCallBack<Void>() {

            @Override
            protected void handleResponseCode(int httpStatus) {
                logger.info("------------------------------------");
                completableFuture.complete(httpStatus);
            }

            @Override
            protected void handleResponseData(Void data) {
            }

            @Override
            protected void handleError(Response<Void> response) {
            }

            @Override
            protected void handleException(Exception t) {
                t.printStackTrace();
            }
        };
    }
}
