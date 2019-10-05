import netmera4j.Netmera;
import netmera4j.NetmeraApi;
import netmera4j.callback.NetmeraCallBack;
import netmera4j.constant.Platform;
import netmera4j.model.notification.BulkMessage;
import netmera4j.model.notification.Target;
import netmera4j.request.notification.SendBulkNotificationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;

import java.util.Collections;
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

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NetmeraApiNotificationTest netmeraApiNotificationTest = new NetmeraApiNotificationTest();
        CompletableFuture completableFuture = new CompletableFuture();
        netmeraApiNotificationTest.testSendBasicBulkNotification(completableFuture);
        completableFuture.get();
    }

    private void testSendBasicBulkNotification(CompletableFuture completableFuture) {
        netmeraApi.sendRequest(SendBulkNotificationRequest.builder()
                .bulkMessage(BulkMessage.builder() //
                        .title("Title") //
                        .text("Sample Text") //
                        .platforms(Collections.singletonList(Platform.ANDROID)) //
                        .build()) //
                .target(Target.builder() //
                        .sendToAll(true) //
                        .build()) //
                .build(), new NetmeraCallBack<Void>() {
            @Override
            protected void handleResponseCode(int httpStatus) {
                logger.info("------------------------------------");
                completableFuture.complete(true);
            }

            @Override
            protected void handleResponseData(Void data) {

            }

            @Override
            protected void handleError(Response<Void> response) {

            }

            @Override
            protected void handleException(Exception t) {

            }
        });
    }
}
