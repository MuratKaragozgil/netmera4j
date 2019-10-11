import netmera4j.Netmera;
import netmera4j.NetmeraApi;
import netmera4j.callback.NetmeraCallBack;
import netmera4j.request.event.FireEventsRequest;
import netmera4j.request.event.SingleEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Murat Karagözgil
 */
public class NetmeraApiEventTest {

    private Logger logger = LoggerFactory.getLogger(NetmeraApiDeviceTest.class);

    private Netmera netmeraApi = new NetmeraApi.NetmeraApiBuilder(TARGET_HOST, API_KEY).build();

    private static final String EXTERNAL_ID = "murat1";
    private static final String EVENT_NAME = "ShareEvent";
    private static final String API_KEY = "N79vhZlSKZD5upipyh7NdHIgPOIassVYRRAdPjga2sv00ORK4DHaBg";
    private static final String TARGET_HOST = "http://nova.sdpaas.com";

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NetmeraApiEventTest netmeraApiEventTest = new NetmeraApiEventTest();

        CompletableFuture completableFuture = new CompletableFuture();
        netmeraApiEventTest.testFireEvent(completableFuture);
        completableFuture.get();
    }

    private void testFireEvent(CompletableFuture completableFuture) {
        netmeraApi.sendRequest(FireEventsRequest.builder()
                .eventList(Arrays.asList(new SingleEvent.SingleEventBuilder(EXTERNAL_ID, EVENT_NAME) //
                        .addParameter("itemId", "1234") //
                        .addParameter("channel", "Facebook") //
                        .build(), new SingleEvent.SingleEventBuilder(EXTERNAL_ID, EVENT_NAME)
                        .addParameter("itemId", "12345") //
                        .addParameter("channel", "Instagram") //
                        .build())) //
                .build(), new NetmeraCallBack<Void>() {
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

            }
        });
    }
}
