import netmera4j.Netmera;
import netmera4j.NetmeraApi;
import netmera4j.callback.NetmeraCallBack;
import netmera4j.constant.Platform;
import netmera4j.constant.RadiusUnit;
import netmera4j.constant.TargetCondition;
import netmera4j.model.notification.AdvanceTarget;
import netmera4j.model.notification.BasicTarget;
import netmera4j.model.notification.BulkMessage;
import netmera4j.model.notification.SingleMessage;
import netmera4j.request.notification.*;
import netmera4j.response.GetPushResultResponse;
import netmera4j.response.GetPushStatsInDateRangeResponse;
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

    private Netmera netmeraApi = new NetmeraApi.NetmeraApiBuilder(TARGET_HOST, API_KEY).withMaxRetryCount(10).withReadTimeout(30).build();

    private static final String EXTERNAL_ID = "murat1";
    private static final String TOKEN = "token1";
    private static final String API_KEY = "N79vhZlSKZD5upipyh7NdHIgPOIassVYRRAdPjga2sv00ORK4DHaBg";
    private static final String TARGET_HOST = "http://nova.sdpaas.com";
    private static final Integer TRANSACTIONAL_NOTIFICATION_KEY = 4665;

    private static final String TAG_NAME = "NETMERA4J_TAG";

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

        completableFuture = new CompletableFuture();
        netmeraApiNotificationTest.testGetPushStatsInDateRange(completableFuture);
        completableFuture.get();

        completableFuture = new CompletableFuture();
        netmeraApiNotificationTest.testGetPushResults(completableFuture);
        completableFuture.get();

        completableFuture = new CompletableFuture();
        netmeraApiNotificationTest.testSendBasicBulkNotificationToSendToAll(completableFuture);
        completableFuture.get();

        completableFuture = new CompletableFuture();
        netmeraApiNotificationTest.testSendAdvancedBulkNotificationToTagList(completableFuture);
        completableFuture.get();

        completableFuture = new CompletableFuture();
        netmeraApiNotificationTest.testSendBasicBulkNotification(completableFuture);
        completableFuture.get();

        completableFuture = new CompletableFuture();
        netmeraApiNotificationTest.testSendTransactionalNotification(completableFuture);
        completableFuture.get();

        completableFuture = new CompletableFuture();
        netmeraApiNotificationTest.testCreateGeofence(completableFuture);
        completableFuture.get();
    }

    private void testSendBasicBulkNotificationToSendToAll(CompletableFuture completableFuture) {
        netmeraApi.sendRequest(SendBulkNotificationRequest.builder()
                .message(BulkMessage.builder() //
                        .title(TITLE) //
                        .text("testSendBasicBulkNotificationToSendToAll") //
                        .platforms(Arrays.asList(Platform.ANDROID, Platform.IOS)) //
                        .build()) //
                .target(new BasicTarget.BasicTargetBuilder() //
                        .sendToAll(true) //
                        .build()) //
                .build(), getNotificationCallBack(completableFuture));
    }

    private void testSendAdvancedBulkNotificationToTagList(CompletableFuture completableFuture) {
        netmeraApi.sendRequest(SendBulkNotificationRequest.builder()
                .message(BulkMessage.builder() //
                        .title(TITLE) //
                        .text("testSendAdvancedBulkNotificationToTagList") //
                        .platforms(Arrays.asList(Platform.ANDROID, Platform.IOS)) //
                        .build()) //
                .target(new AdvanceTarget.AdvanceTargetBuilder() //
                        .addTag(TargetCondition.AND, Arrays.asList("NETMERA4J_TAG", "NETMERA4J_TAG_2"))
                        .build()) //
                .build(), getNotificationCallBack(completableFuture));
    }

    private void testSendBasicBulkNotification(CompletableFuture completableFuture) {
        netmeraApi.sendRequest(SendBulkNotificationRequest.builder()
                .message(BulkMessage.builder() //
                        .title(TITLE) //
                        .text("testSendBasicBulkNotification") //
                        .platforms(Arrays.asList(Platform.ANDROID, Platform.IOS)) //
                        .build()) //
                .target(new BasicTarget.BasicTargetBuilder()
                        .addTag(TAG_NAME)//
                        .build()) //
                .build(), getNotificationCallBack(completableFuture));
    }

    private void testSendTransactionalNotification(CompletableFuture completableFuture) {
        netmeraApi.sendRequest(SendTransactionalNotificationRequest.builder() //
                .message(SingleMessage.builder().build() //
                        .addParameter("customMessage", "testSendTransactionalNotification")) //
                .notificationKey(TRANSACTIONAL_NOTIFICATION_KEY.toString()) //
                .target(new BasicTarget.BasicTargetBuilder() //
                        .externalId(EXTERNAL_ID) //
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

    private void testCreateGeofence(CompletableFuture completableFuture) {
        netmeraApi.sendRequest(CreateGeofenceRequest.builder().title(TITLE).latitude(12d).longitude(12d).radius(10).unit(RadiusUnit.KM).build(), getStandardCallBack(completableFuture));
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

    private void testGetPushStatsInDateRange(CompletableFuture completableFuture) {
        netmeraApi.sendRequest(GetPushStatsInDateRangeRequest.builder().startDate(1559336400000l).endDate(System.currentTimeMillis()).build(), new NetmeraCallBack<GetPushStatsInDateRangeResponse>() {
            @Override
            protected void handleResponseCode(int httpStatus) {
                logger.info("------------------------------------");
                completableFuture.complete(httpStatus);
            }

            @Override
            protected void handleResponseData(GetPushStatsInDateRangeResponse data) {

            }

            @Override
            protected void handleError(Response<GetPushStatsInDateRangeResponse> response) {

            }

            @Override
            protected void handleException(Exception t) {

            }
        });
    }

    private void testGetPushResults(CompletableFuture completableFuture) {
        netmeraApi.sendRequest(GetPushResultsRequest.builder().notificationKey(TRANSACTIONAL_NOTIFICATION_KEY).extId(EXTERNAL_ID).build(), new NetmeraCallBack<GetPushResultResponse>() {
            @Override
            protected void handleResponseCode(int httpStatus) {
                logger.info("------------------------------------");
                completableFuture.complete(httpStatus);
            }

            @Override
            protected void handleResponseData(GetPushResultResponse data) {

            }

            @Override
            protected void handleError(Response<GetPushResultResponse> response) {

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
